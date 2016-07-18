package quora.command;

public class AddCommandBuilder {
	private String type;
	private String id;
	private double score;
	private String dataString;
	
	public AddCommandBuilder setType(String typ) {
		type = typ;
		return this;
	}
	
	public AddCommandBuilder setId(String id) {
		this.id = id;
		return this;
	}
	
	public AddCommandBuilder setScore(Double scr) {
		score = scr;
		return this;
	}
	
	public AddCommandBuilder setDataString(String s) {
		dataString = s;
		return this;
	}
	
	public Command build() {
		return new AddCommand(type, id, score, dataString);
	}
}