package commands;

public class Echo implements Command {
    private String input;

    public Echo(String input) {
        this.input = input;
    }
    public String execute() {
        return input;
    }
}
