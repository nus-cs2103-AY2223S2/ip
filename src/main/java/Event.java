public class Event extends Task {
	protected String from;
	protected String to;

	public Event(String description, String[] fromTo) {
		super(description);
		this.from = fromTo[0];
		this.to = fromTo[1];
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() +
				String.format(" (from: %s to: %s)", from, to);
	}
}
