import java.util.Scanner;

public class Duke {
    public static final String LOGO = "\n ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n\n";

    public static void introduce() {
        System.out.println("Hello I am" + LOGO + "What Can I do for you?");
    }

    public static String input(Scanner sc) {
        System.out.print("> ");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        introduce();
        while(true) {
            String cmd = input(sc);
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(cmd);
        }
        sc.close();
    }
}
