package duke.duke;

public class Duke {
    private TaskList data;
    private Ui ui;

    public Duke() {
        this.data = Storage.populateList();
    }

    public void run() {
        this.ui = new Ui(new Parser(data));
        this.ui.readInput();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
