import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public Parser() {}

    public void parseInputs(String[] inputs, ArrayList<Task> tsk, Database db) throws DukeException, IOException {
        String type = inputs[0];

        switch (type) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tsk.size(); i++) {
                    System.out.println((i + 1) + "." + tsk.get(i));
                }
                break;

            case "mark":
                mark(true, inputs[1], tsk);
                break;

            case "unmark":
                mark(false, inputs[1], tsk);
                break;

            case "todo":
                checkTaskDesc(inputs);
                tsk.add(new Todo(inputs[1]));
                handleTaskOutput(db, tsk);
                break;

            case "deadline":
                checkTaskDesc(inputs);
                tsk.add(Deadline.createDeadline(inputs[1]));
                handleTaskOutput(db, tsk);
                break;

            case "event":
                checkTaskDesc(inputs);
                tsk.add(Event.createEvent(inputs[1]));
                handleTaskOutput(db, tsk);
                break;

            case "delete":
                Task taskToDelete = tsk.get(Integer.parseInt(inputs[1]) - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(taskToDelete);
                tsk.remove(Integer.parseInt(inputs[1]) - 1);
                db.updateDatabase(tsk);
                System.out.println("Now you have " + tsk.size() + " tasks in the list.");
                break;

            default:
                throw new InvalidTaskException();
        }
    }

    public static void mark(boolean isDone, String taskId, ArrayList<Task> inputs) {
        int taskNo = Integer.parseInt(taskId);
        Task taskToMark = inputs.get(taskNo - 1);
        taskToMark.setIsDone(isDone);

        if(isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskToMark);
    }

    public static void checkTaskDesc(String[] splitStr) throws EmptyTaskException {
        if(splitStr.length == 1) {
            throw new EmptyTaskException(splitStr[0]);
        }
    }

    public static void handleTaskOutput(Database db, ArrayList<Task> inputs) throws IOException {
        System.out.println("Got it. I've added this task:");
        System.out.println(inputs.get(inputs.size() - 1));
        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
        db.appendToFile(inputs.get(inputs.size() - 1).toString());
    }
}
