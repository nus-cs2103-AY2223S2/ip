import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        Scanner reader = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<Task>();

        while (true) {
            try {
                String input = reader.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("________________________________");
                    break;
                }
                run(input, toDoList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("________________________________");
            }
        }

        reader.close();

    }

    public static boolean checkDescription(String[] splitInput) {

        return splitInput.length < 2;
    }

    public static void run(String input, ArrayList<Task> toDoList) throws DukeException {

        String[] splitInput = input.split(" ");
        String command = splitInput[0];

        System.out.println("________________________________");

        switch (command) {

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < toDoList.size(); i++) {

                    System.out.println(i + 1 + "." + toDoList.get(i).toString());
                }
                break;

            case "mark":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The value cannot be empty.");
                }

                String taskNumMark = splitInput[1];
                Task taskToMark;
                try {
                    taskToMark = toDoList.get(Integer.parseInt(taskNumMark) - 1);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + taskToMark.mark());

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }

                break;
            case "unmark":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The value cannot be empty.");
                }

                
                try {
                    String taskNumUnmark = splitInput[1];

                    Task taskToUnmark = toDoList.get(Integer.parseInt(taskNumUnmark) - 1);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + taskToUnmark.unMark());

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }

                break;

            case "event":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }

                String eventDescription = input.substring(("event").length() + 1);
                Task newEvent = new Event(eventDescription);
                toDoList.add(newEvent);
                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newEvent.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                break;

            case "deadline":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String deadlineDescription = input.substring(("deadline").length() + 1);
                Task newDeadline = new Deadline(deadlineDescription);
                toDoList.add(newDeadline);
                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newDeadline.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                break;

            case "todo":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }

                String todoDescription = input.substring(("todo").length() + 1);
                Task newTodo = new Todo(todoDescription);
                toDoList.add(newTodo);
                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newTodo.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                break;

            case "delete":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                String taskDelete = splitInput[1];

                try {
                    Task taskToDelete = toDoList.get(Integer.parseInt(taskDelete) - 1);
                    toDoList.remove(Integer.parseInt(taskDelete) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + taskToDelete.toString());
                    System.out.println("Now you have " + toDoList.size() + " tasks on the list.");

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }

                break;

            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

        }

        System.out.println("________________________________");

    }

}
