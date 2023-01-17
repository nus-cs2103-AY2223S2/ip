public class ToDo extends Task {

  /**
   * Constructor for the ToDo class
   * 
   * @param name - The name of the ToDo task
   */
  ToDo(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

}
