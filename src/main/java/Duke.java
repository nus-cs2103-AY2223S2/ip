import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String projName = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    ArrayList<Task> taskList = new ArrayList<>();

    private void addTask(Task task, String name) {
        taskList.add(task);
        System.out.println("Item added: " + name);
    }

    public boolean readInput(String input) throws DukeException{
        String firstInput = input.split(" ")[0];

        switch(firstInput) {
            case "list":
                System.out.println("Here are the tasks you asked for!");
                for (int i = 0; i < taskList.size(); i += 1) {
                    int currItem = i + 1;
                    System.out.println(currItem + ": " + taskList.get(i));
                }
                System.out.println("You now have " + taskList.size() + " items in your list.");
                return true;

            case "bye":
                System.out.println("It was a pleasure to help, goodbye!");
                return false;

            case "mark":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Mark? Mark what?");
                }
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task selectedTask = taskList.get(taskIndex);
                    System.out.println("Done! I've marked this task as done :D");
                    selectedTask.check();
                    System.out.println(selectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }

            case "unmark":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Unmark? Unmark what?");
                }
                try {
                    int untaskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task unselectedTask = taskList.get(untaskIndex);
                    System.out.println("This task is apparently not done huh D:");
                    unselectedTask.unCheck();
                    System.out.println(unselectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }

            case "todo":
                try {
                    String todoTaskName = input.substring(5);
                    TodoTask todoTask = new TodoTask(todoTaskName);
                    addTask(todoTask, todoTaskName);
                    return true;
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Oops, you can't enter an empty task!");
                }

            case "deadline":
                String deadlineDetails = input.substring(9);
                if (deadlineDetails.split(" /by ").length < 2) {
                    throw new DukeException("Wait a minute, you're missing something! Could be the name or date...");
                }
                String deadlineName = deadlineDetails.split(" /by ")[0];
                String deadlineDate = deadlineDetails.split(" /by ")[1];
                DeadlineTask deadlineTask = new DeadlineTask(deadlineName, deadlineDate);
                addTask(deadlineTask, deadlineName);
                return true;

            case "event":
                String eventDetails = input.substring(6);
                if (eventDetails.split(" /from ").length < 2 || eventDetails.split(" /to ").length < 2) {
                    throw new DukeException("Hold up, you might be missing something here buddy!");
                }
                String eventName = eventDetails.split(" /from ")[0];
                String eventDate = eventDetails.split(" /from ")[1];
                String eventStart = eventDate.split(" /to ")[0];
                String eventEnd = eventDate.split(" /to ")[1];
                EventTask eventTask = new EventTask(eventName, eventStart, eventEnd);
                addTask(eventTask, eventName);
                return true;

            case "delete":
                if (input.split(" ").length < 2) {
                    throw new DukeException("Delete? Delete what?");
                }
                try {
                    int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task deleteTask = taskList.get(deleteIndex);
                    System.out.println("Done! " + deleteTask + " has been deleted for good.");
                    taskList.remove(deleteIndex);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Oops, that task number does not exist");
                }


            default:
                throw new DukeException("Oops I do not recognise this command...");
        }
    }

    public static void main(String[] args) throws DukeException{
        System.out.println("Yo! The name is\n" + projName);
        System.out.println("How might I help you today?");
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        boolean cont = true;

        while (cont) {
            String input = scanner.nextLine();
            try {
                cont = duke.readInput(input);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        scanner.close();
    }
}
