package duke.command;

import java.util.function.Supplier;

public class BasicCommand extends Command {
  private final Supplier<String[]> supplier;

  public BasicCommand(String name, String helpStr, Supplier<String[]> supplier) {
    super(name, helpStr, false, new String[]{});
    this.supplier = supplier;
  }

  @Override
  public String[] execute(String[] params) {
    return supplier.get();
  }
}
