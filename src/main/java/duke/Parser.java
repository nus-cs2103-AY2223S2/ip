package duke;

import java.io.IOException;

/**
 * Class for Parser object.
 * Parser parses the input from user and extracts information to perform the respective actions.
 * 
 * @author Bryan Tan
 */
public class Parser {
    private Ui ui;
    private String input;
    private String[] raw;

    public Parser() {
        this.ui = new Ui();
    }

    public String parseInput(String s) throws IOException {
        this.raw = s.split(" ");
        this.input = raw[0];
        switch (input.toUpperCase()) {
            case "INITIALISE":
                return ui.initialise();
            case "LIST":
                return ui.viewList();
            case "MARK":
                return ui.markTask(getTaskNum());
            case "UNMARK":
                return ui.unmarkTask(getTaskNum());
            case "TODO":
                return ui.makeToDo(raw);
            case "EVENT":
                return ui.makeEvent(raw);
            case "DEADLINE":
                return ui.makeDeadline(raw);
            case "DELETE":
                return ui.delete(getTaskNum());
            case "FIND":
                return ui.find(findTaskParsed());
            case "CLEAR":
                return ui.clearList();
            case "TAG":
                return ui.tag(getTaskNum(), getTag());
            case "BYE" :
                return ui.end();
            default:
                return ui.wrongInput();

        }
    }

    public int getTaskNum() {
        return Integer.parseInt(raw[1]) - 1;
    }

    /**
     * Creates String of keyword to be searched with.
     * 
     * @return Keyword or phrase.
     */
    public String findTaskParsed() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < this.raw.length; i++) {
            sb.append(this.raw[i] + " ");
        }
        return sb.toString();
    }

    public String getTag() {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < raw.length; i++) {
            sb.append(this.raw[i]);
        }
        return sb.toString();
    }
}
