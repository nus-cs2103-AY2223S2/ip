package munch;

import AddTasks.Deadlines;
import AddTasks.Events;
import AddTasks.Task;
import AddTasks.Todo;
import Exceptions.IncompleteInputException;
import Exceptions.InvalidInputException;
import munch.Parser;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    public static void run(ArrayList<Task> tasks, String word, String[] words) throws IncompleteInputException, InvalidInputException {
        if (word.equals("list")) {
            Ui.listMessage();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        } else if (words[0].equals("mark") || words[0].equals("unmark")) {
            int i = Integer.parseInt(words[1]) - 1;
            tasks.get(i).marking(words[0]);
        } else if (words[0].equals("delete")) {
            int i = Integer.parseInt(words[1]) - 1;
            deleteTask(tasks, i);
        } else if (words[0].equals("todo")) {
            addTodoTask(tasks, word);
        } else if (words[0].equals("deadline")) {
            addDeadlineTask(tasks, word);
        } else if (words[0].equals("event")) {
            addEventTask(tasks, word);
        } else if (words[0].equals("find")) {
            ArrayList<Task> keywordList = findMatchingTasks(tasks, word);
            for (int i = 0; i < keywordList.size(); i++) {
                System.out.println((i + 1) + "." + keywordList.get(i).toString());
            }
        } else {
            throw new InvalidInputException();
        }
        Ui.divider();
    }

    /**
     * Removes a task object from the arrayList.
     * Prints a deleted message.
     * Prints the remaining number of task in the arrayList.
     * @param tasks arrayList to be deleted from.
     * @param element Integer of the element in the arrayList to be deleted.
     */
    public static void deleteTask(ArrayList<Task> tasks, int element) {
        Ui.deleteMessage();
        System.out.println(tasks.get(element));
        tasks.remove(element);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");
    }

    public static void addTodoTask(ArrayList<Task> tasks, String word) throws IncompleteInputException {
        String separator = "todo";
        int sepPos = word.indexOf(separator);
        String str = word.substring(sepPos + separator.length() + 1);
        if (str.length() != 0) {
            Todo todos = new Todo(str);
            tasks.add(todos);
            Ui.addTaskMessage();
            System.out.println(todos);
            System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");
        } else {
            throw new IncompleteInputException();
        }
    }

    public static void addDeadlineTask(ArrayList<Task> tasks, String word) throws IncompleteInputException {
        if (word.contains("deadline") && word.contains("/by")){
            String separator1 = "deadline";
            String separator2 = "/by";
            int sepPos1 = word.indexOf(separator1);
            int sepPos2 = word.indexOf(separator2);
            String str = word.substring(sepPos1 + separator1.length() + 1, sepPos2 - 1);
            String date = word.substring(sepPos2 + 1 + separator2.length());
            try {
                LocalDate convertDate = Parser.convertToDate(date);
                Deadlines deadline = new Deadlines(str, convertDate);
                tasks.add(deadline);
                Ui.addTaskMessage();
                System.out.println(deadline);
                System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");
            } catch (DateTimeParseException e) {
                Ui.wrongDateFormatMessage();
            }
        } else {
            throw new IncompleteInputException();
        }
    }

    public static void addEventTask(ArrayList<Task> tasks, String word) throws IncompleteInputException {
        if (word.contains("event") && word.contains("/from") && word.contains("/to")) {
            String separator1 = "event";
            String separator2 = "/from";
            String separator3 = "/to";
            int sepPos1 = word.indexOf(separator1);
            int sepPos2 = word.indexOf(separator2);
            int sepPos3 = word.indexOf(separator3);
            String str = word.substring(sepPos1 + separator1.length() + 1, sepPos2 - 1);
            String from = word.substring(sepPos2 + 1 + separator2.length(), sepPos3 - 1);
            String to = word.substring(sepPos3 + 1 + separator3.length());
            try {
                LocalDate convertFrom = Parser.convertToDate(from);
                LocalDate convertTo = Parser.convertToDate(to);
                Events event = new Events(str, convertFrom, convertTo);
                tasks.add(event);
                Ui.addTaskMessage();
                System.out.println(event);
                System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");
            } catch (DateTimeParseException e) {
                Ui.wrongDateFormatMessage();
            }
        } else {
            throw new IncompleteInputException();
        }
    }

    public static ArrayList<Task> findMatchingTasks(ArrayList<Task> tasks, String word) throws IncompleteInputException {
        ArrayList<Task> keywordList = new ArrayList<>();
        if (word.contains("find")) {
            String separator = "find";
            int sepPos = word.indexOf(separator);
            String keyword = word.substring(sepPos + separator.length() + 1);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).description.contains(keyword)) {
                    keywordList.add(tasks.get(i));
                }
            }
        } else {
            throw new IncompleteInputException();
        }
        return keywordList;
    }
}
