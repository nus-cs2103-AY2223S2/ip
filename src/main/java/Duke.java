import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //init
        Scanner sc = new Scanner(System.in);

        //welcome message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.welcomeMsg();
        Duke.input(sc);
    }

    private static void welcomeMsg() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "What can the Duke help you with today?\n" + divider);
    }

    private static void endMsg() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "Goodbye, feel free to call the Duke again whenever you need.\n" + divider);
    }

    private static void input(Scanner sc) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                Duke.endMsg();
                break;
            }
            System.out.println(divider + input + "\n" + divider);
        }
    }
}
