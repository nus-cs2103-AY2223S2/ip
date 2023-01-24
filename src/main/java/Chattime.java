import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Chattime {

    private static final String greet = "Hey! I'm your friend, Chattime!  (•◡•) /\n"
            + "     How can I help you *^*";
    private static final String line = "---------------------------------------------------------------------" +
            "******************CHATTIME";
    private static final String goodBye = "Bye bye >^<! Visit me again when you need me ~";
    private static final String WAITING_TASK = "Task(s) waiting to be completed:";
    private static final String UNRECOGNISED_COMMAND = "Sorry... but I don't understand what you said >,<";
    private static final String LIST_EXP = "OOPS!!! list does not take any description.";
    private static final String NO_DESCRIPTION = "OOPS!!! The description of %s cannot be empty.";
    private static final String NO_INDEX = "OOPS!!! The index to %1$s cannot be empty.";
    private static final String NEED_INT = "OOPS!!! The index to %1$s must be positive integer.";
    private static final String IDX_OUT_OF_BOUND = "OOPS!!! The index is too large! We currently have %d task(s).";
    private static final String MISSED_PARAM = "OOPS!!! %s should be in form of %s.";
    private static final ArrayList<Task> storeList = new ArrayList<>();
    private static File storeFile = null;

    public static void main(String[] args) {
        String logo = "      ___\n"
                    + "     /*  \\    \\(˘◡˘)/\n"
                    + "    /::\\  \\     ___\n"
                    + "   /:/::\\  \\   /*  \\\n"
                    + "  /:/  \\:\\  \\  \\:\\  \\\n"
                    + " /:/__/ \\:\\__\\  \\:\\  \\\n"
                    + " \\:\\ \\   \\/__/  /::\\  \\\n"
                    + "  \\:\\ \\        /:/::\\__\\\n"
                    + "   \\:\\ \\*H*A*T/:/  \\/__/*I*M*E\n"
                    + "    \\:\\_\\    /:/  /\n"
                    + "     \\/__/   \\/__/\n";

        System.out.println("Hello from\n" + logo);

        openFile();
        loadData();

        replyUser(greet);

        Scanner sc = new Scanner(System.in);

        try {
            chat(sc);
        } catch (ChattimeException e) {
            replyUser(e.getMessage());
        }

    }

    public static void replyUser(String message) {
        System.out.println("    " + line);
        System.out.println("     " + message);
        System.out.println("    " + line);
    }

    public static void chat(Scanner sc) throws ChattimeException {
        String userInput = sc.nextLine();
        String[] splitCmd = userInput.split(" ", 2);
        String command = splitCmd[0];

        while (!command.equals("bye")) {
            try {
                switch (command) {
                    case "list":
                        if (splitCmd.length == 1) {
                            displayList();
                        } else {
                            throw new ChattimeException(LIST_EXP);
                        }
                        break;

                    case "todo":
                    case "deadline":
                    case "event":
                    case "listTime":
                        if (splitCmd.length < 2) {
                            throw new ChattimeException(String.format(NO_DESCRIPTION, command));
                        } else {
                            switch (command) {
                                case "todo":
                                    todo(splitCmd[1]);
                                    break;
                                case "deadline":
                                    deadline(splitCmd[1]);
                                    break;
                                case "event":
                                    event(splitCmd[1]);
                                    break;
                                case "listTime":
                                    listTime(splitCmd[1]);
                                    break;
                            }
                        }
                        break;

                    case "mark":
                    case "unmark":
                    case "delete":
                        if (splitCmd.length < 2) {
                            throw new ChattimeException(String.format(NO_INDEX, command));
                        } else if (command.equals("mark")) {
                            mark(splitCmd[1]);
                        } else if (command.equals("unmark")) {
                            unmark(splitCmd[1]);
                        } else {
                            delete(splitCmd[1]);
                        }
                        break;

                    default:
                        throw new ChattimeException(UNRECOGNISED_COMMAND);
                }
            } catch(ChattimeException e) {
                replyUser(e.getMessage());
            }

            userInput = sc.nextLine();
            splitCmd = userInput.split(" ", 2);
            command = splitCmd[0];

        }

        exit();
    }

    public static void displayList() {
        int i = 1;
        String message = WAITING_TASK;

        for (Task task : storeList) {
            message = message.concat(String.format("\n     %d. %s", i, task));
            i++;
        }

        replyUser(message);
    }

    public static void addTask(Task newTask) {
        storeList.add(newTask);
        saveToFile(newTask);
        newTask.printAddTask();
    }

    public static int checkInt(String content, String command) throws ChattimeException {
        if (Pattern.matches("^[0-9]*$", content)) {
            int index = Integer.parseInt(content);
            if (index > Task.getCount()) {
                throw new ChattimeException(String.format(IDX_OUT_OF_BOUND, Task.getCount()));
            } else {
                return index;
            }
        } else {
            throw new ChattimeException(String.format(NEED_INT, command));
        }
    }

    public static void mark(String context) {
        int index;
        try {
            index = checkInt(context, "mark");
            Task target = storeList.get(index - 1);
            target.markAsDone();
            target.doneMessage();
            updateFile(index, storeList.get(index - 1));
        } catch (ChattimeException e) {
            replyUser(e.getMessage());
        }
    }

    public static void unmark(String context) {
        int index;
        try {
            index = checkInt(context, "unmark");
            Task target = storeList.get(index - 1);
            target.unmarkDone();
            target.notDoneMessage();
            updateFile(index, storeList.get(index - 1));
        } catch (ChattimeException e) {
            replyUser(e.getMessage());
        }
    }

    public static void delete(String context) {
        int index;
        try {
            index = checkInt(context, "delete");
            storeList.get(index - 1).removeTask();
            storeList.remove(index - 1);
            deleteFromFile(index);
        } catch (ChattimeException e) {
            replyUser(e.getMessage());
        }
    }

    public static void todo(String task) {
        Todo todoTask = new Todo(task);
        addTask(todoTask);
    }

    public static void deadline(String content) throws ChattimeException {
        String[] splitBy = content.split(" /by ", 2);
        if (splitBy.length < 2 || splitBy[1].equals("")) {
            throw new ChattimeException(String.format(MISSED_PARAM, "deadline", "deadline (task) /by (describe)"));
        }
        String task = splitBy[0];
        Deadline deadlineTask;
        try {
            String[] time = splitBy[1].split(" ", 2);
            LocalDate byDate = LocalDate.parse(time[0]);
            if (time.length == 1) {
                deadlineTask = new Deadline(task, byDate, null);
            } else {
                LocalTime byTime = LocalTime.parse(time[1]);
                deadlineTask = new Deadline(task, byDate, byTime);
            }

            addTask(deadlineTask);
        } catch (DateTimeParseException e) {
            System.err.println("OOPS!!! Please enter date and time in format yyyy-mm-dd or yyyy-mm-dd hh:mm");
        }
    }

    public static void event(String content) throws ChattimeException {
        String[] splitTask = content.split(" /from ", 2);
        String task = splitTask[0];

        if (splitTask.length < 2 || splitTask[1].equals("")) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, "event", "event (task) /from (describe 1) /to (describe 2)"));
        }
        String[] splitFrom = splitTask[1].split(" /to ", 2);

        if (splitFrom.length < 2 || splitFrom[1].equals("")) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, "event", "event (task) /from (describe 1) /to (describe 2)"));
        }
        try {
            String[] from = splitFrom[0].split(" ", 2);
            String[] to = splitFrom[1].split(" ", 2);
            LocalDate fromDate = LocalDate.parse(from[0]);
            LocalTime fromTime = LocalTime.parse(from[1]);
            LocalDate toDate = LocalDate.parse(to[0]);
            LocalTime toTime = LocalTime.parse(to[1]);

            Event eventTask = new Event(task, fromDate, fromTime, toDate, toTime);
            addTask(eventTask);
        } catch (DateTimeParseException e) {
            System.err.println("OOPS!!! Please enter both date and time in format yyyy-mm-dd hh:mm");
        }
    }

    public static void listTime(String content) {
        try {
            LocalDate date = LocalDate.parse(content);
            int i = 1, total = 0, pending = 0;
            String message = "I've sorted the task(s) that have deadlines / take place on " +
                    date.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) + "for you:";

            for (Task task : storeList) {
                if (task.onDate(date)) {
                    message = message.concat(String.format("\n     %d. %s", i, task));
                    i++;
                    total++;
                    if (!task.getTaskStatus()) {
                        pending++;
                    }
                }
            }

            message += "\n     You have " + total + " task(s) on this day. With " + pending + " task(s) to go.";

            replyUser(message);
        } catch (DateTimeParseException e) {
            System.err.println("OOPS!!! Please enter date and time in format yyyy-mm-dd hhmm");
        }
    }

    public static void exit() {
        replyUser(goodBye);
    }
    
    public static void openFile() {
        try {
            storeFile = new File("data/chattimeTask.txt");
            if (!storeFile.exists()) {
                if (!storeFile.getParentFile().exists()) {
                    if(!storeFile.getParentFile().mkdirs()) {
                        throw new IOException("New directory cannot be created!");
                    }
                }
                if(!storeFile.createNewFile()) {
                    throw new IOException("New file cannot be created!");
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void loadData() {
        BufferedReader loader;
        try {
            loader = new BufferedReader(new FileReader(storeFile));
            String task = loader.readLine();

            while (task != null) {
                String[] taskSplit = task.split(" @ ", 7);
                Task inputTask = null;
                switch (taskSplit[0]) {
                case "T":
                    inputTask = new Todo(taskSplit[2]);
                    break;
                case "D":
                    try {
                        LocalDate byDate = LocalDate.parse(taskSplit[3]);
                        LocalTime byTime = (taskSplit[4].equals("0") ? null : LocalTime.parse(taskSplit[4]));
                        inputTask = new Deadline(taskSplit[2], byDate, byTime);
                    } catch (DateTimeParseException e) {
                        System.err.println("OOPS!!! Datetime error in storage!");
                    }
                    break;
                case "E":
                    try {
                        LocalDate fromDate = LocalDate.parse(taskSplit[3]);
                        LocalTime fromTime = LocalTime.parse(taskSplit[4]);
                        LocalDate toDate = LocalDate.parse(taskSplit[5]);
                        LocalTime toTime = LocalTime.parse(taskSplit[6]);

                        inputTask = new Event(taskSplit[2], fromDate, fromTime, toDate, toTime);
                    } catch (DateTimeParseException e) {
                        System.err.println("OOPS!!! Datetime error in storage!");
                    }
                    break;
                default:
                    throw new ChattimeException("Task type error : " + taskSplit[0]);
                }
                if (inputTask != null && taskSplit[1].equals("1")) {
                    inputTask.markAsDone();
                }
                storeList.add(inputTask);
                task = loader.readLine();
            }
        } catch (IOException | ChattimeException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void saveToFile(Task task) {
        String writeString = task.toDataString();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(storeFile, true));
            writer.write(writeString + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void updateFile(int index, Task task) {
        BufferedReader lineSearch;
        try {
            lineSearch = new BufferedReader(new FileReader(storeFile));
            String content = lineSearch.readLine();
            int lineCount = 1;
            StringBuilder updateString = new StringBuilder();

            while (content != null) {
                if (lineCount == index) {
                    content = task.toDataString();
                }
                updateString.append(content).append(System.lineSeparator());
                lineCount++;
                content = lineSearch.readLine();
            }
            if (lineCount < index) {
                throw new IndexOutOfBoundsException("Task not saved in storage!");
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(storeFile));
                writer.write(updateString.toString());
                writer.close();
            }
        } catch (IOException | IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteFromFile(int index) {
        BufferedReader lineSearch;
        try {
            lineSearch = new BufferedReader(new FileReader(storeFile));
            String content = lineSearch.readLine();
            int lineCount = 1;
            StringBuilder updateString = new StringBuilder();

            while (content != null) {
                if (lineCount == index) {
                    lineCount++;
                    content = lineSearch.readLine();
                    continue;
                }
                updateString.append(content).append(System.lineSeparator());
                lineCount++;
                content = lineSearch.readLine();
            }
            if (lineCount < index) {
                throw new IndexOutOfBoundsException("Task not saved in storage!");
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(storeFile));
                writer.write(updateString.toString());
                writer.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /* Unused code
    public static void store(String item) {
        Task task = new Task(item);
        storeList.add(task);
        replyUser("added: " + task.description);
    }

    public static void echo(String userInput) {
        replyUser(userInput);
    }
     */

}
