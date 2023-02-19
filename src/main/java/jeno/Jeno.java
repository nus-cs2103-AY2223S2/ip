package jeno;

import jeno.command.Command;
import jeno.exception.JenoException;
import jeno.parser.Parser;
import jeno.storage.Note;
import jeno.storage.Storage;
import jeno.storage.TaskList;

/**
 * Class for Jeno, a Personal Assistant Chatbot
 */
public class Jeno {
    private static final String TASK_LOG_PATH = ("./data/Jeno.txt");
    private static final String NOTES_PATH = ("./data/Notes.txt");

    private Storage taskStorage;
    private TaskList tasks;
    private Storage noteStorage;
    private Note notes;


    /**
     * Constructor for Jeno Class. If log file does not exist, create a new log file
     */
    public Jeno() {
        Storage.logFileExists(TASK_LOG_PATH);
        taskStorage = new Storage(TASK_LOG_PATH);
        tasks = new TaskList(taskStorage.loadTasksFromTaskLog());

        Storage.notesFileExists(NOTES_PATH);
        noteStorage = new Storage(NOTES_PATH);
        notes = new Note(noteStorage.loadNotesFromFile());
    }

    public static String getTaskLogPath() {
        return TASK_LOG_PATH;
    }

    public static String getNotesPath() {
        return NOTES_PATH;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.getCommandType(input);
            return c.execute(tasks, notes);
        } catch (JenoException e) {
            return e.getMessage();
        }
    }

    /**
     * Show welcome message.
     * @return Welcome message.
     */
    public String showWelcomeMessage() {
        return "Hello! I am JenoBot, a personal CLI chatbot that will "
                + "help you to keep track of your daily tasks.\n\n"
                + "To view the list of features, type in 'help' in the command box.";
    }
}
