package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.exceptions.DukeException;

/**
 * Represents the Checkout Command.
 * It allows the user to check out to a specified version of their program.
 */
public class CheckoutCommand extends Command {
    private static final Pattern VALID_CMD = Pattern.compile("^(checkout)(?<version> ([1-9]|10))?$");
    private final String args;

    /**
     * Initializes a Checkout instance.
     *
     * @param args The parsed arguments.
     */
    public CheckoutCommand(String args) {
        super(CommandType.CHECKOUT);
        this.args = args;
    }

    /**
     * The function to call when the command is executed in the main event loop.
     *
     * @param store In-memory store that holds all existing tasks.
     * @throws DukeException Throws an exception when something goes wrong.
     */
    @Override
    public String execute(Supplier<? extends CacheManager> store) throws DukeException {
        CacheManager cacheManager = store.get();
        Matcher matcher = VALID_CMD.matcher(args);
        if (matcher.find()) {
            String version = matcher.group("version");
            return cacheManager.checkout(version);
        } else {
            throw new DukeException(INVALID_FORMAT_ERROR + " " + "Please ensure you follow: checkout [version]");
        }
    }
}
