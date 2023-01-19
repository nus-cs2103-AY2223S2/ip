import java.util.Scanner;
public class Duke {
    private static String DIV_OPEN = "____________________________________________________________\n";
    private static String DIV_CLOSE = "____________________________________________________________\n";
    public static void main(String[] args) {
        // Initialize
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\n";
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        String[] list = new String[100];
        int listNum = 1;
        System.out.println(DIV_OPEN + logo + greetings + DIV_CLOSE);
        // Initialization complete

        // Accept user input in a loop
        Scanner sc = new Scanner(System.in);

        while(true) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                break;
            }
            System.out.println(DIV_OPEN + msg + "\n" + DIV_CLOSE);
        }




        // End of program
        sc.close();
        System.out.println(DIV_OPEN + "Bye. Hope to see you again soon!\n"+ DIV_CLOSE);
    }
}
