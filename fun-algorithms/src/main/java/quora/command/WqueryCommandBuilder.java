package quora.command;

import java.util.HashMap;
import java.util.Map;

public class WqueryCommandBuilder {
	private int numOfResult;
	private String dataString;
	private int numOfBoost;
	private Map<String,Double> typeBoost;
	private Map<String,Double> IdBoost;
	
	public WqueryCommandBuilder setNumberOfResult(int num) {
		numOfResult = num;
		return this;
	}

	public WqueryCommandBuilder setNumberOfBoost(int num) {
		numOfBoost= num;
		return this;
	}
	
	public WqueryCommandBuilder setDataString(String s) {
		dataString = s;
		return this;
	}
	
	public WqueryCommandBuilder setTypeBoost(String typeB) {
		if(typeB == null) {
			typeBoost = null;
			return this;
		}
		String[] tokens = typeB.split(":");
		int i = 0;
		typeBoost = new HashMap<String,Double>();
		
		while(i<tokens.length) {
			typeBoost.put(tokens[i],Double.parseDouble(tokens[i+1]));
			i+=2;
		}
		return this;
	}
	
	public WqueryCommandBuilder setIdBoost(String IdB) {
		if(IdB == null) {
			IdBoost = null;
			return this;
		}
		
		String[] tokens = IdB.split(":");
		int i = 0;
		IdBoost = new HashMap<String,Double>();
		
		while(i<tokens.length) {
			IdBoost.put(tokens[i],Double.parseDouble(tokens[i+1]));
			i+=2;
		}
		return this;
	}
	
	public Command build() {
		return new WqueryCommand(numOfResult, numOfBoost, typeBoost, IdBoost, dataString);
	}
}