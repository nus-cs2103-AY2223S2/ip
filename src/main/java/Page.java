import java.util.ArrayList;
import java.util.Scanner;

public class Page {

    private Scanner scan;
    private ArrayList<Quest> quests;

    public Page() {
        this.scan = new Scanner(System.in);
        this.quests = new ArrayList<>();
    }

    private void greet() {
        String welcome = "Greetings! 'Tis I, Page, thy medieval assistant.\n" +
                "Type 'help' for the list of available commands.";
        System.out.println(welcome);
    }

    private void listen() {
        String input = scan.nextLine();
        if (input.equals("bye")) {
            bye();
            return;
        } else if (input.equals("log")) {
            log();
        } else if (input.equals("help")) {
            help();
        }
        else {
            String[] splitBySpace = input.split(" ");
            String firstWord = splitBySpace[0];
            if (firstWord.equals("complete")) {
                int questNum = Integer.parseInt(splitBySpace[1]);
                quests.get(questNum - 1).markComplete();
            } else if (firstWord.equals("incomplete")) {
                int questNum = Integer.parseInt(splitBySpace[1]);
                quests.get(questNum - 1).markIncomplete();
            } else if (firstWord.equals("todo")) {
                input = input.replaceFirst("todo ", "");
                addTodo(input);
            } else if (firstWord.equals("deadline")) {
                input = input.replaceFirst("deadline ", "");
                String[] splitByBy = input.split(" /by ");
                addDeadline(splitByBy[0], splitByBy[1]);
            } else if (firstWord.equals("event")) {
                input = input.replaceFirst("event ", "");
                String[] splitByFromTo = input.split(" /from | /to");
                addEvent(splitByFromTo[0], splitByFromTo[1], splitByFromTo[2]);
            }
        }
        listen();
    }

    private void help() {
        String helptext =
                "type in a task (e.g. 'slay dragon') to add it to the Quest Log.\n" +
                "type 'log' to show the current Quest Log.\n" +
                "type 'complete 1' to mark the 1st quest as complete.\n" +
                "type 'incomplete 1' to mark the 1st quest as incomplete.\n" +
                "type 'bye' to exit.";
        System.out.println(helptext);
    }

    private void addTodo(String input) {
        Todo t = new Todo(input);
        quests.add(t);
        System.out.println("Added to Quest Log: " + t.toString());
    }

    private void addDeadline(String input, String to) {
        Deadline d = new Deadline(input, to);
        quests.add(d);
        System.out.println("Added to Quest Log: " + d.toString());
    }

    private void addEvent(String input, String from, String to) {
        Event e = new Event(input, from, to);
        quests.add(e);
        System.out.println("Added to Quest Log: " + e.toString());
    }

    private void log() {
        System.out.println("Quest Log:");
        for (int i = 0; i < quests.size(); i++) {
            Quest q = quests.get(i);
            System.out.println((i + 1) + ": " + q.toString());
        }
    }

    private void bye() {
        System.out.println("Farewell, my liege.");
    }

    public static void main(String[] args) {
        Page page = new Page();
        page.greet();
        page.listen();
    }
}

