import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public static void createEvent(TaskList taskList, String desc) {
        Ui.addedTask();
        String[] splitInput = desc.split("/",2);
        String input = splitInput[0];
        String[] splitDate = splitInput[1].split(" ", 2);
        String date = splitDate[1];
        Event event = new Event(input, LocalDate.parse(date));
        taskList.addTask(event);
        Ui.indent("" + event);
    }

    public static void runEvent(TaskList taskList, String description) {
        createEvent(taskList, description);
        Ui.checkList(taskList);
    }

    @Override public String toSave() {
        return "E | " + super.toSave() + "| " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
