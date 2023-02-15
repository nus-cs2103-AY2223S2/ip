import java.util.Scanner;

public class Event extends Item {
    private static final String TYPE = "[E]";
    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public static Event createEvent(Scanner scanner) throws DukeyException{
        System.out.print("Event name: ");
        String eventName = scanner.nextLine();
        if (eventName.equals("")) {
            throw new DukeyException("Error! Event name cannot be empty!");
        }
        System.out.print("Event start time: ");
        String eventStart = scanner.nextLine();
        if (eventStart.equals("")) {
            throw new DukeyException("Error! Please provide a start time!");
        }
        System.out.print("Event end time: ");
        String eventEnd = scanner.nextLine();
        if (eventEnd.equals("")) {
            throw new DukeyException("Error! Please provide an end time!");
        }

        return new Event(eventName, eventStart, eventEnd);
    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new event:";
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (" + this.start + " to " + this.end + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (" + this.start + " to " + this.end + ")";
    }



}
