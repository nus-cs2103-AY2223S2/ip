package sam.task;

import sam.SamException;

public class SamMissingTaskException extends SamException {
  public SamMissingTaskException() {
    super("Oops, you forgot to specify a task!");
  }
}