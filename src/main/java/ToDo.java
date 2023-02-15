import java.util.Scanner;

public class ToDo extends Item {
    private static final String TYPE = "[T]";
    public ToDo(String name) {
        super(name);
    }


    public static ToDo createToDo(Scanner scanner) throws DukeyException {
        System.out.print("Todo task name: ");
        String toDoName = scanner.nextLine();
        if (toDoName.equals("")) {
            throw new DukeyException("Error!! ToDo name cannot be empty!");
        }
        return new ToDo(toDoName);
    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new todo:";
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X]" + " " + this.getName();
        }

        return TYPE + "[ ]" + " " + this.getName();
    }
}

