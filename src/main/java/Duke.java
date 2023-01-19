import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        String userInput = null;
        while(!(userInput = scan.nextLine()).equals("bye")) {
            String[] splitCheck = userInput.split(" ", 2);
            String message = "";
            try {
                if (userInput.equals("list")) {
                    message = "Here are the tasks in your list:";
                    for (int i = 0; i < taskList.size(); i++) {
                        Task oneTask = taskList.get(i);
                        message += "\n" + (i + 1) + ". " + oneTask.toString();
                    }

                } else if (splitCheck[0].equals("mark")) {
                    if (splitCheck.length == 1 || splitCheck[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The item number is required to mark.");
                    }
                    int taskNum = Integer.parseInt(splitCheck[1]);
                    if (taskNum > taskList.size() || taskNum <= 0) {
                        throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
                    }
                    Task oneTask = taskList.get(taskNum - 1);
                    oneTask.markTask();
                    message = "Nice! I've marked this task as done:\n " + oneTask.toString();

                } else if (splitCheck[0].equals("unmark")) {
                    if (splitCheck.length == 1 || splitCheck[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The item number is required to unmark.");
                    }
                    int taskNum = Integer.parseInt(splitCheck[1]);
                    if (taskNum > taskList.size() || taskNum <= 0) {
                        throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
                    }
                    Task oneTask = taskList.get(taskNum - 1);
                    oneTask.unmarkTask();
                    message = "OK! I've marked this task as not done yet:\n " + oneTask.toString();

                } else if (splitCheck[0].equals("delete")) {
                    if (splitCheck.length == 1 || splitCheck[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The item number is required to delete.");
                    }
                    int taskNum = Integer.parseInt(splitCheck[1]);
                    if (taskNum > taskList.size() || taskNum <= 0) {
                        throw new DukeException(" ☹ OOPS!!! The item number is out of range.");
                    }
                    Task delTask = taskList.get(taskNum-1);
                    taskList.remove(taskNum-1);
                    message = "Noted. I've removed this task:\n " + delTask.toString();
                    message += "\nNow you have " + taskList.size() + " tasks in the list.";
                } else if (splitCheck[0].equals("todo")) {
                    if (splitCheck.length == 1 || splitCheck[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo tempTodo = new Todo(splitCheck[1]);
                    taskList.add(tempTodo);
                    message = "Got it. I've added this task:\n " + tempTodo.toString();
                    message += "\nNow you have " + taskList.size() + " tasks in the list.";

                } else if (splitCheck[0].equals("deadline")) {
                    if (splitCheck.length == 1 || splitCheck[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description and due date of a deadline cannot be empty.");
                    }
                    String[] dlString = splitCheck[1].split(" /by ");
                    if (dlString.length == 1 || dlString[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The due date of a deadline cannot be empty.");
                    }
                    Deadline tempDeadline = new Deadline(dlString[0], dlString[1]);
                    taskList.add(tempDeadline);
                    message = "Got it. I've added this task:\n " + tempDeadline.toString();
                    message += "\nNow you have " + taskList.size() + " tasks in the list.";
                } else if (splitCheck[0].equals("event")) {
                    if (splitCheck.length == 1 || splitCheck[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] eventString = splitCheck[1].split(" /from ");
                    if (eventString.length == 1 || eventString[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The start & end time of an event cannot be empty.");
                    }
                    String[] timeSplit = eventString[1].split(" /to ");
                    if (timeSplit.length == 1 || timeSplit[1].isEmpty()) {
                        throw new DukeException(" ☹ OOPS!!! The start & end time of an event cannot be empty.");
                    }

                    Event tempEvent = new Event(eventString[0], timeSplit[0], timeSplit[1]);
                    taskList.add(tempEvent);
                    message = "Got it. I've added this task:\n " + tempEvent.toString();
                    message += "\nNow you have " + taskList.size() + " tasks in the list.";
                } else {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
                dukeSpeak(message);
            } catch (DukeException de) {
                dukeSpeak(de.getMessage());
            }
        }

        dukeSpeak("Bye. Hope to see you again soon!");

    }

    public static void dukeSpeak(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
