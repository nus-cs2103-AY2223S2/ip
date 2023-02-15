package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.exception.DukeException;
import duke.storage.Note;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Class for Duke, a Personal Assistant Chatbot
 */
public class Duke {
    private Storage taskStorage;
    private TaskList tasks;
    private static final String TASK_LOG_PATH = ("./data/Duke.txt");

    private Storage noteStorage;
    private Note notes;
    private static final String NOTES_PATH = ("./data/Notes.txt");

    /**
     * Constructor for Duke Class. If log file does not exist, create a new log file
     */
    public Duke() {
        Storage.logFileExists(TASK_LOG_PATH);
        taskStorage = new Storage(TASK_LOG_PATH);
        tasks = new TaskList(taskStorage.loadTasksFromTaskLog());

        Storage.notesFileExists(NOTES_PATH);
        noteStorage = new Storage(NOTES_PATH);
        notes = new Note(noteStorage.loadNotesFromFile());
    }

    public static String getTASK_LOG_PATH() {
        return TASK_LOG_PATH;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.getCommandType(input);
            return c.execute(tasks, notes);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String showWelcomeMessage() {
        return "Hello! I am JenoBot, a personal CLI chatbot that will\n" +
                "help you to keep track of your daily tasks.\n\n" +
                "To view the list of features, type in 'help' in the command box.";
    }
}