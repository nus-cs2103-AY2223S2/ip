package parser;

public class inputParser {
    private String input;

    public inputParser(String input) {
        this.input = input;
    }

    public String parse() {
        switch (this.input.toLowerCase()) {
            case "":
                return "Please enter an input for me to echo!";
            default:
                return this.input;
        }
    }
}
