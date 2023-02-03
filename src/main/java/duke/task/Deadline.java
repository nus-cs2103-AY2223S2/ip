package duke.task;

import duke.DukeException;
import duke.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDate duedate;

    /**
     * Constructor for Deadline task, loaded from the storage file.
     * @param input Description of task including its due date.
     * @param isDone Whether the task is marked or unmarked.
     */
    public Deadline(String input, boolean isDone) {
        super(input, isDone);
        this.symbol = 'D';
        String[] temp = input.split(",");
        this.description = temp[0];
        LocalDate inputFormatter = LocalDate.parse(temp[1]);
        this.duedate = inputFormatter;
        this.duedateString = inputFormatter.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    /**
     * Constructor a new Deadline task keyed in by the user.
     * @param input Description and duedate of the task, including its header "deadline".
     * @throws DukeException If description is empty, or duedate keyed in does not fit specified format.
     */
    public Deadline(String input) throws DukeException {
        super(input);
        this.symbol = 'D';
        String[] inputArr = input.split(" ", 2); //split 'deadline' from task input
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new DukeException("Sorry, the description of a deadline cannot be empty!");
        }
        String[] descriptionArr = inputArr[1].split("/"); //split task from due date
        if (descriptionArr.length == 1 || descriptionArr[0].isEmpty()) {
            throw new DukeException("Please include a deadline in the following format: '/yyyy-MM-dd'");
        }
        this.description = descriptionArr[0];
            LocalDate inputFormatter = Parser.parseDate(descriptionArr[1]);
            this.duedate = inputFormatter;
            this.duedateString = inputFormatter.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
    public String saveTask() {
        return this.symbol + "," + isDone + "," + this.description + "," + duedate;
    }
}

//sample input: deadline do homework /2023-01-30