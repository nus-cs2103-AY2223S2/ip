package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DeadLine extends Task {
    LocalDate localDate;

    public DeadLine(String keyword, String message, Boolean status) {
        super(keyword, message, status);
        String[] separateText = this.description.split(" /by ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.localDate = LocalDate.parse(separateText[1], formatter);
    }

    @Override
    public String provideDetails() {
        String[] separateText = this.description.split(" /by ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String date = localDate.format(formatter);
        return this.completed ? "[D]" + "[x] " + separateText[0] + " (by: " + date + ")"
                : "[D]" + "[ ] " + separateText[0] + " (by: " + date + ")";
    }
}

