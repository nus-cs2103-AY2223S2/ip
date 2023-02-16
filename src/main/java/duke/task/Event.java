package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.parser.Parser;

/**
 * Represents an Event task that has a description, a start date and time as well as an end date and time.
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected String startTimeString;
    protected LocalDateTime endTime;
    protected String endTimeString;
    protected DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");


    /**
     * Constructor for Event task, loaded from the storage file.
     * @param input Description of task including event time.
     * @param isDone Whether the task is marked or unmarked.
     */
    public Event(String input, boolean isDone) {
        super(input, isDone);
        this.symbol = 'E';
        String[] taskStringArr = input.split(",");
        this.description = taskStringArr[0].trim();
        String[] dueArr = taskStringArr[1].split(" to ");
        this.startTime = LocalDateTime.parse(dueArr[0],inputFormatter);
        this.startTimeString = startTime.format(displayFormatter);
        this.endTime = LocalDateTime.parse(dueArr[1], inputFormatter);
        this.endTimeString = endTime.format(displayFormatter);
        this.duedateString = taskStringArr[1];
    }

    /**
     * Constructor a new Event task keyed in by the user.
     * @param input Description and event time of the task, including its header "event".
     * @throws DukeException If description is empty, or event time keyed in does not fit specified format.
     */
    public Event(String input) throws DukeException {
        super(input);
        this.symbol = 'E';
        String[] inputArr = input.split(" ", 2); //split 'event' from task input
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new DukeException("Sorry, the description of an event cannot be empty!");
        }
        String[] eventArr = inputArr[1].split("/", 2); //split description from timings
        if (eventArr.length == 1 || eventArr[1].isBlank()) {
            throw new DukeException("Please include duration of the event in the following format:"
                                     + " /<yyyy-MM-dd HHmm> to <yyyy-MM-dd HHmm>");
        }
        this.description = eventArr[0];
        String[] dueArr = eventArr[1].split(" to ");
        this.startTime = LocalDateTime.parse(dueArr[0], inputFormatter);
        this.startTimeString = startTime.format(displayFormatter);
        this.endTime = LocalDateTime.parse(dueArr[1], inputFormatter);
        this.endTimeString = endTime.format(displayFormatter);
        this.duedateString = startTimeString + " to " + endTimeString;

    }

    public String saveTask() {
        return this.symbol + "," + isDone + "," + this.description + "," + duedateString;
    }
}


//sample input: event project meeting /2023-01-30 0900 to 2023-01-30 1800