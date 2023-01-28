package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.exception.DukeException;

import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidIndexException;
public class Parser {

    protected String answer;

    public Parser(String answer) {
        this.answer = answer;
    }

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


    public String getTodoDescription() throws DukeInvalidArgumentException {
        if (answer.substring(5, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Todo cannot be empty");
        }
        return answer.substring(5, answer.length());
    }

    public String getDeadlineDescription() throws DukeInvalidArgumentException {
        if (answer.substring(9, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Deadline cannot be empty");
        }
        return answer.substring(9, answer.length());
    }

    public LocalDateTime getDeadlineBy(String byString) throws DukeInvalidArgumentException{
        if (byString.isEmpty()) {
            throw new DukeInvalidArgumentException("By is empty, please input task again");
        }
        LocalDateTime by;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            by = LocalDateTime.parse(byString, formatter);
        } catch (DateTimeParseException e){
            throw new DukeInvalidArgumentException("Wrong date/time format, please input task again");
        }
        return by;
    }


    public String getEventDescription() throws DukeInvalidArgumentException {
        if (answer.substring(6, answer.length()).isEmpty()) {
            throw new DukeInvalidArgumentException("Event cannot be empty");
        }
        return answer.substring(6, answer.length());
    }


    public LocalDateTime getEventFrom(String fromString) throws DukeInvalidArgumentException {
        if (fromString.isEmpty()) {
            throw new DukeInvalidArgumentException("From is empty, please input task again");
        }
        LocalDateTime from;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            from = LocalDateTime.parse(fromString, formatter);

        } catch (DateTimeParseException e){
            throw new DukeInvalidArgumentException("Wrong date/time format, please input task again");
        }
        return from;
    }

    public LocalDateTime getEventTo(String toString) throws DukeInvalidArgumentException {
        if (toString.isEmpty()) {
            throw new DukeInvalidArgumentException("To is empty, please input task again");
        }

        LocalDateTime to;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            to = LocalDateTime.parse(toString, formatter);

        } catch (DateTimeParseException e){
            throw new DukeInvalidArgumentException("Wrong date/time format, please input task again");
        }
        return to;
    }
}
