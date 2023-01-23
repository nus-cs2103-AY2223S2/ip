package sam.command;

import sam.Ui;
import sam.parser.Parser;
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

public class MarkCommand extends Command {
  boolean isDone;

  public MarkCommand(String args, boolean isDone) {
    super(args);
    this.isDone = isDone;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage)
      throws SamUnknownCommandException, SamMissingTaskException, SamInvalidIntException, SamInvalidTaskException, SamMissingTaskTitleException,
      SamMissingTaskValueException, SamMissingTaskArgException, SamSaveFailedException, SamInvalidDateException {
    if (args.isEmpty()) {
      throw new SamMissingTaskException();
    }
    int id = Parser.parseInt(args);
    boolean success = tasks.markTask(id, isDone);
    if (!success) {
      throw new SamInvalidTaskException();
    }
    String message = isDone
      ? "Great! I'll check the task:"
      : "Okay, I'll uncheck the task:";
    ui.talk(message,
      tasks.getTask(id).toString());
    storage.save(tasks);
  }
}
