import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public enum Command { // Fixed number of commands
        TODO("todo"), DEADLINE("deadline"), EVENT("event"), MARK("mark"),
        UNMARK("unmark"), DELETE("delete"), LIST("list"), BYE("bye"), FILTERDATE("filterdate");

        private final String command;

        Command(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }
    public static void main(String[] args) {
        String logo = "  /\\_/\\\n"
                + " /\u25DE   \u25DF\\\n"
                + "( \u25d5   \u25d5 )\n"
                + " \\     /\n"
                + "  \\   /\n"
                + "   \\ /\n"
                + "    \u25CF\n";
        System.out.println(logo + "BorzAI\n"); // Intro
        System.out.println("\tWhen all I do is for you, Kermie \u2665\n" // Greeting
                + "\tWhat can I do for you?\n");
        Scanner scanner = new Scanner(System.in); // Allow user input
        ArrayList<Task> taskList = new ArrayList<>(); // Create list
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.trim().split(" ", 2);
            Task t = null;
            int index;
            try {
                Command command = Command.valueOf(parts[0].trim().toUpperCase());
                switch (command) {
                    case BYE: // Exit
                        break;
                    case LIST: // Display list
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            t = taskList.get(i);
                            index = i + 1;
                            if (i == taskList.size() - 1) { // Last item
                                System.out.println("\t" + index + "." + t);
                                break;
                            }
                            System.out.println("\t" + index + "." + t);
                        }
                        continue;
                    case FILTERDATE:
                        if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
                            throw new DukeException.InvalidCommandException("Command has to be followed by date.");

                        }
                        LocalDate date = null;

                        DateTimeFormatter[] formatters = {
                                DateTimeFormatter.ofPattern("ddMMyyyy"),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        };

                        for (DateTimeFormatter formatter : formatters) {
                            try {
                                date = LocalDate.parse(parts[1].trim(), formatter);
                                break;
                            } catch (DateTimeParseException e) {
                                // Invalid format, try the next one
                            }
                        }

                        if (date == null) {
                            throw new DukeException.InvalidDateException("Reenter date in this format: (ddMMyyyy)");
                        }

                        int count = 0;
                        for (Task task : taskList) {
                            if (task instanceof Deadline) {
                                Deadline deadline = (Deadline) task;
                                if (deadline.getDeadline().toLocalDate().isEqual(date)) {
                                    System.out.println("\t  " + deadline);
                                    count++;
                                }
                            } else if (task instanceof Event) {
                                Event event = (Event) task;
                                if (event.getStartDateTime().toLocalDate().isEqual(date) ||
                                        event.getEndDateTime().toLocalDate().isEqual(date) ||
                                        (event.getStartDateTime().toLocalDate().isBefore(date) &&
                                                event.getEndDateTime().toLocalDate().isAfter(date))) {
                                    System.out.println("\t  " + event);
                                    count++;
                                }
                            }
                        }
                        System.out.println("\tNumber of tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ": " + count);
                        continue;
                    case MARK: // Mark
                        if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
                            throw new DukeException.InvalidCommandException("Command has to be followed by an integer.");
                        }
                        index = Integer.parseInt(parts[1].trim());
                        t = taskList.get(index - 1);
                        t.markAsDone();
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t  " + t);
                        continue;
                    case UNMARK: // Unmark
                        if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
                            throw new DukeException.InvalidCommandException("Command has to be followed by an integer.");
                        }
                        index = Integer.parseInt(parts[1].trim());
                        t = taskList.get(index - 1);
                        t.unmarkAsDone();
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        System.out.println("\t  " + t);
                        continue;
                    case DELETE: // Delete
                        if (parts.length != 2 || parts[1].trim().isEmpty()) { // 2nd arg not entered
                            throw new DukeException.InvalidCommandException("Command has to be followed by an integer.");
                        }
                        index = Integer.parseInt(parts[1].trim());
                        t = taskList.get(index - 1);
                        taskList.remove(index - 1);
                        System.out.println("\tNoted. I've removed this task:");
                        System.out.println("\t  " + t);
                        if (taskList.size() == 1) {
                            System.out.println("\tNow you have " + taskList.size() + " task in the list.");
                            continue;
                        }
                        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
                        continue;
                    case TODO: // Add todo to list
                        if (parts.length != 2 || parts[1].trim().isEmpty()) {
                            throw new DukeException.InvalidCommandException("The description of a todo cannot be empty.");
                        }
                        t = new ToDo(parts[1]);
                        taskList.add(t);
                        break;
                    case DEADLINE: // Add deadline to list
                        String deadlineError = "A deadline has to have 2 non-blank strings separated with a /by keyword.";
                        if (parts.length != 2 || parts[1].trim().isEmpty()) {
                            throw new DukeException.InvalidCommandException(deadlineError);
                        }
                        String[] d_parts = parts[1].split(" /by ", 2);
                        if (d_parts.length < 2 || d_parts[0].trim().isEmpty() || d_parts[1].trim().isEmpty()) {
                            throw new DukeException.InvalidCommandException(deadlineError);
                        }
                        t = new Deadline(d_parts[0], d_parts[1]);
                        taskList.add(t);
                        break;
                    case EVENT: // Add event to list
                        String eventError = "An event has to have 3 non-blank strings separated with /from and /to keywords.";
                        if (parts.length != 2 || parts[1].trim().isEmpty()) {
                            throw new DukeException.InvalidCommandException(eventError);
                        }
                        String[] e_parts = parts[1].split(" /from | /to ", 3);
                        if (e_parts.length < 3 || e_parts[0].trim().isEmpty() || e_parts[1].trim().isEmpty() || e_parts[2].trim().isEmpty()) {
                            throw new DukeException.InvalidCommandException(eventError);
                        }
                        t = new Event(e_parts[0], e_parts[1], e_parts[2]);
                        taskList.add(t);
                        break;
                    default: // Invalid command but should not reach here because valueOf should throw an IllegalArgumentException
                        throw new DukeException.InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                }
                if (command == Command.BYE) {
                    break;
                }
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t  " + t);
                if (taskList.size() == 1) {
                    System.out.println("\tNow you have " + taskList.size() + " task in the list.");
                    continue;
                }
                System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
            } catch (IndexOutOfBoundsException e) { // Index not in array
                try {
                    throw new DukeException.IndexOutOfBoundsException("Please enter a valid index.");
                } catch (DukeException.IndexOutOfBoundsException e2) {
                    System.out.println(e2.getMessage());
                }
            } catch (NumberFormatException e) { // 2nd arg not int
                try {
                    throw new DukeException.InvalidCommandException("Command has to be followed by an integer.");
                } catch (DukeException.InvalidCommandException e2) {
                    System.out.println(e2.getMessage());
                }
            } catch (IllegalArgumentException e) { // Invalid command
                try {
                    throw new DukeException.InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                } catch (DukeException.InvalidCommandException e2) {
                    System.out.println(e2.getMessage());
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
        System.out.println("\tWoof (\u256F\u11BA\u2570\u0E51)"); // Outro
    }
}
