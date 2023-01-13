import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Hello! I'm TunaBot\n" +
                " What can I do for you?");
        boolean toExit = false;
        while (!toExit) {
            String command = s.next();
            System.out.println("--------------------------");
            switch (command) {
                case "bye":
                    toExit = true;
                    System.out.println("Bye! Blub blub!");
                    break;
                default:
                    System.out.println(command);
            }
            System.out.println("--------------------------");
        }
    }
}
