import command.Command;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {
  private Storage storage;
  private TaskList tasks;
  private Ui ui;

  public Duke(String filePath) {
    this.ui = new Ui();
    this.storage = new Storage(filePath);
    this.tasks = new TaskList(this.storage.load());
  }

  public void run() {
    this.ui.showWelcome();
    this.handleRequest();
    this.ui.showExit();
  }

  public void handleRequest() {
    while (true) {
      try {
        String fullcommand = ui.readCommand();
        Command command = Parser.parse(fullcommand);

        if (command.isExit()) {
          break;
        }

        command.execute(tasks, ui, storage);

      } finally {
        ui.showDivider();
      }
    }
  }

  public void echo(String input) {
    ui.showMessage(input);
  }

  public static void main(String[] args) {
    new Duke("data/duke.txt").run();
  }
}
