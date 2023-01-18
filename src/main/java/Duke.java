import java.util.*;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static String horizontalLine = "________________________________\n";
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        greeting();
        printMenu();
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
     * - "list": Displays the list of text entered by user
     * - "mark": Marks a task as done
     * - "unmark": Unmarks a task as undone
     * - "bye": Exits program
     * - Enters any other String as first word: Treat as a task and stored in an array
     *
     * @return  void
     */
    private static void printMenu() {
        boolean exitStatus = false;
        while(true) {
            String input = sc.nextLine();
            String[] splitInput = input.split(" ");
            String action = splitInput[0];
            switch(action) {
                case "list":
                    list();
                    break;
                case "mark":
                    mark(splitInput[1]);
                    break;
                case "unmark":
                    unmark(splitInput[1]);
                    break;
                case "bye":
                    exit();
                    exitStatus = true;
                    break;
                default:
                    add(input);
            }
            if (exitStatus) {
                break;
            }
        }
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
            taskList.get(i - 1).printTask();
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
        task.isDone = true;
        task.printTask();
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
        task.isDone = false;
        task.printTask();
        System.out.println(horizontalLine);
    }

    /**
     * This method takes in the task in String and stores it into the ArrayList
     * before printing on the console.
     *
     * @param   input   task entered by the user in String
     * @return  void
     */
    private static void add(String input) {
        Task task = new Task(input);
        taskList.add(task);
        System.out.println(horizontalLine
                + "added: " + input + "\n"
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
