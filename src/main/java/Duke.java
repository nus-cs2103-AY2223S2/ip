import java.util.Scanner;

public class Duke {

    public static void userInput() {
        System.out.println("Greetings");
        Scanner user = new Scanner(System.in);

        while (true) {
            String input = user.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye!");
                break;
            }
            System.out.println(input);
        }
        return;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        userInput();
    }
}
