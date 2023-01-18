import java.util.ArrayList;

public class ToDo {
	private static final int MAX_SIZE = 100;
	//private static final int GAPS = 11;
	private static final String GAP = "             ";
	private ArrayList<Task> tdl;
	
	ToDo() {
		this.tdl = new ArrayList<Task>();
	}
	
	int getCount() {
		return this.tdl.size();
	}
	
	String addTask(String str) {
		this.tdl.add(new Task(str));
		return "Task \"" + str + "\" added!";
	}
	
	void printTasks() {
		for (int i = 0; i < getCount(); i++) {
			System.out.println(String.format("%11d. %s",	// GAPS here too
					i + 1, this.tdl.get(i)));
		}
	}
	
	boolean notInRange(int num) {
		return (num <= 0 || num > getCount());
	}
	
	String rangeError(int num) {
		if (num <= 0) {
			return "wadahek pls";
		} else if (num > getCount()) {
			return "Hm, you don't have that many tasks!";
		} else {
			return "All's good! That index is in range :D";
		}
	}
	
	String mark(int num) {
		if (notInRange(num)) {
			return rangeError(num);
		}
		
		int index = num - 1;
		
		if (tdl.get(index).yesDo()) {
			return "Yay! You've completed:\n" + GAP
					+ tdl.get(index).toString();
		} else {
			return "You've already done:\n" + GAP
					+ tdl.get(index).toString();
		}
	}
	
	String unmark(int num) {
		if (notInRange(num)) {
			return rangeError(num);
		}
		
		int index = num - 1;
		
		if (tdl.get(index).noDo()) {
			return "Aw :( I've unmarked:\n" + GAP
					+ tdl.get(index).toString();
		} else {
			return "Hm, you haven't yet done:\n" + GAP
					+ tdl.get(index).toString();
		}
	}
}