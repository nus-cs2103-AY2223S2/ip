import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    String startDayTime;
    String endDayTime;

    String deadlineString;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public Event(String startTime, String endTime, String description) throws IOException {
        super(description);
        String[] startComponents = startTime.strip().split(" ");
        String[] endComponents = endTime.strip().split(" ");
        this.startDayTime = startTime;
        this.endDayTime = endTime;
        try {
            this.startDate = LocalDate.parse(startComponents[0].strip(), formatter);
            this.startTime = LocalTime.parse(startComponents[1].strip(), timeFormatter);
            this.endDate = LocalDate.parse(endComponents[0].strip(), formatter);
            this.endTime = LocalTime.parse(endComponents[1].strip(), timeFormatter);
        } catch (DateTimeParseException e) {
            try {
                this.startDate = LocalDate.parse(startComponents[0].strip(), formatter2);
                this.startTime = LocalTime.parse(startComponents[1].strip(), timeFormatter);
                this.endDate = LocalDate.parse(endComponents[0].strip(), formatter2);
                this.endTime = LocalTime.parse(endComponents[1].strip(), timeFormatter);
            } catch (DateTimeParseException e2) {
                System.out.println("Please enter the date in this format: dd-MMM-YYYY OR dd/MMM/YYYY");
                System.out.println("Please enter the time in this format: HHmm");
            }
        }
    }

    @Override
    public String toString() {
        return (isDone? "[E][X] " : "[E][ ] ") + description + ". From: " + this.startDate.toString() + "@ " +
                this.startTime.toString() +
                ". To: " + this.endDate.toString() + "@ " + this.endTime.toString();
    }
}
