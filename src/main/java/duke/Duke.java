package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

enum Action {
    Bye,
    Deadline,
    Delete,
    Event,
    Find,
    List,
    Mark,
    Todo,
    Undo,
    Unmark,
}

/**
 * Represents a Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialize a Duke with given information
     *
     * @param filepath where storage data will be stored
     * @throws FileNotFoundException if such filepath does not exist
     */
    public Duke(String filepath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Represent a new Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        tasks = new TaskList(storage.load());
    }


    private void run() throws IOException {
        ui.showWelcome();
        TaskList listOfAction = tasks;
        Storage file = storage;
        String s = "";

        String[] arr = ui.getInput();
        s = arr[0];
        //int len = listOfAction.validLen();

        while (!s.equals("bye")) {
            file.overwrite(listOfAction);
            String remaining = "";
            try {
                Action myAction = Action.valueOf(String.valueOf(s.charAt(0)).toUpperCase()
                        + s.substring(1));
                switch (myAction) {
                case Find:
                    ui.says(ui.findWordIntro(listOfAction, arr, listOfAction.checkWord(arr[1])));
                    break;

                case Bye:
                    ui.says(ui.bye());
                    break;

                case Undo:
                    ui.says(ui.undo(listOfAction));
                    break;

                case List:
                    ui.says(ui.list(listOfAction));
                    break;

                case Mark:
                    ui.says(ui.mark(listOfAction, arr));
                    break;

                case Unmark:
                    ui.says(ui.unmark(listOfAction, arr));
                    break;

                case Delete:
                    ui.says(ui.delete(listOfAction, arr));
                    break;

                case Todo:
                    ui.says(ui.addToDo(listOfAction, arr));
                    break;

                case Deadline:
                    ui.says(ui.addDeadline(listOfAction, arr));
                    break;

                case Event:
                    ui.says(ui.addEvent(listOfAction, arr));
                    break;

                default:
                    ui.says(ui.showUnknownError());
                }
            } catch (IllegalArgumentException e) {
                ui.showUnknownError();
            }

            file.overwrite(listOfAction);
            arr = ui.getInput();
            s = arr[0];
        }
        ui.bye();
    }

    public String getResponse(String input) {
        int i = 1;
        //ui.showWelcome();
        TaskList listOfAction = tasks;
        Storage file = storage;
        String[] arr = ui.getInput(input);
        String s = arr[0];
        try {
            Action myAction = Action.valueOf(String.valueOf(s.charAt(0)).toUpperCase()
                    + s.substring(1));
            switch (myAction) {
            case Find:
                return (ui.findWordIntro(listOfAction, arr, listOfAction.checkWord(arr[1])));

            case Bye:
                return (ui.bye());

            case Undo:
                return ui.undo(listOfAction);

            case List:
                return (ui.list(listOfAction));

            case Mark:
                return (ui.mark(listOfAction, arr));

            case Unmark:
                return (ui.unmark(listOfAction, arr));

            case Delete:
                return (ui.delete(listOfAction, arr));

            case Todo:
                return (ui.addToDo(listOfAction, arr));

            case Deadline:
                return (ui.addDeadline(listOfAction, arr));

            case Event:
                return (ui.addEvent(listOfAction, arr));

            default:
                return (ui.showUnknownError());
            }
        } catch (IllegalArgumentException e) {
            ui.showUnknownError();
        }
        return ui.showUnknownError();
    }
    public static void main(String[] args) throws IOException {
        new Duke("./data/tasks.txt").run();
    }
}





