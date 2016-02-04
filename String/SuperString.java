import java.lang.StringBuilder;
import java.util.TreeSet;

public class SuperString {
    private String string;
    
    SuperString(String string) {
	this.string = string; 
    }
    
    public void reverse() {
	StringBuilder sb = new StringBuilder("");
	for (int i = string.length()-1; i > -1; --i) {
	    sb.append(string.charAt(i));
	}	
	string = sb.toString();
    }

    public void add(String toAdd) {
	StringBuilder sb = new StringBuilder(string);
	string = sb.append(toAdd).toString();
    }
    
    public boolean isPalindrome() {
	boolean isPalindrome = true;
	for (int i = 0; i < string.length()/2; ++i) {
	    if (string.charAt(i) != string.charAt(string.length()-1-i)) {
		isPalindrome = false;
		break;
	    }		
	}
	return isPalindrome;
    }

    public void print() {
	System.out.println(string);
    }
    
    public String uppercase() {
	final int TO_UPPER = 32, LOWER_BOUND = 65, UPPER_BOUND = 90;
	StringBuilder sb = new StringBuilder("");
	for (int i = 0; i < string.length(); ++i) {
	    if (!Character.isLetter(string.charAt(i))) {
		sb.append(string.charAt(i));
		continue;
	    }

	    int newChar = string.charAt(i) - TO_UPPER;
	    if (newChar < LOWER_BOUND || newChar > UPPER_BOUND) {
		sb.append(string.charAt(i));
	    } else {
		sb.append((char) newChar);
	    }
	}
	string = sb.toString();
	return string;
    }

    public String lowercase() {
	final int TO_LOWER = 32, LOWER_BOUND = 97, UPPER_BOUND = 122;;
	StringBuilder sb = new StringBuilder("");
	for (int i = 0; i < string.length(); ++i) {	    
	    if (!Character.isLetter(string.charAt(i))) {
		sb.append(string.charAt(i));
		continue;
	    }

	    int newChar = string.charAt(i) + TO_LOWER;
	    if (newChar < LOWER_BOUND || newChar > UPPER_BOUND) {
		sb.append(string.charAt(i));
	    } else {
		sb.append((char) newChar);
	    }
	}
	string = sb.toString();
	return string;
    }

    public void clear() {
	string = "";
    }

    public String trimDigits() {
	string = string.replaceAll("[0-9]+", "");
	return string;
    }

    public String trimCharacters() {
	string = string.replaceAll("[^\\w]","");
	return string;
    }

    public String trimSpace() {
	string = string.replaceAll("\\s+","");
	return string;
    }
    
    public String trimLetters() {
	string = string.replaceAll("[a-zA-Z]+", "");
	return string;
    }

    public String removeDuplicates() {
	StringBuilder sb = new StringBuilder("");
	for (int i = 0; i < string.length(); ++i) {
	    if (sb.indexOf(String.valueOf(string.charAt(i))) == -1)
		sb.append(string.charAt(i));
	}
	
	string = sb.toString();
	return string;
    }
}
