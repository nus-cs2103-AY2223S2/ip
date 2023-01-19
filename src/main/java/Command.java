public enum Command {
	BYE			("bye", false),
	LIST		("list", false),
	MARK		("mark", true),
	UNMARK		("unmark", true),
	TODO		("todo", true),
	DEADLINE	("deadline", true),
	EVENT		("event", true),
	//
	NOMATCH		("", false);
	
	final String str;
	private final boolean hasText;
	
	Command(String str, boolean hasText) {
		this.str = str;
		this.hasText = hasText;
	}
	
	int getLen() {
		return this.str.length();
	}
	
	boolean match(String input) {
		int gap = this.hasText ? 2 : 0;		// to account for the whitespace
		
		if (input.length() < getLen() + gap) {
			return false;
		}
		
		return input.substring(0, getLen()).equals(this.str);
	}
	
	String getText(String input) {
		if (this.hasText) {
			return input.substring(getLen() + 1);
		}
		
		return "";
	}
}