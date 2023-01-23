package duke.commands;

import java.util.function.BiConsumer;

import duke.Duke;
import duke.main.Ui;

public abstract class Command implements BiConsumer<String[], Duke> {
  protected static class ValidationException extends Exception {
    public ValidationException(String message) {
      super(message);
    }

    public ValidationException(String fmt, Object ...args) {
      this(String.format(fmt, args));
    }
  }  

  protected static void validate(boolean condition, String errMsg) throws ValidationException {
    if (!condition) throw new ValidationException(errMsg);
  }

  private final String label;

  public Command(String label) {
    this.label = label;
  }

  protected void output(String str) {
    Ui.print(str);
  } 

  protected void output(String formatStr, Object ...args) {
    output(String.format(formatStr, args));
  }

  public String getLabel() { return label; }

  protected abstract void execute(String[] tokens, final Duke instance) throws ValidationException;

  @Override
  public final void accept(String[] tokens, final Duke instance) {
    try {
      execute(tokens, instance);
    } catch (ValidationException e) {
      output(e.getMessage());
    }
  }
}
