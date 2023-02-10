package lulu.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    final int NUMBER_HYPHENS_IN_DATE = 3;

    private LocalDate byDate;
    private String by;

    public Deadline(String description, String by) {
        super(description);
        try {
            if (by.split("-").length == NUMBER_HYPHENS_IN_DATE) {
                this.byDate = LocalDate.parse(by.substring(1, 11));
            } else {
                this.by = by;
            }
        } catch (DateTimeParseException e) {
            System.out.println("INVALID DATE FORMAT, please use xxxx-xx-xx");
        }
    }

    @Override
    public String toString() {
        String s;
        if (byDate == null) {
            s = by;
        } else {
            s = byDate.toString();
        }
        return ("[D]" + super.toString() + "(by: " + s + ")");
    }

    @Override
    public String toMemory() {
        int i = this.isDone ? 1 : 0;
        String s;
        if (byDate == null) {
            s = by;
        } else {
            s = byDate.toString();
            // space necessary for loading data the next time
            s = ' ' + s;
        }
        return ("D`" + i + "`" + this.description + "`" + s + '\n');
    }

    @Override
    public void update(String text) {
        String[] updateInformation = text.split(" ");
        String update = updateInformation[0].toUpperCase();
        switch (update) {
        case "DESCRIPTION":
            this.description = updateInformation[1];
            break;
        case "BY":
            this.by = updateInformation[1];
            break;
        }
    }
}
