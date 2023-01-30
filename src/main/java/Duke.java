import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");

        Scanner input = new Scanner(System.in);

        while (true) {
            String command = input.nextLine();

            if (command.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("\t " + command);
            }
        }
    }
}
