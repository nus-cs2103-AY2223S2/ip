package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.Event;
import duke.tasks.TaskList;

import java.time.LocalDateTime;

public class EventCommand implements Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] s1 = input.substring(6).split("/from");
        String[] s2 = s1[1].split("/to");

        Event newEvent = new Event(s1[0].stripTrailing(), LocalDateTime.parse(s2[0].trim(), Event.getEventFormatter()),
                LocalDateTime.parse(s2[1].trim(), Event.getEventFormatter()));
        taskList.addTask(newEvent);
        ui.showAddedMessage(newEvent, taskList);
    }
}