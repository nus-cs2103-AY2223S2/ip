import java.util.ArrayList;
import java.util.Scanner;

public class TunaBot {
    public static void main(String[] args) {
        String LINE = "------------------------------";
        Scanner s = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(LINE);
        System.out.println("    Hello! I'm TunaBot\n" +
                "    What can I do for you?");
        System.out.println(LINE);
        boolean toExit = false;
        while (!toExit) {
            String command = s.next();
            System.out.println(LINE);
            int index;
            String name;
            String start;
            String end;
            int count = tasks.size();
            switch (command) {
                case "list":
                    System.out.println("BLUB! There are " + count + " task(s)!");
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
                case "todo":
                    name = s.nextLine();
                    Task newTask = new Task(name);
                    tasks.add(newTask);
                    count++;
                    System.out.println("    Blub! added: \n" + newTask);
                    System.out.println("    Blub! You have " + count + " tasks now!");
                    break;
                case "event":
                    name = s.nextLine();
                    String fromSplit[] = name.split("/from ");
                    String toSplit[] = fromSplit[1].split("/to ");
                    Event newEvent = new Event(fromSplit[0], toSplit[0], toSplit[1]);
                    tasks.add(newEvent);
                    count++;
                    System.out.println("    Blub! added: \n" + newEvent);
                    System.out.println("    Blub! You have " + count + " tasks now!");
                    break;
                case "deadline":
                    name = s.nextLine();
                    String split[] = name.split("/by ");
                    Deadline newDeadline = new Deadline(split[0], split[1]);
                    tasks.add(newDeadline);
                    count++;
                    System.out.println("    Blub! added: \n" + newDeadline);
                    System.out.println("    Blub! You have " + count + " tasks now!");
                    break;
                default:
                    System.out.println("UH OH INVALID COMMAND");

            }
            System.out.println(LINE);
        }
    }
}
