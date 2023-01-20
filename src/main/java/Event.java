public class Event extends Task {
    public Event(String input) throws MissingDescriptionException {
        super(input);
        symbol = 'E';

        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new MissingDescriptionException("Sorry, the description of an event cannot be empty!");
        }
        String[] eventArr = inputArr[1].split("/", 2);
        if (eventArr.length == 1 || eventArr[1].isBlank()) {
            throw new MissingDescriptionException("Please include duration of the event in the following format: /from [time] /to [time]");
        }

        String[] timeArr = eventArr[1].split("/");
        if (timeArr.length == 1 || timeArr[1].isBlank()) {
            throw new MissingDescriptionException("Please include duration of the event in the following format: /from [time] /to [time]");
        }
        String[] fromArr = timeArr[0].split(" ", 2);
        String from = fromArr[0] + ": " + fromArr[1];
        String[] toArr = timeArr[1].split(" ", 2);
        if (toArr.length == 1 || toArr[1].isBlank()) {
            throw new MissingDescriptionException("Please include duration of the event in the following format: /from [time] /to [time]");
        }
        String to = toArr[0] + ": " + toArr[1];
        String time = from + to;

        this.description = eventArr[0] + "(" + time + ")";
    }
}

//sample input: event project meeting /from 2pm /to 6pm