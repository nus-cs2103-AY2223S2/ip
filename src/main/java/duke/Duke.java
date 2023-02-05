package duke;

import duke.controllers.Command;
import duke.entities.managers.CacheManager;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;

/**
 * Represents the Duke Chat bot.
 * Running a duke object loads data from the specified file into memory,
 * and exiting the program writes data to the hard disk.
 */
public class Duke {
    private static CacheManager cacheManager;

    /**
     * Duke Constructor for initializing the duke.Duke Object.
     *
     * @param filename location of Storage
     */
    public Duke(String filename) {
        Storage storage = new Storage(filename);
        try {
            cacheManager = new CacheManager(storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves the command for the given command string.
     *
     * @param input The given command string
     * @return Status of the command execution
     */
    public String getResponse(String input) {
        Command cmd = Parser.parse(input);
        try {
            return cmd.execute(() -> cacheManager);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
