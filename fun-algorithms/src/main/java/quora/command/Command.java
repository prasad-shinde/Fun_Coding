package quora.command;

import quora.trie.Trie;

/**Command interface implemented by all commands.
 * @author Prasad
 *
 */
public interface Command {
	public Object execute(Trie t);
}
