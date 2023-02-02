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
    DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
    DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy/MMM/dd");

    DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
    DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("MMM/dd/yyyy");

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    public Event(String startTime, String endTime, String description) throws IOException {
        super(description);
        String[] startComponents = startTime.strip().split(" ");
        System.out.println(startComponents[0]);
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
                try {
                    this.startDate = LocalDate.parse(startComponents[0].strip(), formatter3);
                    this.startTime = LocalTime.parse(startComponents[1].strip(), timeFormatter);
                    this.endDate = LocalDate.parse(endComponents[0].strip(), formatter3);
                    this.endTime = LocalTime.parse(endComponents[1].strip(), timeFormatter);
                } catch (DateTimeParseException e3) {
                    try {
                        this.startDate = LocalDate.parse(startComponents[0].strip(), formatter4);
                        this.startTime = LocalTime.parse(startComponents[1].strip(), timeFormatter);
                        this.endDate = LocalDate.parse(endComponents[0].strip(), formatter4);
                        this.endTime = LocalTime.parse(endComponents[1].strip(), timeFormatter);
                    } catch (DateTimeParseException e4) {
                        try {
                            this.startDate = LocalDate.parse(startComponents[0].strip(), formatter5);
                            this.startTime = LocalTime.parse(startComponents[1].strip(), timeFormatter);
                            this.endDate = LocalDate.parse(endComponents[0].strip(), formatter5);
                            this.endTime = LocalTime.parse(endComponents[1].strip(), timeFormatter);
                        } catch (DateTimeParseException e5) {
                            try {
                                this.startDate = LocalDate.parse(startComponents[0].strip(), formatter6);
                                this.startTime = LocalTime.parse(startComponents[1].strip(), timeFormatter);
                                this.endDate = LocalDate.parse(endComponents[0].strip(), formatter6);
                                this.endTime = LocalTime.parse(endComponents[1].strip(), timeFormatter);
                            } catch (DateTimeParseException e6) {
                                throw new IOException();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return (isDone? "[E][X] " : "[E][ ] ") + description + ". From: " + this.startDayTime + ". To: " +
                endDayTime;
    }
}
