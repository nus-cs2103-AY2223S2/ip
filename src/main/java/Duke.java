import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;

public class Duke {

    public enum Command {
        bye, list, mark, unmark, todo, deadline, event, delete, err
    }

    private static File taskList  = retrieveTaskList();

    private static FileWriter taskWriter = createTaskWriter();

    private static int numberOfTasks = 0;

    public static void main(String[] args) {

        //open scanner to accept user input
        Scanner userInput = new Scanner(System.in);

        Scanner numberOfLinesReader = createTaskReader();
        while (numberOfLinesReader.hasNextLine()) {
            numberOfTasks ++;
            numberOfLinesReader.nextLine();
        }
        numberOfLinesReader.close();

        //greet user
        System.out.println(makeOutput(" Hello! I'm Duke\n" + " What can I do for you?"));

        while (true) {
            String newInput = userInput.nextLine();
            String[] parsedInput = newInput.split(" ");
            Command command;

            try {
                command = Command.valueOf(parsedInput[0]);
            } catch (IllegalArgumentException e) {
                command = Command.err;
            }

            try {
                switch (command) {
                    case bye:
                        userInput.close();
                        exit();
                        break;
                    case list:
                        showList();
                        break;
                    case mark:
                        markTask(processMarkUnmarkDel(newInput));
                        break;
                    case unmark:
                        unmarkTask(processMarkUnmarkDel(newInput));
                        break;
                    case todo:
                        addTodo(newInput.split("todo", 2)[1]);
                        break;
                    case deadline:
                        processDeadline(newInput);
                        break;
                    case event:
                        processEvent(newInput);
                        break;
                    case delete:
                        delete(processMarkUnmarkDel(newInput));
                        break;
                    case err:
                        handleUnknownCommand();
                }
            } catch (Exception d) {
                System.out.println(makeOutput("☹ OOPS!!! " +d.getMessage()));
            }
        }
    }

    public static File retrieveTaskList(){
        File taskList = null;
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            taskList = new File(dataFolder.getAbsolutePath() + File.separator + "duke.txt");
            if (!taskList.exists()) {
                boolean taskListExists = taskList.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(makeOutput("☹ OOPS!!! " +e.getMessage()));
            exit();
        }
        return taskList;
    }

    public static FileWriter createTaskWriter(){
        FileWriter taskWriter = null;
        try {
            taskWriter = new FileWriter(taskList, true);
        } catch (IOException e) {
            System.out.println(makeOutput("☹ OOPS!!! " +e.getMessage()));
            exit();
        }
        return taskWriter;
    }

    public static Scanner createTaskReader() {
        Scanner taskReader = null;
        try {
            taskReader = new Scanner(taskList);
        } catch (FileNotFoundException e) {
            System.out.println(makeOutput("☹ OOPS!!! " + e.getMessage()));
        }
        return taskReader;
    }

    public static void handleUnknownCommand() throws DukeException{
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    public static void exit(){
        //exit protocol
        System.out.println(makeOutput("Bye. Hope to see you again soon!"));
        System.exit(0);
    }

    public static String makeOutput(String in){
        return "____________________________________________________________\n" + in +"\n" + "____________________________________________________________";
    }

    public static void addTodo(String description) throws DukeException{
        if (description.equals("")){
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(description);
        try {
            taskWriter.write(newTodo.toString() + "\n");
            taskWriter.flush();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,newTodo, ++numberOfTasks)));
    }

    public static int processMarkUnmarkDel(String input) throws DukeException{
        String[] parsedInput = input.split(" ");
        if (parsedInput.length != 2) {
            throw new DukeException("index of task to delete is missing");
        }
        try {
            int index = Integer.parseInt(parsedInput[1]);
            if(index > numberOfTasks) {
                throw new DukeException("invalid task index");
            }
            return index - 1;
        } catch(NumberFormatException n) {
            throw new DukeException(n.getMessage());
        }
    }

    public static void processDeadline(String input) throws DukeException{
        String raw = input.split("deadline", 2)[1];
        if (raw.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] parsed = raw.split("/by", 2);
        if (parsed.length < 2) {
            throw new DukeException("When the deadline should be completed by should be specified using /by.");
        }
        addDeadline(parsed[0], parsed[1]);
    }

    public static void processEvent(String input) throws DukeException{
        String raw = input.split("event", 2)[1];
        if (raw.equals("")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] parsed1 = raw.split("/from", 2);
        if (parsed1.length < 2) {
            throw new DukeException("The event's timeline should be specified using /from and /to.");
        }
        String[] parsed2 = parsed1[1].split("/to", 2);
        if (parsed2.length < 2) {
            throw new DukeException("The event's timeline should be specified using /from and /to.");
        }
        addEvent(parsed1[0], parsed2[0], parsed2[1]);
    }

    public static void addDeadline(String description, String by) throws DukeException {
        Deadline newDd = new Deadline(description, by);
        try {
            taskWriter.write(newDd.toString() + "\n");
            taskWriter.flush();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,newDd, ++numberOfTasks)));
    }

    public static void addEvent(String description, String from, String to) throws DukeException {
        Event newEvent = new Event(description, from, to);
        try {
            taskWriter.write(newEvent.toString() + "\n");
            taskWriter.flush();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        System.out.println(makeOutput(String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list."
                ,newEvent, ++numberOfTasks)));
    }

    public static void showList() throws IOException {
        String currentList = "";
        int lineNumber = 1;
        for (String line : Files.readAllLines(Paths.get(taskList.getAbsolutePath()), StandardCharsets.UTF_8)) {
            if (lineNumber < numberOfTasks) {
                currentList += lineNumber + "." + line + "\n";
            } else {
                currentList += lineNumber + "." + line;
            }
            lineNumber ++;
        }
        System.out.println(makeOutput(currentList));
    }

    public static void markTask(int taskNo) throws IOException {
        List<String> newLines = new ArrayList<>();
        String result = "Nice! I've marked this task as done:\n";
        int currentLine = 0;
        for (String line : Files.readAllLines(Paths.get(taskList.getAbsolutePath()), StandardCharsets.UTF_8)) {
            if (currentLine == taskNo) {
                String markedLine = line.replace("[ ]", "[X]");
                newLines.add(markedLine);
                result += markedLine;
            } else {
                newLines.add(line);
            }
            currentLine ++;
        }
        Files.write(Paths.get(taskList.getAbsolutePath()), newLines, StandardCharsets.UTF_8);
        System.out.println(makeOutput(result));
    }

    public static void unmarkTask(int taskNo) throws IOException {
        List<String> newLines = new ArrayList<>();
        String result = "OK, I've marked this task as not done yet:\n";
        int currentLine = 0;
        for (String line : Files.readAllLines(Paths.get(taskList.getAbsolutePath()), StandardCharsets.UTF_8)) {
            if (currentLine == taskNo) {
                String unmarkedLine = line.replace("[X]", "[ ]");
                newLines.add(unmarkedLine);
                result += unmarkedLine;
            } else {
                newLines.add(line);
            }
            currentLine++;
        }
        Files.write(Paths.get(taskList.getAbsolutePath()), newLines, StandardCharsets.UTF_8);
        System.out.println(makeOutput(result));
    }

    public static void delete(int taskNo) throws IOException {
        List<String> newLines = new ArrayList<>();
        String deleted = "";
        int currentLine = 0;
        for (String line : Files.readAllLines(Paths.get(taskList.getAbsolutePath()), StandardCharsets.UTF_8)) {
            if (currentLine == taskNo) {
                deleted = line;
            } else {
                newLines.add(line);
            }
            currentLine++;
        }
        Files.write(Paths.get(taskList.getAbsolutePath()), newLines, StandardCharsets.UTF_8);
        System.out.println(makeOutput(String.format("Noted. I've removed this task:\n%s\n" +
                "Now you have %d tasks in the list.", deleted, --numberOfTasks)));
    }
}
