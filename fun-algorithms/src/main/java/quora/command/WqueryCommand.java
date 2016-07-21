package quora.command;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import quora.trie.Trie;

/**The wquery command
 * @author Prasad
 *
 */
public class WqueryCommand implements Command {
	
	private int numOfResults;
	private String dataString;
	private int numOfBoost;
	private Map<String,Double> typeBoost;
	private Map<String,Double> IdBoost;
	
	public WqueryCommand(int num,int numBoost,Map<String,Double> typeBoost,Map<String,Double> IdBoost,String searchText) {
		numOfResults = num;
		numOfBoost = numBoost;
		this.typeBoost = typeBoost;
		this.IdBoost = IdBoost;
		dataString = searchText;
	}
	
	public Object execute(Trie t) {
		List<String> result = t.wquery(numOfResults, numOfBoost, typeBoost, IdBoost, dataString);
		if(result == null) {
			System.out.println();
			return null;
		}
		synchronized(this) {	
			Iterator<String> it = result.iterator();
			while(it.hasNext()) {
				System.out.print(it.next()+" ");
			}
			System.out.println();
		}
		return result;
	}
}
