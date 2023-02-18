package duke;

public class GUI {
    private Parser parser;

    public GUI() {
        this.parser = new Parser();
    }

    public String getResponse(String input) throws Exception {
        return this.parser.parseInput(input);

    }
}