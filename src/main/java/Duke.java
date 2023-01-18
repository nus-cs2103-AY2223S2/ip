import java.util.*;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String horizontalLine = "________________________________\n";

    public static void main(String[] args) {
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
        printMenu();
    }

    /**
     * This method does not return anything.
     * It only continuously prints the menu until the user enters "Bye".
     */
    public static void printMenu() {
        boolean exitStatus = false;
        while(true) {
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    System.out.println(horizontalLine
                            + "Hope I have been useful to you.\n"
                            + "See you again soon. Bye!~\n"
                            + horizontalLine);
                    exitStatus = true;
                    break;
                default:
                    System.out.println(horizontalLine
                            + input + "\n"
                            + horizontalLine);
            }
            if (exitStatus) {
                break;
            }
        }
    }
}
