
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        //Initialisation of variables
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");


        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ", 2);

            // Handles the case when no command entered so user does enter
            //if (tokens[0].equals("")) {
            //System.out.println("No command has been entered. Please enter a valid command!");
            //}

            //Exit program once user says bye (DONE)
            if (tokens[0].equals("bye")) {
                System.out.print("Bye. Hope to see you again soon!");
                break;
            }

            // List all tasks (DONE)
            if (tokens[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                printList(taskList);
                continue;
            }

            // Unmark a task as not done yet
            if (tokens[0].equals("unmark")) {
                unmarkTask(tokens, taskList);
                continue;
            }

            // Mark a task as done
            if (tokens[0].equals("mark")) {
                markTask(tokens, taskList);
                continue;
            }

            switch (tokens[0]) {
                case "todo": {
                    if (tokens.length < 2) {
                        System.out.println("The description of a todo cannot be empty!");
                    } else {
                        String desc = tokens[1];
                        Task created = new Todo(desc);
                        taskList.add(created);
                        System.out.println("Got it. I've added this task:\n" + created);
                        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    }
                    break;
                }
                case "deadline": {
                    if (tokens.length < 2) {
                        System.out.println("The description of a deadline cannot be empty!");
                        break;
                    }
                    String[] separated = tokens[1].split("/by ");
                    if (separated.length < 2) {
                        System.out.println("Deadline needs to include a specific end date or time!");
                        break;
                    }
                    Task created = new Deadline(separated[0], separated[1]);
                    taskList.add(created);
                    System.out.println("Got it. I've added this task:\n" + created);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    break;
                }
                case "event": {
                    if (tokens.length < 2) {
                        System.out.println("The description of a event cannot be empty!");
                        break;
                    }
                    String[] separated = tokens[1].split("/from |/to ");
                    if (separated.length < 3) {
                        System.out.println("Event needs to include a start date/time and a end date/time!");
                        break;
                    }
                    Task created = new Event(separated[0], separated[1], separated[2]);
                    taskList.add(created);
                    System.out.println("Got it. I've added this task:\n" + created);
                    System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
                    break;
                }
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void printList(ArrayList<Task> tasks) {
        int idx = 1;
        for (Task task : tasks) {
            System.out.printf("%d.%s%n", idx, task);
            idx++;
        }
    }

    private static void markTask(String[] inputTokens, ArrayList<Task> tasks) {
        try {
            int chosenId = Integer.parseInt(inputTokens[1]);
            Task chosenTask = tasks.get(chosenId - 1);
            chosenTask.mark();
            System.out.println("Nice! I've marked this task as done:\n" + chosenTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Given task number is invalid");
        }
    }

    private static void unmarkTask(String[] inputTokens, ArrayList<Task> tasks) {
        try {
            int chosenId = Integer.parseInt(inputTokens[1]);
            Task chosenTask = tasks.get(chosenId - 1);
            chosenTask.unmark();
            System.out.println("OK, I've marked this task as not done yet:\n" + chosenTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Given task number is invalid");
        }
    }

}