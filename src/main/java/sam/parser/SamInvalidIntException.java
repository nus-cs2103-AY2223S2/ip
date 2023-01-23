package sam.parser;

import sam.SamException;

public class SamInvalidIntException extends SamException {
  public SamInvalidIntException() {
    super("Oops, I was expecting an integer!");
  }
}