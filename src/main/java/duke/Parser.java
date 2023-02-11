package duke;

import command.*;
import exception.UnknownInputException;
import exception.EmptyDescriptionException;

/**
 * Represents a parser object to parse input commands
 */
public class Parser {
    private DukeList dukelist;
    private Storage storage;
    private Ui ui;

    /**
     * Creates a Parser object
     *
     * @param dukeList a DukeList that can be accessed by this Parser
     * @param storage a Storage that is used to read and write
     */
    public Parser (DukeList dukeList, Storage storage , Ui ui) {
        this.dukelist = dukeList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Translates an input String to its corresponding command
     * @param inputText the input String that is to be parsed
     * @return the command that corresponds to the input text
     * @throws EmptyDescriptionException when there is a valid command with no content
     * @throws UnknownInputException when the input text doe not correspond to any known command
     */
    public Command parse(String inputText) throws EmptyDescriptionException, UnknownInputException{
        String[] array = inputText.split(" ", 2);
        try {
            String first = array[0];
            if (inputText.equals("bye")) {
                return new ExitCommand(this.dukelist, this.storage, this.ui);
            } else if (first.equals("list")){
                return new ListCommand(this.dukelist, this.ui);
            } else if (first.equals("mark")) {
                return new MarkCommand(array[1], true, dukelist);
            } else if (first.equals("unmark")) {
                return new MarkCommand(array[1], false, dukelist);
            } else if (first.equals("delete")){
                return new DeleteCommand(array[1], dukelist);
            } else if (first.equals("find")) {
                return new FindCommand(array[1], dukelist);
            } else {
                if (array.length != 2) {
                    if (first.equals("todo") || first.equals("deadline") || first.equals("event")) {
                        throw new EmptyDescriptionException("Sorry, your " + first + " task description is missing.");

                    } else {
                        throw new UnknownInputException("Hmm, I'm not sure what you're saying man.");
                    }
                }
                return new AddCommand(array[0], array[1], dukelist);

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your command is too short man");
        }
        return new InvalidCommand();
    }
}
