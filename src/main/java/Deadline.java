public class Deadline extends Task{

    public Deadline(String input) throws MissingDescriptionException {
        super(input);
        this.symbol = 'D';
        String[] inputArr = input.split(" ", 2); //split 'deadline' from task input
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new MissingDescriptionException("Sorry, the description of a deadline cannot be empty!");
        }
        String[] descriptionArr= inputArr[1].split("/"); //split task from due date
        if (descriptionArr.length == 1 || descriptionArr[1].isBlank()) {
            throw new MissingDescriptionException("Please include a deadline in the following format: '/[deadline]'");
        }
        this.description = descriptionArr[0];
        this.due = descriptionArr[1];
    }

    public Deadline(String input, boolean isDone) {
        super(input, isDone);
        this.symbol = 'D';
        String[] temp = input.split(",");
        this.description = temp[0];
        this.due = temp[1];
    }
    public String saveTask() {
        return this.symbol + "," + isDone + "," + this.description + "," + this.due;
    }
}

//sample input: deadline do homework /by Sunday 10pm