package duke;

import duke.exceptions.InvalidCommandException;

import java.io.FileNotFoundException;
import java.io.IOException;

/** A class that represents a Personal Assistant Chatbot
 * that helps keep track of various things.
 */
public class Duke {
    /** Storage that handles hard-disk saving */
    private Storage storage;

    /** TaskList that keeps track of all the tasks added */
    private TaskList tasks;

    /** TaskList that keeps track of all the notes added */
    private NoteList notes;

    /** Ui that handles user interface jobs */
    private Ui ui;

    /**
     * Initializes a Duke object with the given values.
     *
     * @param taskFilePath The name of the file where you keep list of tasks
     * @param noteFilePath The name of the file where you keep list of tasks
     */
    public Duke(String taskFilePath, String noteFilePath) {
        try {
            ui = new Ui();
            storage = new Storage(taskFilePath, noteFilePath);
            tasks = new TaskList(storage.initializeTasks());
            notes = new NoteList(storage.initializeNotes());
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        }

    }

    public Duke() {
        try {
            ui = new Ui();
            storage = new Storage("data/duke.txt", "data/note.txt");
            tasks = new TaskList(storage.initializeTasks());
            notes = new NoteList(storage.initializeNotes());

        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        }

    }

    /**
     * Starts running the Duke
     *
     * @throws IOException Exception in reading commands
     */
    public void run() throws IOException {
        ui.greet();
        String str;
        while (true) {
            str = ui.getLine().trim();
            String[] keywords = str.split(" ");
            String command = keywords[0].toLowerCase();

            switch (command) {
            case "bye":
                ui.goodBye();
                tasks.close();
                notes.close();
                ui.close();
                return ;

            case "list":
                ui.listCommand();
                break;

            case "mark":
                ui.markCommand(str, storage);
                break;

            case "unmark":
                ui.unmarkCommand(str, storage);
                break;

            case "todo":
                ui.todoCommand(str, storage);
                break;

            case "event":
                ui.eventCommand(str, storage);
                break;

            case "deadline":
                ui.deadlineCommand(str, storage);
                break;

            case "delete":
                ui.deleteCommand(str, storage);
                break;

            case "find":
                ui.findCommand(str);
                break;

            case "note":
                ui.noteCommand(str, storage);
                break;

            case "n-delete":
                ui.deleteNoteCommand(str, storage);
                break;

            case "notes":
                ui.listNotesCommand();
                break;

            default:
                System.out.println(new InvalidCommandException().getMessage());
            }

        }
    }

    public String getResponse(String command) throws IOException {
        String[] keywords = command.split(" ");
        String lowerCaseCommand = keywords[0].toLowerCase();

        switch (lowerCaseCommand) {
        case "bye":
            tasks.close();
            notes.close();
            return ui.goodBye();

        case "list":
            return ui.listCommand();

        case "mark":
            return ui.markCommand(command, storage);

        case "unmark":
            return ui.unmarkCommand(command, storage);

        case "todo":
            return ui.todoCommand(command, storage);

        case "event":
            return ui.eventCommand(command, storage);

        case "deadline":
            return ui.deadlineCommand(command, storage);

        case "delete":
            return ui.deleteCommand(command, storage);

        case "find":
            return ui.findCommand(command);

        case "note":
            return ui.noteCommand(command, storage);

        case "n-delete":
            return ui.deleteNoteCommand(command, storage);

        case "notes":
            return ui.listNotesCommand();

        default:
            System.out.println(new InvalidCommandException().getMessage());
            return new InvalidCommandException().getMessage();
        }


    }


    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt", "data/note.txt").run();
    }
}
