/**
 * CS2103T Individual Project.
 *
 * @author Wang Jiefan
 * @version 1.0
 * @since 1/20/2023
 */

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
        TaskList temp;
        try {
            temp = new TaskList(filename);
        } catch (Exception e) {
            ui.loadError(e);
            temp = new TaskList(new ArrayList<>());
        }
        taskList = temp;
        Command[] commands = new Command[]{
                new BasicCommand("exit"
                        , "exit the app"
                        , hasQuit -> new String[]{"Goodbye."}),
                new BasicCommand("help"
                        , "show this help message"
                        , hasQuit -> {
                    ui.print();
                    return new String[]{};
                }),
                new BasicCommand("list"
                        , "list tasks"
                        , hasQuit -> taskList.stringify()),
                new ArgCommand("add"
                        , "add task"
                        , new String[]{"\\s"}
                        , (args, hasQuit) -> taskList.add(args)),
                new ArgCommand("mark"
                        , "mark/unmark task as done"
                        , new String[]{}
                        , (args, hasQuit) -> taskList.mark(args)),
                new ArgCommand("delete"
                        , "delete task"
                        , new String[]{}
                        , (args, hasQuit) -> taskList.delete(args)),
                new ArgCommand("find"
                    , "find tasks containing text fragment"
                    , new String[]{}
                    , (args, hasQuit) -> taskList.find(args)),
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
    Boolean hasQuit = false; // in case there are more commands that affect
                     // program execution or state, might replace
                     // with a proper State class()
    while (scanner.hasNextLine() && !hasQuit) {
      try {
        lineParts = scanner.nextLine().split("\\s", 2);
        cmd = this.parser.parseCommand(lineParts[0]);
        if (cmd.hasParams()) {
          if (lineParts.length < 2 || lineParts[1].isEmpty()) {
            throw new IllegalArgumentException("Missing argument.");
          }
          arguments = Parser.parseArgs(lineParts[1], cmd);
          outputs = cmd.execute(arguments, hasQuit);
        } else {
          outputs = cmd.execute(new String[]{}, hasQuit);
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
    scanner.close();
  }

  /**
   * The start of execution of the Duke program.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    new Duke("data.txt").run();
  }
}