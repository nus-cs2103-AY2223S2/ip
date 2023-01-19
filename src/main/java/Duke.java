import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String LINE = "------------------------------------------------------------";

    private static final ArrayList<Task> TASKS = new ArrayList<Task>();

    private static void listTasks() {
        if (TASKS.size() == 0) {
            System.out.println("You do not have any tasks added to the list.");
        } else {
            System.out.println("Listing all tasks...");
            for (int i = 0; i < TASKS.size(); i++) {
                System.out.println((i + 1) + ") " + TASKS.get(i));
            }
        }
    }

    private static void processCommand(String command) {
        if (command.equals("")) {
            System.out.println("You have not entered anything...");
            System.out.println("\n" + LINE + "\n");
            return;
        }

        String [] cmdParts = command.split(" ");
        String cmdHeader = cmdParts[0];

        if (cmdHeader.equals("list")) {
            listTasks();

        } else if (cmdHeader.equals("mark") || cmdHeader.equals("unmark")) {

            if (cmdParts.length != 2) {
                System.out.println("Sorry... That is an invalid command :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            try {
                int taskNumber = Integer.parseInt(cmdParts[1]);
                boolean isValidTaskNumber =
                        (taskNumber > 0 && taskNumber <= TASKS.size());

                if (isValidTaskNumber) {
                    if (cmdHeader.equals("mark")) {
                        TASKS.get(taskNumber - 1).mark();
                        System.out.println("I have marked Task " + taskNumber + " as done.");
                        System.out.println(TASKS.get(taskNumber - 1));

                    } else {
                        TASKS.get(taskNumber - 1).unmark();
                        System.out.println("I have marked Task " + taskNumber + " as undone.");
                        System.out.println(TASKS.get(taskNumber - 1));

                    }

                } else {
                    System.out.println("Sorry... That is an invalid task number :/");
                }

            } catch (NumberFormatException e) {
                System.out.println("Sorry... That is an invalid task number :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

        } else if (cmdHeader.equals("todo")) {

            if (cmdParts.length == 1) {
                System.out.println("Uh-oh. The description of the to-do is empty :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            String[] descWords = Arrays.copyOfRange(cmdParts, 1, cmdParts.length);
            String description = String.join(" ", descWords);

            Task toDo = new ToDo(description);
            TASKS.add(toDo);
            System.out.println("I have added the to-do to the list.");
            System.out.println(toDo);

        } else if (cmdHeader.equals("deadline")) {

            if (cmdParts.length == 1) {
                System.out.println("Uh-oh. The description of the deadline is empty :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            String[] cmdRest = Arrays.copyOfRange(cmdParts, 1, cmdParts.length);
            List<String> descList = Arrays.asList(cmdRest);

            int byIndex = descList.indexOf("/by");
            if (byIndex == -1) {
                System.out.println("Uh-oh. There is no 'by' given for the deadline :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            if (byIndex == 0) {
                System.out.println("Uh-oh. The description of the deadline is empty :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            if (cmdRest.length - 1 == byIndex) {
                System.out.println("Uh-oh. The 'by' of the deadline is empty :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            String[] descWords = Arrays.copyOfRange(cmdRest, 0, byIndex);
            String description = String.join(" ", descWords);

            String[] byArray = Arrays.copyOfRange(cmdRest, byIndex + 1, cmdRest.length);
            String by = String.join(" ", byArray);

            Task deadline = new Deadline(description, by);
            TASKS.add(deadline);
            System.out.println("I have added the deadline to the list.");
            System.out.println(deadline);

        } else if (cmdHeader.equals("event")) {

            if (cmdParts.length == 1) {
                System.out.println("Uh-oh. The description of the event is empty :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            String[] cmdRest = Arrays.copyOfRange(cmdParts, 1, cmdParts.length);
            List<String> descList = Arrays.asList(cmdRest);

            int fromIndex = descList.indexOf("/from");
            if (fromIndex == -1) {
                System.out.println("Uh-oh. There is no 'from' given for the event. :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            int toIndex = descList.indexOf("/to");
            if (toIndex == -1) {
                System.out.println("Uh-oh. There is no 'to' given for the event :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            if (Math.min(fromIndex, toIndex) == 0) {
                System.out.println("Uh-oh. The description of the event is empty :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            if (toIndex - fromIndex == 1 || cmdRest.length - 1 == fromIndex) {
                System.out.println("Uh-oh. The 'from' of the deadline is empty :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            if (fromIndex - toIndex == 1 || cmdRest.length - 1 == toIndex) {
                System.out.println("Uh-oh. The 'to' of the event is empty :/");
                System.out.println("\n" + LINE + "\n");
                return;
            }

            String[] descWords =
                    Arrays.copyOfRange(cmdRest, 0, Math.min(fromIndex, toIndex));
            String description = String.join(" ", descWords);

            String[] fromArray;
            String[] toArray;

            if (fromIndex > toIndex) {
                fromArray =
                        Arrays.copyOfRange(cmdRest, fromIndex + 1, cmdRest.length);
                toArray =
                        Arrays.copyOfRange(cmdRest, toIndex + 1, fromIndex);

            } else {
                fromArray =
                        Arrays.copyOfRange(cmdRest, fromIndex + 1, toIndex);
                toArray =
                        Arrays.copyOfRange(cmdRest, toIndex + 1, cmdRest.length);
            }

            String from = String.join(" ", fromArray);
            String to = String.join(" ", toArray);

            Task event = new Event(description, from, to);
            TASKS.add(event);
            System.out.println("I have added the event to the list.");
            System.out.println(event.toString());

        } else {
            System.out.println("Sorry... I did not understand that :/");
        }

        System.out.println("\n" + LINE + "\n");

    }

    private static void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("You:\n");
            String command = sc.nextLine().strip().toLowerCase();

            System.out.println("\nDuke:");

            if (command.equals("bye")) {
                System.out.println("Bye. Have a nice day!\n");
                System.out.println(LINE);
                break;

            } else {
                processCommand(command);
            }

        }

        sc.close();

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        System.out.println("How can I help you?\n");
        System.out.println(LINE + "\n");

        start();

    }
}
