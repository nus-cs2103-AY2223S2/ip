public class Deadline extends Task {

	protected String by;
	protected String tag;
	public Deadline(String description, String by) {
		super(description);
		this.by = by;
		this.tag = "[D]";
	}

	public Deadline(String description, String by, boolean isMark) {
		super(description);
		this.by = by;
		this.tag = "[D]";
		super.markTask(isMark);
	}
	public String getTag() {
		return this.tag;
	}
	
	public String getDate() {
		return this.by;
	}


	@Override
	public String toString() {
		return this.tag + super.toString() + " (by: " + by + ")";
    	}
}
