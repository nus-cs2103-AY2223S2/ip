package duke;

import java.io.IOException;

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

    public void run() throws IOException {
        ui.start();
        while (parser.parseInput(ui.input())) {
        }
        ui.end();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
