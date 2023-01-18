import java.util.*;

public class Duke {
    private final static String[] COMMANDS = new String[]{"list", "bye", "todo", "mark", "unmark", "event", "deadline"};

    public static void markTask(Task[] taskList, int index) {
        Task unmarkedTask = taskList[index];
        Task markedTask = unmarkedTask.markTask();
        taskList[index] = markedTask;
    }


    public static void unmarkTask(Task[] taskList, int index) {
        Task markedTask = taskList[index];
        Task unmarkedTask = markedTask.unmarkTask();
        taskList[index] = unmarkedTask;
    }

    public static void addTask(Task[] taskList, Task newTask, int taskCount) {
        taskList[taskCount] = newTask;
        taskCount++;
        System.out.println("Got it, I've added this task:");
        System.out.println(newTask);
        System.out.println(String.format("Now you have %d tasks in the list.", taskCount));
    }

    public static void checkCommand(String command) throws UnknownCommandException {
        for (String cmd : COMMANDS) {
            if (cmd.equals(command)) {
                return;
            }
        }
        throw new UnknownCommandException();
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
        Task[] taskList = new Task[100];
        int taskCount = 0;
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
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            Task task = taskList[i];
                            System.out.println(String.format("%d.%s", i + 1, task));
                        }
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case "mark":
                        // taskNumber in 1-indexing
                        taskNumber = splitInput[1];
                        // index in 0-indexing
                        index = Integer.parseInt(taskNumber) - 1;
                        markTask(taskList, index);
                        break;
                    case "unmark":
                        taskNumber = splitInput[1];
                        index = Integer.parseInt(taskNumber) - 1;
                        unmarkTask(taskList, index);
                        break;
                    case "todo":
                        try {
                            description = String.join(" ",
                                    Arrays.copyOfRange(splitInput, 1, splitInput.length));
                            checkDescription(description);
                            newTask = new Todo(description);
                            addTask(taskList, newTask, taskCount);
                            taskCount++;
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
                            addTask(taskList, newTask, taskCount);
                            taskCount++;
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
                            addTask(taskList, newTask, taskCount);
                            taskCount++;
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
