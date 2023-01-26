public class Event extends Task {
    public Event(String input) throws MissingDescriptionException {
        super(input);
        this.symbol = 'E';
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new MissingDescriptionException("Sorry, the description of an event cannot be empty!");
        }
        String[] eventArr = inputArr[1].split("/", 2);
        if (eventArr.length == 1 || eventArr[1].isBlank()) {
            throw new MissingDescriptionException("Please include duration of the event in the following format: /from [time] /to [time]");
        }
        this.description = eventArr[0];
        this.due = eventArr[1];
    }
    public Event(String input, boolean isDone) {
        super(input, isDone);
        this.symbol = 'E';
        String[] temp = input.split(",");
        this.description = temp[0];
        this.due=temp[1];
    }
    public String saveTask() {
        return this.symbol + "," + isDone + "," + this.description + "," + this.due;
    }
}

//sample input: event project meeting /from 2pm to 6pm