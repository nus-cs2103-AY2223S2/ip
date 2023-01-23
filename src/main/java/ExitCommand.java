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
