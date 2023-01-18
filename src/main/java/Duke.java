import java.util.Scanner;
public class Duke {
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I help you today?\n");
    }

    public static void echo(String user_input) {
        System.out.println("> Duke's response:");
        System.out.println(user_input);
        System.out.println("--------------------------------\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String user_input =  sc.nextLine();
        String exit_command = "bye";
        while (user_input.equals(exit_command) == false) {
            echo(user_input);
            user_input =  sc.nextLine();
        }
        exit();

    }
}
