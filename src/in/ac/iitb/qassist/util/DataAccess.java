package in.ac.iitb.qassist.util;

import in.ac.iitb.qassist.data.WikiCategory;
import in.ac.iitb.qassist.util.exception.DataAccessException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

	private static Connection conn = null;

	private void loadDriver() throws DataAccessException {
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			throw new DataAccessException(ex);
		}
	}

	private Connection getConnection() throws DataAccessException {
		System.out.println("Getting connection");
		try {
			if (null == conn)
				conn = DriverManager
						.getConnection("jdbc:mysql://10.129.1.91:3306/wikispot?"
								+ "user=root&password=aneedo");
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		System.out.println("Received connection");
		return conn;
	}

	public void getSubcategories(WikiCategory pCat) throws DataAccessException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<WikiCategory> categories = new ArrayList<WikiCategory>();
		try {
			String outlinksQ = QueryLoader.getInstance().getOutlinksQuery();
			System.out.println("Query: " + outlinksQ);
			stmt = getConnection().prepareStatement(outlinksQ);
			stmt.setInt(1, pCat.getId());
			rs = stmt.executeQuery();
			WikiCategory cat = null;
			while (null != rs && rs.next()) {
				cat = new WikiCategory();
				cat.setId(rs.getInt(1));
				cat.setName(rs.getString(2));
				categories.add(cat);
			}
		} catch (IOException e) {
			throw new DataAccessException(e);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			close(rs);
			close(stmt);
		}
		pCat.setSubcategories(categories);
	}

	private void close(Statement stmt) throws DataAccessException {
		if (null != stmt)
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}

	}

	private void close(ResultSet rs) throws DataAccessException {
		if (null != rs)
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}

	}

	public void getPages(WikiCategory pCat) throws DataAccessException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<WikiCategory> categories = new ArrayList<WikiCategory>();
		try {
			String pagesQ = QueryLoader.getInstance().getPagesQuery();
			System.out.println(pagesQ);
			stmt = getConnection().prepareStatement(pagesQ);
			stmt.setInt(1, pCat.getId());
			rs = stmt.executeQuery();
			WikiCategory cat = null;
			while (null != rs && rs.next()) {
				cat = new WikiCategory();
				cat.setId(rs.getInt(1));
				cat.setName(rs.getString(2));
				categories.add(cat);
			}
		} catch (IOException e) {
			throw new DataAccessException(e);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			close(rs);
			close(stmt);
		}
		pCat.setSubcategories(categories);
	}

}
