import java.util.ArrayList;
import java.util.Scanner;

public class Page {

    private Scanner scan;
    private ArrayList<String> tasks;

    public Page() {
        scan = new Scanner(System.in);
        tasks = new ArrayList<String>();
    }

    private void greet() {
        String welcome = "Greetings! 'Tis I, Page, thy medieval assistant.\n" +
                "Type 'help' for the list of commands I can recognise.";
        System.out.println(welcome);
    }

    private void listen() {
        String input = scan.nextLine();
        if (input.equals("bye")) {
            bye();
        } else if (input.equals("list")) {
            list();
        } else if (input.equals("help")) {
            help();
        } else {
            add(input);
        }
    }

    private void help() {
        String helptext =
                "type in a task (e.g. 'tax subjects') to add it to the list of tasks.\n" +
                "type 'list' to show the current list of tasks.\n" +
                "type 'bye' to exit.";
        System.out.println(helptext);
        listen();
    }

    private void add(String input) {
        tasks.add(input);
        System.out.println("Added to the royal scrolls: " + input);
        listen();
    }

    private void list() {
        System.out.println("Tasks for the management of the kingdom: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
        listen();
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

