package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;

/**
 * Events is a sub class of Task, because it allows the user to key in
 * additional data: the start date and the end date of the event task. By
 * default, all events are initially marked as undone. The user can either
 * choose to key in dates in MMM dd YYYY format, or normal Strings that
 * represents time for them (e.g. Sunday.) Since immutability is employed,
 * when a user toggles the event task as done or undone, a new event task
 * will be returned, but with the exact same description, from and to
 * timeframe. 
 *  
 *  @author Muhammad Reyaaz 
 *  @version %I% %G%
 *  @since 11
 *  @see Task
 *
 */

class Events extends Task {

    protected String from;
    protected String to;

    /**
    * Sole constructor. (For invocation by subclass
    * constructors, typically implicit)
    */

    protected Events() {

    }
    
    /**
     * Allows the user's input to be processed as the task's title, the
     * start time of the event and the end time of the event 
     *
     * @param description The title of the task in String 
     * @param from The start time of the event task 
     * @param to The end time of the event task 
     */

    Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    
    /**
     * Allows the user's input to be processed as the task's title, the
     * start time of the event and the end time of the event, and make the
     * event marked as done, which will be displayed as [X] in the user's
     * machine. 
     *
     * @param description The title of the task in String 
     * @param from The start time of the event task 
     * @param to The end time of the event task
     * @param isDone The boolean state of whether the event task is marked
     * as completed or yet to be completed
     */

    Events(String description,String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
        
    /**
     * Process the user's from and to date as a LocalDate into String if the
     * user keys in a valid format for LocalDate to process (MMM dd YYYY) in
     * this case. It also process the from and to date if the user keys in
     * terms that represents time for him (e.g. Sunday.) However, if the
     * user keys in an invalid date format (e.g. dd MM YY), the
     * DateTimeParseException will need to be caught 
     *
     * @param date The date of from or to
     * @exception DateTimeParseException
     * @return date String format of a valid time representation
     */
    String localDateParser(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return date;
        }
    }
    
    /**
     * Since the Event class is immutable, a new Event object will be
     * returned with the exact same description, from and to, except now,
     * the event task will be marked as done
     *
     *  @return Events The new Event task object 
     */

    @Override
    Events markAsDone() {
        return new Events(getDescription(), from, to, true);
    }
    
    /**
     * Since the Event class is immutable, a new Event object will be
     * returned with the exact same description, from and to, except now,
     * the event task will be marked as undone
     *
     *  @return Events The new Event task object 
     */

    @Override
    Events markAsUndone() {
        return new Events(getDescription(), from, to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + localDateParser(from) + ")" + "(to: " + localDateParser(to) + ")";
    }
}
