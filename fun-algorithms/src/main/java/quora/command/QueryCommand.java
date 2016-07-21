package quora.command;

import java.util.Iterator;
import java.util.List;

import quora.trie.Trie;

/**The Query command
 * @author Prasad
 *
 */
public class QueryCommand implements Command {
	// Format : QUERY <number of results> <query string that can contain spaces>
	private int numOfResults;
	private String dataString;
	
	public QueryCommand(int num,String searchString) {
		numOfResults = num;
		dataString = searchString;
	}
	
	public Object execute(Trie t) {
		List<String> result = t.query(numOfResults, dataString);
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
