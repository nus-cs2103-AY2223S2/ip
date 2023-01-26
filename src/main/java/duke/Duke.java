package duke;

import duke.exceptions.DukeException;

public class Duke {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    Duke(String dataStoragePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(dataStoragePath, this.taskList);
        this.parser = new Parser();
    }

    private void exit() {
        storage.updateData(this.taskList);
        ui.displayMessage("Bye. Hope to see you again soon!\n");
    }

    void run() {
        ui.welcomeMessage();
        while (true) {
            String[] tokens = parser.parseUserInput();
            if (tokens.length == 1 && tokens[0].equals("bye")) {
                exit();
                break;
            } else if (tokens.length == 1 && tokens[0].equals("list")) {
                ui.displayItemList(taskList);
            } else if (tokens[0].equals("mark")) {
                taskList.markListItem(tokens, ui);
            } else if (tokens[0].equals("unmark")) {
                taskList.unmarkListItem(tokens, ui);
            } else if (tokens[0].equals("todo")) {
                try {
                    taskList.addToDo(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("deadline")) {
                try {
                    taskList.addDeadline(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("event")) {
                try {
                    taskList.addEvent(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("delete")) {
                try {
                    taskList.deleteItem(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else if (tokens[0].equals("find")) {
                try {
                    taskList.findItemInList(tokens, ui);
                } catch (DukeException e) {
                    ui.displayMessage(e.getMessage());
                }
            } else {
                ui.displayMessage("unknown command\n");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
