package duke;

public class Parser {
  String action;

  public void parse(String textInput) {
    if (textInput.equalsIgnoreCase("bye")) {
      this.action = "bye";
    }

    else if (textInput.equalsIgnoreCase("list")) {
      this.action = "list";
    }

    else if (textInput.length() >= 8 &&
            textInput.substring(0, 6).equalsIgnoreCase("delete")) {
      this.action = "delete";
    }

    else if (textInput.length() >= 6 && 
            textInput.substring(0, 4).equalsIgnoreCase("mark")) {
      this.action = "mark";
    }

    else if (textInput.length() >= 8 &&
            textInput.substring(0, 6).equalsIgnoreCase("unmark")) {
      this.action = "unmark";  
    }

    else if (textInput.length() >= 6 &&
            textInput.substring(0, 4).equalsIgnoreCase("find")) {
      this.action = "find";
    }

    else if (textInput.length() >= 4 &&
            textInput.substring(0, 4).equalsIgnoreCase("todo")) {
      this.action = "todo";
    }

    else if (textInput.length() >= 10 && 
            textInput.substring(0, 8).equalsIgnoreCase("deadline")) {
      this.action = "deadline";
    }

    else if (textInput.length() >= 7 && 
            textInput.substring(0, 5).equalsIgnoreCase("event")) {
      this.action = "event";
    }

    else {
      this.action = "not found";
    }
  }
}
