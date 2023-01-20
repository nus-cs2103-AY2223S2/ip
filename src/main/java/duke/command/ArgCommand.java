package duke.command;

import java.util.function.Function;

/**
 * A command that takes arguments.
 *
 * @see Command
 */
public class ArgCommand extends Command {
  private final Function<String[], String[]> function;

  /**
   * Creates a new ArgCommand.
   *
   * @param name     The name of the command.
   * @param helpStr  The help string of the command.
   * @param params   The named parameters of the command.
   * @param function The function to be called when the command is executed.
   */
  public ArgCommand(String name, String helpStr,
                    String[] params, Function<String[], String[]> function) {
    super(name, helpStr, true, params);
    this.function = function;
  }

  @Override
  public String[] execute(String[] args) {
    return function.apply(args);
  }
}