package tigerlily.commands;

import tigerlily.Storage;
import tigerlily.Ui;

import tigerlily.tasks.Event;
import tigerlily.tasks.TaskList;

import java.time.LocalDateTime;

public class EventCommand implements Command {
    private String input;

    /**
     * Constructor for EventCommand
     * @param input the input given to create a Event
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new Event, adds it to the TaskList, and displays confirmation message of addition.
     *
     * @param taskList the TaskList the new Event is added to
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used during this session
     * @return the confirmation message that the new Event has been added successfully
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String[] s1 = input.substring(6).split("/from");
        String[] s2 = s1[1].split("/to");

        Event newEvent = new Event(s1[0].stripTrailing(), LocalDateTime.parse(s2[0].trim(), Event.getEventFormatter()),
                LocalDateTime.parse(s2[1].trim(), Event.getEventFormatter()));
        taskList.addTask(newEvent);
        return ui.showAddedMessage(newEvent, taskList);
    }
}