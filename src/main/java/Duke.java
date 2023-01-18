import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean program_running_status = true;

        String echo;
        Scanner scan = new Scanner(System.in);

        while (program_running_status) {
            echo = scan.nextLine();

            if (echo.equals("bye")) {
                break;
            }
            System.out.println("    Duke says:");
            System.out.println("    " + echo);
            
        }
        scan.close();
        System.out.println("    Duke says:");
        System.out.println("    Bye. Hope you run this program again!");
    }
}
