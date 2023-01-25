package peppa;

import peppa.commands.Command;

public class Peppa {
    public static final String FILE_PATH = "data/todo.txt";
    private Ui screen;
    private TaskList tasks;
    private Storage storage;

    /**
     * Starts the chatbot interface.
     */
    public void run() {
        storage.loadData(tasks, screen);
        screen.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = screen.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, screen, storage);
                isExit = c.isExit();
            } catch (PeppaException e) {
                Ui.displayMessage(e.getMessage());
            } finally {
                screen.insertDivider();
            }
        }
    }

    public Peppa(String filepath) {
        this.screen = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filepath);
    }

    public static void main(String[] args) {
        new Peppa(FILE_PATH).run();
    }
}