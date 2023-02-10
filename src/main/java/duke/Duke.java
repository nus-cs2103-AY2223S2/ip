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

    private NoteList notes;


    /** Ui that handles user interface jobs */
    private Ui ui;

    /**
     * Initializes an Duke object with the given values.
     *
     * @param taskFilePath The name of the file where you keep list of tasks
     * @param noteFilePath The name of the file where you keep list of tasks
     * @return A Duke instance
     * @throws FileNotFoundException
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
     * @throws IOException
     */
    public void run() throws IOException {
        ui.greet();
        String str;
        while (true) {
            str = ui.getLine();
            if (str.equals("bye")) {
                ui.goodBye();
                tasks.close();
                break;
            }
            if (str.equalsIgnoreCase("list")) {
                ui.listCommand();
            } else if (str.length() >= 5 && str.toLowerCase().startsWith("mark ")) {
                ui.markCommand(str, storage);
            } else if (str.length() >= 6 && str.toLowerCase().startsWith("unmark ")) {
                ui.unmarkCommand(str, storage);
            } else if (str.length() >= 5 && str.toLowerCase().startsWith("todo ")) {
                ui.todoCommand(str, storage);
            } else if (str.length() >= 6 && str.toLowerCase().startsWith("event ")) {
                ui.eventCommand(str, storage);
            } else if (str.length() >= 9 && str.toLowerCase().startsWith("deadline ")) {
                ui.deadlineCommand(str, storage);
            } else if (str.length() >= 7 && str.toLowerCase().startsWith("delete ")) {
                ui.deleteCommand(str, storage);
            } else if (str.length() >= 5 && str.toLowerCase().startsWith("find ")) {
                ui.findCommand(str);
            }  else if (str.length() >= 5 && str.toLowerCase().startsWith("note ")) {
                ui.noteCommand(str, storage);
            }  else if (str.equalsIgnoreCase("notes")) {
                ui.listNotesCommand();
            } else {
                System.out.println(new InvalidCommandException().getMessage());
            }
        }
        ui.close();
    }

    public String getResponse(String command) throws IOException {
        if (command.equals("bye")) {
            tasks.close();
            return ui.goodBye();
        }
        if (command.equalsIgnoreCase("list")) {
            return ui.listCommand();
        } else if (command.equalsIgnoreCase("notes")) {
            return ui.listNotesCommand();
        } else if (command.length() >= 5 && command.toLowerCase().startsWith("mark ")) {
            return ui.markCommand(command, storage);
        } else if (command.length() >= 6 && command.toLowerCase().startsWith("unmark ")) {
            return ui.unmarkCommand(command, storage);
        } else if (command.length() >= 5 && command.toLowerCase().startsWith("todo ")) {
            return ui.todoCommand(command, storage);
        } else if (command.length() >= 6 && command.toLowerCase().startsWith("event ")) {
            return ui.eventCommand(command, storage);
        } else if (command.length() >= 9 && command.toLowerCase().startsWith("deadline ")) {
            return ui.deadlineCommand(command, storage);
        } else if (command.length() >= 7 && command.toLowerCase().startsWith("delete ")) {
            return ui.deleteCommand(command, storage);
        } else if (command.length() >= 5 && command.toLowerCase().startsWith("find ")) {
            return ui.findCommand(command);
        } else if (command.length() >= 5 && command.toLowerCase().startsWith("note ")) {
            return ui.noteCommand(command, storage);
        } else {
            System.out.println(new InvalidCommandException().getMessage());
            return new InvalidCommandException().getMessage();
        }
    }


    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt", "data/note.txt").run();
    }
}
