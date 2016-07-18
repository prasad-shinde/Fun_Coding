package quora.parser;

import quora.command.AddCommandBuilder;
import quora.command.Command;
import quora.command.DeleteCommandBuilder;
import quora.command.QueryCommandBuilder;
import quora.command.WqueryCommandBuilder;

/**Parses the input line and returns the appropriate command objects from it.
 * @author Prasad
 *
 */
public class CommandParser {
	public static Command parse(String line) {
		String[] tokens = line.split(" ",2);
		if(tokens[0].equalsIgnoreCase("add")) {
			AddCommandBuilder builder = new AddCommandBuilder();
			tokens = tokens[1].split(" ", 4);
			builder.setType(tokens[0]);
			builder.setId(tokens[1]);
			builder.setScore(Double.parseDouble(tokens[2]));
			builder.setDataString(tokens[3]);
			return builder.build();
		} else if(tokens[0].equalsIgnoreCase("query")) {
			tokens = tokens[1].split(" ", 2);
			QueryCommandBuilder builder = new QueryCommandBuilder();
			builder.setNumberOfResult(Integer.parseInt(tokens[0]));
			builder.setDataString(tokens[1]);
			return builder.build();
		} else if(tokens[0].equalsIgnoreCase("del")) {
			DeleteCommandBuilder builder = new DeleteCommandBuilder();
			builder.setId(tokens[1]);
			return builder.build();
		} else if(tokens[0].equalsIgnoreCase("wquery")) {
			return getWqueryCommand(tokens[1]);
		} else
			return  null;
	}
	
	private static Command getWqueryCommand(String command) {
		WqueryCommandBuilder builder = new WqueryCommandBuilder();
		String[] tokens = command.split(" ");
		builder.setNumberOfResult(Integer.parseInt(tokens[0]));
		builder.setNumberOfBoost(Integer.parseInt(tokens[1]));
		int i = 2;
		if(tokens[i].contains(":"))
			builder.setTypeBoost(tokens[i++]);
		else
			builder.setTypeBoost(null);
		if(tokens[i].contains(":"))
			builder.setIdBoost(tokens[i++]);
		else
			builder.setIdBoost(null);
		
		String rest = "";
		for(;i<tokens.length;i++){
			rest = rest + tokens[i] + " ";
		}
		
		builder.setDataString(rest);
		return builder.build();
	}
}
