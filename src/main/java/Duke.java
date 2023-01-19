import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm DonkeyChat!\nWhat can I do for you?");
        boolean isRunning = true;
        while (isRunning) {
            String currInput = input.nextLine();
            String[] splitInput = currInput.split(" ", 2);
            String currCommand = splitInput[0];
            Integer taskIndex;
            switch (currCommand) {
                case "bye":
                    System.out.println("Adios!");
                    isRunning = false;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        Task task = taskList.get(i);
                        System.out.println(i + 1 + "." + task);
                    }
                    break;
                case "mark":
                    try {
                        taskIndex = Integer.valueOf(splitInput[1]) - 1;
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        break;
                    }
                    if (taskIndex < 0 || taskIndex > taskList.size()) {
                        System.out.println("invalid task index!");
                        break;
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    Task task = taskList.get(taskIndex);
                    task.setDone(true);
                    System.out.println(task);
                    break;
                case "unmark":
                    try {
                        taskIndex = Integer.valueOf(splitInput[1]) - 1;
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        break;
                    }
                    if (taskIndex < 0 || taskIndex > taskList.size()) {
                        System.out.println("invalid task index!");
                        break;
                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                    task = taskList.get(taskIndex);
                    task.setDone(false);
                    System.out.println(task);
                    break;
                case "todo":
                    taskList.add(new ToDo(splitInput[1]));
                    System.out.println("Added Todo task:\n" + taskList.get(taskList.size() - 1));
                    System.out.println("Now you have " + taskList.size() + " tasks in the list!");
                    break;
                case "deadline":
                    Integer indexBy = splitInput[1].indexOf("/by ");
                    taskList.add(
                        new Deadline(splitInput[1].substring(0, indexBy - 1), splitInput[1].substring(indexBy + 4)));
                    System.out.println("Added Deadline task:\n" + taskList.get(taskList.size() - 1));
                    System.out.println("Now you have " + taskList.size() + " tasks in the list!");
                    break;
                case "event":
                    Integer indexFrom = splitInput[1].indexOf("/from ");
                    Integer indexTo = splitInput[1].indexOf("/to ");
                    taskList.add(new Event(
                        splitInput[1].substring(0, indexFrom - 1),
                        splitInput[1].substring(indexFrom + 6, indexTo - 1),
                        splitInput[1].substring(indexTo + 4)));
                    System.out.println("Added Event task:\n" + taskList.get(taskList.size() - 1));
                    System.out.println("Now you have " + taskList.size() + " tasks in the list!");
                    break;
                default:
                    System.out.println("Please input a valid command");
                    break;
            }
        }
    }
}
