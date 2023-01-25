package Duke;

import Command.*;
import Exception.UnknownInputException;
import Exception.EmptyDescriptionException;

public class Parser {
    private DukeList dukelist;
    private Storage storage;

    public Parser (DukeList dukeList, Storage storage ) {
        this.dukelist = dukeList;
        this.storage = storage;
    }

    public Command parse(String inputText) throws EmptyDescriptionException, UnknownInputException{
        String[] array = inputText.split(" ", 2);
        String first = array[0];
        if (inputText.equals("bye")) {
            return new ExitCommand(this.dukelist, this.storage);
        } else if (first.equals("list")){
            return new ListCommand(this.dukelist);
        } else if (first.equals("mark")) {
            return new MarkCommand(array[1], true, dukelist);
        } else if (first.equals("unmark")) {
            return new MarkCommand(array[1], false, dukelist);
        } else if (first.equals("delete")){
            return new DeleteCommand(array[1], dukelist);
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

    }
}
