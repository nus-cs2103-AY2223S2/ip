package Nerd;

import Nerd.Parser.Parser;
import Nerd.Ui.Ui;
import Nerd.entities.TaskList;
import Nerd.exceptions.NerdException;
import Nerd.storage.Storage;
import Nerd.Commands.*;

/**
 * Represents the Duke.Duke Chat bot.
 * Running a duke object loads data from the specified file into memory,
 * and exiting the program writes data to the hard disk.
 */
public class Nerd {
    private TaskList list;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private String filePath;

    /**
     * Duke.Duke Constructor for initializing the Duke object.
     *
     * @param filePath of the storage
     */
    public Nerd(String filePath) {
        this.filePath = filePath;
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

    /**
     * duke.Duke Main method that runs the duke chatbot.
     *
     * @param args arguments of the main function
     */
    public static void main(String[] args) {
        new Nerd("duke.txt").run();
    }


    /**
     * Runs the Chat bot and processes the inputs given by user.
     * Parses each input string and runs the corresponding command.
     * Saves the output into the read file.
     */
    private void run() {
        ui.showWelcome();
        ui.showCommandList();
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
                } else if (command instanceof SearchDateCommand) {
                    ((SearchDateCommand) command).processCommand(list, parser.parseDescription(input), ui);
                } else if (command instanceof FindCommand) {
                    ((FindCommand) command).processCommand(list, parser.parseDescription(input), ui);
                }
            } catch (NerdException e) {
                ui.printError(e.getMessage());
                ui.printDivider();
            }
            input = ui.readCommand();
        }
        storage.save(this.list);
        ui.printBye();

    }

    public String getResponse(String input) {
        String output = "";
        try {
            Command command = parser.parseCommand(input);
            if (command instanceof ListCommand) {
                output = ((ListCommand) command).processCommand(list, ui);
            } else if (command instanceof DeleteCommand) {
                output = ((DeleteCommand) command).processCommand(list, parser.parseIndex(input), ui);
            } else if (command instanceof MarkCommand) {
                output = ((MarkCommand) command).processCommand(list, parser.parseIndex(input), ui);
            } else if (command instanceof UnmarkCommand) {
                output = ((UnmarkCommand) command).processCommand(list, parser.parseIndex(input), ui);
            } else if (command instanceof TodoCommand) {
                output = ((TodoCommand) command).processCommand(list, parser.parseDescription(input), ui);
            } else if (command instanceof DeadlineCommand) {
                output = ((DeadlineCommand) command).processCommand(list, parser.parseDescription(input),
                        parser.parseDeadline(input), ui);
            } else if (command instanceof EventCommand) {
                String[] eventlist = parser.parseEvent(input);
                output = ((EventCommand) command).processCommand(list, parser.parseDescription(input),
                        eventlist[0], eventlist[1], ui);
            } else if (command instanceof ExitCommand) {
                output = ((ExitCommand) command).processCommand(ui);
            } else if (command instanceof SearchDateCommand) {
                output = ((SearchDateCommand) command).processCommand(list, parser.parseDescription(input), ui);
            } else if (command instanceof FindCommand) {
                output = ((FindCommand) command).processCommand(list, parser.parseDescription(input), ui);
            }
        } catch (NerdException e) {
            return e.getMessage();
        }
        return output;
    }
}








