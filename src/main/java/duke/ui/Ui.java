package duke.ui;

import duke.parser.Parser;

/**
 * Ui class to handle interactions with user
 */
public class Ui {
    private Parser parser;

    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Greet user when application is first launched
     * @return String to greet user
     */
    public String greet() {
        return "Hello! I'm Pingu \nWhat can I do for you?";
    }


    public String generateReply(String input) {
        return this.parser.performCommand(input);
    }

}
