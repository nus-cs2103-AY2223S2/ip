import java.util.ArrayList;
import java.util.Arrays;
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

    private void displayItemList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb
                    .append(i+1)
                    .append(".")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        displayMessage(sb.toString());
    }

    private void addToList(Task task) {
        this.tasks.add(task);
        displayMessage("Got it. I've added this task: \n" +
                task.toString() +
                "\nNow you have " +
                tasks.size() +
                " tasks in the list\n");
    }

    private void addToDo(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            sb.append(tokens[i]).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        ToDo td = new ToDo(sb.toString());
        addToList(td);
    }

    private void addDeadline(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        int idxDelimiter = Arrays.asList(tokens).indexOf("/by");
        if (idxDelimiter == -1) {
            displayMessage("please include /by [deadline]");
        } else {
            for (int i = 1; i < idxDelimiter; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String taskName = sb.deleteCharAt(sb.length()-1).toString();
            sb.delete(0, sb.length());
            for (int i = idxDelimiter + 1; i < tokens.length; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String dueDate = sb.deleteCharAt(sb.length()-1).toString();
            addToList(new Deadline(taskName, dueDate));
        }
    }

    private void addEvent(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        int idxFrom = Arrays.asList(tokens).indexOf("/from");
        int idxTo = Arrays.asList(tokens).indexOf("/to");
        if (idxFrom == -1 || idxTo == -1) {
            displayMessage("please include /from [start] /to [end]");
        } else {
            for (int i = 1; i < idxFrom; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String taskName = sb.deleteCharAt(sb.length()-1).toString();
            sb.delete(0, sb.length());

            for (int i = idxFrom + 1; i < idxTo; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String taskFrom = sb.deleteCharAt(sb.length()-1).toString();
            sb.delete(0, sb.length());

            for (int i = idxTo + 1; i < tokens.length; i++) {
                sb.append(tokens[i]).append(" ");
            }
            String taskTo = sb.deleteCharAt(sb.length()-1).toString();
            addToList(new Event(taskName, taskFrom, taskTo));
        }
    }

    private void markListItem(String[] tokens) {
        try {
            int listIndex = Integer.parseInt(tokens[1])-1;
            tasks.get(listIndex).setStatus("X");
            displayMessage("Nice! I've marked this task as done:\n" +
                    tasks.get(listIndex).toString() + "\n");
        } catch (NumberFormatException e) {
            displayMessage("Please specify a numerical task index to mark\n");
        } catch (IndexOutOfBoundsException e) {
            displayMessage("Please specify a valid index to mark\n");
        }
    }

    private void unmarkListItem(String[] tokens) {
        try {
            int listIndex = Integer.parseInt(tokens[1])-1;
            tasks.get(listIndex).setStatus(" ");
            displayMessage("OK, I've marked this task as not done yet:\n" +
                    tasks.get(listIndex).toString() + "\n");
        } catch (NumberFormatException e) {
            displayMessage("Please specify a numerical task index to unmark\n");
        } catch (IndexOutOfBoundsException e) {
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
                continue;
            } else if (tokens.length == 2 && tokens[0].equals("unmark")) {
                duke.unmarkListItem(tokens);
                continue;
            } else if (tokens[0].equals("todo")) {
                duke.addToDo(tokens);
            } else if (tokens[0].equals("deadline")) {
                duke.addDeadline(tokens);
            } else if (tokens[0].equals("event")) {
                duke.addEvent(tokens);
            } else {
                displayMessage("unknown command\n");
            }

        }
    }
}
