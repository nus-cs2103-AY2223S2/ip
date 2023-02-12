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
import storage.Storage;

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
     * Returns Response output of the command input.
     *
     * @param input Command input.
     * @param ui User Interface.
     * @param list List of tasks.
     * @return Response output of the command input.
     */
    public String parse(String input, Ui ui, TaskList list, Storage storage) {
        try {
            String[] inputWords = input.split(" ", 2);
            String command = inputWords[0];
            if (command.equals(EXIT_COMMAND)) {
                ui.isClosed = true;
                return ui.printBye(list, storage);
            } else if (command.equals(LIST_COMMAND)) {
                return ui.printGetList(list);
            } else if (command.equals(FIND_COMMAND)) {
                return this.handleFind(inputWords, ui, list);
            } else if (isMark(command)) {
                return this.handleMark(inputWords, ui, list);
            } else if (isUnmark(command)) {
                return this.handleUnmark(inputWords, ui, list);
            } else if (isToDo(command)) {
                return this.handleToDo(inputWords, ui, list);
            } else if (isDeadline(command)) {
                return this.handleDeadline(inputWords, ui, list);
            } else if (isEvent(command)) {
                return this.handleEvent(inputWords, ui, list);
            } else if (command.equals(DELETE_COMMAND)) {
                return this.handleDelete(inputWords, ui, list);
            } else {
                throw new InvalidInputException();
            }
        } catch (Exception e) {
            return ui.printException(e);
        }
    }

    /**
     * Handles finding tasks based on a search word
     * Returns found tasks
     *
     * @param inputWords Command input.
     * @param ui User interface.
     * @param list List of tasks.
     * @return Found tasks
     */
    public String handleFind(String[] inputWords, Ui ui, TaskList list) {
        String searchWord = inputWords[1];
        return ui.printFind(list, searchWord);
    }

    /**
     * Handles deleting of task.
     * Returns response of deleting task.
     *
     * @param inputWords Command input.
     * @param ui User interface.
     * @param list List of tasks.
     * @return Response of deleting task.
     */
    public String handleDelete(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        list.removeTask(index - 1);
        return ui.printHandleDelete(task, list);
    }

    /**
     * Handles marking of task.
     * Returns task marked.
     *
     * @param inputWords Command input.
     * @param ui User interface.
     * @param list List of tasks.
     * @return Task marked response.
     */
    public String handleMark(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        task.mark();
        return ui.printHandleMark(task);
    }

    /**
     * Handles unmarking of task.
     * Returns unmarked task response.
     *
     * @param inputWords Command input.
     * @param ui User interface.
     * @param list List of tasks.
     * @return Unmarked task response.
     */
    public String handleUnmark(String[] inputWords, Ui ui, TaskList list) {
        int index = Integer.parseInt(inputWords[1]);
        Task task = list.getTask(index - 1);
        task.unmark();
        return ui.printHandleUnmark(task);
    }

    /**
     * Handles ToDo task.
     * Returns response of added task.
     *
     * @param inputWords Description of task.
     * @param ui User interface.
     * @param list List of tasks.
     * @throws EmptyDescriptionException If there is no description of task.
     * @return Response of added task.
     */
    public String handleToDo(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String description = inputWords[1];
        Task newTask = new ToDo(description);
        list.addTask(newTask);
        return ui.printAddTask(newTask, list);
    }

    /**
     * Handles Deadline task.
     * Returns response of added task.
     *
     * @param inputWords Description of task.
     * @param ui User interface.
     * @param list List of tasks.
     * @throws EmptyDescriptionException If there is no description of task.
     * @return response of adeded task.
     */
    public String handleDeadline(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /by ");
        String action = splitedString[0];
        String date = splitedString[1]; // in yyyy/mm/d HHMM format
        LocalDateTime inputDateTime = LocalDateTime.parse(date, inputFormatter);
        String outputDateTime = inputDateTime.format(outputFormatter);
        Task newTask = new Deadline(action, outputDateTime);
        list.addTask(newTask);
        return ui.printAddTask(newTask, list);
    }

    /**
     * Handles Event task.
     * Returns response of adding task.
     *
     * @param inputWords Description of task.
     * @param ui User interface.
     * @param list List of tasks.
     * @throws EmptyDescriptionException If there is no description of task.
     * @return Response of adding task.
     */
    public String handleEvent(String[] inputWords, Ui ui, TaskList list) throws EmptyDescriptionException {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /from ");
        String action = splitedString[0];
        String duration = splitedString[1];
        String[] fromTo = duration.split(" /to ");
        String from = fromTo[0];
        String to = fromTo[1];
        Task newTask = new Event(action, from, to);
        list.addTask(newTask);
        return ui.printAddTask(newTask, list);
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
