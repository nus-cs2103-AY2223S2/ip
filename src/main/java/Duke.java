import java.util.*;

public class Duke {
    private final static String[] COMMANDS =
            new String[]{"list", "bye", "todo", "mark", "unmark", "event", "deadline", "delete"};

    public static void markTask(ArrayList<Task> taskList, int index) {
        try {
            Task unmarkedTask = taskList.get(index);
            Task markedTask = unmarkedTask.markTask();
            taskList.set(index, markedTask);
        } catch (MarkingException err) {
            System.out.println(err);
        }
    }

    public static void unmarkTask(ArrayList<Task> taskList, int index) {
        try {
            Task markedTask = taskList.get(index);
            Task unmarkedTask = markedTask.unmarkTask();
            taskList.set(index, unmarkedTask);
        } catch (UnmarkingException err) {
            System.out.println(err);
        }
    }

    public static void addTask(ArrayList<Task> taskList, Task newTask) {
        taskList.add(newTask);
        System.out.println("Got it, I've added this task:");
        System.out.println(newTask);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static void deleteTask(ArrayList<Task> taskList, int index) {
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static void checkCommand(String command) throws UnknownCommandException {
        for (String cmd : COMMANDS) {
            if (cmd.equals(command)) {
                return;
            }
        }
        throw new UnknownCommandException();
    }

    public static String getTaskNumber(String[] splitInput) throws InvalidTypeException, EmptyTaskNumberException {
        try {
            if (splitInput.length == 1) {
                throw new EmptyTaskNumberException();
            } else {
                String taskNumber = splitInput[1];
                int taskNumberInt = Integer.parseInt(taskNumber);
                return taskNumber;
            }
        } catch (NumberFormatException err) {
            throw new InvalidTypeException();
        }
    }

    public static int checkTaskNumber(ArrayList<Task> taskList, String taskNumber) throws InvalidTaskNumberException {
        int index = Integer.parseInt(taskNumber) - 1;
        if (index >= taskList.size() || index < 0) {
            throw new InvalidTaskNumberException();
        } else {
            return index;
        }
    }

    public static void checkDescription(String description) throws EmptyDescException {
        if (description.equals("")) {
            throw new EmptyDescException();
        }
    }

    public static int checkDeadline(String[] splitInput) throws NoDeadlineException {
        int byIndex = Arrays.asList(splitInput).indexOf("/by");
        if (byIndex == -1) {
            throw new NoDeadlineException();
        } else {
            return byIndex;
        }
    }

    public static int checkStarting(String[] splitInput) throws NoStartingException {
        int byIndex = Arrays.asList(splitInput).indexOf("/from");
        if (byIndex == -1) {
            throw new NoStartingException();
        } else {
            return byIndex;
        }
    }

    public static int checkEnding(String[] splitInput) throws NoEndingException {
        int byIndex = Arrays.asList(splitInput).indexOf("/to");
        if (byIndex == -1) {
            throw new NoEndingException();
        } else {
            return byIndex;
        }
    }

    public static void checkTimestamp(String timestamp) throws InvalidTimeException {
        if (timestamp.equals("")) {
            throw new InvalidTimeException();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner (System.in);
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();
        while (!input.equals("bye")) {
            // split command into each word
            input = sc.nextLine();
            String[] splitInput = input.split(" ");
            String command = splitInput[0];
            try {
                checkCommand(command);
                String taskNumber;
                int index;
                String description;
                Task newTask;
                switch (command) {
                    case "delete":
                        try {
                            // taskNumber in 1-indexing
                            taskNumber = getTaskNumber(splitInput);
                            // index in 0-indexing
                            index = checkTaskNumber(taskList, taskNumber);
                            deleteTask(taskList, index);
                        } catch (EmptyTaskNumberException err) {
                            System.out.println(err);
                        } catch (InvalidTypeException err) {
                            System.out.println(err);
                        } catch (InvalidTaskNumberException err) {
                            System.out.println(err);
                        }
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            Task task = taskList.get(i);
                            System.out.println(String.format("%d.%s", i + 1, task));
                        }
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "mark":
                        try {
                            // taskNumber in 1-indexing
                            taskNumber = getTaskNumber(splitInput);
                            // index in 0-indexing
                            index = checkTaskNumber(taskList, taskNumber);
                            markTask(taskList, index);
                        } catch (EmptyTaskNumberException err) {
                            System.out.println(err);
                        } catch (InvalidTypeException err) {
                            System.out.println(err);
                        } catch (InvalidTaskNumberException err) {
                            System.out.println(err);
                        }
                        break;
                    case "unmark":
                        try {
                            taskNumber = getTaskNumber(splitInput);
                            index = checkTaskNumber(taskList, taskNumber);
                            unmarkTask(taskList, index);
                        } catch (EmptyTaskNumberException err) {
                            System.out.println(err);
                        } catch (InvalidTypeException err) {
                            System.out.println(err);
                        } catch (InvalidTaskNumberException err) {
                            System.out.println(err);
                        }
                        break;
                    case "todo":
                        try {
                            description = String.join(" ",
                                    Arrays.copyOfRange(splitInput, 1, splitInput.length));
                            checkDescription(description);
                            newTask = new Todo(description);
                            addTask(taskList, newTask);
                        } catch (EmptyDescException err) {
                            System.out.println(err);
                        }
                        break;
                    case "deadline":
                        try {
                            int byIndex = checkDeadline(splitInput);
                            description = String.join(" ", Arrays.copyOfRange(splitInput, 1, byIndex));
                            checkDescription(description);
                            String deadline = String.join(" ", Arrays.copyOfRange(splitInput,
                                    byIndex + 1, splitInput.length));
                            checkTimestamp(deadline);
                            newTask = new Deadline(description, deadline);
                            addTask(taskList, newTask);
                        } catch (NoDeadlineException err) {
                            System.out.println(err);
                        } catch (EmptyDescException err) {
                            System.out.println(err);
                        } catch (InvalidTimeException err) {
                            System.out.println(err);
                        }
                        break;
                    case "event":
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
                        } catch (NoStartingException err) {
                            System.out.println(err);
                        } catch (NoEndingException err) {
                            System.out.println(err);
                        } catch (EmptyDescException err) {
                            System.out.println(err);
                        } catch (InvalidTimeException err) {
                            System.out.println(err);
                        }
                        break;
                }
            } catch(UnknownCommandException err){
                System.out.println(err);
            }
        }
    }
}
