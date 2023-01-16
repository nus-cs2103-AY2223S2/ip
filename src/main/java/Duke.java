import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);

        boolean isChatting = true;
        while (isChatting) {
            String chat = in.nextLine();
            if (chat.equals("bye")) {
                isChatting = false;
                break;
            }
            respond(chat);
        }
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }

    private static void respond(String chat) {
        System.out.println("----------------------------------");
        System.out.println(chat);
        System.out.println("----------------------------------");
    }
}
