public class EmptyDescription extends DuckException {
  public String emptyDescription;
  public EmptyDescription(Task1 task) {
    this.emptyDescription = "OOPS!!! The description of a "
        + task.toString() +  " cannot be empty.";
  }
}
