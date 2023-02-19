package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dukeexceptions.DatetimeException;
import dukeexceptions.EmptyDescriptionException;
import dukeexceptions.InvalidInputException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;

/**
 * Parser Class deals with making sense of the user's command.
 */

public class Parser {
    private static final String EXIT_COMMAND = "bye";
    private static final String B_COMMAND = "b";
    private static final String LIST_COMMAND = "list";
    private static final String L_COMMAND = "l";
    private static final String DELETE_COMMAND = "delete";
    private static final String CAP_D_COMMAND = "D";
    private static final String MARK_COMMAND = "mark";
    private static final String M_COMMAND = "m";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String U_COMMAND = "u";
    private static final String TODO_COMMAND = "todo";
    private static final String T_COMMAND = "t";
    private static final String D_COMMAND = "d";
    private static final String E_COMMAND = "e";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String FIND_COMMAND = "find";
    private static final String F_COMMAND = "f";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/d HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

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
            if (isExit(command)) {
                ui.close();
                return ui.printBye(list, storage);
            } else if (isList(command)) {
                return ui.printGetList(list);
            } else if (isFind(command)) {
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
            } else if (isDelete(command)) {
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
     * @return Response of added task.
     * @throws EmptyDescriptionException If there is no description of task.
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
     * @return response of added task.
     * @throws EmptyDescriptionException If there is no description of task.
     */
    public String handleDeadline(String[] inputWords, Ui ui, TaskList list) throws Exception {
        checkEmptyDescription(inputWords);
        String[] splitedString = inputWords[1].split(" /by ");
        String action = splitedString[0];
        String date = splitedString[1];
        if (validDatetimeFormat(date)) {
            LocalDateTime inputDateTime = LocalDateTime.parse(date, INPUT_FORMATTER);
            String outputDateTime = inputDateTime.format(OUTPUT_FORMATTER);
            Task newTask = new Deadline(action, outputDateTime);
            list.addTask(newTask);
            return ui.printAddTask(newTask, list);
        } else {
            throw new DatetimeException();
        }
    }

    /**
     * Handles Event task.
     * Returns response of adding task.
     *
     * @param inputWords Description of task.
     * @param ui User interface.
     * @param list List of tasks.
     * @return Response of adding task.
     * @throws EmptyDescriptionException If there is no description of task.
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
     * Checks if datetime given is in the correct format.
     *
     * @param datetime Datetime of deadline task.
     * @return Validity of datetime format.
     */
    public boolean validDatetimeFormat(String datetime) {
        try {
            LocalDateTime.parse(datetime, INPUT_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if user wants to mark a task.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isMark(String word) {
        return word.equals(MARK_COMMAND) || word.equals(M_COMMAND);
    }

    /**
     * Checks if user wants to unmark a task.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isUnmark(String word) {
        return word.equals(UNMARK_COMMAND) || word.equals(U_COMMAND);
    }

    /**
     * Checks if it is a ToDo command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isToDo(String word) {
        return word.equals(TODO_COMMAND) || word.equals(T_COMMAND);
    }

    /**
     * Checks if it is a Deadline command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isDeadline(String word) {
        return word.equals(DEADLINE_COMMAND) || word.equals(D_COMMAND);
    }

    /**
     * Checks if it is a Event command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isEvent(String word) {
        return word.equals(EVENT_COMMAND) || word.equals(E_COMMAND);
    }

    /**
     * Checks if command is a Delete command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isDelete(String word) {
        return word.equals(DELETE_COMMAND) || word.equals(CAP_D_COMMAND);
    }
    /**
     * Checks if command is a Exit command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isExit(String word) {
        return word.equals(EXIT_COMMAND) || word.equals(B_COMMAND);
    }
    /**
     * Checks if command is a Find command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isFind(String word) {
        return word.equals(FIND_COMMAND) || word.equals(F_COMMAND);
    }
    /**
     * Checks if command is a List command.
     *
     * @param word First word of the user command.
     * @return Yes or no in the form of a boolean, where true is yes and false is no.
     */
    public boolean isList(String word) {
        return word.equals(LIST_COMMAND) || word.equals(L_COMMAND);
    }

}
