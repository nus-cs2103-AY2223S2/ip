import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();
        System.out.println("Hello! I'm TunaBot\n" +
                "What can I do for you?");
        boolean toExit = false;
        while (!toExit) {
            String command = s.nextLine();
            System.out.println("--------------------------");
            switch (command) {
                case "list":
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + ". " + tasks.get(i - 1));
                    }
                    break;
                case "bye":
                    toExit = true;
                    System.out.println("Bye! Blub blub!");
                    break;
                default:
                    tasks.add(command);
                    System.out.println("blub! added: " + command);
            }
            System.out.println("--------------------------");
        }
    }
}
