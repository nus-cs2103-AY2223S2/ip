import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Chattime {

    private static final String greet = "Hey! I'm your friend, Chattime!  (•◡•) /\n"
            + "     How can I help you *^*";
    private static final String line = "---------------------------------------------------------------------" +
            "******************CHATTIME";
    private static final String goodBye = "Bye bye >^<! Visit me again when you need me ~";
    private static final String WAITING_TASK = "Task(s) waiting to be completed:";
    private static final String UNRECOGNISED_COMMAND = "Sorry... but I don't understand what you said >,<";
    private static final String LIST_EXP = "OOPS!!! list does not take any description.";
    private static final String NO_DESCRIPTION = "OOPS!!! The description of %s cannot be empty.";
    private static final String NO_INDEX = "OOPS!!! The index to %1$s cannot be empty.";
    private static final String NEED_INT = "OOPS!!! The index to %1$s must be positive integer.";
    private static final String IDX_OUT_OF_BOUND = "OOPS!!! The index is too large! We currently have %d task(s).";
    private static final String MISSED_PARAM = "OOPS!!! %s should be in form of %s.";
    private static final ArrayList<Task> storeList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = "      ___\n"
                    + "     /*  \\    \\(˘◡˘)/\n"
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

        try {
            chat(sc);
        } catch (ChattimeException e) {
            replyUser(e.getMessage());
        }

    }

    public static void replyUser(String message) {
        System.out.println("    " + line);
        System.out.println("     " + message);
        System.out.println("    " + line);
    }

    public static void chat(Scanner sc) throws ChattimeException {
        String userInput = sc.nextLine();
        String[] splitCmd = userInput.split(" ", 2);
        String command = splitCmd[0];

        while (!command.equals("bye")) {
            try {
                switch (command) {
                    case "list":
                        if (splitCmd.length == 1) {
                            displayList();
                        } else {
                            throw new ChattimeException(LIST_EXP);
                        }
                        break;

                    case "todo":
                    case "deadline":
                    case "event":
                        if (splitCmd.length < 2) {
                            throw new ChattimeException(String.format(NO_DESCRIPTION, command));
                        } else {
                            switch (command) {
                                case "todo":
                                    todo(splitCmd[1]);
                                    break;
                                case "deadline":
                                    deadline(splitCmd[1]);
                                    break;
                                case "event":
                                    event(splitCmd[1]);
                                    break;
                            }
                        }
                        break;

                    case "mark":
                    case "unmark":
                    case "delete":
                        if (splitCmd.length < 2) {
                            throw new ChattimeException(String.format(NO_INDEX, command));
                        } else if (command.equals("mark")) {
                            mark(splitCmd[1]);
                        } else if (command.equals("unmark")) {
                            unmark(splitCmd[1]);
                        } else {
                            delete(splitCmd[1]);
                        }
                        break;

                    default:
                        throw new ChattimeException(UNRECOGNISED_COMMAND);
                }
            } catch(ChattimeException e) {
                replyUser(e.getMessage());
            }

            userInput = sc.nextLine();
            splitCmd = userInput.split(" ", 2);
            command = splitCmd[0];

        }

        exit();
    }

    public static void displayList() {
        int i = 1;
        String message = WAITING_TASK;

        for (Task task : storeList) {
            message = message.concat(String.format("\n     %d. %s", i, task.toString()));
            i++;
        }

        replyUser(message);
    }

    public static void addTask(Task newTask) {
        storeList.add(newTask);
        newTask.printAddTask();
    }

    public static int checkInt(String content, String command) throws ChattimeException {
        if (Pattern.matches("^[0-9]*$", content)) {
            int index = Integer.parseInt(content);
            if (index > Task.count) {
                throw new ChattimeException(String.format(IDX_OUT_OF_BOUND, Task.count));
            } else {
                return index;
            }
        } else {
            throw new ChattimeException(String.format(NEED_INT, command));
        }
    }

    public static void mark(String context) {
        int index;
        try {
            index = checkInt(context, "mark");
            storeList.get(index - 1).markAsDone();
        } catch (ChattimeException e) {
            replyUser(e.getMessage());
        }
    }

    public static void unmark(String context) {
        int index;
        try {
            index = checkInt(context, "unmark");
            storeList.get(index - 1).unmarkDone();
        } catch (ChattimeException e) {
            replyUser(e.getMessage());
        }
    }

    public static void delete(String context) {
        int index;
        try {
            index = checkInt(context, "delete");
            storeList.get(index - 1).removeTask();
            storeList.remove(index - 1);
        } catch (ChattimeException e) {
            replyUser(e.getMessage());
        }
    }

    public static void todo(String task) {
        Todo todoTask = new Todo(task);
        addTask(todoTask);
    }

    public static void deadline(String content) throws ChattimeException {
        String[] splitBy = content.split(" /by ", 2);
        if (splitBy.length < 2 || splitBy[1].equals("")) {
            throw new ChattimeException(String.format(MISSED_PARAM, "deadline", "deadline (task) /by (describe)"));
        }
        String task = splitBy[0];
        String by = splitBy[1];

        Deadline deadlineTask = new Deadline(task, by);
        addTask(deadlineTask);
    }

    public static void event(String content) throws ChattimeException {
        String[] splitTask = content.split(" /from ", 2);
        String task = splitTask[0];

        if (splitTask.length < 2 || splitTask[1].equals("")) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, "event", "event (task) /from (describe 1) /to (describe 2)"));
        }
        String[] splitFrom = splitTask[1].split(" /to ", 2);
        String from = splitFrom[0];

        if (splitFrom.length < 2 || splitFrom[1].equals("")) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, "event", "event (task) /from (describe 1) /to (describe 2)"));
        }
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
