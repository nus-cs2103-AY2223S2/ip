abstract class Command {
  protected String args;

  public Command(String args) {
    this.args = args;
  }

  public abstract void execute(TaskList tasks, Ui ui, Storage storage) 
      throws SamUnknownCommandException, SamMissingTaskException, SamInvalidTaskException, SamInvalidIntException,
          SamMissingTaskTitleException, SamMissingTaskValueException, SamMissingTaskArgException,
          SamSaveFailedException, SamInvalidDateException;
}
