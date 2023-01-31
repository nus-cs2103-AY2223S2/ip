package munch;

import AddTasks.Deadlines;
import AddTasks.Events;
import AddTasks.Task;
import AddTasks.Todo;
import Exceptions.IncompleteInputException;
import munch.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    public static void deleteTask(ArrayList<Task> tasks, int element) {
        Ui.deleteMessage();
        System.out.println(tasks.get(element));
        tasks.remove(element);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list ~~");
    }

    public static void addTodoTask(ArrayList<Task> tasks, String word) throws IncompleteInputException {
        String separator = "todo";
        int sepPos = word.indexOf(separator);
        String str = word.substring(sepPos + separator.length());
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
            String str = word.substring(sepPos1 + separator1.length(), sepPos2);
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
        if (word.contains("event") && word.contains("/from") && word.contains("/to")){
            String separator1 = "event";
            String separator2 = "/from";
            String separator3 = "/to";
            int sepPos1 = word.indexOf(separator1);
            int sepPos2 = word.indexOf(separator2);
            int sepPos3 = word.indexOf(separator3);
            String str = word.substring(sepPos1 + separator1.length(), sepPos2);
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
}
