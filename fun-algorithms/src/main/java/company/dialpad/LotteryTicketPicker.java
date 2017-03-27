package company.dialpad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link LotteryTicketPicker} helps pick possbile lottery ticket numbers 
 * 
 * @author prasad
 *
 */
public class LotteryTicketPicker {
	public List<String> lottoPicks(String[] possiblePicks) {
		List<String> lottoPicks = new ArrayList<String>();
		for(String ticket:possiblePicks) {
			if(isPossibleTicket(ticket))
				lottoPicks.add(ticket);
		}
		return lottoPicks;
	}
	
	private boolean isPossibleTicket(String ticket) {
		List<LotteryRule> rules = new ArrayList<LotteryRule>();
		rules.add(new LengthRangeRule(7, 14));
		rules.add(new UniqueInorderRangeRule(1, 59, 7));
		for(LotteryRule rule:rules) {
			if(!rule.isValid(ticket))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// You can also pass the command line arguments to the method
		String[] numbers = {"1", "42", "4938532894754", "1234567", "472844278465445"};
		if(args.length > 0)
			numbers = args;
		LotteryTicketPicker ticketPicker = new LotteryTicketPicker();
		for(String pick:ticketPicker.lottoPicks(numbers)) {
			System.out.println(pick);
		}
	}
}


/**
 * Interface {@link LotteryRule} defines the interface for the lottery rules
 * 
 * @author prasad
 *
 */
interface LotteryRule {
	public boolean isValid(String lotteryNumber);
}

/**
 * {@link LengthRangeRule} defines the range rule on the ticket number
 * 
 * @author prasad
 *
 */
class LengthRangeRule implements LotteryRule {
	private int minSize,maxSize;
	
	public LengthRangeRule(int minSize,int maxSize) {
		this.maxSize = maxSize;
		this.minSize = minSize;
	}
	
	public boolean isValid(String lotteryNumber) {
		return lotteryNumber.length() >= minSize && lotteryNumber.length() <= maxSize;
	}
}

/**
 * {@link UniqueInorderRangeRule} defines 3 rules :
 * 1. number of unique numbers in lottery
 * 2. Range of the unique numbers
 * 3. The unique numbers should be inorder and all the numbers in the ticket should be used
 * 
 * @author prasad
 */
class UniqueInorderRangeRule implements LotteryRule {
	private int numberOfUniqueNumbers;
	private int minValue,maxValue; 
	
	public UniqueInorderRangeRule(int minValue,int maxValue,int n) {
		this.numberOfUniqueNumbers = n;
		this.maxValue = maxValue;
		this.minValue = minValue;
	}
	
	public boolean isValid(String lotteryNumber) {
		Set<Integer> uniqueNumbers = new HashSet<Integer>();
		return isValid(lotteryNumber,numberOfUniqueNumbers,uniqueNumbers);
	}
	
	private boolean isValid(String lotteryNumber,int numberOfNumbersInLottery,Set<Integer> uniqueNumbers) {
		if(numberOfNumbersInLottery == 0)
			return lotteryNumber.length() == 0;
		
		for(int i = 1;i<=String.valueOf(maxValue).length();i++) {
			int number = Integer.parseInt(lotteryNumber.substring(0, i));
			String remaningNumbers = lotteryNumber.substring(i);
			if(!uniqueNumbers.contains(number)) {
				uniqueNumbers.add(number);
				if(isValidNumber(number) && isValid(remaningNumbers,numberOfNumbersInLottery-1,uniqueNumbers)) {
					return true;
				}
				uniqueNumbers.remove(number);
			}
		}
		return false;
	}
	
	private boolean isValidNumber(int num) {
		return num >= minValue && num <= maxValue;
	}
}