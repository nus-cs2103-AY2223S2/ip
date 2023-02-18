package duke;

/**
 * Custom exception for duke bot.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class DukeException extends Exception {
    /**
     * A public constructor to initialize DukeException instance.
     *
     * @param message Error message.
     */
    DukeException(String message) {
        super(message);
    }
}
