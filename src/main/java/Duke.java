import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final List<Task> tasks;

    private Duke() {
        this.tasks = new ArrayList<>();
    }

    private static void displayMessage(String message) {
        System.out.println(
                "-----------------------------------------------------------\n" +
                message +
                "-----------------------------------------------------------\n"
        );
    }

    private void init() {
        displayMessage("""
                Hello! I'm Bob
                What can I do for you?
                """);
    }

    private void exit() {
        displayMessage("Bye. Hope to see you again soon!\n");
    }

    private String generateListEntry(int i) {
        Task task = tasks.get(i);
        return "[" + task.getStatus() + "] " + task.getName();
    }

    private void displayItemList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb
                    .append(i+1)
                    .append(".")
                    .append(generateListEntry(i))
                    .append("\n");
        }
        displayMessage(sb.toString());
    }

    private void addNewItem(String item) {
        this.tasks.add(new Task(item));
    }

    private void markListItem(String[] tokens) {
        try {
            int listIndex = Integer.parseInt(tokens[1])-1;
            tasks.get(listIndex).setStatus("X");
            displayMessage("Nice! I've marked this task as done:\n" +
                    generateListEntry(listIndex) + "\n");
        } catch (NumberFormatException e) {
            displayMessage("Please specify a numerical task index to mark\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            displayMessage("Please specify a valid index to mark\n");
        }
    }

    private void unmarkListItem(String[] tokens) {
        try {
            int listIndex = Integer.parseInt(tokens[1])-1;
            tasks.get(listIndex).setStatus(" ");
            displayMessage("OK, I've marked this task as not done yet:\n" +
                    generateListEntry(listIndex) + "\n");
        } catch (NumberFormatException e) {
            displayMessage("Please specify a numerical task index to unmark\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            displayMessage("Please specify a valid index to unmark\n");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.init();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] tokens = input.split(" ");
            if (tokens.length == 1 && input.equals("bye")) {
                duke.exit();
                break;
            } else if (tokens.length == 1 && input.equals("list")) {
                duke.displayItemList();
                continue;
            } else if (tokens.length == 2 && tokens[0].equals("mark")) {
                duke.markListItem(tokens);
            } else if (tokens.length == 2 && tokens[0].equals("unmark")) {
                duke.unmarkListItem(tokens);
            }

            else {
                duke.addNewItem(input);
                displayMessage("added: " + input + "\n");
            }

        }
    }
}
