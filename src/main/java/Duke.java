import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        /*
        String logo2 = "    ___     _____    __     ___  \n"
                     + "   /   \\   |  ___|  |  |   /   \\ \n"
                     + "  /  |  \\  |  |__   |  |  |  |  | \n"
                     + "  |     |  |  ___|  |  |  |  |  | \n"
                     + "  |__|__|  |__|     |__|   \\__/\\_\\";
         */
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello I'm Duke\n"
                + "What can I do for you?\n");

        Task[] taskArray = new Task[100];
        int arrayIndex = 0;
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String instruction =  input.split(" ")[0];

            switch(instruction) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for (int i = 0; i < arrayIndex; i++) {
                        int listNumber = i + 1;
                        System.out.println(listNumber + ". " + taskArray[i].toString());
                    }
                    System.out.println("");
                    break;
                case "mark":
                    int toMark = input.charAt(5) - 48;
                    Task toMarkTask = taskArray[toMark - 1];
                    toMarkTask.markTask();

                    System.out.println("Nice! I've marked this task as done:\n   "
                                        + toMarkTask + "\n");
                    break;
                case "unmark":
                    int toUnMark = input.charAt(7) - 48;
                    Task toUnMarkTask = taskArray[toUnMark - 1];
                    toUnMarkTask.unmarkTask();

                    System.out.println("OK, I've marked this task as not done yet:\n   "
                            + toUnMarkTask + "\n");
                    break;
                case "todo":
                    try {
                        ToDo toDoTask = new ToDo(input.substring(5));
                        taskArray[arrayIndex] = toDoTask;
                        arrayIndex++;

                        System.out.println("Got it. I've added this task:\n   "
                                + toDoTask
                                + "\nNow you have " + arrayIndex + " tasks in your list\n");
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    String[] dSegments = input.split("/");
                    String deadlineName = dSegments[0].substring(9);
                    String deadline = dSegments[1].substring(3);

                    Deadline deadlineTask = new Deadline(deadlineName, deadline);
                    taskArray[arrayIndex] = deadlineTask;
                    arrayIndex++;

                    System.out.println("Got it. I've added this task:\n   "
                            + deadlineTask
                            + "\nNow you have " + arrayIndex + " tasks in your list\n");
                    break;
                case "event":
                    String[] eSegments = input.split("/");
                    String eventName = eSegments[0].substring(6);
                    String startTime = eSegments[1].substring(5);
                    String endTime = eSegments[2].substring(3);

                    Event eventTask = new Event(eventName, startTime, endTime);
                    taskArray[arrayIndex] = eventTask;
                    arrayIndex++;

                    System.out.println("Got it. I've added this task:\n   "
                            + eventTask
                            + "\nNow you have " + arrayIndex + " tasks in your list\n");
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
