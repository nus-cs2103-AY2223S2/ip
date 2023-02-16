package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidIndexException;

/**
 * Parser class.
 * Handles user input.
 */
public class Parser {

    protected String answer;

    /**
     * Constructor for Parser object.
     */
    public Parser() {}
    /**
     * Constructor for Parser object.
     * @param answer Command user inputs.
     */
    public Parser(String answer) {
        this.answer = answer;
    }

    /**
     * Returns the index of the task which the user wants to mark.
     * @param tasksLength Length of task list.
     * @return Integer of index of task.
     * @throws DukeException If no index is given or index is out of bounds.
     */
    public Integer getMarkIndex(int tasksLength) throws DukeException {
        if (answer.substring(5, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Mark cannot be empty");
        }
        int index = Integer.valueOf(answer.substring(5, answer.length()));
        if (index <= 0 || index > tasksLength) {
            throw new DukeInvalidIndexException("That index is out of bounds");
        }
        return index;
    }

    /**
     * Returns the index of the task which the user wants to unmark.
     * @param tasksLength Length of task list.
     * @return Integer of index of task.
     * @throws DukeException If no index is given or index is out of bounds.
     */
    public Integer getUnmarkIndex(int tasksLength) throws DukeException {
        if (answer.substring(7, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Unmark cannot be empty");
        }
        int index = Integer.valueOf(answer.substring(7, answer.length()));
        if (index <= 0 || index > tasksLength) {
            throw new DukeInvalidIndexException("That index is out of bounds");
        }
        return index;
    }

    /**
     * Returns the index of the task which the user wants to delete.
     * @param tasksLength Length of task list.
     * @return Integer of index of task.
     * @throws DukeException If no index is given or index is out of bounds.
     */
    public Integer getDeleteIndex(int tasksLength) throws DukeException {
        if (answer.substring(7, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Delete cannot be empty");
        }

        int index = Integer.valueOf(answer.substring(7, answer.length()));

        if (index <= 0 || index > tasksLength) {
            throw new DukeInvalidIndexException("That index is out of bounds");
        }

        return index;
    }


    /**
     * Returns the description of todo task the user inputs.
     * @return Description of task.
     * @throws DukeInvalidArgumentException If description input by user is empty.
     */
    public String getTodoDescription() throws DukeInvalidArgumentException {

        if (answer.substring(5, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Todo cannot be empty");
        }

        String description = answer.substring(5, answer.length());
        return description;
    }

    /**
     * Returns the description of deadline task the user inputs.
     * @return Description of task.
     * @throws DukeInvalidArgumentException If description input by user is empty.
     */
    public String[] getDeadlineDescription() throws DukeInvalidArgumentException {

        var arr = answer.substring(9, answer.length()).split("//");
        if (answer.substring(9, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Deadline cannot be empty!");
        }
        if (arr.length != 2) {
            throw new DukeInvalidArgumentException("Too many or little arguments");
        }
        if (arr[0].isEmpty()) {
            throw new DukeInvalidArgumentException("No empty task allowed!");
        }

        if (arr[1].isEmpty()) {
            throw new DukeInvalidArgumentException("No empty date allowed!");
        }

        return arr;

    }


    /**
     * Returns the LocalDateTime of a string input.
     * @return LocalDateTime of string.
     * @throws DukeInvalidArgumentException If format of input is wrong.
     */
    public LocalDateTime getAsLocalDate(String s) throws DukeInvalidArgumentException {
        LocalDateTime date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            date = LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("Wrong date/time format (correct: yyyy-MM-DD hh:mm), "
                    + "please input task again");
        }
        return date;
    }


    /**
     * Returns the description of event task the user inputs.
     * @return Description of task.
     * @throws DukeInvalidArgumentException If description input by user is empty.
     */
    public String[] getEventDescription() throws DukeInvalidArgumentException {
        var arr = answer.substring(6, answer.length()).split("//");
        if (answer.substring(6, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Event cannot be empty!");
        }
        if (arr.length != 3) {
            throw new DukeInvalidArgumentException("Too many or little arguments");
        }
        if (arr[0].isEmpty()) {
            throw new DukeInvalidArgumentException("No empty task allowed!");
        }

        if (arr[1].isEmpty() || arr[2].isEmpty()) {
            throw new DukeInvalidArgumentException("No empty date allowed!");
        }

        return arr;
    }





    /**
     * Returns the keyword the user inputs.
     * @return Keyword.
     * @throws DukeInvalidArgumentException If keyword is empty.
     */
    public String getFindKeyword() throws DukeInvalidArgumentException {
        if (answer.substring(5, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Keyword is empty, please input command again");
        }
        return answer.substring(5, answer.length());
    }
}
