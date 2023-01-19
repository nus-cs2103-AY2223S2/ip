import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    static final Pattern DEADLINE_PATTERN = Pattern.compile("(.+)/by (.+)");
    static final Pattern EVENT_PATTERN = Pattern.compile("(.+)/from (.+) /to (.+)");

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        printASCII();

        try (Scanner sc = new Scanner(System.in)) {
            String command;
            do {
                command = sc.next();
                String body = sc.nextLine();

                switch (command) {
                    case ("bye"):
                        break;
                    case ("list"):
                        System.out.println("Your current tracked tasks: ");
                        for (int i = 0; i < tasks.size(); i++) {
                            Task curr = tasks.get(i);
                            System.out.println((i + 1) + "." + curr);
                        }
                        break;
                    case ("unmark"):
                    case ("mark"):
                        body = body.substring(1);
                        int index = Integer.parseInt(body) - 1;
                        tasks.get(index).toggleDone();
                        System.out.println("Toggled state:\n [" + tasks.get(index).getDoness() + "] " + tasks.get(index).desc);
                        break;
                    case ("todo"):
                        body = body.substring(1);
                        ToDo curr = new ToDo(body, false);
                        tasks.add(curr);
                        printNotif(curr);
                    case ("deadline"):
                        body = body.substring(1);
                        Matcher dlMatcher = DEADLINE_PATTERN.matcher(body);
                        if(dlMatcher.matches()) {
                            String desc = dlMatcher.group(1);
                            String deadlineDay = dlMatcher.group(2);

                            Deadline dl = new Deadline(desc, false, deadlineDay);
                            tasks.add(dl);
                            printNotif(dl);
                        }
                        break;
                    case ("event"):
                        body = body.substring(1);
                        Matcher eMatcher = EVENT_PATTERN.matcher(body);
                        if(eMatcher.matches()) {
                            String desc = eMatcher.group(1);
                            String from = eMatcher.group(2);
                            String to = eMatcher.group(3);

                            Event dl = new Event(desc, false, from, to);
                            tasks.add(dl);
                            printNotif(dl);
                        }
                        break;
                    default:
                        System.out.println("ERROR: Unknown command");
                        break;
                }
            } while (!command.equals("bye"));

        } catch (Exception e) {
            System.out.println("Encountered exception: " + e + "\nExiting program");
        } finally {
            System.out.println("Goodbye!");
        }
    }

    private static void printNotif(Task curr) {
        System.out.println("____________________________________________________________");
        System.out.println("Me add your task to list: ");
        System.out.println("\t" + curr);
        System.out.println("You currently have " + tasks.size() + " tracked tasks");
        System.out.println("____________________________________________________________");
    }

    static void printASCII(){
        String line = " ---------------------------------------------------------";
        String logo = "\t\t\t\t            | |       \n" +
                "\t\t\t\t _ __  _   _| | _____ \n" +
                "\t\t\t\t| '_ \\| | | | |/ / _ \\\n" +
                "\t\t\t\t| |_) | |_| |   <  __/\n" +
                "\t\t\t\t| .__/ \\__,_|_|\\_\\___|\n" +
                "\t\t\t\t| |                   \n" +
                "\t\t\t\t|_|                  ";
        System.out.println(line + "\n" + logo + "\n" + line);
        System.out.println("Welcome to PUKE, the worst program in existence");
        System.out.println("Input a command");
    }
}
