package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{
    protected LocalDate deadline;
    private static final String PREFIX = "D";

    public Deadline(String desc, LocalDate deadline){
//        String[] args = rawargs.split(" ", 2);
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String save() {
        StringBuilder response = new StringBuilder("");
        response.append(getPrefix() + ",");
        response.append(isDone + ",");
        response.append(description + ",");
        response.append(deadline + "\n");
        return response.toString();
    }

    @Override
    public String toString(){
        return super.toString()
                + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}
