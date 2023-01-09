import java.util.Scanner;

public class Duke {
    private static final String greeting = "____________________________________________________________\n" + "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________";
    private static final String goodbye = "____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________";
    private static boolean active = true;
    public static void main(String[] args) {
        System.out.println(greeting);
        while(active) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                active = false;
                System.out.println(goodbye);
            }
            else {
                System.out.println("____________________________________________________________\n" + input + "\n____________________________________________________________");
            }
        }
    }
}
