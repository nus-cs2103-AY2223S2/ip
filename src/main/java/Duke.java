import java.util.*;

public class Duke {
    private final static String[] COMMANDS_LIST =
            new String[]{"list", "bye", "todo", "mark", "unmark", "event", "deadline", "delete"};

    enum Commands {
        start, list, bye, todo, mark, unmark, event, deadline, delete
    }

    public static void markTask(ArrayList<Task> taskList, int index) {
        try {
            Task unmarkedTask = taskList.get(index);
            Task markedTask = unmarkedTask.markTask();
            taskList.set(index, markedTask);
        } catch (DukeException err) {
            System.out.println(err.getErrorMessage());
        }
    }

    public static void unmarkTask(ArrayList<Task> taskList, int index) {
        try {
            Task markedTask = taskList.get(index);
            Task unmarkedTask = markedTask.unmarkTask();
            taskList.set(index, unmarkedTask);
        } catch (DukeException err) {
            System.out.println(err.getErrorMessage());
        }
    }

    public static void addTask(ArrayList<Task> taskList, Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it, I've added this task:");
        System.out.println(newTask);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }

    public static void deleteTask(ArrayList<Task> taskList, int index) {
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }

    public static String checkCommand(String command) throws DukeException {
        for (String cmd : COMMANDS_LIST) {
            if (cmd.equals(command)) {
                return command;
            }
        }
        throw new DukeException("I don't know what this command means!");
    }

    public static String getTaskNumber(String[] splitInput) throws DukeException {
        try {
            if (splitInput.length == 1) {
                throw new DukeException("No task number was given!");
            } else {
                return splitInput[1];
            }
        } catch (DukeException err) {
            throw new DukeException("The task number given is not numeric!");
        }
    }

    public static int checkTaskNumber(ArrayList<Task> taskList, String taskNumber) throws DukeException {
        int index = Integer.parseInt(taskNumber) - 1;
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("The task number given does not exist!");
        } else {
            return index;
        }
    }

    public static void checkDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("There was no task description given!");
        }
    }

    public static int checkDeadline(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("There was no deadline given!");
        } else {
            return byIndex;
        }
    }

    public static int checkStarting(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/from");
        if (byIndex == -1) {
            throw new DukeException("Please indicate a starting period!");
        } else {
            return byIndex;
        }
    }

    public static int checkEnding(String[] splitInput) throws DukeException {
        int byIndex = Arrays.asList(splitInput).indexOf("/to");
        if (byIndex == -1) {
            throw new DukeException("Please indicate an ending period!");
        } else {
            return byIndex;
        }
    }

    public static void checkTimestamp(String timestamp) throws DukeException {
        if (timestamp.equals("")) {
            throw new DukeException("There was no time period given!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner (System.in);
        String input;
        Commands command = Commands.start;
        ArrayList<Task> taskList = new ArrayList<>();
        while (!command.equals(Commands.bye)) {
            // split command into each word
            input = sc.nextLine();
            String[] splitInput = input.split(" ");
            String cmd = splitInput[0];
            try {
                command = Commands.valueOf(checkCommand(cmd));
                String taskNumber;
                int index;
                String description;
                Task newTask;
                switch (command) {
                    case delete:
                        try {
                            // taskNumber in 1-indexing
                            taskNumber = getTaskNumber(splitInput);
                            // index in 0-indexing
                            index = checkTaskNumber(taskList, taskNumber);
                            deleteTask(taskList, index);
                        } catch (DukeException err) {
                            System.out.println(err.getErrorMessage());
                        }
                        break;
                    case list:
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            Task task = taskList.get(i);
                            System.out.printf("%d.%s%n", i + 1, task);
                        }
                        break;
                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case mark:
                        try {
                            // taskNumber in 1-indexing
                            taskNumber = getTaskNumber(splitInput);
                            // index in 0-indexing
                            index = checkTaskNumber(taskList, taskNumber);
                            markTask(taskList, index);
                        } catch (DukeException err) {
                            System.out.println(err.getErrorMessage());
                        }
                        break;
                    case unmark:
                        try {
                            taskNumber = getTaskNumber(splitInput);
                            index = checkTaskNumber(taskList, taskNumber);
                            unmarkTask(taskList, index);
                        } catch (DukeException err) {
                            System.out.println(err.getErrorMessage());
                        }
                        break;
                    case todo:
                        try {
                            description = String.join(" ",
                                    Arrays.copyOfRange(splitInput, 1, splitInput.length));
                            checkDescription(description);
                            newTask = new Todo(description);
                            addTask(taskList, newTask);
                        } catch (DukeException err) {
                            System.out.println(err.getErrorMessage());
                        }
                        break;
                    case deadline:
                        try {
                            int byIndex = checkDeadline(splitInput);
                            description = String.join(" ", Arrays.copyOfRange(splitInput, 1, byIndex));
                            checkDescription(description);
                            String deadline = String.join(" ", Arrays.copyOfRange(splitInput,
                                    byIndex + 1, splitInput.length));
                            checkTimestamp(deadline);
                            newTask = new Deadline(description, deadline);
                            addTask(taskList, newTask);
                        } catch (DukeException err) {
                            System.out.println(err.getErrorMessage());
                        }
                        break;
                    case event:
                        try {
                            int fromIndex = checkStarting(splitInput);
                            int toIndex = checkEnding(splitInput);
                            description = String.join(" ", Arrays.copyOfRange(splitInput, 1, fromIndex));
                            checkDescription(description);
                            String from = String.join(" ",
                                    Arrays.copyOfRange(splitInput, fromIndex + 1, toIndex));
                            checkTimestamp(from);
                            String to = String.join(" ",
                                    Arrays.copyOfRange(splitInput, toIndex + 1, splitInput.length));
                            checkTimestamp(to);
                            newTask = new Event(description, from, to);
                            addTask(taskList, newTask);
                        } catch (DukeException err) {
                            System.out.println(err.getErrorMessage());
                        }
                        break;
                }
            } catch(DukeException err){
                System.out.println(err.getErrorMessage());
            }
        }
    }
}
