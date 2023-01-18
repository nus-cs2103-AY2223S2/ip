import java.util.*;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static String horizontalLine = "________________________________\n";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        greeting();
        while(true) {
            try {
                String input = sc.nextLine();
                if (printMenu(input)) {
                    break;
                }
            } catch (DukeException | NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void greeting() {
        String logo = "  _____     _       _  __  U _____ u      ____     _   _    _  __  U _____ u \n"
                + " |\" ___|U  /\"\\  u  |\"|/ /  \\| ___\"|/     |  _\"\\ U |\"|u| |  |\"|/ /  \\| ___\"|/ \n"
                + "U| |_  u \\/ _ \\/   | ' /    |  _|\"      /| | | | \\| |\\| |  | ' /    |  _|\"   \n"
                + "\\|  _|/  / ___ \\ U/| . \\\\u  | |___      U| |_| |\\ | |_| |U/| . \\\\u  | |___   \n"
                + " |_|    /_/   \\_\\  |_|\\_\\   |_____|      |____/ u<<\\___/   |_|\\_\\   |_____|  \n"
                + " )(\\\\,-  \\\\    >>,-,>> \\\\,-.<<   >>       |||_  (__) )(  ,-,>> \\\\,-.<<   >>  \n"
                + "(__)(_/ (__)  (__)\\.)   (_/(__) (__)     (__)_)     (__)  \\.)   (_/(__) (__) \n";
        System.out.println(horizontalLine
                + "Hello!~ I'm the one and only\n"
                + logo
                + "What can I do for you?\n"
                + horizontalLine);
    }

    /**
     * This method prints a menu that consists of these features:
     * - "todo": Adds todo task
     * - "deadline": Adds deadline task
     * - "event": Adds event task
     * - "list": Displays the list of text entered by user
     * - "mark": Marks a task as done
     * - "unmark": Unmarks a task as undone
     * - "delete": Deletes indicated task
     * - "bye": Exits program
     * - Enters any other String of invalid syntax: Rejected
     *
     * @param   input   User input for the program menu
     * @return  Status whether the program should exit or not
     */
    private static boolean printMenu(String input) throws DukeException, NoSuchElementException {
        boolean exitStatus = false;

        String[] splitInput = input.split(" ");
        String action = splitInput[0];

        switch (action) {
            case "todo":
                if (splitInput.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                todo(input.split(" ", 2)[1]);
                break;
            case "deadline":
                if (splitInput.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                deadline(input.split(" ", 2)[1]);
                break;
            case "event":
                if (splitInput.length < 2) {
                    throw new DukeException("The description of a event cannot be empty.");
                }
                event(input.split(" ", 2)[1]);
                break;
            case "list":
                list();
                break;
            case "mark":
                if (splitInput.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                mark(splitInput[1]);
                break;
            case "unmark":
                if (splitInput.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                unmark(splitInput[1]);
                break;
            case "delete":
                if (splitInput.length < 2) {
                    throw new DukeException("The task index cannot be empty.");
                }
                delete(splitInput[1]);
                break;
            case "bye":
                exit();
                exitStatus = true;
                break;
            default:
                throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        }
        return exitStatus;
    }

    /**
     * This method handles the adding of todo tasks.
     *
     * @param   taskDesc    Description of task.
     * @return  void
     */
    private static void todo(String taskDesc) {
        Todo todo = new Todo(taskDesc);
        add(todo);
    }

    /**
     * This method handles the adding of deadline tasks.
     *
     * @param   taskDesc    Description of task.
     * @return  void
     */
    private static void deadline(String taskDesc) {
        Deadline deadline = new Deadline(taskDesc);
        add(deadline);
    }

    /**
     * This method handles the adding of event tasks.
     *
     * @param   taskDesc    Description of task.
     * @return  void
     */
    private static void event(String taskDesc) {
        Event event = new Event(taskDesc);
        add(event);
    }

    /**
     * This method handles the adding of any tasks.
     *
     * @param   task    Task to be added
     * @return  void
     */
    private static void add(Task task) {
        taskList.add(task);
        System.out.println(horizontalLine
                + "Got it. I've added this task:");
        task.getTask();
        System.out.println("Now you have "
                + taskList.size()
                + " tasks in the list.\n"
                + horizontalLine);
    }

    /**
     * This displays the list of tasks.
     *
     * @return  void
     */
    private static void list() {
        System.out.println(horizontalLine
                + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.print(i + ".");
            taskList.get(i - 1).getTask();
        }
        System.out.println(horizontalLine);
    }

    /**
     * This method marks task as done.
     *
     * @param   strIdx  Index of task
     * @return  void
     */
    private static void mark(String strIdx) {
        System.out.println(horizontalLine
                + "Nice! I've marked this task as done:");
        int idx = Integer.parseInt(strIdx);
        Task task = taskList.get(idx - 1);
        task.markTask();
        System.out.println(horizontalLine);
    }

    /**
     * This method unmarks task as undone.
     *
     * @param   strIdx  Index of task
     * @return  void
     */
    private static void unmark(String strIdx) {
        System.out.println(horizontalLine
                + "OK, I've marked this task as not done yet:");
        int idx = Integer.parseInt(strIdx);
        Task task = taskList.get(idx - 1);
        task.unmarkTask();
        System.out.println(horizontalLine);
    }

    /**
     * This method deletes task given its index.
     *
     * @param   strIdx  Index of task
     * @return  void
     */
    private static void delete(String strIdx) {
        System.out.println(horizontalLine
                + "Noted. I've removed this task:");
        int idx = Integer.parseInt(strIdx);
        taskList.get(idx - 1).getTask();
        taskList.remove(idx - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n"
                + horizontalLine);
    }

    /**
     * This method prints out an exit message and exits the program.
     *
     * @return  void
     */
    private static void exit() {
        System.out.println(horizontalLine
                + "Hope I have been useful to you.\n"
                + "See you again soon. Bye!~\n"
                + horizontalLine);
    }
}
