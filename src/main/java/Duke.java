import java.util.Scanner;
import java.util.ArrayList;

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
            String input = reader.nextLine();
            String[] splitInput = input.split(" ");
            String command = splitInput[0];

            System.out.println("________________________________");

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________");
                break;
            }
            switch (command) {

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < toDoList.size(); i++) {

                        System.out.println(i + 1 + "." + toDoList.get(i).toString());
                    }
                    break;

                case "mark":
                    String taskNumMark = splitInput[1];

                    Task taskToMark = toDoList.get(Integer.parseInt(taskNumMark) - 1);

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + taskToMark.mark());

                    break;
                case "unmark":
                    String taskNumUnmark = splitInput[1];

                    Task taskToUnmark = toDoList.get(Integer.parseInt(taskNumUnmark) - 1);

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + taskToUnmark.unMark());

                    break;

                case "event":
                    String eventDescription = input.substring(("event").length() + 1);
                    Task newEvent = new Event(eventDescription);
                    toDoList.add(newEvent);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + newEvent.toString());
                    System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                    break;

                case "deadline":
                    String deadlineDescription = input.substring(("deadline").length() + 1);
                    Task newDeadline = new Deadline(deadlineDescription);
                    toDoList.add(newDeadline);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + newDeadline.toString());
                    System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                    break;

                case "todo":
                    String todoDescription = input.substring(("todo").length() + 1);
                    Task newTodo = new Todo(todoDescription);
                    toDoList.add(newTodo);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("  " + newTodo.toString());
                    System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                    break;

                default:
                    Task newTask = new Task(input);
                    toDoList.add(newTask);
                    System.out.println("added: " + input);

                    break;

            }

            System.out.println("________________________________");

        }
        reader.close();

    }

}
