import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> taskStore = new ArrayList<>();

    private static ArrayList<String> splitBySpace(String input) {
        return new ArrayList<String>(Arrays.asList(input.split(" ")));
    }

    private static Task getTaskForMarking(ArrayList<String> parsed) {
        int completedIndex = Integer.parseInt(parsed.get(1)) - 1; // index of the task completed
        Task completedTask = taskStore.get(completedIndex); // actual task
        return completedTask;
    }

    public static void main(String[] args) {
        System.out.println("  insert ingenious greeting here");

        while (true) {
            Scanner myScanner = new Scanner(System.in);
            String command = myScanner.nextLine();

            ArrayList<String> parsed = splitBySpace(command);

            String first = parsed.get(0);

            if (first.equals("bye")) {
                System.out.println("  Bye. Hope to see you soon again!");
                break;
            }
            else if (first.equals("mark")) {
                Task completedTask = getTaskForMarking(parsed);
                completedTask.changeCompletion();

                System.out.println("  You are done with: ");
                System.out.println("    " + completedTask.toString());
            }
            else if (first.equals("unmark")) {
                Task completedTask = getTaskForMarking(parsed);
                completedTask.changeCompletion();

                System.out.println("  OK, continue working on: ");
                System.out.println("    " + completedTask.toString());
            }
            else if (first.equals("list")) {
                for (int i = 0; i < taskStore.size(); i++) {
                    System.out.println("  " + String.valueOf(i + 1) + ". " + taskStore.get(i));
                }
            }
            // add task
            else {
                Task newTask = new Task(command, false);
                taskStore.add(newTask);
                System.out.println("  added: " + command);
            }
        }
    }
}
