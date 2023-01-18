/**
 * Defines classes that represent commands that Duke recognizes. Commands are either {@link BasicCommand}s, which do not
 * accept arguments; or {@link ArgCommand}s, which do.
 *
 * @see duke.Duke
 */
package duke.command;

import duke.Ui;

/**
 * Represents a command that Duke recognizes.
 */
public abstract class Command {
  private final String name;
  private final String helpStr;
  private final boolean hasBaseParam;
  private final String[] params;

    /**
     * Creates a new Command.
     *
     * @param name The name of the command.
     * @param helpStr The help string of the command.
     * @param hasBaseParam Whether the command accepts a base (unnamed) parameter.
     * @param params The named parameters of the command.
     */
    public Command(String name, String helpStr, boolean hasBaseParam, String[] params) {
        this.name = name;
        this.helpStr = helpStr;
        this.hasBaseParam = hasBaseParam;
        this.params = params;
    }

    /**
     * Returns the help string of the command. Used by the {@link Ui} class to generate the help message.
     *
     * @return The help string of the command.
     */
    public String getHelpStr() {
        return helpStr;
    }

    /**
     * Returns the name of the command.
     * @return The name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the named parameters of the command.
     * @return The named parameters of the command.
     */
    public String[] getParams() {
        return params;
    }

    /**
     * True if the command accepts at least one parameter.
     * @return True if the command accepts at least one parameter.
     */
    public boolean hasParams() {
        return params.length > 0 || hasBaseParam;
    }

    /**
     * Each command encapsulates an operation that Duke can perform, as a function that may accept parameters. This
     * method executes the operation.
     * @param params The parameters to pass to the operation.
     * @return The output of the operation.
     */
    public abstract String[] execute(String[] params);
}