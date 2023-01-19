import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DukeContainer {
    List<Task> tasks;

    public DukeContainer() {
        tasks = new ArrayList<Task>();
    }

    public String listTasks() {
        String output = "";
        int counter = 1;

        Stream<String> s = tasks.stream().map(t -> String.format("%d: %s",counter,t.description()));
        output = s.collect(Collectors.joining("\n"));

        return output;
    }

    public String addTask(String input) {
        Todo todo = new Todo(input);
        tasks.add(todo);

        return String.format("added: %s", todo.description());
    }

    private static void printGreeting() {
        printLine();
        System.out.println();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void printLine() {
        System.out.println("_________________________________________________________");
    }

    private static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private boolean handleInput(String input) {
        if (input.equals("bye")) {
            printBye();
            return true;
        } else if (input.equals("list")) {
            System.out.println(listTasks());

        } else {
            printLine();
            System.out.println(addTask(input));
        }

        printLine();
        return false;
    }

    public void loop() {
        printGreeting();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();

            boolean exit = handleInput(input);
            if (exit)
                break;
        }

    }

}