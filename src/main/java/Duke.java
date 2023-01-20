import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String[] history = new String[100];
        int count = 0;
        String input = "";

        String greeting = "-----------------------------------------\n" +
                        "Hello! I'm Duke, what can I do for you?\n" +
                        "-----------------------------------------\n";
        String goodbye = "-----------------------------------------\n" +
                         "Bye! Hope to see you again soon!\n" +
                         "-----------------------------------------\n";

        System.out.println(greeting);
        while (!input.equals("bye")) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println("-----------------------------------------");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + history[i]);
                }
                System.out.println("-----------------------------------------");
            }
            else {
                history[count] = input;
                count++;
                System.out.println("-----------------------------------------\n" +
                        "added: " + input + "\n" +
                        "-----------------------------------------\n");
            }
        }

        System.out.println(goodbye);
    }
}
