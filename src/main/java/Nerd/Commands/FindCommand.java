package Nerd.Commands;

import Nerd.entities.TaskList;
import Nerd.Parser.Parser;
import Nerd.Ui.Ui;

public class FindCommand extends Command {
    private final String description;
    private Parser parser;

    /**
     * Constructor of Find commands
     *
     * @param description The description of the task.
     */
    public FindCommand(String description, Parser parser) {
        this.description = description;
        this.parser = parser;
    }

    /**
     * Overridden processCommand method from the abstract class Command.
     * Processes the command for an FindCommand.
     *
     * @param list Tasklist of the Chat bot.
     * @param ui   User interface of the Chat bot.
     * @return The tasks searched by the command.
     */
    @Override
    public String processCommand(TaskList list, Ui ui) {
        String result = parser.searchDescription(list, description);
        String output = ui.printSearchResponse(result, this.description);
        return output;
    }
}