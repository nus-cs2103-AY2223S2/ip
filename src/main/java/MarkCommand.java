public class MarkCommand extends Command {
  boolean isDone;

  public MarkCommand(String args, boolean isDone) {
    super(args);
    this.isDone = isDone;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage)
      throws SamUnknownCommandException, SamMissingTaskException, SamInvalidTaskException, SamMissingTaskTitleException,
      SamMissingTaskValueException, SamMissingTaskArgException, SamSaveFailedException, SamInvalidDateException {
    if (args.isEmpty()) {
      throw new SamMissingTaskException();
    }
    int id = Integer.parseInt(args);
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
