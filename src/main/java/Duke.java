import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //init
        Scanner sc = new Scanner(System.in);
        todoList ls = new todoList();

        //welcome message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.welcomeMsg();
        Duke.input(sc, ls);
    }

    private static void welcomeMsg() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "What can the Duke help you with today?\n" + divider);
    }

    private static void endMsg() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider + "Goodbye, feel free to call the Duke again whenever you need.\n" + divider);
    }

    private static void input(Scanner sc, todoList ls) {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                Duke.endMsg();
                break;
            }
            if (input.equals("list")) {
                System.out.println(ls);
                continue;
            }
            ls.add(input);
            System.out.println(divider + "added: " + input + "\n" + divider);
        }
    }
}
