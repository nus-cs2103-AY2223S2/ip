package duke.parser;

public class Parser {
    private String input;
    private String[] splitInput;
    private String command;

    public Parser(String input) {
        this.input = input;
        this.splitInput = input.split(" ");
        command = splitInput[0].toLowerCase();
    }

    public void updateInput(String input) {
        this.input = input;
        this.splitInput = input.split(" ");
        command = splitInput[0].toLowerCase();
    }

    public String getCommand() {
        return command;
    }

    public int getTaskNumber() {
        return Integer.parseInt(splitInput[1]);
    }

    public boolean hasDescription() {
        return input.length() > 5;
    }

    public String parseToDoDescription() {
        return input.substring(5);
    }

    public String parseDeadlineDescription() {
        int byIndex = input.indexOf("/by");
        return input.substring(9, byIndex - 1);
    }

    public String parseDeadlineDate() {
        int byIndex = input.indexOf("/by");
        return input.substring(byIndex + 4);
    }

    public String parseEventDescription() {
        int fromIndex = input.indexOf("/from");
        return input.substring(6, fromIndex - 1);
    }

    public String parseEventFrom() {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        return input.substring(fromIndex + 6, toIndex - 1);
    }

    public String parseEventTo() {
        int toIndex = input.indexOf("/to");
        return input.substring(toIndex + 4);
    }

    public String parseFindKeyword() {
        return input.substring(5);
    }
}
