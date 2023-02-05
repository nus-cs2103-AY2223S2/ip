package duke;

import java.io.IOException;

/**
 * Duke class, main driver of the duke application. The duke app is an
 * application that takes users inputs and stores tasks in a list as a
 * reminder for the user.
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;



    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui();
        try {
            this.taskList = FileReadWrite.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.parser = new Parser(taskList);
    }




    public String getResponse(String input) {
        assert (!isEmptyInput(input));
        return "Duke: " + parser.parseInput(input);
    }

    public boolean isEmptyInput(String input) {
        return input.equals("") || input.equals(null);
    }
}
