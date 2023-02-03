import Exceptions.CommandNotFoundException;
import Exceptions.EmptyListException;
import Exceptions.MissingInputException;
import Exceptions.MissingSpacingException;
import Exceptions.UnspecifiedTimeException;
import Exceptions.WessyException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Wessy {
    static String OPENING_LINE = "    -Wessy------------------------------" +
            "---------------------------------- ";
    static String CLOSING_LINE = "    -----------------------------------" +
            "----------------------------------- ";
    static List<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        startsUp();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (checkCmd(userInput, CmdType.BYE)) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            } else if (checkCmd(userInput, CmdType.LIST)) {
                printListMessage();
            } else if (checkCmd(userInput, CmdType.CLEAR)) {
                tasks.clear();
                saveTasks();
                printMessage("Your task list has just been cleared. It is now empty.");
            } else {
                try {
                    if (checkCmd(userInput, CmdType.MARK)) {
                        markOrUnmark(userInput, true);
                    } else if (checkCmd(userInput,CmdType.UNMARK)) {
                        markOrUnmark(userInput, false);
                    } else if (checkCmd(userInput, CmdType.DELETE)) {
                        deleteTask(userInput);
                    } else if (checkCmd(userInput, CmdType.TODO)) {
                        printAddedMessage(addTask(parse(userInput, CmdType.TODO)));
                    } else if (checkCmd(userInput, CmdType.DEADLINE)) {
                        printAddedMessage(addTask(parse(userInput, CmdType.DEADLINE)));
                    } else if (checkCmd(userInput, CmdType.EVENT)) {
                        printAddedMessage(addTask(parse(userInput, CmdType.EVENT)));
                    } else {
                        throw new CommandNotFoundException();
                    }
                } catch (WessyException wessyEx) {
                    printMessage(String.valueOf(wessyEx));
                } catch (NumberFormatException nfe) {
                    printMessage("☹ OOPS!!! It is not a number." +
                            " Please enter a number.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    printMessage("☹ OOPS!!! Please enter the " +
                            "correct format.");
                }
            }
        }
    }

    static void startsUp() {
        System.out.println(OPENING_LINE);
        println("Hi, I am Wessy, your personal assistant chatbot.");
        File savedFile = new File("data/Wessy.txt");
        if (savedFile.exists()) {
            try {
                Scanner sc = new Scanner(savedFile);
                while (sc.hasNextLine()) {
                    String[] taskComponents = sc.nextLine().split("~%~");
                    boolean isDone = taskComponents[1].charAt(0) == '1' ? true : false;
                    switch (taskComponents[0].charAt(0)) {
                        case 'T':
                            tasks.add(new ToDo(taskComponents[2], isDone));
                            break;
                        case 'D':
                            tasks.add(new Deadline(taskComponents[2], taskComponents[3], isDone));
                            break;
                        case 'E':
                            tasks.add(new Event(taskComponents[2], taskComponents[3], taskComponents[4], isDone));
                            break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        println("");
        printList();
        System.out.println(CLOSING_LINE);
    }

    static String[] parse(String description, CmdType type) throws
            MissingSpacingException, MissingInputException,
            UnspecifiedTimeException {
        checkForMissingInput(description, type);
        checkForSpacingAftCmd(description, type);
        String byStr = "/by";
        String fromStr = "/from";
        String toStr = "/to";
        int firstIdx;
        int secondIdx;
        description = description.substring(type.len() + 1);
        switch (type) {
            case DEADLINE:
                firstIdx = description.indexOf(byStr);
                return new String[]{description.substring(0, firstIdx - 1),
                        description.substring(firstIdx +
                                byStr.length() + 1)};
            case EVENT:
                firstIdx = description.indexOf(fromStr);
                secondIdx = description.indexOf(toStr);
                return new String[]{description.substring(0, firstIdx - 1),
                        description.substring(firstIdx + fromStr.length() + 1, secondIdx - 1),
                        description.substring(secondIdx +
                                toStr.length() + 1)};
            case TODO:
                return new String[]{description};
        }
        return new String[] {};
    }

    static LocalDateTime parseDateTime(String str) {
        int n = str.length();
        if (n <= 10) {
            return LocalDateTime.parse(parseDate(str) + "T12:34:56");
        }
        int idx = 10;
        if (str.charAt(9) == ' ') {
            idx = 9;
        } else if (str.charAt(8) == ' ') {
            idx = 8;
        }
        if (str.charAt(idx + 3) == ':') {
            return LocalDateTime.parse(parseDate(str.substring(0, idx)) + "T" + str.substring(idx + 1) + ":00");
        }
        if (str.charAt(idx + 3) == '.') {
            return LocalDateTime.parse(parseDate(str.substring(0, idx)) + "T" + str.substring(idx + 1, idx + 3) + ":" + str.substring(idx + 4) + ":00");
        }
        return LocalDateTime.parse(parseDate(str.substring(0, idx)) + "T" + str.substring(idx + 1, idx + 3) + ":" + str.substring(idx + 3) + ":00");
    }

    static String parseDate(String str) {
        String[] components = str.split("-", 3);
        if (str.indexOf("/") != -1) {
            components = str.split("/", 3);
        }
        for (int i = 0; i < components.length; i++) {
            if (components[i].length() == 1) {
                components[i] = "0" + components[i];
            }
        }
        if (components[0].length() == 4) {
            return components[0] + "-" + components[1] + "-" + components[2];
        }
        return components[2] + "-" + components[1] + "-" + components[0];
    }

    static boolean checkCmd(String userInput, CmdType type) {
        int threshold = type.len();
        return userInput.length() >= threshold && userInput.substring(0,
                threshold).equalsIgnoreCase(type.toString());
    }

    static void checkForSpacingAftCmd(String userInput, CmdType type) throws
            MissingSpacingException {
        String cmd = type.toString();
        if (userInput.charAt(cmd.length()) != ' ') {
            throw new MissingSpacingException(cmd, true);
        }
    }

    static boolean checkAllSpaces(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    static void checkForMissingInput(String userInput, CmdType type) throws
            MissingInputException, MissingSpacingException,
            UnspecifiedTimeException {
        String cmd = type.toString();
        if (userInput.equalsIgnoreCase(cmd) || checkAllSpaces(
                userInput.substring(cmd.length()))) {
            throw new MissingInputException(cmd);
        }
        if (type == CmdType.DEADLINE) {
            checkForTimeKeywordEx(userInput, "/by");
            int idxOfBy = userInput.indexOf("/by");
            if (idxOfBy == 8 || checkAllSpaces(userInput.substring(8,
                    idxOfBy))) {
                throw new MissingInputException(cmd);
            }
            if (idxOfBy + 3 == userInput.length() || checkAllSpaces(
                    userInput.substring(idxOfBy + 3))) {
                throw new UnspecifiedTimeException("/by");
            }
        }
        if (type == CmdType.EVENT) {
            checkForTimeKeywordEx(userInput, "/from");
            int idxOfFrom = userInput.indexOf("/from");
            if (idxOfFrom == 5 || checkAllSpaces(userInput.substring(5,
                    idxOfFrom))) {
                throw new MissingInputException(cmd);
            }
            checkForTimeKeywordEx(userInput, "/to");
            int idxOfTo = userInput.indexOf("/to");
            if (idxOfTo == idxOfFrom + 5 || checkAllSpaces(
                    userInput.substring(idxOfFrom + 5, idxOfTo))) {
                throw new UnspecifiedTimeException("/from");
            }
            if (idxOfTo + 3 == userInput.length() || checkAllSpaces(
                    userInput.substring(idxOfTo + 3))) {
                throw new UnspecifiedTimeException("/to");
            }
        }
    }

    static void checkForTimeKeywordEx(String userInput, String keyword) throws
            UnspecifiedTimeException, MissingSpacingException {
        int idx = userInput.indexOf(keyword);
        if (idx == -1) {
            throw new UnspecifiedTimeException(keyword);
        }
        if (userInput.charAt(idx - 1) != ' ') {
            throw new MissingSpacingException(keyword, false);
        }
        if (userInput.length() == idx + keyword.length() ||
                userInput.charAt(idx + keyword.length()) != ' ') {
            throw new MissingSpacingException(keyword, true);
        }
    }

    static void checkForEmptyList(CmdType type) throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException(type.toString());
        }
    }

    static void printMessage(String... linesOfString) {
        System.out.println(OPENING_LINE);
        for (String line : linesOfString) {
            println(line);
        }
        System.out.println(CLOSING_LINE);
    }

    static void printAddedMessage(Task task) {
        int size = tasks.size();
        String numOfTasks = " " + size + " task";
        if (size > 1) {
            numOfTasks += "s";
        }
        printMessage("Got it. I've added this task:",
                "  " + task, "Now you have" + numOfTasks + " in the list.");
    }

    static void println(String str) {
        int length = str.length();
        if (length <= 64) {
            String message = "   |   " + str;
            for (int i = 0; i < 67 - length; i++) {
                message += " ";
            }
            message += "|";
            System.out.println(message);
        } else {
            System.out.println("   |   " + str.substring(0, 64) + "   |");
            int remainingLength = length - 64;
            int leftover = remainingLength % 62;
            int n = (int) Math.floor(remainingLength/62);
            for (int i = 0; i < n; i++) {
                System.out.println("   |     " + str.substring(62 * i, 62 * (i + 1)) + "   |");
            }
            String message = "   |     " + str.substring(length - leftover);
            for (int i = 0; i < 65 - leftover; i++) {
                message += " ";
            }
            message += "|";
            System.out.println(message);
        }
    }

    static Task addTask(String[] strings) {
        int len = strings.length;
        if (len == 1) {
            tasks.add(new ToDo(strings[0]));
        } else if (len == 2) {
            tasks.add(new Deadline(strings[0], parseDateTime(strings[1])));
        } else if (len == 3) {
            tasks.add(new Event(strings[0], parseDateTime(strings[1]), parseDateTime(strings[2])));
        }
        saveTasks();
        return tasks.get(tasks.size() - 1);
    }

    static void printListMessage() {
        System.out.println(OPENING_LINE);
        printList();
        System.out.println(CLOSING_LINE);
    }

    static void printList() {
        int size = tasks.size();
        if (size == 0) {
            println("WOOHOO! You do not have any task on the list.");
        } else {
            println("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    static int parseInt(String userInput, CmdType type) throws
            EmptyListException, MissingInputException,
            MissingSpacingException, NumberFormatException,
            ArrayIndexOutOfBoundsException, UnspecifiedTimeException {
        checkForEmptyList(type);
        checkForMissingInput(userInput, type);
        checkForSpacingAftCmd(userInput, type);
        int idx = Integer.parseInt(userInput.substring(
                type.len() + 1)) - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return idx;
    }

    static void markOrUnmark(String userInput, boolean isMark) throws
            EmptyListException, MissingInputException,
            MissingSpacingException, NumberFormatException,
            ArrayIndexOutOfBoundsException, UnspecifiedTimeException {
        CmdType type = isMark ? CmdType.MARK : CmdType.UNMARK;
        int idx = parseInt(userInput, type);
        String start = isMark ? "Nice! I've" : "OK, I've";
        if (isMark == tasks.get(idx).isDone) {
            start = "You have already";
        }
        if (isMark) {
            tasks.get(idx).mark();
        } else {
            tasks.get(idx).unmark();
        }
        String end = isMark ? "done:" : "not done yet:";
        printMessage(start + " marked this task as " + end, "  " +
                tasks.get(idx));
        saveTasks();
    }

    static void deleteTask(String userInput) throws EmptyListException,
            MissingInputException, MissingSpacingException,
            NumberFormatException, ArrayIndexOutOfBoundsException,
            UnspecifiedTimeException {
        int idx = parseInt(userInput, CmdType.DELETE);
        Task removedTask = tasks.get(idx);
        tasks.remove(idx);
        saveTasks();
        int size = tasks.size();
        printMessage("Noted. I've removed this task:", "  " +
                removedTask, String.format(
                        "Now you have %d task%s in the list.", size,
                        size == 1 ? "" : "s"));
    }

    static void saveTasks() {
        String tasksStr = "";
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            tasksStr += tasks.get(i).saveAsStr() + "\n";
        }
        File dir = new File("data");
        // If folder doesn't exist, create it
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            // CREATE FILE
            String path = "data/Wessy.txt";
            File file = new File(path);
            file.createNewFile();
            // WRITE FILE
            FileWriter fw = new FileWriter(path);
            fw.write(tasksStr);
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
