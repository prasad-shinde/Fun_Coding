package quora.command;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import quora.trie.Trie;

/**
 * The Command design pattern is used to create and execute commands.
 * This class maintains a list of command in a Blocking Queue and
 * executes them in parallel.
 * @author Prasad
 *
 */
public class CommandProcessor implements Runnable {
	private BlockingQueue<Command> queue;
	private Trie trie;
	
	public CommandProcessor(Trie t) {
		trie = t;
		queue = new ArrayBlockingQueue<Command>(2048);
	}
	
	public void addCommand(Command cmd) {
		queue.offer(cmd);
	}

	public void run() {
		while(true) {
			try {
				Command cmd = queue.take();
				if(cmd!=null) {
					AsyncCommandExecuter executer = new AsyncCommandExecuter(cmd, trie);
					new Thread(executer).start();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
