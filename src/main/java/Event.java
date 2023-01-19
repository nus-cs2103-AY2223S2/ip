import Exceptions.EmptyEventException;

import java.util.ArrayList;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static void createEvent(ArrayList<Task> taskList, String desc) {
        String[] s = desc.split("/");
        Format.line();
        System.out.println("Got it. I've added this task:");
        Event event = new Event(s[0], s[1].substring(4), s[2].substring(2));
        taskList.add(event);
        Format.indent("" + event);
    }

    public static void runEvent(ArrayList<Task> taskList, String description) {
        try {
            if (description.length() == 0) {
                throw new EmptyEventException("");
            }
            Event.createEvent(taskList, description);
            Format.checkList(taskList);
        } catch (EmptyEventException e) {
            Format.line();
            System.out.println(e.getMessage());
            Format.line();
        } catch (ArrayIndexOutOfBoundsException e) {
            Format.line();
            System.out.println("Hey! The description of a deadline cannot be empty!");
            Format.line();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
    }
}
