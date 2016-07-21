package quora.command;

import quora.trie.Trie;

/**This class executes the commands in separate thread. This speeds up the processing
 * @author Prasad
 *
 */
public class AsyncCommandExecuter implements Runnable {
	private Command cmd;
	private Trie trie;
	
	public AsyncCommandExecuter(Command c,Trie t) {
		cmd = c;
		trie = t;
	}
	
	public void run() {
		cmd.execute(trie);
	}
}
