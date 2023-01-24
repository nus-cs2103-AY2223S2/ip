import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String reply = "";
        String dataFolderPath = "data";
        String dataFilePath = dataFolderPath + "/duke.txt";
        FileWriter writer;

        // Create the file if it does not exist
        try {
            File targetFile = new File(dataFilePath);
            File parent = targetFile.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
            if (!targetFile.createNewFile()) {
                // Load data
                Scanner dataFile = new Scanner(targetFile);
                String dataLine;
                Pattern wholeStrPattern = Pattern.compile("\\d+\\.\\[(T|D|E)]\\[( |X)\\] (.*)");
                Pattern deadlinePattern = Pattern.compile("(.*) /by (.*)");
                Pattern eventPattern = Pattern.compile("(.*) /from (.*) /to (.*)");;
                Matcher wholeStrMatcher, individualMatcher;
                Task toAdd;
                while (dataFile.hasNext()) {
                    dataLine = dataFile.nextLine();
                    wholeStrMatcher = wholeStrPattern.matcher(dataLine);
                    if (wholeStrMatcher.find()) {
                        // Group 0 is the whole matched string
                        switch (wholeStrMatcher.group(1)) {
                        case "T":
                            toAdd = new ToDo(wholeStrMatcher.group(3));
                            break;
                        case "D":
                            individualMatcher = deadlinePattern.matcher(wholeStrMatcher.group(3));
                            if (individualMatcher.find()) {
                                toAdd = new Deadline(individualMatcher.group(1), individualMatcher.group(2));
                            } else {
                                throw new InvalidDataFileException();
                            }
                            break;
                        case "E":
                            individualMatcher = eventPattern.matcher(wholeStrMatcher.group(3));
                            if (individualMatcher.find()) {
                                toAdd = new Event(individualMatcher.group(1), individualMatcher.group(2), individualMatcher.group(3));
                            } else {
                                throw new InvalidDataFileException();
                            }
                            break;
                        default:
                            throw new InvalidDataFileException();
                        }

                        if (wholeStrMatcher.group(2).equals("X")) {
                            toAdd.setDone(true);
                        }

                        taskList.addTask(toAdd);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        printTextWithLines("Hello! I'm Duke\nWhat can I do for you?");

        while (true) {
            reply = input.nextLine();
            try {
                if (reply.equals("bye")) {
                    printTextWithLines("Bye. Hope to see you again soon!");
                    break;
                } else if (reply.equals("list")) {
                    printTextWithLines(taskList.toString());
                } else if (reply.startsWith("mark")) {
                    Matcher matcher = compileAndMatch("mark (.*)", reply);
                    if (matcher.find() && matcher.group(1).length() > 0) {
                        int taskNumber = Integer.parseInt(matcher.group(1));
                        taskList.setDone(taskNumber, true);
                        printTextWithLines("Nice! I've marked this task as done:\n  " + taskList.getTask(taskNumber));
                    } else {
                        throw new InvalidCommandException("The task number of a mark command cannot be empty.");
                    }
                } else if (reply.startsWith("unmark")) {
                    Matcher matcher = compileAndMatch("unmark (.*)", reply);
                    if (matcher.find() && matcher.group(1).length() > 0) {
                        int taskNumber = Integer.parseInt(matcher.group(1));
                        taskList.setDone(taskNumber, true);
                        printTextWithLines("OK, I've marked this task as not done yet:\n  " + taskList.getTask(taskNumber));
                    } else {
                        throw new InvalidCommandException("The task number of an unmark command cannot be empty.");
                    }
                } else if (reply.startsWith("delete")) {
                    Matcher matcher = compileAndMatch("delete (.*)", reply);
                    if (matcher.find() && matcher.group(1).length() > 0) {
                        int taskNumber = Integer.parseInt(matcher.group(1));
                        String taskDescription = taskList.getTask(taskNumber).toString();
                        taskList.deleteTask(taskNumber);
                        printTextWithLines("Noted. I've removed this task:\n  " + taskDescription + "\n" + taskList.describeLength());
                    } else {
                        throw new InvalidCommandException("The task number to be deleted must be specified, and must be an integer.");
                    }
                } else if (reply.startsWith("todo")) {
                    Matcher matcher = compileAndMatch("todo (.*)", reply);
                    if (matcher.find() && matcher.group(1).length() > 0) {
                        Task task = new ToDo(matcher.group(1));
                        taskList.addTask(task);
                        printTextWithLines("Got it. I've added this task:\n  " + task + "\n" + taskList.describeLength());
                    } else {
                        throw new InvalidCommandException("The description of a todo cannot be empty.");
                    }
                } else if (reply.startsWith("deadline")) {
                    Matcher matcher = compileAndMatch("deadline (.*) /by (.*)", reply);
                    if (matcher.find() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0) {
                        Task task = new Deadline(matcher.group(1), matcher.group(2));
                        taskList.addTask(task);
                        printTextWithLines("Got it. I've added this task:\n  " + task + "\n" + taskList.describeLength());
                    } else {
                        throw new InvalidCommandException("The end date of a deadline cannot be empty.");
                    }
                } else if (reply.startsWith("event")) {
                    Matcher matcher = compileAndMatch("event (.*) /from (.*) /to (.*)", reply);
                    if (matcher.find() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0 && matcher.group(3).length() > 0) {
                        Task task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                        taskList.addTask(task);
                        printTextWithLines("Got it. I've added this task:\n  " + task + "\n" + taskList.describeLength());
                    } else {
                        throw new InvalidCommandException("An event must have a nonempty from date and a to date.");
                    }
                } else {
                    throw new UnknownCommandException();
                }

                // After each command, save the current task list to the file
                if (taskList.getLength() != 0) {
                    writer = new FileWriter(dataFilePath);
                    writer.write(taskList.toEncodedString());
                    writer.close();
                }
            } catch (Exception e) {
                printTextWithLines(e.getMessage());
            }
        }
    }

    static Matcher compileAndMatch(String regex, String toMatch) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toMatch);
    }

    static void printTextWithLines(String text) {
        printLineBreak();
        System.out.println(text);
        printLineBreak();
        System.out.println();
    }

    static void printLineBreak() {
        System.out.println("_________________________________________________________________");
    }
}
