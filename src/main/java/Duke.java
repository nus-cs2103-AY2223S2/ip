import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

/*
Level-1
idea:
- design a "reply" method to format the duke reply text
- print hello statement
- scanf input
    - print indented horizontal line
    - print input
    - print indented horizontal line

Level-2
idea:
- addTask method to add to static array of strings
- formatTaskList to take task array and output string
    in list form
- if list command, reply(formatTaskList(taskArray))
 */
public class Duke {
    private TaskList taskList = new TaskList();
    private Parser parser = new Parser();
    private Task task;

    private Task parseTaskString(String taskString) {
        String taskType;
        String isDone;
        String taskName;
        String by;
        String from;
        String to;
        Task task;
        String[] parsedTask;

        parsedTask = taskString.split("\\|");
        taskType = parsedTask[0];
        isDone = parsedTask[1];
        taskName = parsedTask[2];
        if (taskType.equals("T")) {
            task = new Todo(taskName);
        } else if (taskType.equals("D")) {
            by = parsedTask[3];
            task = new Deadline(taskName, by);
        } else {
            from = parsedTask[3];
            to = parsedTask[4];
            task = new Event(taskName, from, to);
        }

        if (Boolean.valueOf(isDone)) {
            task.mark();
        }

        return task;
    }

    private void saveTasks() throws IOException {
        FileWriter taskFileWriter = new FileWriter("C:\\Users\\jedng\\Documents\\CS2103T\\ip\\ip\\data\\tasks.txt");
        try (BufferedWriter bw = new BufferedWriter(taskFileWriter)) {
            for (int i = 0; i < taskList.getListSize(); i++) {
                task = taskList.getTask(i);
                bw.write(task.getParsedTaskDataString());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    private TaskList loadTasks() {
        Task parsedTask;
        String taskString;
        File dataDirectory = new File("..\\..\\..\\data");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        File taskFilePath = new File("C:\\Users\\jedng\\Documents\\CS2103T\\ip\\ip\\data\\tasks.txt");
        try {
            if (!taskFilePath.exists()) {
                taskFilePath.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(taskFilePath));
            while ((taskString = br.readLine()) != null) {
                parsedTask = parseTaskString(taskString);
                taskList.addTask(parsedTask);
            }
        } catch (IOException e) {
            //idea: if no file, create directory AND file
            System.out.println("An error occurred while creating the file");
            e.printStackTrace();
        }
        return taskList;
    }

    private void reply(String s) {
        System.out.println("\t"
                + "____________________________________________________________");
        System.out.println("\t" + s.replace("\n", "\n    "));
        System.out.println("\t"
                + "____________________________________________________________");
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        reply("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Takes string and adds to static array of strings.
     *
     */

    private void printExitMessage() {
        reply("Bye! Hope to see you again soon!");
    }

    private String reformatDate(String unformattedDate) {
        LocalDate deadlineDateObj;
        DateTimeFormatter dtf;
        String formattedDeadline;

        deadlineDateObj = LocalDate.parse(unformattedDate);
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formattedDeadline = deadlineDateObj.format(dtf);
        return formattedDeadline;
    }

    private String[] parseDeadline(String command) throws ZeroLengthDescriptionException {
        String[] split_command = command.split("/");
        String by = split_command[1];
        //need to split task type and name
        String[] taskAndName = split_command[0].split(" ");
        checkCommandLength(taskAndName);
        String[] taskNameSplit = Arrays.copyOfRange(taskAndName, 1, taskAndName.length);
        String taskName = String.join(" ", taskNameSplit);

        String unformattedDeadline = by.split(" ")[1];
        String formattedDeadline = reformatDate(unformattedDeadline);

        String[] parsedCommand = new String[] {taskAndName[0], taskName, formattedDeadline};
        return parsedCommand;
    }

    private String[] parseEvent(String command) throws ZeroLengthDescriptionException {
        String[] split_command = command.split("/");
        String from = split_command[1];
        String to = split_command[2];
        //format from and to
        String unformattedFrom = from.split(" ")[1];
        String unformattedTo = to.split(" ")[1];
        String formattedFrom = reformatDate(unformattedFrom);
        String formattedTo = reformatDate(unformattedTo);

        //need to split task type and name
        String[] taskAndName = split_command[0].split(" ");
        checkCommandLength(taskAndName);
        String[] taskNameSplit = Arrays.copyOfRange(taskAndName, 1, taskAndName.length);
        String taskName = String.join(" ", taskNameSplit);
        String[] parsedCommand = new String[] {taskAndName[0], taskName, formattedFrom, formattedTo};
        return parsedCommand;
    }

    private String[] parseCommand(String command) throws ZeroLengthDescriptionException {
        String[] splitCommand = command.split(" ");
        String[] parsedCommand;
        switch (splitCommand[0]) {
        case "todo":
            parsedCommand = parseTodo(command);
            break;
        case "deadline":
            parsedCommand = parseDeadline(command);
            break;
        case "event":
            parsedCommand = parseEvent(command);
            break;
        default:
            return splitCommand;
        }
        return parsedCommand;
    }

    private void checkCommandLength(String[] strArray) throws ZeroLengthDescriptionException {
        if (strArray.length <= 1) {
            throw new ZeroLengthDescriptionException();
        }
    }

    private String formatAddTaskReply(TaskList taskList, Task task) {
        String formattedReply;
        formattedReply = String.format(
                "Got it. I've added this task:\n\t%s\n" +
                        "Now you have %d task(s) in the list.",
                task.toString(),
                taskList.getListSize());
        return formattedReply;
    }

    public void runDuke() throws IOException {
        Scanner sc = new Scanner(System.in);
        String[] parsedCommand;
        String formattedReply;
        int taskIndex;
        taskList = loadTasks();

        greet();
        while (true) {
            String userInput = sc.nextLine().toLowerCase();
            try {
                parsedCommand = parser.parseCommand(userInput);
                switch (parsedCommand[0]) {
                case "todo":
                    //checkCommandLength(parsedCommand);
                    Task newTodo = new Todo(parsedCommand[1]);
                    taskList.addTask(newTodo);
                    formattedReply = formatAddTaskReply(taskList, newTodo);
                    reply(formattedReply);
                    break;
                case "deadline":
                    //checkCommandLength(parsedCommand);
                    Task newDeadline = new Deadline(parsedCommand[1], parsedCommand[2]);
                    taskList.addTask(newDeadline);
                    formattedReply = formatAddTaskReply(taskList, newDeadline);
                    reply(formattedReply);
                    break;
                case "event":
                    //checkCommandLength(parsedCommand);
                    Task newEvent = new Event(parsedCommand[1], parsedCommand[2], parsedCommand[3]);
                    taskList.addTask(newEvent);
                    formattedReply = formatAddTaskReply(taskList, newEvent);
                    reply(formattedReply);
                    break;
                case "list":
                    formattedReply = String.format(
                            "Here are the tasks in your list:\n%s", taskList.getTaskList());
                    reply(formattedReply);
                    break;
                case "delete":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.delTask(taskIndex);
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.markTask(taskIndex - 1);
                    formattedReply = String.format(
                            "Nice! I've marked this task as done:\n%s", taskList.getTask(taskIndex - 1));
                    reply(formattedReply);
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.unmarkTask(taskIndex - 1);
                    formattedReply = String.format(
                            "OK, I've marked this task as not done yet:\n%s", taskList.getTask(taskIndex - 1));
                    reply(formattedReply);
                    break;
                case "bye":
                    printExitMessage();
                    break;
                default:
                    throw new InvalidCommandException("Sorry, I don't understand that command, try again.");
                }
            } catch (InvalidCommandException e) {
                reply(e.getMessage());
                continue;
            }
            if (userInput.equals("bye")) {
                saveTasks();
                break;
            }
        }
    }
}
