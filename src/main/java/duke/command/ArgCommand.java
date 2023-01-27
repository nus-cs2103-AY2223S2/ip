package duke.command;

import java.util.function.BiFunction;

/**
 * A command that takes arguments.
 *
 * @see Command
 */
public class ArgCommand extends Command {
  private final BiFunction<String[], Boolean, String[]> function;

  /**
   * Creates a new ArgCommand.
   *
   * @param name     The name of the command.
   * @param helpStr  The help string of the command.
   * @param params   The named parameters of the command.
   * @param function The function to be called when the command is executed.
   */
  public ArgCommand(String name, String helpStr, String[] params,
                    BiFunction<String[], Boolean, String[]> function) {
    super(name, helpStr, true, params);
    this.function = function;
  }

  @Override
  public String[] execute(String[] args, Boolean hasQuit) {
    return function.apply(args, hasQuit);
  }
}