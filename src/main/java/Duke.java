import java.util.Scanner;
public class Duke {
    private static String DIV_OPEN = "____________________________________________________________\n";
    private static String DIV_CLOSE = "____________________________________________________________\n";
    public static void main(String[] args) {
        // Initialize
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\n";
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        Task[] list = new Task[101];
        int listNum = 1; // starts from 1 for convenience
        boolean running = true;
        System.out.println(DIV_OPEN + logo + greetings + DIV_CLOSE);
        // Initialization complete

        // Accept user input in a loop
        Scanner sc = new Scanner(System.in);
        while(running) {

            String msg = sc.next();

            // Terminate Duke
            if (msg.equals("bye")) {
                break;
            }

            // Commands
            System.out.printf(DIV_OPEN); // DIV_OPEN for output

            switch(msg) {

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < listNum; i++) {
                        System.out.println(i + ". " + list[i]);
                    }
                    break;

                case "mark":
                    int markNum = sc.nextInt();
                    list[markNum].markDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + list[markNum]);
                    break;

                case "unmark":
                    int unmarkNum = sc.nextInt();
                    list[unmarkNum].markUndone();
                    System.out.println("Ok, I've marked this task as not done yet:\n  " + list[unmarkNum]);
                    break;

                case "todo":
                    msg = sc.nextLine();
                    String todoName = msg.substring(1);
                    Todo todo = new Todo(todoName);
                    list[listNum] = todo;
                    System.out.println("Got it. I've added this task:\n  " + list[listNum]);
                    System.out.println("Now you have " + listNum + " tasks in the list.");
                    listNum++;
                    break;

                case "deadline":
                    msg = sc.nextLine();
                    int byIndex = msg.indexOf(" /by ");
                    String dlName = msg.substring(1, byIndex);
                    String dlBy = msg.substring(byIndex + 5);
                    Deadline deadline = new Deadline(dlName, dlBy);
                    list[listNum] = deadline;
                    System.out.println("Got it. I've added this task:\n  " + list[listNum]);
                    System.out.println("Now you have " + listNum + " tasks in the list.");
                    listNum++;
                    break;

                case "event":
                    msg = sc.nextLine();
                    int fromIndex = msg.indexOf(" /from ");
                    int toIndex = msg.indexOf(" /to ");
                    String eventName = msg.substring(1, fromIndex);
                    String eventFrom = msg.substring(fromIndex + 7, toIndex);
                    String eventTo = msg.substring(toIndex + 5);
                    Event event = new Event(eventName, eventFrom, eventTo);
                    list[listNum] = event;
                    System.out.println("Got it. I've added this task:\n  " + list[listNum]);
                    System.out.println("Now you have " + listNum + " tasks in the list.");
                    listNum++;
                    break;

                default:
                    msg += sc.nextLine(); // read finish the task
                    Task task = new Task(msg);
                    list[listNum] = task;
                    System.out.println("added: " + msg);
                    listNum++;
            }

            System.out.println(DIV_CLOSE); // DIV_CLOSE for output
        }




        // End of program
        sc.close();
        System.out.printf(DIV_OPEN + "Bye. Hope to see you again soon!\n"+ DIV_CLOSE);
    }
}
