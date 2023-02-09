package timeslot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FreeTimeBlock {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public FreeTimeBlock(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    private int getTimeDifference() {
        if (hasNoBounds()) {
            return -1;
        }
        long difference = Math.abs(Duration.between(start, end).toSeconds());
        return Math.toIntExact(difference);
    }

    public boolean isValidSlot(int desiredFreeTime) {
        if (hasNoBounds()) {
            return true;
        }
        return this.getTimeDifference() >= desiredFreeTime;
    }

    private boolean hasNoBounds() {
        return (start == null || end == null);
    }

    @Override
    public String toString() {
        return "FreeTimeBlock{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public String describeSelf() {

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, dd/M/yyyy");

        if (start == null && end == null) {
            return "Wooks wike you are fwee anytime from now!";
        }

        if (start != null && end == null) {
            String startTime = start.format(timeFormat);
            String date = start.format(dateFormat);
            return String.format("Wooks wike you are free from %s starting %s UwU!", startTime, date);
        }

        if (start == null) {
            String endTime = end.format(timeFormat);
            String date = end.format(dateFormat);
            return String.format("Wooks wike you are free from now until %s starting %s UwU!", endTime, date);
        }

        String startTime = start.format(timeFormat);
        String endTime = end.format(timeFormat);
        String date = start.format(dateFormat);
        return String.format("Wooks wike you are free between %s and %s on %s UwU!", startTime, endTime, date);
    }
}
