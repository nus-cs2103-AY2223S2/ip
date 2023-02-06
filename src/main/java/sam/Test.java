package sam;

public enum Test {
  GREETING("Hello, I am Sam!"),
  BYE("Goodbye!"),
  LIST("Here is your list:"),
  LIST_EMPTY("Your list is empty!"),
  MARK("Great! I'll check the task:"),
  UNMARK("Okay, I'll uncheck the task:"),
  ADD("Gotcha, I'll add the task to your list:"),
  ADD_COUNT("Now you have %d tasks in the list");

  private String dialog;

  private Test(String dialog) {
      this.dialog = dialog;
  }

  public String getDialog() {
      return dialog;
  }

  @Override
  public String toString() {
    return dialog;
  }
}