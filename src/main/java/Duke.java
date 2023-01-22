
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
        try {
            Scanner sc = new Scanner(new File("src/data/duke.txt"));
            while (sc.hasNextLine()) {
                String[] tokens = sc.nextLine().split(",");
                parseEventFromFile(tokens);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        displayMessage("""
                Hello! I'm Bob
                What can I do for you?
                """);
    }

    private void exit() {
        displayMessage("Bye. Hope to see you again soon!\n");
    }

    private void parseEventFromFile(String[] tokens) {
        String taskType = tokens[0];
        if (Objects.equals(taskType, "T")) {
            tasks.add(new ToDo(tokens[2], tokens[1]));
        } else if (Objects.equals(taskType, "D")) {
            tasks.add(new Deadline(tokens[2], tokens[3], tokens[1]));
        } else if (Objects.equals(taskType, "E")) {
            tasks.add(new Event(tokens[2], tokens[3], tokens[4], tokens[1]));
        }
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
        updateData();
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
            addToList(new Deadline(taskName, dueDate));
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
            addToList(new Event(taskName, taskFrom, taskTo));
        }
    }

    private void markListItem(String[] tokens) {
        try {
            int listIndex = Integer.parseInt(tokens[1])-1;
            tasks.get(listIndex).setStatus("X");
            displayMessage("Nice! I've marked this task as done:\n" +
                    tasks.get(listIndex).toString() + "\n");
            updateData();
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
            updateData();
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
            updateData();
        } catch (NumberFormatException e) {
            displayMessage("please specify a valid number to delete entry\n");
        } catch (IndexOutOfBoundsException e) {
            displayMessage("please specify a valid index to delete\n");
        }
    }

    private void updateData() {
        String path = "src/data/duke.txt";
        File file = new File(path);
        file.getParentFile().mkdirs();
        try {
            FileWriter fw = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for (Task task : tasks) {
                sb.append(task.asTokens()).append('\n');
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
                displayMessage("unknown command\n");
            }

        }
    }
}
