import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static void createDeadline(TaskList taskList, String desc) {
        Ui.addedTask();
        String[] inputSplit = desc.split("/",2);
        String input = inputSplit[0];
        String[] dateSplit = inputSplit[1].split(" ", 2);
        String date = dateSplit[1];
        Deadline deadline = new Deadline(input, LocalDate.parse(date));
        taskList.addTask(deadline);
        Ui.indent("" + deadline);
    }

    public static void runDeadline(TaskList taskList, String description) {
        createDeadline(taskList, description);
        Ui.checkList(taskList);
    }

    @Override
    public String toSave () {
        return "D | " + super.toSave() + "| " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
