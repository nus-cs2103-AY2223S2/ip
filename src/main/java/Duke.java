import java.util.Scanner;

public class Duke {
    private static final String greeting = "____________________________________________________________\n" + "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________";
    private static final String goodbye = "____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________";
    private static boolean active = true;
    private static final String[] list = new String[100];
    public static void main(String[] args) {
        System.out.println(greeting);
        int counter = 0;
        while(active) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                active = false;
                System.out.println(goodbye);
            }
            else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for(int i = 1; i < 100; i++) {
                    if(list[i] != null) {
                        System.out.println(i + ". " + list[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            }
            else {
                counter++;
                list[counter] = input;
                System.out.println("____________________________________________________________\n added: " + input + "\n____________________________________________________________");
            }
        }
    }
}
