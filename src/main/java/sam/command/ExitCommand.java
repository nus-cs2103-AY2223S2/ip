package sam.command;

import sam.Ui;
import sam.parser.SamInvalidDateException;
import sam.parser.SamInvalidIntException;
import sam.parser.SamInvalidTaskException;
import sam.parser.SamUnknownCommandException;
import sam.storage.SamSaveFailedException;
import sam.storage.Storage;
import sam.task.SamMissingTaskArgException;
import sam.task.SamMissingTaskException;
import sam.task.SamMissingTaskTitleException;
import sam.task.SamMissingTaskValueException;
import sam.task.TaskList;

public class ExitCommand extends Command {
  public ExitCommand(String args) {
    super(args);
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage)
      throws SamUnknownCommandException, SamMissingTaskException, SamInvalidIntException, SamInvalidTaskException, SamMissingTaskTitleException,
      SamMissingTaskValueException, SamMissingTaskArgException, SamSaveFailedException, SamInvalidDateException {
    ui.talk("Goodbye!");    
  }
}
