import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?" + logo);
        ArrayList<Task> array = new ArrayList<>(100);
        Scanner myObj = new Scanner(System.in);
        String reply = myObj.nextLine();
        int count = 0;

        while (!reply.startsWith("bye")) {
            if (reply.startsWith("deadline")) {
                reply = reply.replaceAll("deadline", "");
                String[] replies = reply.split("/",2);
                handleInvalidArgs checked = new handleInvalidArgs(replies);
                checked.checkForDeadline(checked.replies);
                Deadline deadline = new Deadline(replies[0],replies[1]);
                array.add(deadline);
                count += 1;
                System.out.println("Got it. I've added this task: ");
                System.out.println(deadline);
                System.out.println("Now you have " + Task.actions + " tasks in the list");

            }else if (reply.startsWith("todo")) {
                reply = reply.replaceAll("todo", "");
                handleInvalidArgs checked = new handleInvalidArgs(reply);
                checked.checkForToDo(checked.reply);
                ToDo todo = new ToDo(reply);
                array.add(todo);
                count += 1;
                System.out.println("Got it. I've added this task: ");
                System.out.println(todo);
                System.out.println("Now you have " + Task.actions + " tasks in the list");

            } else if (reply.startsWith("event")) {
                reply = reply.replaceAll("event", "");
                String[] replies = reply.split("/",3);
                handleInvalidArgs checked = new handleInvalidArgs(replies);
                checked.checkForEvent(checked.replies);
                Event event = new Event(replies[0],replies[1],replies[2]);
                array.add(event);
                count += 1;
                System.out.println("Got it. I've added this task: ");
                System.out.println(event);
                System.out.println("Now you have " + Task.actions + " tasks in the list");


            }
            else if (reply.startsWith("unmark")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                array.get(value).unmark();
            } else if (reply.startsWith("mark")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                array.get(value).mark();

            }
            else if (reply.startsWith("list")) {
                System.out.println("Here are the tasks in your list:\n");
                int listcount = 1;
                for (Task element: array) {
                    if (element != null) {
                        System.out.println("" + listcount + "." + element);
                        listcount += 1;
                    }
                }
            } else if (reply.startsWith("delete")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                Task deleted = array.get(value);
                Task.actions -= 1;
                array.remove(deleted);
                System.out.println("Noted. I've removed this task: \n" + deleted);
                System.out.println("Now you have " + Task.actions + " tasks in the list");
            } else {
                handleInvalidArgs checked = new handleInvalidArgs(reply);
                checked.checkForRandomWords(checked.reply);
            }

            myObj = new Scanner(System.in);
            reply = myObj.nextLine();
        }
        System.out.println("Bye, Hope to see you again soon!");
    }

}


