import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> taskStore = new ArrayList<>();

    private static ArrayList<String> splitBySpace(String input) {
        return new ArrayList<String>(Arrays.asList(input.split(" ")));
    }

    private static ArrayList<String> splitFirst(String input) {
        return new ArrayList<String>(Arrays.asList(input.split("\\s")));
    }

    private static Task getTaskForMarking(ArrayList<String> parsed) {
        int completedIndex = Integer.parseInt(parsed.get(1)) - 1; // index of the task completed
        Task completedTask = taskStore.get(completedIndex); // actual task
        return completedTask;
    }

    private static class Task {
        private final String taskName;
        private boolean completed;

        public Task(String taskName, boolean completed) {
            this.taskName = taskName;
            this.completed = false;
        }

        public void changeCompletion() {
            this.completed = !this.completed;
        }

        @Override
        public String toString() {
            String icon = this.completed ? "[X] " : "[ ] ";
            return icon + this.taskName;
        }
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
                Duke.Task newTask = new Duke.Task(command, false);
                taskStore.add(newTask);
                System.out.println("  added: " + command);
            }
        }
    }
}
