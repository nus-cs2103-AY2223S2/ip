import java.util.Scanner;

public class ToDo extends Task {
    private static final String TYPE = "[T]";

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isMarked) {
        super(name, isMarked);
    }


    public static ToDo createToDo(Scanner scanner) throws DukeyException {
        System.out.print("Todo task name: ");
        String toDoName = scanner.nextLine();
        if (toDoName.equals("")) {
            throw new DukeyException("Error!! ToDo name cannot be empty!");
        }
        return new ToDo(toDoName);
    }

    public static ToDo createToDoFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        boolean isMarked = !logStringArray[1].equals("0");
        return new ToDo(name, isMarked);
    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new todo:";
    }

    @Override
    public String getLogString() {
        return "T" + "," + isDoneStatus() + "," + this.getName();
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X]" + " " + this.getName();
        }

        return TYPE + "[ ]" + " " + this.getName();
    }
}

