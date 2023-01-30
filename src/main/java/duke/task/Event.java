package duke.task;

import duke.DukeException;
import duke.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    public Event(String input) throws DukeException {
        super(input);
        this.symbol = 'E';
        String[] inputArr = input.split(" ", 2); //split 'event' from task input
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new DukeException("Sorry, the description of an event cannot be empty!");
        }
        String[] eventArr = inputArr[1].split("/", 2); //split description from timings
        if (eventArr.length == 1 || eventArr[1].isBlank()) {
            throw new DukeException("Please include duration of the event in the following format: /<yyyy-MM-dd HHmm> to <yyyy-MM-dd HHmm>");
        }
        this.description = eventArr[0];
        String[] dueArr = eventArr[1].split(" to ");
        this.startTime = Parser.parseDateTime(dueArr[0]);
        this.endTime = Parser.parseDateTime(dueArr[1]);
        this.duedateString = startTime.format(displayFormatter) + " to " + endTime.format(displayFormatter);
    }
    public Event(String input, boolean isDone) throws DukeException {
        super(input, isDone);
        this.symbol = 'E';
        String[] temp = input.split(",");
        this.description = temp[0];
        String[] dueArr = temp[1].split(" to ");
        this.startTime = LocalDateTime.parse(dueArr[0], displayFormatter);
        this.endTime = LocalDateTime.parse(dueArr[1], displayFormatter);
        this.duedateString = temp[1];
    }
    public String saveTask() {
//        String startTimeString = this.startTime.format(displayFormatter);
//        String endTimeString = this.endTime.format(displayFormatter);
//        return this.symbol + "," + isDone + "," + this.description + "," + startTimeString + " to " + endTimeString;
        return this.symbol + "," + isDone + "," + this.description + "," + duedateString;

    }
}

//sample input: event project meeting /2023-01-30 0900 to 2023-01-30 1800