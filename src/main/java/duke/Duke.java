package duke;

import duke.command.Command;

/**
 *
 * Represents the chat bot.
 *
 */
public class Duke {
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
    }

    private void run() {
        ui.begin();

        Storage storage = new Storage();
        TodoList todoList = storage.load();

        Parser bot = new Parser(todoList);

        String input = ui.readCommand();

        while (!input.equals("bye")) {
            try {
                Command command = bot.parse(input);
                command.execute();
            } catch (DukeExceptions error) {
                System.out.println(error.getErrorMessage());
            }
            ui.showLine();
            input = ui.readCommand();; //ready for next input
        }
        storage.save(todoList);
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
