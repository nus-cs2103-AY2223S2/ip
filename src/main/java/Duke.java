import java.io.IOException;
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
                Database db = new Database("data", "dukeTasks.txt");
                if(inputs.size() == 0) {
                    db.updateInputs(inputs);
                }
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
                    handleTaskOutput(db, inputs);
                    break;

                case "deadline":
                    checkTaskDesc(splitStr);
                    inputs.add(Deadline.createDeadline(splitStr[1]));
                    handleTaskOutput(db, inputs);
                    break;

                case "event":
                    checkTaskDesc(splitStr);
                    inputs.add(Event.createEvent(splitStr[1]));
                    handleTaskOutput(db, inputs);
                    break;

                case "delete":
                    Task taskToDelete = inputs.get(Integer.parseInt(splitStr[1]) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskToDelete);
                    inputs.remove(Integer.parseInt(splitStr[1]) - 1);
                    db.updateDatabase(inputs);
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
            } catch (IOException e) {
                e.printStackTrace();
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

    public static void handleTaskOutput(Database db, ArrayList<Task> inputs) throws IOException {
        System.out.println("Got it. I've added this task:");
        System.out.println(inputs.get(inputs.size() - 1));
        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
        db.appendToFile(inputs.get(inputs.size() - 1).toString());
    }
}
