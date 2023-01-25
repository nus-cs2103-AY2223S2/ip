import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.LocalDate;

public class Duke {
    private static ArrayList<Task> taskStore = new ArrayList<>();
    private enum ParseFunctions {
        SPLIT_ALL, TODO, DEADLINE, EVENT
    }
    private static Task getTaskForMarking(String[] parsed) {
        int completedIndex = Integer.parseInt(parsed[1]) - 1; // index of the task completed
        Task completedTask = Duke.taskStore.get(completedIndex); // actual task
        return completedTask;
    }
    private static String[] parser(String input, ParseFunctions parse_type) throws EmptyDescriptionException {
        switch (parse_type) {
            case SPLIT_ALL:
                return input.split(" ");
            case TODO:
                String[] parsed = input.split(" ", 2);
                if (parsed.length < 2) {
                    throw new EmptyDescriptionException("Add an argument");
                }
                return parsed; // split into 2
            case DEADLINE:
                String[] otherArgs = input.split(" ", 2);
                String[] taskAndTime = otherArgs[1].split(" /by ", 2);
                return new String[]{otherArgs[0], taskAndTime[0], taskAndTime[1]};
            case EVENT:
                otherArgs = input.split(" ", 2);
                taskAndTime = otherArgs[1].split(" /from ", 2);
                String[] toTime = taskAndTime[1].split(" /to ", 2);
                return new String[]{otherArgs[0], taskAndTime[0], toTime[0], toTime[1]};
        }
        return null;
    }
    private static int countTasks() {
        return Duke.taskStore.size();
    }
    private static void printNewTask(Task t) {
        if (t instanceof Event) {
            System.out.println("  new event added!");
        }
        else if (t instanceof Deadline) {
            System.out.println("  new deadline added!");
        }
        else if (t instanceof ToDo) {
            System.out.println("  new todo added!");
        }
        System.out.println("    " + t.toString());
        System.out.println("  Now you have " + String.valueOf(Duke.countTasks()) +
                " tasks in the list!");
    }
    private static void loadFromFile() throws IOException {
        String home = System.getProperty("user.dir");
        Path dukeFolderPath = Paths.get(home, "data");
        Path dukeFilePath = Paths.get(home, "data", "duke.txt");
        if (!Files.exists(dukeFolderPath)) {
            Files.createDirectories(dukeFolderPath);
            Files.createFile(dukeFilePath);
        }
        else if (!Files.exists(dukeFilePath)) {
            Files.createFile(dukeFilePath);
        }
        BufferedReader dukeReader = Files.newBufferedReader(dukeFilePath);
        String task;

        while ((task = dukeReader.readLine()) != null) {
            // process it
            // format: E|0|project meeting|Aug 6th|2pm|4pm
            String[] params = task.split("\\|");
            String type = params[0];
            boolean isCompleted = params[1].equals("1");
            String description = params[2];

            if (type.equals("T")) {
                taskStore.add(new ToDo(description, isCompleted));
            }
            else if (type.equals("D")) {
                LocalDate by = LocalDate.parse(params[3]);
                taskStore.add(new Deadline(description, isCompleted, by));
            }
            else if (type.equals("E")) {
                LocalDate start = LocalDate.parse(params[3]);
                LocalDate end = LocalDate.parse(params[4]);
                taskStore.add(new Event(description, isCompleted, start, end));
            }
        }
        dukeReader.close();
    }
    private static void addTaskToFile(Task currTask) throws IOException {
        String home = System.getProperty("user.dir");
        Path dukeFilePath = Paths.get(home, "data", "duke.txt");

        String newEntry = "\n";
        String completedBit = currTask.getCompletion() ? "1" : "0";
        if (currTask instanceof Event) {
            Event e = (Event) currTask;
            String taskType = "E";
            newEntry = taskType + "|" + completedBit + "|" + currTask.getDescription() + "|"
                    + e.getStartTime() + "|" + e.getEndTime() + newEntry;
        }
        else if (currTask instanceof ToDo) {
            String taskType = "T";
            newEntry = taskType + "|" + completedBit + "|" + currTask.getDescription() + newEntry;
        }
        else if (currTask instanceof Deadline) {
            Deadline d = (Deadline) currTask;
            String taskType = "D";
            newEntry = taskType + "|" + completedBit + "|" + currTask.getDescription() + "|"
                    + d.getDeadline() + newEntry;
        }
        Files.write(dukeFilePath, newEntry.getBytes(), StandardOpenOption.APPEND); // don't need to close
    }
    private static void deleteTask(int taskNo) throws IOException {
        String home = System.getProperty("user.dir");
        Path dukeFilePath = Paths.get(home, "data", "duke.txt");
        Path tempPath = Paths.get(home, "data", "temp.txt");
        BufferedReader dukeReader = Files.newBufferedReader(dukeFilePath);
        BufferedWriter dukeWriter = Files.newBufferedWriter(tempPath);

        String currentLine;
        int currentLineIndex = 1;
        while ((currentLine = dukeReader.readLine()) != null) {
            if (currentLineIndex != taskNo) {
                dukeWriter.write(currentLine + System.getProperty("line.separator"));
            }
            taskNo++; // don't copy over to temp file (which is equivalent to deleting)
        }
        dukeReader.close();
        dukeWriter.close();
        Files.delete(dukeFilePath);
        Files.move(tempPath, dukeFilePath); // move from src to dest (replace)
    }
    public static void main(String[] args) throws EmptyDescriptionException, IOException {
        Duke.loadFromFile();
        System.out.println("  insert ingenious greeting here");

        label:
        while (true) {
            Scanner myScanner = new Scanner(System.in);
            String command = myScanner.nextLine();

            String[] toFindFirstWord = parser(command, ParseFunctions.SPLIT_ALL); // take a comment

            String first = toFindFirstWord[0];

            switch (first) {
                case "bye":
                    System.out.println("  Bye. Hope to see you soon again!");
                    break label;
                case "mark": {
                    Task completedTask = getTaskForMarking(toFindFirstWord);
                    completedTask.setCompletion();

                    System.out.println("  You are done with: ");
                    System.out.println("    " + completedTask.toString());
                    break;
                }
                case "unmark": {
                    Task completedTask = getTaskForMarking(toFindFirstWord);
                    completedTask.setCompletion();

                    System.out.println("  OK, continue working on: ");
                    System.out.println("    " + completedTask.toString());
                    break;
                }
                case "delete": {
                    Task toDelete = getTaskForMarking(toFindFirstWord);
                    taskStore.remove(toDelete);

                    String[] parsed = parser(command, ParseFunctions.TODO);
                    deleteTask(Integer.parseInt(parsed[1]));

                    System.out.println("  I've removed this task:");
                    System.out.println("    " + toDelete.toString());
                    System.out.println("  Now you have "+ String.valueOf(Duke.countTasks()) +
                            " tasks in the list!");
                    break;
                }
                case "deadline": {
                    String[] parsed = parser(command, ParseFunctions.DEADLINE);
                    Task newDeadline = new Deadline(parsed[1], LocalDate.parse(parsed[2]));
                    Duke.taskStore.add(newDeadline);
                    addTaskToFile(newDeadline);
                    printNewTask(newDeadline);
                    break;
                }
                case "event": {
                    String[] parsed = parser(command, ParseFunctions.EVENT);
                    Task newEvent = new Event(parsed[1], LocalDate.parse(parsed[2]), LocalDate.parse(parsed[3]));
                    Duke.taskStore.add(newEvent);
                    addTaskToFile(newEvent);
                    printNewTask(newEvent);
                    break;
                }
                case "todo": {
                    try {
                        String[] parsed = parser(command, ParseFunctions.TODO);
                        ToDo newToDo = new ToDo(parsed[1]);
                        Duke.taskStore.add(newToDo);
                        addTaskToFile(newToDo);
                        printNewTask(newToDo);
                        break;
                    }
                    catch (EmptyDescriptionException e) {
                        System.out.println("  Add an argument");
                    }
                }
                case "list":
                    for (int i = 0; i < taskStore.size(); i++) {
                        System.out.println("  " + String.valueOf(i + 1) + ". " + taskStore.get(i));
                    }
                    break;
                default:
                    System.out.println("  this is not a task, contact admin");
                    break;
            }
        }
    }
}
