package duke;

import duke.command.ArgCommand;
import duke.command.BasicCommand;
import duke.command.Command;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class of the Duke program.
 */
public class Duke {

  private final Ui ui;
  private final Parser parser;

    private Duke(String filename) {
        this.ui = new Ui();
        TaskList taskList;
        try {
            taskList = new TaskList(filename);
        } catch (Exception e) {
            ui.loadError(e);
            taskList = new TaskList(new ArrayList<>());
        }
        Command[] commands = new Command[]{
                new BasicCommand("exit"
                        , "exit the app"
                        , () -> new String[]{"Goodbye."}),
                new BasicCommand("help"
                        , "show this help message"
                        , () -> {
                    ui.print();
                    return new String[]{};
                }),
                new BasicCommand("list"
                        , "list tasks"
                        , taskList::stringify),
                new ArgCommand("add"
                        , "add task"
                        , new String[]{"\\s"}
                        , taskList::add),
                new ArgCommand("mark"
                        , "mark/unmark task as done"
                        , new String[]{}
                        , taskList::mark),
                new ArgCommand("delete"
                        , "delete task"
                        , new String[]{}
                        , taskList::delete),
                new ArgCommand("find"
                    , "find tasks containing text fragment"
                    , new String[]{}
                    , taskList::find),
        };
        ui.setCommands(commands);
        this.parser = new Parser(commands);
    }

  private void run() {
    this.ui.printIntro();
    Scanner scanner = new Scanner(System.in);
    String[] outputs;
    String[] arguments;
    String[] lineParts;
    Command cmd;

    while (scanner.hasNextLine()) {
      try {
        lineParts = scanner.nextLine().split("\\s", 2);
        cmd = this.parser.parseCommand(lineParts[0]);
        if (cmd.hasParams()) {
          if (lineParts.length < 2 || lineParts[1].isEmpty()) {
            throw new IllegalArgumentException("Missing argument.");
          }
          arguments = Parser.parseArgs(lineParts[1], cmd);
          outputs = cmd.execute(arguments);
        } else {
          outputs = cmd.execute(new String[]{});
        }
        this.ui.print(outputs);
        if (cmd.getName().equals("exit")) {
          break;
        }
      } catch (Exception e) {
        this.ui.error(e);
        this.ui.print();
      }
    }
  }

    /**
     * The start of execution of the Duke program.
     * @param args  The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}