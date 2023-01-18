public class Task {
	private String desc;
	private boolean isDone;
	
	Task(String desc) {
		this.desc = desc;
		this.isDone = false;
	}
	
	String getIcon() {
		if (this.isDone) {
			return "X";
		} else {
			return " ";
		}
	}
	
	@Override
	public String toString() {
		return String.format("[%s]  %s",
				getIcon(), this.desc);
	}
	
	boolean yesDo() {
		if (this.isDone) {
			return false;
		} else {
			this.isDone = true;
			return true;
		}
	}
	
	boolean noDo() {
		if (this.isDone) {
			this.isDone = false;
			return true;
		} else {
			return false;
		}
	}
}