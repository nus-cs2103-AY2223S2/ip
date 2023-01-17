import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Chattime {

    private static final String greet = "Hey! I'm your friend, Chattime!\n" + "     How can I help you *^*";
    private static final String line = "--------------------------------------------------******************CHATTIME";
    private static final String goodBye = "Bye bye >^<! Visit me again when you need me ~";
    private static final ArrayList<Task> storeList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = "      ___\n"
                    + "     /*  \\       *@ ^ @*\n"
                    + "    /::\\  \\     ___\n"
                    + "   /:/::\\  \\   /*  \\\n"
                    + "  /:/  \\:\\  \\  \\:\\  \\\n"
                    + " /:/__/ \\:\\__\\  \\:\\  \\\n"
                    + " \\:\\ \\   \\/__/  /::\\  \\\n"
                    + "  \\:\\ \\        /:/::\\__\\\n"
                    + "   \\:\\ \\*H*A*T/:/  \\/__/*I*M*E\n"
                    + "    \\:\\_\\    /:/  /\n"
                    + "     \\/__/   \\/__/\n";

        System.out.println("Hello from\n" + logo);

        replyUser(greet);

        Scanner sc = new Scanner(System.in);

        chat(sc);

    }

    public static void replyUser(String message) {
        System.out.println("    " + line);
        System.out.println("     " + message);
        System.out.println("    " + line);
    }

    public static void chat(Scanner sc) {
        String userInput = sc.nextLine();
        String[] splitCmd = userInput.split(" ", 2);
        String command = splitCmd[0];

        while (!command.equals("bye")) {

            if (command.equals("list") && splitCmd.length == 1) {
                displayList();

            } else if (command.equals("mark")) {
                mark(splitCmd[1]);

            } else if (command.equals("unmark")) {
                unmark(splitCmd[1]);

            } else if (command.equals("todo")) {
                todo(splitCmd[1]);

            } else if (command.equals("deadline")) {
                deadline(splitCmd[1]);

            } else if (command.equals("event")) {
                event(splitCmd[1]);

            } else {
                replyUser("I don't understand what you said >,<");

            }

            userInput = sc.nextLine();
            splitCmd = userInput.split(" ", 2);
            command = splitCmd[0];

        }

        exit();
    }


    public static void displayList() {
        int i = 1;
        String message = "Task(s) waiting to be completed:";

        for (Task task : storeList) {
            message = message.concat(String.format("\n     %d. %s", i, task.toString()));
            i++;
        }

        replyUser(message);
    }

    public static void addTask(Task newTask) {
        storeList.add(newTask);
        String taskData = newTask.toString();
        String message = "Got it! I've added this task:\n       " + taskData + "\n     " + Task.totalTask();

        replyUser(message);
    }

    public static int checkInt(String content) {
        if (Pattern.matches("^[0-9]*$", content)) {
            return Integer.parseInt(content);
        } else {
            return -1;
        }
    }

    public static void mark(String context) {
        int index = checkInt(context);
        storeList.get(index - 1).markAsDone();
    }

    public static void unmark(String context) {
        int index = checkInt(context);
        storeList.get(index - 1).unmarkDone();
    }

    public static void todo(String task) {
        Todo todoTask = new Todo(task);
        addTask(todoTask);
    }

    public static void deadline(String content) {
        String[] splitBy = content.split(" /by ", 2);
        String task = splitBy[0];
        String by = splitBy[1];

        Deadline deadlineTask = new Deadline(task, by);
        addTask(deadlineTask);
    }

    public static void event(String content) {
        String[] splitTask = content.split(" /from ", 2);
        String task = splitTask[0];
        String[] splitFrom = splitTask[1].split(" /to ", 2);
        String from = splitFrom[0];
        String to = splitFrom[1];

        Event eventTask = new Event(task, from, to);
        addTask(eventTask);
    }

    public static void exit() {
        replyUser(goodBye);
    }

    /* Unused code
    public static void store(String item) {
        Task task = new Task(item);
        storeList.add(task);
        replyUser("added: " + task.description);
    }

    public static void echo(String userInput) {
        replyUser(userInput);
    }
     */

}
