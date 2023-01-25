package duke;

import duke.exception.DukeException;
import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.TaskList;
import duke.helper.Ui;

import java.io.IOException;

public class Duke {
  public static void main(String[] args) throws IOException {
    Ui ui = new Ui();
    TaskList taskList = new TaskList();
    Parser parser = new Parser();
    Storage store = new Storage(taskList);

    ui.printGreeting();
    String command = ui.readLine();
    while (!command.equals("bye")) {
      try {
        parser.dispatch(command, ui, taskList);
        store.saveTasks(taskList);
      } catch (DukeException e) {
        ui.print(e.getMessage());
      }
      command = ui.readLine();
    }
    ui.printBye();
  }
}
