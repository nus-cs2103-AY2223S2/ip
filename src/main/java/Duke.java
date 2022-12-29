import java.util.Scanner;
public class Duke {
    private static final String greeting = "____________________________________________________________\n" + " Hello! I'm Duke\n" + " What can I do for you?\n" + "____________________________________________________________";
    private static final String goodbye = "____________________________________________________________\n" + " Bye. Hope to see you again soon!\n" + "____________________________________________________________";
    private static boolean online = true;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        Scanner sc = new Scanner(System.in);
        System.out.println(greeting);

        while(online) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                online = false;
                System.out.println(goodbye);
            }
            else {
                System.out.println("____________________________________________________________\n" + input + "\n" + "____________________________________________________________");
            }
        }
    }
}
