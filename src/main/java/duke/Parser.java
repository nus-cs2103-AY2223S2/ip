package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.exception.DukeException;

import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidIndexException;
public class Parser {

    protected String answer;

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
        return answer.substring(5, answer.length());
    }

    /**
     * Returns the description of deadline task the user inputs.
     * @return Description of task.
     * @throws DukeInvalidArgumentException If description input by user is empty.
     */
    public String getDeadlineDescription() throws DukeInvalidArgumentException {
        if (answer.substring(9, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Deadline cannot be empty");
        }
        return answer.substring(9, answer.length());
    }

    /**
     * Returns the due date of deadline task the user inputs.
     * @return Due date of task.
     * @throws DukeInvalidArgumentException If description input by user is empty.
     */
    public LocalDateTime getDeadlineBy(String byString) throws DukeInvalidArgumentException{
        if (byString.isEmpty()) {
            throw new DukeInvalidArgumentException("By is empty, please input task again");
        }
        LocalDateTime by;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            by = LocalDateTime.parse(byString, formatter);
        } catch (DateTimeParseException e){
            throw new DukeInvalidArgumentException("Wrong date/time format (correct: yyyy-MM-DD hh:mm), please input task again");
        }
        return by;
    }


    /**
     * Returns the description of event task the user inputs.
     * @return Description of task.
     * @throws DukeInvalidArgumentException If description input by user is empty.
     */
    public String getEventDescription() throws DukeInvalidArgumentException {
        if (answer.substring(6, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Event cannot be empty");
        }
        return answer.substring(6, answer.length());
    }


    /**
     * Returns the start date of event task the user inputs.
     * @return Start date of task.
     * @throws DukeInvalidArgumentException If description input by user is empty.
     */
    public LocalDateTime getEventFrom(String fromString) throws DukeInvalidArgumentException {
        if (fromString.isEmpty()) {
            throw new DukeInvalidArgumentException("From is empty, please input task again");
        }
        LocalDateTime from;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            from = LocalDateTime.parse(fromString, formatter);

        } catch (DateTimeParseException e){
            throw new DukeInvalidArgumentException("Wrong date/time format (correct: yyyy-MM-DD hh:mm), please input task again");
        }
        return from;
    }

    /**
     * Returns the end date of event task the user inputs.
     * @return End date of task.
     * @throws DukeInvalidArgumentException If description input by user is empty.
     */
    public LocalDateTime getEventTo(String toString) throws DukeInvalidArgumentException {
        if (toString.isEmpty()) {
            throw new DukeInvalidArgumentException("To is empty, please input task again");
        }

        LocalDateTime to;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            to = LocalDateTime.parse(toString, formatter);

        } catch (DateTimeParseException e){
            throw new DukeInvalidArgumentException("Wrong date/time format (correct: yyyy-MM-DD hh:mm), please input task again");
        }
        return to;
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
