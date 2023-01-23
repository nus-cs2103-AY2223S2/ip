public class Duke {
  public static void main(String[] args) {
    Ui ui = new Ui();
    TaskList taskList = new TaskList();
    Parser parser = new Parser();

    ui.printGreeting();
    String command = ui.readLine();
    while (!command.equals("bye")) {
      try {
        parser.dispatch(command, ui, taskList);
      } catch (DukeException e) {
        ui.print(e.getMessage());
      }
      command = ui.readLine();
    }
    ui.printBye();
  }
}
