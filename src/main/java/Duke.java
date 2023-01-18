import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
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
            if (userInput.equals("list")) {
                message = "Here are the tasks in your list:";
                for (int i = 0; i < taskList.size(); i++) {
                    Task oneTask = taskList.get(i);
                    message += "\n" + (i+1) + ". " + oneTask.toString();
                }

            } else if (splitCheck[0].equals("mark")){
                int taskNum = Integer.parseInt(splitCheck[1]);
                Task oneTask = taskList.get(taskNum-1);
                oneTask.markTask();
                message = "Nice! I've marked this task as done:\n " + oneTask.toString();

            } else if (splitCheck[0].equals("unmark")) {
                int taskNum = Integer.parseInt(splitCheck[1]);
                Task oneTask = taskList.get(taskNum-1);
                oneTask.unmarkTask();
                message = "OK! I've marked this task as not done yet:\n " + oneTask.toString();

            } else if (splitCheck[0].equals("todo")){
                Todo tempTodo = new Todo(splitCheck[1]);
                taskList.add(tempTodo);
                message = "Got it. I've added this task:\n " + tempTodo.toString();
                message += "\nNow you have " + taskList.size() + " tasks in the list.";

            } else if (splitCheck[0].equals("deadline")) {
                String[] dlString = splitCheck[1].split(" /by ");
                Deadline tempDeadline = new Deadline(dlString[0], dlString[1]);
                taskList.add(tempDeadline);
                message = "Got it. I've added this task:\n " + tempDeadline.toString();
                message += "\nNow you have " + taskList.size() + " tasks in the list.";
            } else if (splitCheck[0].equals("event")) {
                String[] eventString = splitCheck[1].split(" /from ");
                String[] timeSplit = eventString[1].split(" /to ");
                Event tempEvent = new Event(eventString[0], timeSplit[0], timeSplit[1]);
                taskList.add(tempEvent);
                message = "Got it. I've added this task:\n " + tempEvent.toString();
                message += "\nNow you have " + taskList.size() + " tasks in the list.";
            }
            dukeSpeak(message);
        }

        dukeSpeak("Bye. Hope to see you again soon!");

    }

    public static void dukeSpeak(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
