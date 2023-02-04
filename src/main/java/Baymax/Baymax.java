package Baymax;

import exceptions.BaymaxException;

public class Baymax {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Baymax(String filepath) {
        storage = new Storage(filepath);
        try {
            tasks= new TaskList(storage.load());
        } catch (BaymaxException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.welcomeMessage();
        new Baymax("./data/Baymax.txt").run();
        ui.exitMessage();
    }

    public void run() {
        Parser parser = new Parser();
        tasks.updateList(parser.makeSense(tasks.getTaskList()));
        storage.store(tasks.getTaskList());
    }
}

