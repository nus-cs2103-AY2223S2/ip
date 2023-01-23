import exception.InvalidCommandException;
import exception.MissingTaskDescriptionException;
import exception.TaskFactoryException;
import exception.TreeBotException;

public class TaskFactory {
    public Task make(String instruction) throws TaskFactoryException {
        String[] splitStr = instruction.split("\\s+", 2);
        String taskType = splitStr[0];

        try {
            switch (taskType) {
            case "todo":
                return makeTodo(instruction);
            case "deadline":
                return makeDeadline(instruction);
            case "event":
                return makeEvent(instruction);
            default:

            }
        } catch (MissingTaskDescriptionException e) {
            throw e;
        }
        return null;
    }

    private Task makeTodo(String instruction) throws MissingTaskDescriptionException {
        String[] splitStr = instruction.split("\\s+", 2);

        if (splitStr.length < 2) {
            throw new MissingTaskDescriptionException("Task Description cannot be empty!");
        }

        String taskDescription = splitStr[1];
        return new Todo(taskDescription);
    }

    private Task makeDeadline(String instruction){
        String[] splitStr = instruction.split("\\s+", 2);
        String taskDescription = splitStr[1].split(" /by ")[0];
        String taskDeadline =splitStr[1].split(" /by ")[1];

        return new Deadline(taskDescription, taskDeadline);
    }

    private Task makeEvent(String instruction) {
        String[] splitStr = instruction.split("\\s+", 2);
        String taskDescription = splitStr[1].split(" /from ")[0];
        String taskStart =splitStr[1].split(" /from ")[1].split(" /to ")[0];
        String taskEnd =splitStr[1].split(" /from ")[1].split(" /to ")[1];

        return new Event(taskDescription, taskStart, taskEnd);


    }




}
