package seedu.shao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Shao {

    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static String dataDirectory = "data";

    public static String sep = File.separator;

    public static String dataFilePath = dataDirectory + sep + "shao.txt";

    public static void printRowLine() {
        println("________________________________________________________");
    }

    public static void println(String s) {
        System.out.println("\t" + s);
    }

    public static void printAddedTask(Task task, int tasksCnt) {
        printRowLine();
        println("Noted. I've added this task:");
        println("  " + task.toString());
        println(String.format("You have %d %s in your list currently.", tasksCnt, tasksCnt > 1 ? "tasks" : "task"));
        printRowLine();
    }

    public static void printMarkedTask(Task task, boolean isMark) {
        String body = isMark
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        printRowLine();
        println(body);
        println(task.toString());
        printRowLine();
    }

    public static void printItemDeleted(Task task, int tasksCnt) {
        printRowLine();
        println("Sure, I've removed this task:");
        println("  " + task.toString());
        println(String.format(
                "You have %d %s in your list currently.",
                tasksCnt, tasksCnt > 1 ? "tasks" : "task"));
        printRowLine();
    }

    public static void printList(List<Task> items) {
        int numItems = items.size();
        String header = numItems == 0 ? "There are no tasks in your list. Please add one."
                : "Here are the tasks in your list: ";

        printRowLine();
        println(header);
        for (int i = 0; i < items.size(); i++) {
            Task curTask = items.get(i);
            println(String.format("%d.%s", i + 1, curTask));
        }
        printRowLine();
    }

    public static void printDeadlineEventOnDatetime(List<Task> items, String dateTimeStr) {
        boolean hasItem = false;
        LocalDateTime dateTime = parseDateTimeStr(dateTimeStr);
        String dtStrOutput = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));

        printRowLine();
        for (int i = 0; i < items.size(); i++) {
            Task curTask = items.get(i);
            if (curTask instanceof Deadline) {
                Deadline deadline = (Deadline) curTask;
                if (deadline.by.equals(dateTime)) {
                    if (!hasItem) {
                        println(String.format(
                                "These are the deadline/events that occur on %s", dtStrOutput));
                    }
                    hasItem = true;
                    println(String.format("%d.%s", i + 1, deadline));
                }
            }
            if (curTask instanceof Event) {
                Event event = (Event) curTask;
                if (event.from.equals(dateTime) || event.to.equals(dateTime)) {
                    if (!hasItem) {
                        println(String.format(
                                "These are the deadline/events that occur on %s", dtStrOutput));
                    }
                    hasItem = true;
                    println(String.format("%d.%s", i + 1, event));
                }
            }
        }
        if (!hasItem) {
            println(String.format(
                    "No deadline/events occur on %s", dtStrOutput));
        }
        printRowLine();
    }

    public static void printError(String errorMessage) {
        printRowLine();
        println(errorMessage);
        printRowLine();
    }

    public static void greetUser() {
        printRowLine();
        println("\tHi There! I'm Shao");
        println("\tWhat can I do for you?");
        printRowLine();
    }

    public static void exitUser() {
        printRowLine();
        println("Bye! Have a nice day!");
        printRowLine();
    }

    public static void markItem(String itemNum, List<Task> items, boolean isMark) throws NumberFormatException {
        int idx = Integer.parseInt(itemNum) - 1;
        try {
            Task task = items.get(idx);
            if (isMark) {
                task.markAsDone();
                printMarkedTask(task, true);
                markSavedTask(idx, true);
                return;
            }
            task.markAsUndone();
            printMarkedTask(task, false);
            markSavedTask(idx, false);
        } catch (IndexOutOfBoundsException ex) {
            printError(String.format("Oops! Please select item from 1 to %d inclusive.", items.size()));
        }
    }

    public static void markSavedTask(int idx, boolean isMark) {
        try (Stream<String> lines = Files.lines(Paths.get(dataFilePath))) {
            String line = lines.skip(idx).findFirst().get();
            modifyLineFile(dataFilePath, idx + 1,
                    line.replaceFirst("[01]", isMark ? "1" : "0"));
        } catch (IOException ex) {
            printError("Something went wrong while marking the task status.");
        }
    }

    public static void modifyLineFile(String filePath, int lineNum, String newLine) {
        File fileToBeModified = new File(filePath);
        String content = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        int curLineNum = 1;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();

            while (line != null) {
                if (curLineNum == lineNum) {
                    content += newLine + System.lineSeparator();
                } else {
                    content += line + System.lineSeparator();
                }
                line = reader.readLine();
                curLineNum += 1;
            }
            writer = new FileWriter(fileToBeModified);
            writer.write(content);
        } catch (IOException e) {
            printError("Something went wrong while modifying the file.");
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                printError("Something went wrong while modifying the file.");
            }
        }
    }

    public static void deleteItem(String itemNum, List<Task> items) throws NumberFormatException {
        try {
            int idx = Integer.parseInt(itemNum) - 1;
            deleteLineFile(dataFilePath, idx + 1);
            printItemDeleted(items.remove(idx), items.size());
        } catch (IndexOutOfBoundsException ex) {
            printError(String.format("Oops! Please select item from 1 to %d inclusive.", items.size()));
        }
    }

    public static void deleteLineFile(String filePath, int lineNum) {
        File fileToBeModified = new File(filePath);
        String content = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        int curLineNum = 1;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();

            while (line != null) {
                if (curLineNum != lineNum) {
                    content += line + System.lineSeparator();
                }
                line = reader.readLine();
                curLineNum += 1;
            }
            writer = new FileWriter(fileToBeModified);
            writer.write(content);
        } catch (IOException e) {
            printError("Something went wrong while deleting a line from the file.");
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                printError("Something went wrong while deleting a line from the file.");
            }
        }
    }

    public static LocalDateTime getBy(String[] inputArr) {
        int l = inputArr.length;
        for (int i = 0; i < l; i++) {
            if (i < l - 1 && inputArr[i].equals("/by")) {
                return parseDateTimeStr(sliceArrAndConcate(inputArr, i + 1, l));
            }
        }
        return null;
    }

    public static LocalDateTime parseDateTimeStr(String dateTimeStr) {
        if (dateTimeStr.isBlank()) {
            return null;
        }
        String[] dateTimeArr = dateTimeStr.split(" ");
        LocalTime time = LocalTime.MIN;
        Integer[] dateArr;
        if (dateTimeArr.length > 1) {
            try {
                int hr = Integer.parseInt(dateTimeArr[1].substring(0, 2));
                int min = Integer.parseInt(dateTimeArr[1].substring(2, 4));
                time = LocalTime.of(hr, min);
            } catch (NumberFormatException ex) {
                printError("Oops! Time format needs to be specified in proper form.");
                return null;
            }
        }

        try {
            if (dateTimeStr.contains("/")) { // Input Format: dd/MM/YYYY
                dateArr = Stream.of(dateTimeArr[0].split("/")).map(Integer::valueOf).toArray(Integer[]::new);
                return LocalDateTime.of(LocalDate.of(dateArr[2], dateArr[1], dateArr[0]), time);
            }

            // Input Format: YYYY-MM-dd
            dateArr = Stream.of(dateTimeArr[0].split("-")).map(Integer::valueOf).toArray(Integer[]::new);
            return LocalDateTime.of(LocalDate.of(dateArr[0], dateArr[1], dateArr[2]), time);
        } catch (ArrayIndexOutOfBoundsException ex) {
            printError("Oops! Time format needs to be specified in proper form.");
            return null;
        }

    }

    public static LocalDateTime[] getFromTo(String[] inputArr) {
        int l = inputArr.length;
        int fromStartIdx = -1, fromEndIdx = l, toStartIdx = -1;
        LocalDateTime from = LocalDateTime.MIN;
        LocalDateTime to = LocalDateTime.MIN;
        for (int i = 0; i < l; i++) {
            if (i < l - 1) {
                if (inputArr[i].equals("/from")) {
                    fromStartIdx = i + 1;
                }
                if (inputArr[i].equals("/to")) {
                    fromEndIdx = i;
                    toStartIdx = i + 1;
                }
            }
        }
        if (fromStartIdx > -1) {
            from = parseDateTimeStr(sliceArrAndConcate(inputArr, fromStartIdx, fromEndIdx));
        }
        if (toStartIdx > -1) {
            to = parseDateTimeStr(sliceArrAndConcate(inputArr, toStartIdx, l));
        }
        return new LocalDateTime[] { from, to };
    }

    public static String trimDate(String input) {
        String[] inputArr = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : inputArr) {
            if (s.contains("/")) {
                return sb.toString().trim();
            }
            sb.append(s + " ");
        }
        return sb.toString().trim();
    }

    public static String sliceArrAndConcate(String[] arr, int startIdx, int endIdx) {
        return String.join(" ", Arrays.copyOfRange(arr, startIdx, endIdx));
    }

    public static void fetchData(String input, List<Task> items) {
        String inputLower = input.toLowerCase();
        if (inputLower.isBlank())
            return;
        String[] inputArr = inputLower.split("\\|");
        TaskType operationType = inputLower.startsWith("t")
                ? TaskType.TODO
                : inputLower.startsWith("d")
                        ? TaskType.DEADLINE
                        : TaskType.EVENT;

        Task newTask = null;

        switch (operationType) {
            case TODO:
                newTask = new Todo(inputArr[2]);
                break;

            case DEADLINE:
                newTask = new Deadline(inputArr[2], parseDateTimeStr(inputArr[3]));
                break;

            case EVENT:
                newTask = new Event(inputArr[2],
                        new LocalDateTime[] { parseDateTimeStr(inputArr[3]), parseDateTimeStr(inputArr[4]) });
                break;

            default:
                break;
        }
        if (inputArr[1].equals("1"))
            newTask.markAsDone();
        items.add(newTask);
    }

    public static void readInput(List<Task> items) {
        Scanner scan = new Scanner(System.in);

        // Prompting
        while (true) {
            if (!scan.hasNextLine())
                continue;
            String input = scan.nextLine().trim();
            String inputLower = input.toLowerCase();
            if (inputLower.isBlank())
                continue;
            switch (inputLower) {
                case "bye":
                    exitUser();
                    scan.close();
                    return;
                case "list":
                    printList(items);
                    break;
                default:
                    boolean isDateTimeOperation = inputLower.startsWith("datetime");
                    boolean isDeleteOperation = inputLower.startsWith("delete");
                    boolean isMarkOperation = inputLower.startsWith("mark")
                            || inputLower.startsWith("unmark");
                    String[] inputArr = input.split(" ");
                    if (isDateTimeOperation) {
                        if (inputArr.length < 2) {
                            printError("Oops! Datetime cannot be empty.");
                        } else {
                            printDeadlineEventOnDatetime(items, sliceArrAndConcate(inputArr, 1, inputArr.length));
                        }
                    } else if (isDeleteOperation || isMarkOperation) {
                        if (inputArr.length < 2) {
                            printError("Oops! The item number cannot be empty.");
                        } else {
                            try {
                                if (isDeleteOperation) {
                                    deleteItem(inputArr[1], items);
                                } else {
                                    markItem(inputArr[1], items, inputLower.startsWith("mark"));
                                }
                            } catch (NumberFormatException ex) {
                                printError("Oops! An item number must be provided.");
                            }
                        }
                    } else if (inputLower.startsWith("todo") || inputLower.startsWith("deadline")
                            || inputLower.startsWith("event")) {

                        TaskType operationType = inputLower.startsWith("todo")
                                ? TaskType.TODO
                                : inputLower.startsWith("deadline")
                                        ? TaskType.DEADLINE
                                        : TaskType.EVENT;

                        if (inputArr.length < 2) {
                            printError(String.format("Oops! The description of a %s cannot be empty.",
                                    operationType == TaskType.TODO ? "todo"
                                            : operationType == TaskType.DEADLINE ? "deadline" : "event"));
                        } else {
                            Task newTask = null;
                            String description = sliceArrAndConcate(inputArr, 1, inputArr.length);
                            switch (operationType) {
                                case TODO:
                                    newTask = new Todo(description);
                                    break;

                                case DEADLINE:
                                    LocalDateTime by = getBy(inputArr);
                                    if (by == null) {
                                        printError(
                                                "Oops! The description of deadline must include a completion date/time.");
                                        continue;
                                    }
                                    newTask = new Deadline(trimDate(description), by);
                                    break;

                                case EVENT:
                                    LocalDateTime[] fromTo = getFromTo(inputArr);
                                    if (fromTo[0] == LocalDateTime.MIN) {
                                        printError("Oops! The description of event must include a from date/time.");
                                        continue;
                                    }
                                    if (fromTo[1] == LocalDateTime.MIN) {
                                        printError("Oops! The description of event must include a to date/time.");
                                        continue;
                                    }
                                    newTask = new Event(trimDate(description), fromTo);
                                    break;

                                default:
                                    break;
                            }
                            items.add(newTask);
                            saveNewData(newTask);
                            printAddedTask(newTask, items.size());
                        }
                    } else {
                        printError("Oops! I'm sorry but I don't know what that means.");
                    }
                    break;

            }
        }
    }

    public static <T extends Task> void saveNewData(T task) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath, true))) {
            bw.write(task.getSavedFormat());
            bw.newLine();
        } catch (IOException e) {
            printError("Something went wrong while saving the new task.");
        }
    }

    public static void main(String[] args) {
        List<Task> items = new ArrayList<>();
        File myDir = new File(dataDirectory);
        File myFile = new File(dataFilePath);

        greetUser();

        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                fetchData(myReader.nextLine().trim(), items);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            try {
                myDir.mkdirs();
                myFile.createNewFile();
            } catch (IOException ex) {
                printError("Something went wrong while creating a new file.");
            }
        } finally {
            readInput(items);
        }

    }

}
