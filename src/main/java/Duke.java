import java.time.format.DateTimeParseException;
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
        displayMessage("Got it. I've added this task:\n" +
                task.toString() +
                "\nNow you have " +
                tasks.size() +
                " tasks in the list\n");
    }

    private void addToDo(String[] tokens) throws DukeException {
        StringBuilder sb = new StringBuilder();
        if (tokens.length < 2) {
            throw new DukeException("The description of a todo cannot be empty");
        }
        for (int i = 1; i < tokens.length; i++) {
            sb.append(tokens[i]).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        ToDo td = new ToDo(sb.toString());
        addToList(td);
    }

    private void addDeadline(String[] tokens) throws DukeException {
        StringBuilder sb = new StringBuilder();
        int idxDelimiter = Arrays.asList(tokens).indexOf("/by");
        if (idxDelimiter == -1) {
            throw new DukeException("deadline tasks must be specified by /by [deadline] format");
        } else if (idxDelimiter == tokens.length - 1) {
            throw new DukeException("please specify a deadline after the /by tag");
        } else if (idxDelimiter == 1) {
            throw new DukeException("The description of a deadline cannot be empty");
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
            try {
                Task task = new Deadline(taskName, dueDate);
                addToList(task);
            } catch (DateTimeParseException e) {
                displayMessage("Please enter a valid date in the format YYYY-MM-DD/HH:mm\n");
            }
        }
    }

    private void addEvent(String[] tokens) throws DukeException {
        StringBuilder sb = new StringBuilder();
        int idxFrom = Arrays.asList(tokens).indexOf("/from");
        int idxTo = Arrays.asList(tokens).indexOf("/to");
        if (idxFrom == -1 || idxTo == -1) {
            throw new DukeException("event tasks must be specified by a /from [start] /to [end] format");
        } else if (idxFrom > idxTo) {
            throw new DukeException("/to flag must come after /from flag");
        } else if (idxFrom == 1) {
            throw new DukeException("The description of a event task cannot be empty");
        } else if (idxTo - idxFrom == 1) {
            throw new DukeException("please specify a start datetime after /from flag");
        } else if (tokens.length - 1 == idxTo) {
            throw new DukeException("please specify an end datetime after /to flag");
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
            try {
                Task task = new Event(taskName, taskFrom, taskTo);
                addToList(task);
            } catch (DateTimeParseException e) {
                displayMessage("Please enter valid dates in the format YYYY-MM-DD/HH:mm\n");
            }

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
            int listIndex = Integer.parseInt(tokens[1]) - 1;
            tasks.get(listIndex).setStatus(" ");
            displayMessage("OK, I've marked this task as not done yet:\n" +
                    tasks.get(listIndex).toString() + "\n");
        } catch (NumberFormatException e) {
            displayMessage("Please specify a numerical task index to unmark\n");
        } catch (IndexOutOfBoundsException e) {
            displayMessage("Please specify a valid index to unmark\n");
        }
    }

    private void deleteItem(String[] tokens) throws DukeException {
        if (tokens.length != 2) {
            throw new DukeException("please specify delete command as delete [list index]");
        } else if (tasks.size() == 0) {
            throw new DukeException("Task list is empty");
        }
        try {
            int listIndex = Integer.parseInt(tokens[1]) - 1;
            Task removed = tasks.remove(listIndex);
            displayMessage("Noted. I've removed this task:\n" +
                    removed.toString() +
                    "\nNow you have " + tasks.size() + " tasks in the list\n");
        } catch (NumberFormatException e) {
            displayMessage("please specify a valid number to delete entry\n");
        } catch (IndexOutOfBoundsException e) {
            displayMessage("please specify a valid index to delete\n");
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
            } else if (tokens[0].equals("mark")) {
                duke.markListItem(tokens);
            } else if (tokens[0].equals("unmark")) {
                duke.unmarkListItem(tokens);
            } else if (tokens[0].equals("todo")) {
                try {
                    duke.addToDo(tokens);
                } catch (DukeException e) {
                    displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("deadline")) {
                try {
                    duke.addDeadline(tokens);
                } catch (DukeException e) {
                    displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("event")) {
                try {
                    duke.addEvent(tokens);
                } catch (DukeException e) {
                    displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("delete")) {
                try {
                    duke.deleteItem(tokens);
                } catch (DukeException e) {
                    displayMessage(e.getMessage());
                }

            } else {
                displayMessage("unknown command\n");//TODO
            }

        }
    }
}
