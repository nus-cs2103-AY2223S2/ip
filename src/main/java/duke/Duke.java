package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import duke.exception.DukeException;
import duke.exception.EmptyTaskListException;
import duke.exception.FileLoadingException;

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
        storage = new Storage("./data/tasks.txt");
        tasks = new TaskList(storage.load());
    }


    private void run() {
        ui.showWelcome();
        TaskList listOfAction = tasks;
        Storage file = storage;
        String s = "";

        String[] arr = ui.getInput();
        s = arr[0];

        while (!s.equals("bye")) {
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
            } catch (DukeException error) {
                ui.says(error.getMessage());
            }
            try {
                file.overwrite(listOfAction);
                arr = ui.getInput();
                s = arr[0];
            } catch (DukeException error) {
                ui.says(error.getMessage());
            }
        }
        ui.bye();
    }

    public String getResponse(String input) {
        TaskList listOfAction = tasks;
        Storage file = storage;
        String[] arr = ui.getInput(input);
        String s = arr[0];
        String response = "";
        try {
            Action myAction = Action.valueOf(String.valueOf(s.charAt(0)).toUpperCase()
                    + s.substring(1));
            switch (myAction) {
            case Find:
                response = (ui.findWordIntro(listOfAction, arr, listOfAction.checkWord(arr[1])));
                break;

            case Bye:
                response = (ui.bye());
                break;

            case Undo:
                response = ui.undo(listOfAction);
                break;

            case List:
                response = (ui.list(listOfAction));
                break;

            case Mark:
                response = (ui.mark(listOfAction, arr));
                break;

            case Unmark:
                response = (ui.unmark(listOfAction, arr));
                break;

            case Delete:
                response = (ui.delete(listOfAction, arr));
                break;

            case Todo:
                response = (ui.addToDo(listOfAction, arr));
                break;

            case Deadline:
                response = (ui.addDeadline(listOfAction, arr));
                break;

            case Event:
                response = (ui.addEvent(listOfAction, arr));
                break;

            default:
                response = (ui.showUnknownError());
                break;
            }
            file.overwrite(listOfAction);
        } catch (IllegalArgumentException e) {
            return ui.showUnknownError();
        } catch (DukeException e) {
            return (e.getMessage());
        }
        return response;
    }
    public static void main(String[] args) throws IOException {
        new Duke("./data/tasks.txt").run();
    }
}





