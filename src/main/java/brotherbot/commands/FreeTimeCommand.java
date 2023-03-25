package brotherbot.commands;

import brotherbot.storage.Event;
import brotherbot.storage.Task;
import brotherbot.storage.TaskList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class FreeTimeCommand extends Command {

    /**
     * Constructor to create a FreeTimeCommand object.
     *
     * @param input Input string required for command execution.
     */
    public FreeTimeCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     */
    public String execute(TaskList storage) {
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 0; i < storage.size(); i++) {
            Task x = storage.get(i);
            if (Objects.equals(x.type, "E")) {
                events.add((Event) x);
            }
        }
        events.sort((e1, e2) -> e1.start.compareTo(e2.start));

        LocalDateTime start = LocalDateTime.now();
        Duration duration = Duration.parse("PT" + this.input.substring(5));
        LocalDateTime end = start.plus(duration);

        for (Event x : events) {
            if (start.isBefore(x.start) && end.isBefore(x.start)) {
                break;
            }
            start = x.end;
            end = start.plus(duration);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String output = "My brother, your next freeslot is from " + start.format(formatter) + " to " + end.format(formatter) + ". Create new event now?";
        return output;
    }
}
