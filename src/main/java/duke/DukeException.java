package duke;

/**
 * An exception for any errors in the Duke chat operation
 */
public class DukeException extends Exception{

  /**
   * Creates an exception for the Duke chat
   *
   * @param e description of the exception to be thrown
   */
  public DukeException(String e) {
    super(e);
  }
}