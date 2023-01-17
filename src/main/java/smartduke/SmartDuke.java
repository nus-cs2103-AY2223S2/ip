package smartduke;

import smartduke.command.Command;

public class SmartDuke {
    private TaskList taskList;
    private Ui ui;

    public SmartDuke() {
        try {
            this.taskList = new TaskList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        this.ui = new Ui();
        this.startSession();
    }

    /**
     * Begins the chat session with the user.
     */
    private void startSession() {
        /* Greet the user */
        ui.showLine();
        ui.showWelcome();
        ui.showLine();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(userCommand);
                c.execute(this.taskList, this.ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.close();
    }

    public static void main(String[] args) {
        new SmartDuke();
    }
}