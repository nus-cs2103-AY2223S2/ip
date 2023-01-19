import java.util.Scanner;

public class TreeBot {

    private static final String EXIT_TOKEN = "bye";

    public void start() {

        greet();
        listen();
    }


    private void listen() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String commandString = sc.nextLine();

            if (commandString.equals(EXIT_TOKEN)) {
                exit();
                break;
            }

            echo(commandString);

        }

    }

    private void greet() {
        System.out.println("Hello, I'm a tree. How may I be of service?");
    }

    private void echo(String command) {
        System.out.println(command);
    }

    private void exit() {
        System.out.println("Thank you, i'll be rooting for you.");
    }
}
