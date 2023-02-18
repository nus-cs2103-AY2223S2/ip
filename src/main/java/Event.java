import java.time.LocalDate;
import java.util.Scanner;

public class Event extends Task {
    private static final String TYPE = "[E]";
    private final LocalDate start;
    private final LocalDate end;

    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, LocalDate start, LocalDate end, boolean isMarked) {
        super(name, isMarked);
        this.start = start;
        this.end = end;
    }

    public static Event createEvent(Scanner scanner) throws DukeyException{
        System.out.print("Event name: ");
        String eventName = scanner.nextLine();
        if (eventName.equals("")) {
            throw new DukeyException("Error! Event name cannot be empty!");
        }
        System.out.print("Event start: ");
        String eventStartString = scanner.nextLine();
        if (eventStartString.equals("")) {
            throw new DukeyException("Error! Please provide a start time!");
        }
        System.out.print("Event end: ");
        String eventEndString = scanner.nextLine();
        if (eventEndString.equals("")) {
            throw new DukeyException("Error! Please provide an end time!");
        }

        return new Event(eventName, DukeyTime.getDateFromString(eventStartString),
                DukeyTime.getDateFromString(eventEndString));
    }

    public static Event createEventFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        String startString = logStringArray[3];
        String endString = logStringArray[4];
        boolean isMarked = !logStringArray[1].equals("0");

        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        try {
            start = DukeyTime.getDateFromString(startString);
            end = DukeyTime.getDateFromString(endString);
        } catch (DukeyException e) {
            e.printMessage();
        }
        return new Event(name, start, end, isMarked);
    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new event:";
    }

    @Override
    public String getLogString() {
        return "E" + "," + isDoneStatus() + "," + this.getName() + "," + this.start + "," + this.end;
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (" + DukeyTime.dateToString(this.start)
                    + " to " + DukeyTime.dateToString(this.end) + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (" + DukeyTime.dateToString(this.start)
                + " to " + DukeyTime.dateToString(this.end) + ")";
    }



}
