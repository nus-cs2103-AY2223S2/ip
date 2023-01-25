package commands;

import data.MyData;
import tasks.Event;
import ui.Ui;

public class AddEvent extends Command {
    protected Event event;

    public AddEvent(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }

    public void execute(MyData data, Ui ui) {
        data.setData(event);
        data.saveToFile();
        ui.add(event.toString(), data.len());
    }
}

