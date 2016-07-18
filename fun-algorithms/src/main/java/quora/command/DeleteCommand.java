package quora.command;

import quora.trie.Trie;

/**The delete command
 * @author Prasad
 *
 */
public class DeleteCommand implements Command {
	String uId;
	
	public DeleteCommand(String id) {
		uId = id;
	}
	
	@Override
	public Object execute(Trie t) {
		t.delete(uId);
		return null;
	}
}
