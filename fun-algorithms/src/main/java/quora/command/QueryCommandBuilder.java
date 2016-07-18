package quora.command;

public class QueryCommandBuilder {
	private int numOfResult;
	private String dataString;
	
	public QueryCommandBuilder setNumberOfResult(int num) {
		numOfResult = num;
		return this;
	}
	
	public QueryCommandBuilder setDataString(String s) {
		dataString = s;
		return this;
	}
	
	public Command build() {
		return new QueryCommand(numOfResult, dataString);
	}
}