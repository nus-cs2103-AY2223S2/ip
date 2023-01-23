package sam.parser;

import sam.SamException;

public class SamUnknownCommandException extends SamException {
  public SamUnknownCommandException() {
    super("Sorry, I don't know what that means");
  }
}