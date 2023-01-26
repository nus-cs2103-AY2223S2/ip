import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;



public class Duke {
    private static final List<Task> taskList = new ArrayList<>();
    private static final String BOT_NAME = "Tyrone";
    private static final String FILE_PATH = "data/duke.txt";

    public static void greet() {
        Duke.say("Sup, my name is " + Duke.BOT_NAME);
    }

    public static void interact() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();

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
                Duke.say(e.toString());
            } catch (IllegalArgumentException e) {
                Duke.say("What are you talkin about man?");
            }
        }

        sc.close();
    }

    public static void say(String s) {
        String opener = "◤━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◥";
        String ender = "◣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━◢";
        System.out.println("\n" + opener + "\n" + s + "\n" + ender + "\n");
    }

    public static void listTasks() throws DukeException {

        if (taskList.size() == 0) {
            throw new DukeException("No tasks available right now. :>");
        }

        StringBuilder stringList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            stringList.append((i + 1) + ". " + task.toString());

            if (i < taskList.size() - 1) {
                stringList.append("\n");
            }
        }

        Duke.say(stringList.toString());
    }

    public static boolean isInvalidTask(String[] command) {
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
            throw new DukeException("You gave me no task.");
        }

        if (isInvalidTask(command)) {
            throw new DukeException("Task given is invalid / unavailable.");
        }
    }

    public static void markTask(String[] command) throws DukeException {
        Duke.checkCommandValidity(command);
        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        t.markAsDone();
        Duke.say("Task masked as done: " + t);
    }

    public static void unmarkTask(String[] command) throws DukeException {
        Duke.checkCommandValidity(command);
        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        t.markAsUndone();
        Duke.say("Task masked as undone: " + t);
    }

    public static void addTask(String[] command, Operation op) throws DukeException {

        if (command.length < 2) {
            throw new DukeException("No task description given.");
        }

        String description = command[1];

        StringBuilder text = new StringBuilder();

        switch (op) {
            case TODO:
                Task todo = new ToDo(description);
                taskList.add(todo);
                text.append("New todo task added: " + todo);
                break;

            case DEADLINE:
                String[] deadlineDescription = description.split("/by", 2);
                Task deadline = new Deadline(deadlineDescription[0], deadlineDescription[1]);
                taskList.add(deadline);
                text.append("New deadline added: " + deadline);
                break;

            case EVENT:
                String[] eventDescription = description.split("/from", 2);

                // Parse the string to get to and from dates of the event
                String[] fromAndTo = eventDescription[1].split("/to", 2);
                String from = fromAndTo[0];
                String to = fromAndTo[1];

                Task event = new Event(eventDescription[0], from, to);
                taskList.add(event);
                text.append("New event added: " + event);
                break;
        }

        text.append("\nYou have " + taskList.size() + " task(s) in the list.");
        Duke.say(text.toString());
    }

    public static void deleteTask(String[] command) throws DukeException {

        Duke.checkCommandValidity(command);

        Task t = taskList.get(Integer.parseInt(command[1]) - 1);
        taskList.remove(t);

        StringBuilder text = new StringBuilder();
        text.append("Task deleted: " + t + "\n");
        text.append("You have " + taskList.size() + " task(s) remained.");
        Duke.say(text.toString());
    }

    public static void shutdown() {
        Duke.say("Aight imma head out");
    }


    /**
     * Loads the record file at startup.
     * If record file is not found, create a new file.
     * If folder where file is contained not found, create a new folder.
     *
     */
    public static void loadFile() {

    }



    public static void main(String[] args) {
        Duke.greet();

        Duke.loadFile();

        Duke.interact();

        Duke.shutdown();
    }

}
