import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected DateTimeFormatter format;

    public Deadline(String description, String by, DateTimeFormatter format) {
        super(description);
        this.by = LocalDateTime.parse(by, format);
        this.format = format;
    }

    public Deadline(String description, boolean done, String by, DateTimeFormatter format) {
        super(description, done);
        this.by = LocalDateTime.parse(by, format);
        this.format = format;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | by: " + by.format(format);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Deadline) {
            Deadline x = (Deadline) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }
}

