package quora.command;

import quora.trie.Trie;

/**
 * Represents the add command.
 * @author Prasad
 *
 */
public class AddCommand implements Command {
	
	// Format : ADD <type> <id> <score> <data string that can contain spaces>
	private String type;
	private String id;
	private double score;
	private String dataString;
	
	public AddCommand(String typ,String uniqueId,double scr,String ds) {
		type = typ;
		id = uniqueId;
		score = scr;
		dataString = ds;
	}
	
	@Override
	public Object execute(Trie t) {
		t.add(dataString,type, score, id);
		return null;
	}
}


