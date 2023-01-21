import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

public class Duke {

    private static final List<Task> taskList = new ArrayList<>();
    private static final String botName = "Tyrone";

    public static void print(String s) {
        System.out.println(s);
    }

    public static void listTasks() throws DukeException {

        if (taskList.size() == 0) {
            throw new DukeException("    > Aint no tasks available rn homie");
        }

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            Duke.print("    > " + (i + 1) + ". " + task.toString());
        }
    }

    public static boolean isValidTask(String[] command) {
        String idx = command[1];

        // False if idx is not an integer or idx is out of bounds
        return (!idx.matches("-?\\d+")
                || Integer.parseInt(idx) < 1
                || Integer.parseInt(idx) > taskList.size());
    }

    public static boolean isNoTaskGiven(String[] command) {
        return command.length < 2;
    }

    public static void checkCommandValidity(String[] command) throws DukeException {
        if (isNoTaskGiven(command)) {
            throw new DukeException("    > chu gave me no task");
        }

        if (!isValidTask(command)) {
            throw new DukeException("    > aint no such task available");
        }
    }

    public static void markTask(String[] command) throws DukeException {
        Duke.checkCommandValidity(command);
        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        t.markAsDone();
        Duke.print("    > Task masked as done: " + t);
    }

    public static void unmarkTask(String[] command) throws DukeException {
        Duke.checkCommandValidity(command);
        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        t.markAsUndone();
        Duke.print("    > Task masked as undone: " + t);
    }

    public static void addTask(String[] command, Operation op) throws DukeException {

        if (command.length < 2) {
            throw new DukeException("    > No task description given");
        }

        String description = command[1];

        switch (op) {
            case TODO:
                Task todo = new ToDo(description);
                taskList.add(todo);
                Duke.print("    > Got chu homie, new todo task added: " + todo);
                break;

            case DEADLINE:
                String[] deadlineDescription = description.split("/by", 2);
                Task deadline = new Deadline(deadlineDescription[0], deadlineDescription[1]);
                taskList.add(deadline);
                Duke.print("    > Got chu homie, new deadline added: " + deadline);
                break;

            case EVENT:
                String[] eventDescription = description.split("/from", 2);

                // Parse the string to get to and from dates of the event
                String[] fromAndTo = eventDescription[1].split("/to", 2);
                String from = fromAndTo[0];
                String to = fromAndTo[1];

                Task event = new Event(eventDescription[0], from, to);
                taskList.add(event);
                Duke.print("    > Got chu homie, new event added: " + event);
                break;
        }

        Duke.print("    > Chu have " + taskList.size() + " task(s) in the list.");

    }

    public static void deleteTask(String[] command) throws DukeException {

        if (isNoTaskGiven(command)) {
            throw new DukeException("    > chu gave me no task");
        }

        if (isValidTask(command)) {
            throw new DukeException("    > aint no such task available");
        }

        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        taskList.remove(t);

        Duke.print("    > Task deleted: " + t);
        Duke.print("    > Chu have " + taskList.size() + " task(s) remained.");
    }

    public static void shutdown() {
        Duke.print("    > Aight imma head out");
    }

    public static void greet() {
        Duke.print("    > What's up dawg");
    }

    public static void interact() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                input = sc.nextLine();

                // If input is "bye", terminate loop
                if (input.equals("bye")) {
                    break;
                }

                // Split strings into 2, first part is the operation, 2nd part is the description
                String[] command = input.split(" ", 2);
                Operation op = Operation.valueOf(command[0].toUpperCase());

                switch (op) {
                    case MARK:
                        Duke.markTask(command);
                        break;
                    case UNMARK:
                        Duke.unmarkTask(command);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        Duke.addTask(command, op);
                        break;
                    case LIST:
                        Duke.listTasks();
                        break;
                    case DELETE:
                        Duke.deleteTask(command);
                }

            } catch (DukeException e) {
                Duke.print(e.toString());
            } catch (IllegalArgumentException e) {
                Duke.print("    > What r chu talkin about man");
            }
        }
    }

    public static void main(String[] args) {
        Duke.greet();

        Duke.interact();

        Duke.shutdown();
    }

}
