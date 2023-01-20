public class Todo extends Task {
    public Todo(String input) throws MissingDescriptionException {
        super(input);
        String[] inputArr = input.split(" ", 2);
        if (inputArr.length == 1 || inputArr[1].isBlank()) {
            throw new MissingDescriptionException("Sorry, the description of a todo cannot be empty!");
        }
        this.description = inputArr[1];
        symbol = 'T';
    }
}
