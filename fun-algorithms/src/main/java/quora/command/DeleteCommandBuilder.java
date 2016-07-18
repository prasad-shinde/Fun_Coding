package quora.command;

public class DeleteCommandBuilder {
	private String id;
	
	public DeleteCommandBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public Command build() {
		return new DeleteCommand(id);
	}
}