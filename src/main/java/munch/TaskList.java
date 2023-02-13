package munch;

import AddTasks.Deadlines;
import AddTasks.Events;
import AddTasks.Task;
import AddTasks.Todo;
import Exceptions.IncompleteInputException;
import Exceptions.InvalidInputException;
import javafx.application.Platform;
import munch.Parser;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    public static ArrayList<String> getResponse(ArrayList<Task> tasks, String word, String[] words) throws IncompleteInputException, InvalidInputException {
        ArrayList<String> lines = new ArrayList<>();
        try {
            if (word.equals("list")) {
                lines = listCommand(tasks);
            } else if (words[0].equals("mark") || words[0].equals("unmark")) {
                int i = Integer.parseInt(words[1]) - 1;
                tasks.get(i).marking(words[0]);
                lines.add(tasks.get(i).marking(words[0]));
            } else if (words[0].equals("delete")) {
                int i = Integer.parseInt(words[1]) - 1;
                lines.add(deleteTask(tasks, i));
            } else if (words[0].equals("todo")) {
                lines.add(addTodoTask(tasks, word));
            } else if (word.contains("deadline") && word.contains("/by")) {
                lines.add(addDeadlineTask(tasks, word));
            } else if (word.contains("event") && word.contains("/from") && word.contains("/to")) {
                lines.add(addEventTask(tasks, word));
            } else if (words[0].equals("find")) {
                lines = findMatchingTasks(tasks, word);
            } else if (word.equals("bye")) {
                lines.add(Ui.exitMessage());
                Platform.exit();
            } else {
                throw new InvalidInputException();
            }
        } catch (InvalidInputException e) {
            lines.add(Ui.wrongInputMessage());
        }
        return lines;
    }

    /**
     * Removes a task object from the arrayList.
     * Prints a deleted message.
     * Prints the remaining number of task in the arrayList.
     * @param tasks arrayList to be deleted from.
     * @param element Integer of the element in the arrayList to be deleted.
     */
    public static String deleteTask(ArrayList<Task> tasks, int element) {
        assert element >= 0 : "Invalid element";
        Task removedElement = tasks.get(element);
        tasks.remove(element);
        return Ui.deleteMessage() + "\n" + removedElement + "\n" + "Now you have " + tasks.size() + " task(s) in the list ~~";
    }

    public static String addTodoTask(ArrayList<Task> tasks, String word) {
        assert word.length() > 5 : "Invalid input";
        String separator = "todo";
        int sepPos = word.indexOf(separator);
        String str = word.substring(sepPos + separator.length() + 1);
        Todo todos = new Todo(str);
        if (isDuplicate(tasks, str)) {
            return Ui.duplicateTaskMessage();
        } else {
            tasks.add(todos);
            return Ui.addTaskMessage() + "\n" + todos + "\n" + "Now you have " + tasks.size() + " task(s) in the list ~~";
        }
    }

    public static String addDeadlineTask(ArrayList<Task> tasks, String word) {
        String separator1 = "deadline";
        String separator2 = "/by";
        int sepPos1 = word.indexOf(separator1);
        int sepPos2 = word.indexOf(separator2);
        String str = word.substring(sepPos1 + separator1.length() + 1, sepPos2 - 1);
        String date = word.substring(sepPos2 + 1 + separator2.length());
        LocalDate convertDate = Parser.convertToDate(date);
        Deadlines deadline = new Deadlines(str, convertDate);
        if (isDuplicate(tasks, str)) {
            return Ui.duplicateTaskMessage();
        } else {
            tasks.add(deadline);
            return Ui.addTaskMessage() + "\n" + deadline + "\n" + "Now you have " + tasks.size() + " task(s) in the list ~~";
        }
    }

    public static String addEventTask(ArrayList<Task> tasks, String word) {
        String separator1 = "event";
        String separator2 = "/from";
        String separator3 = "/to";
        int sepPos1 = word.indexOf(separator1);
        int sepPos2 = word.indexOf(separator2);
        int sepPos3 = word.indexOf(separator3);
        String str = word.substring(sepPos1 + separator1.length() + 1, sepPos2 - 1);
        String from = word.substring(sepPos2 + 1 + separator2.length(), sepPos3 - 1);
        String to = word.substring(sepPos3 + 1 + separator3.length());
        LocalDate convertFrom = Parser.convertToDate(from);
        LocalDate convertTo = Parser.convertToDate(to);
        Events event = new Events(str, convertFrom, convertTo);
        if (isDuplicate(tasks, str)) {
            return Ui.duplicateTaskMessage();
        } else {
            tasks.add(event);
            return Ui.addTaskMessage() + "\n" + event + "\n" + "Now you have " + tasks.size() + " task(s) in the list ~~";
        }
    }

    public static ArrayList<String> findMatchingTasks(ArrayList<Task> tasks, String word) {
        ArrayList<Task> keywords = new ArrayList<>();
        ArrayList<String> keywordList = new ArrayList<>();
        String separator = "find";
        int sepPos = word.indexOf(separator);
        String keyword = word.substring(sepPos + separator.length() + 1);
        assert keyword.length() > 0 : "Invalid input";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                keywords.add(tasks.get(i));
            }
        }
        keywordList.add(Ui.findMessage(keyword));
        for (int i = 0; i < keywords.size(); i++) {
            int label = i + 1;
            keywordList.add(label + "." + keywords.get(i).toString());
        }
        return keywordList;
    }

    public static ArrayList<String> listCommand(ArrayList<Task> tasks) {
        ArrayList<String> list = new ArrayList<>();
        list.add(Ui.listMessage());
        for (int i = 0; i < tasks.size(); i++) {
            int label = i + 1;
            list.add(label + "." + tasks.get(i).toString());
        }
        return list;
    }

    public static Boolean isDuplicate(ArrayList<Task> tasks, String word) {
        boolean isDuplicate = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(word)) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }
}
