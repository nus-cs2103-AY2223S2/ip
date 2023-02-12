package parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke_exception.InvalidInputException;
import duke_exception.EmptyDescriptionException;
import ui.Ui;
import task.Task;
import tasklist.TaskList;
import task.ToDo;
import task.Deadline;
import task.Event;

/**
 * Parser Class deals with making sense of the user's command.
 */

public class Parser {
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";
    final static String DELETE_COMMAND = "delete";
    final static String MARK_COMMAND = "mark";
    final static String UNMARK_COMMAND = "unmark";
    final static String TODO_COMMAND = "todo";
    final static String DEADLINE_COMMAND = "deadline";
    final static String EVENT_COMMAND = "event";
    final static String FIND_COMMAND = "find";
    final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/d HHmm");
    final static DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Handles what to do based on the command input.
     *
     * @param input Command input.
     * @param ui User Interface .
     * @param list List of tasks.
     */
    public void parse(String input, Ui ui, TaskList list) {
        try {
            String[] inputWords = input.split(" ", 2);
            String command = inputWords[0];
            if (command.equals(EXIT_COMMAND)) {
                ui.printBye();
                ui.isClosed = true;
            } else if (command.equals(LIST_COMMAND)) {
                ui.printGetList(list);
            } else if (command.equals(FIND_COMMAND)) {
                this.handleFind(inputWords, ui, list);
            } else if (isMark(command)) {
                this.handleMark(inputWords, ui, list);
            } else if (isUnmark(command)) {
                this.handleUnmark(inputWords, ui, list);
            } else if (isToDo(command)) {
                this.handleToDo(inputWords, ui, list);
            } else if (isDeadline(command)) {
                this.handleDeadline(inputWords, ui, list);
            } else if (isEvent(command)) {
                this.handleEvent(inputWords, ui, list);
            } else if (command.equals(DELETE_COMMAND)) {
                this.handleDelete(inputWords, ui, list);
            } else {
                throw new InvalidInputException();
            }
        } catch (Exception e) {
            ui.printException(e);
        }
    }

    /**
     * Handles finding tasks based on a search word.
     *
     * @param inputWords Command input.
     * @param ui User interface.
     * @param list List of tasks.
     */
    public void handleFind(String[] inputWords, Ui ui, TaskList list) {
        String searchWord = inputWords[1];
        ui.printFind(list, searchWord);
    }

    /**
     * Handles deleting of task
     *
     * @param inputWords Command input.
     * @param ui User interface.
     * @param list List of tasks.
     */
    public void handleDelete(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        list.removeTask(index-1);
        ui.printHandleDelete(task, list);
    }

    /**
     * Handles marking of task
     *
     * @param inputWords Command input.
     * @param ui User interface.
     * @param list List of tasks.
     */
    public void handleMark(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        task.mark();
        ui.printHandleMark(task);
    }

    /**
     * Handles unmarking of task
     *
     * @param inputWords Command input.
     * @param ui User interface.
     * @param list List of tasks.
     */
    public void handleUnmark(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        task.unmark();
        ui.printHandleUnmark(task);
    }

    /**
     * Handles ToDo task.
     *
     * @param inputWords Description of task.
     * @param ui User interface.
     * @param list List of tasks.
     * @throws EmptyDescriptionException If there is no description of taks.
     */
    public void handleToDo(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String description = inputWords[1];
        Task newTask = new ToDo(description);
        list.addTask(newTask);
        ui.printAddTask(newTask, list);
    }

    /**
     * Handles Deadline task.
     *
     * @param inputWords Description of task.
     * @param ui User interface.
     * @param list List of tasks.
     * @throws EmptyDescriptionException If there is no description of task.
     */
    public void handleDeadline(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /by ");
        String action = splitedString[0];
        String date = splitedString[1]; // in yyyy/mm/d HHMM format
        LocalDateTime inputDateTime = LocalDateTime.parse(date, inputFormatter);
        String outputDateTime = inputDateTime.format(outputFormatter);
        Task newTask = new Deadline(action, outputDateTime);
        list.addTask(newTask);
        ui.printAddTask(newTask, list);
    }

    /**
     * Handles Event task.
     *
     * @param inputWords Description of task.
     * @param ui User interface.
     * @param list List of tasks.
     * @throws EmptyDescriptionException If there is no description of task.
     */
    public void handleEvent(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /from ");
        String action = splitedString[0];
        String duration = splitedString[1];
        String[] fromTo = duration.split(" /to ");
        String from = fromTo[0];
        String to = fromTo[1];
        Task newTask = new Event(action, from, to);
        list.addTask(newTask);
        ui.printAddTask(newTask, list);
    }

    /**
     * Check if the description of the task is empty.
     *
     * @param inputWords Description of the task.
     * @throws EmptyDescriptionException If there is no description of task.
     */
    public void checkEmptyDescription(String[] inputWords) throws EmptyDescriptionException {
        if (inputWords.length < 2) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Checks if user wants to mark a task.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isMark(String word) {
        return word.equals(MARK_COMMAND);
    }

    /**
     * Checks if user wants to unmark a task.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isUnmark(String word) {
        return word.equals(UNMARK_COMMAND);
    }

    /**
     * Checks if it is a ToDo command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isToDo(String word) {
        return word.equals(TODO_COMMAND);
    }

    /**
     * Checks if it is a Deadline command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isDeadline(String word) {
        return word.equals(DEADLINE_COMMAND);
    }

    /**
     * Checks if it is a Event command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isEvent(String word) {
        return word.equals(EVENT_COMMAND);
    }

}
