import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    private static final ArrayList<Task> taskArrayList = new ArrayList<>();
    private static final String PATH_TO_FILE = "data/duke.txt";

    public static void main(String[] args) throws IOException {

        //Always greets user when Duke is run
        String greeting = "Hello! I'm Duke! \n What can I do for you? \n";
        System.out.println(greeting);
        readFromFile(PATH_TO_FILE);


        //Use scanner to read input
        Scanner scanner = new Scanner(System.in);

        String keyWord = scanner.next();

        //6 cases: list, mark, unmark, todo, deadline, event
        //while loop checks for user exit input("bye")
        while (!keyWord.equals("bye")) {
            int taskIndex;
            Task currentTask;
            StringBuilder taskDescription;
            String tempScanner;
            switch (keyWord) {
            case "list":
                System.out.println("\nHere are your tasks:\n");
                int taskCount = 1;
                //iterate through taskArray to print out list
                for (Task t : taskArrayList) {
                    if (t == null) {
                        break;
                    } else {
                        System.out.println(taskCount + ". " + t + "\n");
                        taskCount++;
                    }
                }
                if (taskCount == 1) {
                    System.out.println("You have no task in the list.");
                }
                keyWord = scanner.next();
                break;
            case "mark":
                try {
                    taskIndex = scanner.nextInt() - 1;
                    currentTask = taskArrayList.get(taskIndex);
                    currentTask.markAsDone();
                    System.out.println("\n____________________________________________________________");
                    System.out.println("\nNice! I've marked this task as done:\n");
                    System.out.println(currentTask);
                    System.out.println("\n____________________________________________________________\n");
                    writeToFile(PATH_TO_FILE);
                    keyWord = scanner.next();
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Oops! Mark must be followed by an integer.");
                }
            case "unmark":
                try {
                    taskIndex = scanner.nextInt() - 1;
                    currentTask = taskArrayList.get(taskIndex);
                    currentTask.markAsUndone();
                    System.out.println("\n____________________________________________________________");
                    System.out.println("\nOK, I've marked this task as not done yet:\n");
                    System.out.println(currentTask);
                    System.out.println("\n____________________________________________________________\n");
                    writeToFile(PATH_TO_FILE);
                    keyWord = scanner.next();
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Oops! Unmark must be followed by an integer.");
                }
            case "todo": // ToDo tasks have no date/time attached
                tempScanner = scanner.nextLine();
                try {
                    if (tempScanner.isEmpty() || tempScanner.equals(" ")) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    } else {
                        taskDescription = new StringBuilder(tempScanner);
                        currentTask = new ToDo(taskDescription.toString());
                        taskArrayList.add(currentTask);
                        addTaskReply(currentTask);
                        appendToFile(PATH_TO_FILE, currentTask);
                        keyWord = scanner.next();
                        break;
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                    keyWord = scanner.next();
                    break;
                }
            case "deadline": // Deadline tasks have one date/time
                tempScanner = scanner.nextLine();
                try {
                    if (tempScanner.isEmpty()) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    } else {
                        taskDescription = new StringBuilder();
                        StringBuilder by = new StringBuilder();
                        String[] arrOfStr = tempScanner.split(" ");
                        boolean isDateTime = false;
                        for (String s : arrOfStr) {
                            if (s.isEmpty()) {
                                continue;
                            }
                            if (s.equals("/by")) {
                                isDateTime = true;
                                continue;
                            }
                            if (isDateTime) {
                                by.append(" ").append(s);
                            } else {
                                taskDescription.append(" ").append(s);
                            }
                        }

                        //Everything after /by is the deadline
                        currentTask = new Deadline(taskDescription.toString(),by.toString());
                        taskArrayList.add(currentTask);
                        addTaskReply(currentTask);
                        appendToFile(PATH_TO_FILE, currentTask);
                        keyWord = scanner.next();
                        break;
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                    keyWord = scanner.next();
                    break;
                }
            case "event": // Event tasks have two date/times: from and to
                taskDescription = new StringBuilder();
                tempScanner = scanner.next();
                StringBuilder from = new StringBuilder();

                //Using while loop, append scanner input until /from to taskDescription
                while (!tempScanner.equals("/from")) {
                    taskDescription.append(" ").append(tempScanner);
                    tempScanner = scanner.next();
                }

                //Ignore /from
                tempScanner = scanner.next();

                //Using while loop, append scanner input until '/to' to from
                while (!tempScanner.equals("/to")) {
                    from.append(" ").append(tempScanner);
                    tempScanner = scanner.next();
                }

                //Everything after /to is the end timing of event
                String to = scanner.nextLine();
                currentTask = new Event(taskDescription.toString(), from.toString(), to);
                taskArrayList.add(currentTask);
                addTaskReply(currentTask);
                appendToFile(PATH_TO_FILE, currentTask);
                keyWord = scanner.next();
                break;
            case "delete":
                try {
                    taskIndex = scanner.nextInt() - 1;
                    currentTask = taskArrayList.remove(taskIndex);
                    System.out.println("\n____________________________________________________________");
                    System.out.println("\nNoted. I've removed this task:\n");
                    System.out.println(currentTask.toString());
                    System.out.println("\nNow you have " + taskArrayList.size() + " tasks in the list.");
                    System.out.println("\n____________________________________________________________\n");
                    writeToFile(PATH_TO_FILE);
                    keyWord = scanner.next();
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Oops! Mark must be followed by an integer.");
                }
            default:
                //if user input does not match any case at all
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
                keyWord = scanner.next();
            }
        }

        //Exit after user inputs "bye"
        System.out.println("\nBye. Hope to see you again soon!\n");
    }

    //helper method that prints the reply to each added task
    public static void addTaskReply(Task currentTask) {
        System.out.println("\n____________________________________________________________");
        System.out.println("\nGot it. I've added this task: " + currentTask.toString());
        System.out.println("\nNow you have " + taskArrayList.size() + " tasks in the list.");
        System.out.println("\n____________________________________________________________\n");
    }

    public static void readFromFile(String filePath) throws IOException {
        if (hasTaskListFile(filePath)) { //file exists
            File fileTxt = new File(filePath);
            Scanner scanner = new Scanner(fileTxt);
            while (scanner.hasNext()) {
                //create task for each line of tasks
                String taskString = scanner.nextLine();
                String[] splitTaskString = taskString.split(" \\| ");
                Task scannedTask;
                boolean markAsDone = false;
                switch (splitTaskString[0]) {
                case "T":
                    scannedTask = new ToDo(splitTaskString[2]);
                    markAsDone = splitTaskString[1].equals("1");
                    if (markAsDone) {
                        scannedTask.markAsDone();
                    }
                    taskArrayList.add(scannedTask);
                    break;
                case "D":
                    scannedTask = new Deadline(splitTaskString[2], splitTaskString[3]);
                    markAsDone = splitTaskString[1].equals("1");
                    if (markAsDone) {
                        scannedTask.markAsDone();
                    }
                    taskArrayList.add(scannedTask);
                    break;
                case "E":
                    scannedTask = new Event(splitTaskString[2], splitTaskString[3], splitTaskString[4]);
                    markAsDone = splitTaskString[1].equals("1");
                    if (markAsDone) {
                        scannedTask.markAsDone();
                    }
                    taskArrayList.add(scannedTask);
                    break;
                }
            }
        } else {
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
    }

    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskArrayList) {
            fw.write(t.toTXT() + System.lineSeparator());
        }
        fw.close();
    }

    public static void appendToFile(String filePath, Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(task.toTXT() + System.lineSeparator());
        fw.close();
    }

    public static boolean hasTaskListFile(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
}