package in.ac.iitb.qassist.util;

import gnu.trove.TIntObjectHashMap;
import gnu.trove.TIntObjectIterator;

import in.ac.iitb.qassist.util.exception.SearchException;

import java.util.List;

import com.aneedo.indexing.IndexingConstants;
import com.aneedo.search.SemClassStore;
import com.aneedo.search.SemanticIndexSearcher;
import com.aneedo.search.bean.SemEntityBean;
import com.aneedo.search.bean.SemInterpretation;

public class SemstoreUtil {

	public SemClassStore searchPageId(String id) throws SearchException {
		SemanticIndexSearcher searcher = SemanticIndexSearcher.getInstance();
		// SemClassStore semclassStore = searcher.getResults(rawQuery, null);
		SemClassStore semClassStore = new SemClassStore();
		try {
			searcher.storeEntitiesMatchingQuery(IndexingConstants.PAGE_ID, id,
					semClassStore);
		} catch (Exception e) {
			throw new SearchException(e);
		}

		return semClassStore;
	}

	public void displayInterpretations(SemClassStore store) {
		List<SemInterpretation> semInterpretations = store
				.getSemInterpretationList();

		InterpretationSplitter splitter = null;
		String[] entities = null;
		for (SemInterpretation interpretation : semInterpretations) {
			System.out.println(interpretation.getOverlapSemMembersDtls());
			splitter = new InterpretationSplitter(
					interpretation.getOverlapSemMembersDtls());
			entities = splitter.getInterpretations();
			for (String entity : entities)
				System.out.print(store.getSemEntity(Integer.parseInt(entity))
						.getTitle() + ", ");
			System.out.println("(" + interpretation.getInterpretation() + ")");
		}
	}

	public void displayAllNodes(SemClassStore semclassStore) {
		TIntObjectHashMap<SemEntityBean> semEntityBeanMap = semclassStore
				.getSemEntityBeanMap();

		TIntObjectIterator<SemEntityBean> iter = semEntityBeanMap.iterator();
		SemEntityBean semEntityBean = null;
		TIntObjectHashMap<List<String>> semClassMap = null;
		TIntObjectIterator<List<String>> semClassIter = null;
		while (iter.hasNext()) {
			iter.advance();
			semEntityBean = iter.value();
			System.out.println("-------------------------");
			System.out.println(semEntityBean.getTitle());
			System.out.println("-------------------------");
			semClassMap = semEntityBean.getWordSemClassMap();
			semClassIter = semClassMap.iterator();
			while (semClassIter.hasNext()) {
				semClassIter.advance();
				System.out.println();
				System.out.print(semClassIter.key() + ": ");
				for (String entity : semClassIter.value()) {
					System.out.print(entity + ", ");
				}
			}
		}
	}
}
