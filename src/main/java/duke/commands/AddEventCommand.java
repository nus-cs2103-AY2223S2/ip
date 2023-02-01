package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddEventCommand extends AddCommand {
    public AddEventCommand(ArrayList<String> tokens) throws DukeException, DateTimeParseException {
        super(tokens);
        int fromId = tokens.indexOf("/from");
        int toId = tokens.indexOf("/to");
        if (fromId < 0 || toId < 0){
            throw new DukeException("Invalid input received! \ntasks.Event commands are in the form of: event name /from fromtime /to totime \n(remember to include '/from' and '/to')");
        }

        String name = String.join(" ", tokens.subList(1, fromId));
        LocalDate from = LocalDate.parse(String.join(" ", tokens.subList(fromId+1, toId)));
        LocalDate to = LocalDate.parse(String.join(" ", tokens.subList(toId+1, tokens.size())));
        Event newEvent = new Event(name, from, to);
        super.setTaskToAdd(newEvent);
    }
}
