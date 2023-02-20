package tasks;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String by;
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
        if (by.matches("\\d{4}-\\d{2}-\\d{2}")){
            LocalDate date = LocalDate.parse(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }
    @Override
    public void updateFields(String[] fields, String[] values) {
        for (int i = 0; i < fields.length; i++) {
            switch(fields[i]){
                case "desc":
                    this.description = values[i];
                    break;
                case "by":
                    if (values[i].matches("\\d{4}-\\d{2}-\\d{2}")){
                        LocalDate date = LocalDate.parse(values[i]);
                        this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    } else {
                        this.by = values[i];
                    }
                    break;
            }
        }
    };
    @Override
    public String toFileString() {
        String mark = this.isDone()? "1": "0";
        return String.format("D | %s | %s | %s", mark, this.getDescription(), this.by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
