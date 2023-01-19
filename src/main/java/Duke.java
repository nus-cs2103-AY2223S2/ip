import java.util.Scanner;
public class Duke {

    private static String div_open = "____________________________________________________________\n";
    private static String div_close = "____________________________________________________________\n";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\n";
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

        System.out.println(div_open + logo + greetings + div_close);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                break;
            }
            System.out.println(div_open + msg + "\n" + div_close);
        }




        // End of program
        sc.close();
        System.out.println(div_open + "Bye. Hope to see you again soon!\n"+ div_close);
    }
}
