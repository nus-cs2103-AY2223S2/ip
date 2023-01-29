import Duke.Parser.Parser;
import Duke.Ui.Ui;
import Duke.entities.TaskList;
import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.Commands.*;

public class Duke {
    private TaskList list;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            storage.connect();
            ui.print("Successfully connected to the database!");
            list = new TaskList(storage);
        } catch (Exception e) {
            ui.printError(e.getMessage());
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("duke.txt").run();
    }

    private void run() throws DukeException {
        ui.showWelcome();
        ui.startScanner();
        String input = ui.readCommand();
        while (input.equals("bye") == false) {
            try {
                Command command = parser.parseCommand(input);
                if (command instanceof ListCommand) {
                    ((ListCommand) command).processCommand(list, ui);
                } else if (command instanceof DeleteCommand) {
                    ((DeleteCommand) command).processCommand(list, parser.parseIndex(input), ui);
                } else if (command instanceof MarkCommand) {
                    ((MarkCommand) command).processCommand(list, parser.parseIndex(input), ui);
                } else if (command instanceof UnmarkCommand) {
                    ((UnmarkCommand) command).processCommand(list, parser.parseIndex(input), ui);
                } else if (command instanceof TodoCommand) {
                    ((TodoCommand) command).processCommand(list, parser.parseDescription(input), ui);
                } else if (command instanceof DeadlineCommand) {
                    ((DeadlineCommand) command).processCommand(list, parser.parseDescription(input),
                            parser.parseDeadline(input), ui);
                } else if (command instanceof EventCommand) {
                    String[] eventlist = parser.parseEvent(input);
                    ((EventCommand) command).processCommand(list, parser.parseDescription(input),
                            eventlist[0], eventlist[1], ui);
                } else if (command instanceof ExitCommand) {
                    ((ExitCommand) command).processCommand(ui);
                } else if (command instanceof SameDateCommand) {
                    ((SameDateCommand) command).processCommand(list, parser.parseDescription(input), ui);
                }
            } catch (DukeException e) {
                ui.printError(e.getMessage());
                ui.printDivider();
            }
            input = ui.readCommand();
        }
        storage.save(this.list);
        ui.printBye();

    }
}








