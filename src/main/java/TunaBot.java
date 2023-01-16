import java.util.ArrayList;
import java.util.Scanner;

public class TunaBot {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("------------------------------");
        System.out.println("    Hello! I'm TunaBot\n" +
                "    What can I do for you?");
        System.out.println("------------------------------");
        boolean toExit = false;
        while (!toExit) {
            String command = s.next();
            System.out.println("------------------------------");
            int index;
            switch (command) {
                case "list":
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("    " + i + ". " + tasks.get(i - 1));
                    }
                    break;
                case "bye":
                    toExit = true;
                    System.out.println("    Bye! Blub blub!");
                    break;
                case "mark":
                    index = s.nextInt();
                    tasks.get(index - 1).markDone();
                    System.out.println("    Blub! i have marked this as done!");
                    System.out.println(tasks.get(index - 1));
                    break;
                case "unmark":
                    index = s.nextInt();
                    tasks.get(index - 1).unmarkDone();
                    System.out.println("    Blub! i have marked this as not done!");
                    System.out.println(tasks.get(index - 1));
                    break;
                default:
                    command += s.nextLine();
                    tasks.add(new Task(command));
                    System.out.println("    blub! added: " + command);
            }
            System.out.println("------------------------------");
        }
    }
}
