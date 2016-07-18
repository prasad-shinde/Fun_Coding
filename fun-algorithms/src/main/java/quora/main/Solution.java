package quora.main;

import java.util.Scanner;

import quora.command.Command;
import quora.command.CommandProcessor;
import quora.parser.CommandParser;
import quora.trie.Trie;

/**The main class which starts reading and executing queries
 * @author Prasad
 *
 */
public class Solution {
	private CommandProcessor processor;
	Trie t;
	
	public Solution() {
		t = new Trie();
		processor = new CommandProcessor(t);
		new Thread(processor).start();
	}
	
	public void process() {
		Scanner sc = new Scanner(System.in);
        int numOfCommands = Integer.parseInt(sc.nextLine()); 
        
        for(int i = 0; i < numOfCommands; i++) {
            String command = sc.nextLine();
            Command cmd = CommandParser.parse(command);
            processor.addCommand(cmd);
        }
        
        sc.close();
	}
	
	
	
	public static void main(String[] args) {
		Solution typeAhead = new Solution();
		typeAhead.process();
	}
}
