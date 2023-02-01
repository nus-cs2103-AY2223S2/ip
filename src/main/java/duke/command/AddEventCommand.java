package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.reply(taskList.addEvent(description, from, to));
            storage.saveState(taskList);
        } catch (DukeException e) {
            ui.reply(e.toString());
        }
    }
}
