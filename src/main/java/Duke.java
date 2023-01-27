import Duke.Parser.Parser;
import Duke.Ui.Ui;
import Duke.entities.TaskList;
import Duke.exceptions.DukeFileNotFoundException;
import Duke.exceptions.EmptyDescException;
import Duke.exceptions.InvalidInputException;
import Duke.storage.Storage;
import Duke.Commands.*;

import java.io.FileNotFoundException;

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
        } catch (FileNotFoundException e) {
            ui.printError(e.getMessage());
        } catch (InvalidInputException e) {
            ui.printError(e.getMessage());
        }

    }

    public static void main(String[] args) throws EmptyDescException, InvalidInputException, DukeFileNotFoundException {
        new Duke("duke.txt").run();
    }

    private void run() throws InvalidInputException, EmptyDescException {
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
            } catch (InvalidInputException e) {
                ui.printError(e.getMessage());
                ui.printDivider();
            }
            input = ui.readCommand();
        }
        storage.save(this.list);
        ui.printBye();

    }
}








