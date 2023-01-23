public class ListCommand extends Command {
  public ListCommand(String args) {
    super(args);
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage)
      throws SamUnknownCommandException, SamMissingTaskException, SamInvalidIntException, SamInvalidTaskException, SamMissingTaskTitleException,
      SamMissingTaskValueException, SamMissingTaskArgException, SamSaveFailedException, SamInvalidDateException {
    if (tasks.count() == 0) {
      ui.talk("Your list is empty!");
    } else {
      // "Here is your list:"
      String[] list = tasks.generateList();
      ui.talk(list);
    }  
  }  
}
