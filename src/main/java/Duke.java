import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> inputs = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        String currInput = scanner.nextLine();
        System.out.println("____________________________________________________________");

        String[] splitStr = currInput.split(" ", 2);

        while(!currInput.equals("bye")) {
            try {
                String type = splitStr[0];

                switch (type) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println((i + 1) + "." + inputs.get(i));
                    }
                    break;

                case "mark":
                    mark(true, splitStr[1], inputs);
                    break;

                case "unmark":
                    mark(false, splitStr[1], inputs);
                    break;

                case "todo":
                    checkTaskDesc(splitStr);
                    inputs.add(new Todo(splitStr[1]));
                    printTaskOutput(inputs);
                    break;

                case "deadline":
                    checkTaskDesc(splitStr);
                    String[] deadlineArr = splitStr[1].split("/", 2);
                    String deadlineDesc = deadlineArr[0].trim();
                    String by = deadlineArr[1].substring(3);
                    inputs.add(new Deadline(deadlineDesc, by));
                    printTaskOutput(inputs);
                    break;

                case "event":
                    checkTaskDesc(splitStr);
                    String[] eventArr = splitStr[1].split("/", 3);
                    String eventDesc = eventArr[0].trim();
                    String from = eventArr[1].substring(5).trim();
                    String to = eventArr[2].substring(3);
                    inputs.add(new Event(eventDesc, from, to));
                    printTaskOutput(inputs);
                    break;

                case "delete":
                    Task taskToDelete = inputs.get(Integer.parseInt(splitStr[1]) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskToDelete);
                    inputs.remove(Integer.parseInt(splitStr[1]) - 1);
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    break;

                default:
                    throw new InvalidTaskException();
                }
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Mark commands need to be followed by an integer!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(String.format("Sorry but there are only %d tasks stored!", inputs.size()));
            } finally {
                System.out.println("____________________________________________________________\n");
                currInput = scanner.nextLine();
                splitStr = currInput.split(" ", 2);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
        scanner.close();
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

    public static void printTaskOutput(ArrayList<Task> inputs) {
        System.out.println("Got it. I've added this task:");
        System.out.println(inputs.get(inputs.size() - 1));
        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
    }
}
