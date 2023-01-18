package duke.command;

import java.util.function.Supplier;

/**
 * A simple command that does not take arguments.
 *
 * @see Command
 */
public class BasicCommand extends Command {
  private final Supplier<String[]> supplier;

  /**
   * Creates a new BasicCommand.
   * @param name The name of the command.
   * @param helpStr The help string of the command.
   * @param supplier The supplier to be called when the command is executed.
   */
  public BasicCommand(String name, String helpStr, Supplier<String[]> supplier) {
    super(name, helpStr, false, new String[]{});
    this.supplier = supplier;
  }

  @Override
  public String[] execute(String[] params) {
    return supplier.get();
  }
}
