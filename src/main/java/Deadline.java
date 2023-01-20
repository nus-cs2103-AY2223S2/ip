public class Deadline extends Task{
    public Deadline(String input) throws MissingDescriptionException {
        super(input);
        symbol = 'D';
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new MissingDescriptionException("Sorry, the description of a deadline cannot be empty!");
        }
        String[] descriptionArr= inputArr[1].split("/");
        if (descriptionArr.length == 1 || descriptionArr[1].isBlank()) {
            throw new MissingDescriptionException("Please include a deadline in the following format: '/by [deadline]'");
        }
        String[] dueArr = descriptionArr[1].split(" ", 2);
        if (dueArr.length == 1 || dueArr[1].isBlank()) {
            throw new MissingDescriptionException("Please include a deadline in the following format: '/by [deadline]'");
        }
        String due = "(" + dueArr[0] + ": " + dueArr[1] + ")";
        this.description = descriptionArr[0] + due;
    }
}

//sample input: deadline do homework /by Sunday 10pm