import Exceptions.CommandNotFoundException;
import Exceptions.EmptyListException;
import Exceptions.MissingInputException;
import Exceptions.MissingSpacingException;
import Exceptions.UnspecifiedTimeException;
import Exceptions.WessyException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Wessy {
    static String OPENING_LINE = "    -Wessy---------------------------------------------------------------- ";
    static String CLOSING_LINE = "    ---------------------------------------------------------------------- ";
    static List<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        System.out.println(OPENING_LINE);
        println("Hi, I am Wessy, your personal assistant chatbot.");
        println("Please type something to interact with me.");
        System.out.println(CLOSING_LINE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (checkCmd(userInput, "bye")) {
                printNormal("Bye. Hope to see you again soon!");
                break;
            } else if (checkCmd(userInput, "list")) {
                printList();
            } else {
                try{
                    if (checkCmd(userInput, "mark")) {
                        markOrUnmark(userInput, true);
                    } else if (checkCmd(userInput,"unmark")) {
                        markOrUnmark(userInput, false);
                    } else if (checkCmd(userInput, "delete")) {
                        delete(userInput);
                    } else if (checkCmd(userInput, "todo")) {
                        printAdded(add(parse(userInput, "todo")));
                    } else if (checkCmd(userInput, "deadline")) {
                        printAdded(add(parse(userInput, "deadline")));
                    } else if (checkCmd(userInput, "event")) {
                        printAdded(add(parse(userInput, "event")));
                    } else {
                        throw new CommandNotFoundException();
                    }
                } catch (WessyException wEx) {
                    printNormal(String.valueOf(wEx));
                } catch (NumberFormatException nfe) {
                    printNormal("☹ OOPS!!! It is not a number. Please enter a number.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    printNormal("☹ OOPS!!! Please enter a valid task number.");
                }
            }
        }
    }

    static String[] parse(String description, String cmd) throws MissingSpacingException, MissingInputException, UnspecifiedTimeException {
        checkForMissingInput(description, cmd);
        checkForSpacingAftCmd(description, cmd);
        String byStr = "/by";
        String fromStr = "/from";
        String toStr = "/to";
        int firstIdx;
        int secondIdx;
        description = description.substring(cmd.length() + 1);
        if (cmd.charAt(0) == 'd') {
            firstIdx = description.indexOf(byStr);
            return new String[] {description.substring(0, firstIdx - 1),
                    description.substring(firstIdx + byStr.length() + 1)};
        } else if (cmd.charAt(0) == 'e') {
            firstIdx = description.indexOf(fromStr);
            secondIdx = description.indexOf(toStr);
            return new String[] {description.substring(0, firstIdx - 1),
                    description.substring(firstIdx + fromStr.length() + 1, secondIdx - 1),
                    description.substring(secondIdx + toStr.length() + 1)};
        } else if (cmd.charAt(0) == 't') {
            return new String[] {description};
        }
        return new String[] {};
    }

    static boolean checkCmd(String userInput, String cmd) {
        int threshold = cmd.length();
        return userInput.length() >= threshold && userInput.substring(0,threshold).equalsIgnoreCase(cmd);
    }

    static void checkForSpacingAftCmd(String userInput, String cmd) throws MissingSpacingException {
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

    static void checkForMissingInput(String userInput, String cmd) throws MissingInputException, MissingSpacingException, UnspecifiedTimeException {
        if (userInput.equalsIgnoreCase(cmd) || checkAllSpaces(userInput.substring(cmd.length()))) {
            throw new MissingInputException(cmd);
        }
        if (cmd.equals("deadline")) {
            checkForTimeKeywordEx(userInput, "/by");
            int idxOfBy = userInput.indexOf("/by");
            if (idxOfBy == 8 || checkAllSpaces(userInput.substring(8, idxOfBy))) {
                throw new MissingInputException(cmd);
            }
            if (idxOfBy + 3 == userInput.length() || checkAllSpaces(userInput.substring(idxOfBy + 3))) {
                throw new UnspecifiedTimeException("/by");
            }
        }
        if (cmd.equals("event")) {
            checkForTimeKeywordEx(userInput, "/from");
            int idxOfFrom = userInput.indexOf("/from");
            if (idxOfFrom == 5 || checkAllSpaces(userInput.substring(5, idxOfFrom))) {
                throw new MissingInputException(cmd);
            }
            checkForTimeKeywordEx(userInput, "/to");
            int idxOfTo = userInput.indexOf("/to");
            if (idxOfTo == idxOfFrom + 5 || checkAllSpaces(userInput.substring(idxOfFrom + 5, idxOfTo))) {
                throw new UnspecifiedTimeException("/from");
            }
            if (idxOfTo + 3 == userInput.length() || checkAllSpaces(userInput.substring(idxOfTo + 3))) {
                throw new UnspecifiedTimeException("/to");
            }
        }

    }

    static void checkForTimeKeywordEx(String userInput, String keyword) throws UnspecifiedTimeException, MissingSpacingException {
        int idx = userInput.indexOf(keyword);
        if (idx == -1) {
            throw new UnspecifiedTimeException(keyword);
        }
        if (userInput.charAt(idx - 1) != ' ') {
            throw new MissingSpacingException(keyword, false);
        }
        if (userInput.length() == idx + keyword.length() || userInput.charAt(idx + keyword.length()) != ' ') {
            throw new MissingSpacingException(keyword, true);
        }
    }

    static void checkForEmptyList(String cmd) throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException(cmd);
        }
    }

    static void printNormal(String... linesOfString) {
        System.out.println(OPENING_LINE);
        for (String line : linesOfString) {
            println(line);
        }
        System.out.println(CLOSING_LINE);
    }

    static void printAdded(Task task) {
        int size = tasks.size();
        String numOfTasks = " " + size + " task";
        if (size > 1) {
            numOfTasks += "s";
        }
        printNormal("Got it. I've added this task:",
                "  " + task, "Now you have" + numOfTasks + " in the list.");
    }

    static void println(String str) {
        int length = str.length();
        String message = "   |   " + str;
        for (int i = 0; i < 70 - length - 3; i++)
            message += " ";
        message += "|";
        System.out.println(message);
    }

    static Task add(String[] strings) {
        int len = strings.length;
        if (len == 1) {
            tasks.add(new ToDo(strings[0]));
        } else if (len == 2) {
            tasks.add(new Deadline(strings[0], strings[1]));
        } else if (len == 3) {
            tasks.add(new Event(strings[0], strings[1], strings[2]));
        }
        return tasks.get(tasks.size() - 1);
    }

    static void printList() {
        int size = tasks.size();
        System.out.println(OPENING_LINE);
        if (size == 0) {
            println("WOOHOO! You do not have any task on the list.");
        } else {
            println("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                println((i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(CLOSING_LINE);
    }

    static int parseInt(String userInput, String cmd) throws EmptyListException, MissingInputException, MissingSpacingException, NumberFormatException, ArrayIndexOutOfBoundsException, UnspecifiedTimeException {
        checkForEmptyList(cmd);
        checkForMissingInput(userInput, cmd);
        checkForSpacingAftCmd(userInput, cmd);
        int idx = Integer.parseInt(userInput.substring(cmd.length() + 1)) - 1;
        if (idx < 0 || idx >= tasks.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return idx;
    }

    static void markOrUnmark(String userInput, boolean isMark) throws EmptyListException, MissingInputException, MissingSpacingException, NumberFormatException, ArrayIndexOutOfBoundsException, UnspecifiedTimeException {
        String cmd = isMark ? "mark" : "unmark";
        int idx = parseInt(userInput, cmd);
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
        printNormal(start + " marked this task as " + end, "  " + tasks.get(idx));
    }

    static void delete(String userInput) throws EmptyListException, MissingInputException, MissingSpacingException, NumberFormatException, ArrayIndexOutOfBoundsException, UnspecifiedTimeException {
        int idx = parseInt(userInput, "delete");
        Task removedTask = tasks.get(idx);
        tasks.remove(idx);
        int size = tasks.size();
        printNormal("Noted. I've removed this task:", "  " + removedTask, String.format("Now you have %d task%s in the list.", size, size == 1 ? "" : "s"));
    }
}
