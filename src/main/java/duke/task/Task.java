package duke.task;

/**
 * The type Task.
 */
public abstract class Task {
  /**
   * The Task name.
   */
  public String taskName;
  /**
   * The Message add.
   */
  public String messageAdd;
  /**
   * The Message marked.
   */
  public String messageMarked;
  /**
   * The Message unmarked.
   */
  public String messageUnmarked;
  /**
   * The Message display.
   */
  public String messageDisplay;
  /**
   * The Message delete.
   */
  public String messageDelete;
  /**
   * The Done.
   */
  public boolean done;

  /**
   * Instantiates a new Task.
   *
   * @param name the name
   * @param done the done
   */
  Task(String name, boolean done) {
    this.taskName = name;
    this.done = done;
    this.messageAdd = "";
    this.messageMarked = "";
    this.messageUnmarked = "";
    this.messageDelete = "";
  }

  /**
   * Add.
   */
  public abstract void add();

  /**
   * Marked.
   */
  public abstract void marked();

  /**
   * Unmarked.
   */
  public abstract void unmarked();

  /**
   * Display.
   */
  public abstract void display();

  /**
   * Delete.
   */
  public abstract void delete();
}
