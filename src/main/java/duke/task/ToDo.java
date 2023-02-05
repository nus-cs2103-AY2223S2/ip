package duke.task;

import duke.utilities.Parser;

/**
 * The type To do.
 */
public class ToDo extends Task {

  /**
   * The Raw input.
   */
  public final String rawInput;

  /**
   * Instantiates a new To do.
   *
   * @param name the name
   * @param done the done
   */
  public ToDo(String name, boolean done) {
    super(name, done);
    rawInput = name;
  }

  @Override
  public void add() {
    messageAdd = Parser.ADDED_THIS_TASK
            + Parser.TODO_UNMARKED_SPACED + taskName;
  }

  @Override
  public void display() {
    if (done) {
      messageDisplay = Parser.TODO_MARKED + taskName;
    } else {
      messageDisplay = Parser.TODO_UNMARKED + taskName;
    }

  }

  @Override
  public void delete() {
    if (done) {
      messageDelete = Parser.REMOVED_THIS_TASK
              + Parser.TODO_MARKED_SPACED + taskName;
    } else {
      messageDelete = Parser.REMOVED_THIS_TASK
              + Parser.TODO_UNMARKED_SPACED + taskName;
    }
  }

  @Override
  public void marked() {
    messageMarked = Parser.MARKED_THIS_TASK_AS_DONE
            + Parser.TODO_MARKED_SPACED + taskName + "\n";
    done = true;
  }

  @Override
  public void unmarked() {
    messageUnmarked = Parser.MARKED_THIS_TASK_AS_NOT_DONE_YET
            + Parser.TODO_UNMARKED_SPACED + taskName +"\n";
    done = false;
  }

}
