public class Event extends Task {
	
	protected String tag;
	protected String from;
	protected String to;
	public Event(String description, String from, String to) {
		super(description);
		this.from = from;
		this.to = to;
		this.tag = "[E]";
	}

	public Event(String description, String from, String to, boolean isMark) {
		super(description);
		this.from = from;
		this.to = to;
		this.tag = "[E]";
		if (isMark) {
			super.markTask(isMark);
		}
	}
	public String getDate() {
		return this.from + "|" + this.to;
	}

	public String getTag() {
		return this.tag;
	}

	@Override
	public String toString() {
		return this.tag + super.toString() + " (from: " + from + " to: " + to + ")";
    	}
}
