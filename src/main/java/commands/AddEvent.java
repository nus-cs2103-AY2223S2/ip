package commands;

import data.MyData;
import tasks.Event;

public class AddEvent extends Commands {
    protected Event event;

    public AddEvent(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }

    public void execute(MyData data) {
        data.setData(event);
        System.out.print(getLine() +
                "     Got it. I've added this task:\n" +
                "       " + event + "\n" +
                "     Now you have " + data.len() + " tasks in the list.\n" +
                getLine());
    }
}
