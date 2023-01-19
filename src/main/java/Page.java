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
            String[] splitInput = input.split(" ");
            if (splitInput[0].equals("complete")) {
                int questNum = Integer.parseInt(splitInput[1]);
                quests.get(questNum - 1).markComplete();
            } else if (splitInput[0].equals("incomplete")) {
                int questNum = Integer.parseInt(splitInput[1]);
                quests.get(questNum - 1).markIncomplete();
            } else {
                addQuest(input);
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

    private void addQuest(String input) {
        quests.add(new Quest(input));
        System.out.println("Added to Quest Log: " + input);
    }

    private void log() {
        System.out.println("Quest Log: ");
        for (int i = 0; i < quests.size(); i++) {
            Quest q = quests.get(i);
            System.out.println((i + 1) + ": " + q.getCompletionIcon() + " " + q.getDescription());
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

