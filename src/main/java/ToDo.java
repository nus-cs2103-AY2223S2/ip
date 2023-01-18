import java.util.Scanner;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static ToDo parseToDoCommand(Scanner stringStream) throws DukeException {
        String taskDesc = "";

        while (stringStream.hasNext()) {
            String temp = stringStream.nextLine();
            taskDesc += temp;
        }

        if (taskDesc.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a ToDo cannot be empty.");
        }

        ToDo newTask = new ToDo(taskDesc.trim());
        return newTask;
    }
}
