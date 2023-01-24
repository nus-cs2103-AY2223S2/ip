package duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
    }
    public void run() {
        this.ui.nextMission(this.tasks);
        this.storage.update(tasks.print());
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
