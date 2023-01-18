import java.util.*;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String horizontalLine = "________________________________\n";
    public static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        greeting();
        printMenu();
    }

    public static void greeting() {
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
     * This method prints a menu that consists of:
     * - "list": Displays the list of text entered by user
     * - "bye": Exits program
     * - Enters any text: Text is stored in an array
     *
     * @return  void
     */
    public static void printMenu() {
        boolean exitStatus = false;
        while(true) {
            String input = sc.nextLine();
            switch(input) {
                case "list":
                    list();
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
     * This displays the list of text entered by user.
     *
     * @return  void
     */
    public static void list() {
        System.out.println(horizontalLine);
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i - 1).getDescription());
        }
        System.out.println(horizontalLine);
    }

    /**
     * This method takes in the task in String and stores it into the ArrayList
     * before printing on the console.
     *
     * @param   input   task entered by the user in String
     * @return  void
     */
    public static void add(String input) {
        Task task = new Task(input);
        taskList.add(task);
        System.out.println(horizontalLine
                + "added: " + task.getDescription() + "\n"
                + horizontalLine);
    }

    /**
     * This method prints out an exit message and exits the program.
     *
     * @return  void
     */
    public static void exit() {
        System.out.println(horizontalLine
                + "Hope I have been useful to you.\n"
                + "See you again soon. Bye!~\n"
                + horizontalLine);
    }
}
