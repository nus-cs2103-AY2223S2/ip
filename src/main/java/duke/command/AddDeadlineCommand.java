package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.textui.TextUi;

/**
 * A command that stores the command to add a new deadline task. The action of
 * adding the task can be carried out when called.
 */
public class AddDeadlineCommand extends Command {
  /**
   * The DESCRIPTION and due date stored as string representation.
   */
  private final String data;

  /**
   * Constructor for a command to add new deadline task.
   *
   * @param data The DESCRIPTION and due date of the deadline task
   */
  public AddDeadlineCommand(String data) {
    super(AvailableCommands.ADD_DEADLINE);
    this.data = data;
  }

  /**
   * Adds a new deadline task into the task list.
   * The due date is filtered out. If it exists, then a deadline task will be
   * created. Otherwise, an exception would be thrown stating that a due date
   * was not specified.
   *
   * @param taskList List of tasks that are stored
   * @param ui       UI to deal with the visual output
   * @param storage  Storage to deal with input and output of data
   */
  @Override
  public String execute(TaskList taskList, TextUi ui, Storage storage)
      throws DukeException {
    String[] splitData = data.split(" /by ", 2);
    if (splitData.length < 2) {
      throw new DukeException("Deadline command format error. Missing /by");
    }

    Task deadline = new Deadline(splitData[0], splitData[1]);
    taskList.addTask(deadline);

    return ui.showAddTask(deadline.toString(), taskList.size());
  }
}
