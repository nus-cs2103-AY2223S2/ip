import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    protected LocalDate duedate;
    public Deadline(String input) throws MissingDescriptionException {
        super(input);
        this.symbol = 'D';
        String[] inputArr = input.split(" ", 2); //split 'deadline' from task input
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new MissingDescriptionException("Sorry, the description of a deadline cannot be empty!");
        }
        String[] descriptionArr= inputArr[1].split("/"); //split task from due date
        if (descriptionArr.length == 1 || descriptionArr[1].isBlank()) {
            throw new MissingDescriptionException("Please include a deadline in the following format: '/<due date>'");
        }
        this.description = descriptionArr[0];
        LocalDate inputFormatter = LocalDate.parse(descriptionArr[1]);
        this.duedate = inputFormatter;
        this.duedateString = inputFormatter.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    public Deadline(String input, boolean isDone) {
        super(input, isDone);
        this.symbol = 'D';
        String[] temp = input.split(",");
        this.description = temp[0];
        LocalDate inputFormatter = LocalDate.parse(temp[1]);
        this.duedate = inputFormatter;
        this.duedateString = inputFormatter.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
    public String saveTask() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return this.symbol + "," + isDone + "," + this.description + "," + duedate;
    }
}

//sample input: deadline do homework /by Sunday 10pm