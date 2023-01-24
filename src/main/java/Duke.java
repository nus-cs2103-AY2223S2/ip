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

    public boolean readInput(String input) {
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
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task selectedTask = taskList.get(taskIndex);
                    System.out.println("Done! I've marked this task as done :D");
                    selectedTask.check();
                    System.out.println(selectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops, that task number does not exist");
                    break;
                }

            case "unmark":
                try {
                    int untaskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task unselectedTask = taskList.get(untaskIndex);
                    System.out.println("This task is apparently not done huh D:");
                    unselectedTask.unCheck();
                    System.out.println(unselectedTask);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops, that task number does not exist");
                    break;
                }

            case "todo":
                String todoTaskName = input.substring(5);
                TodoTask todoTask = new TodoTask(todoTaskName);
                addTask(todoTask, todoTaskName);
                return true;


            case "deadline":
                String deadlineDetails = input.substring(9);
                String deadlineName = deadlineDetails.split(" /by ")[0];
                String deadlineDate = deadlineDetails.split(" /by ")[1];
                DeadlineTask deadlineTask = new DeadlineTask(deadlineName, deadlineDate);
                addTask(deadlineTask, deadlineName);
                return true;

            case "event":
                String eventDetails = input.substring(6);
                String eventName = eventDetails.split(" /from ")[0];
                String eventDate = eventDetails.split(" /from ")[1];
                String eventStart = eventDate.split(" /to ")[0];
                String eventEnd = eventDate.split(" /to ")[1];
                EventTask eventTask = new EventTask(eventName, eventStart, eventEnd);
                addTask(eventTask, eventName);
                return true;

            default:
                System.out.println("Oops I do not recognise this command...");
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Yo! The name is\n" + projName);
        System.out.println("How might I help you today?");
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        boolean cont = true;

        while (cont) {
            String input = scanner.nextLine();
            cont = duke.readInput(input);
        }
        scanner.close();
    }
}
