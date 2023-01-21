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

    /**
     * Runs the duke application.
     *
     * @throws IOException  if failed I/O operation
     */
    public void run() throws IOException {
        ui.start();
        while(parser.parseInput(ui.input())) {
        }
        ui.end();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
