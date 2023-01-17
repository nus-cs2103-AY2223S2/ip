import java.util.Scanner;
public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?" + logo);
        Task[] array = new Task[100];
        Scanner myObj = new Scanner(System.in);
        String reply = myObj.nextLine();
        int count = 0;

        while (!reply.contains("bye")) {
            if (reply.contains("deadline")) {
                reply = reply.replaceAll("deadline", "");
                String[] replies = reply.split("/",2);
                handleInvalidArgs checked = new handleInvalidArgs(replies);
                checked.checkForDeadline(checked.replies);
                Deadline deadline = new Deadline(replies[0],replies[1]);
                array[count] = deadline;
                count += 1;
                System.out.println("Got it. I've added this task: ");
                System.out.println(deadline);
                System.out.println("Now you have " + Task.actions + " tasks in the list");

            }else if (reply.contains("todo")) {
                reply = reply.replaceAll("todo", "");
                handleInvalidArgs checked = new handleInvalidArgs(reply);
                checked.checkForToDo(checked.reply);
                ToDo todo = new ToDo(reply);
                array[count] = todo;
                count += 1;
                System.out.println("Got it. I've added this task: ");
                System.out.println(todo);
                System.out.println("Now you have " + Task.actions + " tasks in the list");

            } else if (reply.contains("event")) {
                reply = reply.replaceAll("event", "");
                String[] replies = reply.split("/",3);
                handleInvalidArgs checked = new handleInvalidArgs(replies);
                checked.checkForEvent(checked.replies);
                Event event = new Event(replies[0],replies[1],replies[2]);
                array[count] = event;
                count += 1;
                System.out.println("Got it. I've added this task: ");
                System.out.println(event);
                System.out.println("Now you have " + Task.actions + " tasks in the list");


            }
            else if (reply.contains("unmark")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                array[value].unmark();
            } else if (reply.contains("mark")) {
                int value = Integer.parseInt(reply.replaceAll("[^0-9]", "")) - 1;
                array[value].mark();

            }
            else if (reply.contains("list")) {
                System.out.println("Here are the tasks in your list:\n");
                int listcount = 1;
                for (Task element: array) {
                    if (element != null) {
                        System.out.println("" + listcount + "." + element);
                        listcount += 1;
                    }
                }
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


