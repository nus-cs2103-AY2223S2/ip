package duke.commands;

import java.io.IOException;

import duke.Duke;
import duke.main.Storage;

public class SaveCommand extends Command {
  public SaveCommand() {
    super("save");
  }

  @Override
  protected void execute(String[] tokens, Duke instance) {
    try {
      Storage.saveToDisk("data.dat", instance.getTaskList());
      output("Saved your tasks to disk!");
    } catch (IOException e) {
      output("Failed to save your data: %s", e.getMessage());
    }
  }
}
