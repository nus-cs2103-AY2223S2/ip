package Tasks;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        String[] temp = by.split(" ");
        String[] date = temp[0].split("-");
        if (temp.length == 1) { this.by = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), 00, 00); 
        } else { this.by = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(temp[1].substring(0,2)), Integer.parseInt(temp[1].substring(2))); }

    }

    public String getBy() {
        return this.by.getYear() + "-" + this.by.getMonthValue() + "-" + this.by.getDayOfMonth() + " " + this.by.getHour() + (this.by.getMinute() < 10 ? "0" + this.by.getMinute() : this.by.getMinute());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.getMonth().toString().substring(0,3) + " " + this.by.getDayOfMonth() + " " + this.by.getYear() + ", " + this.by.toLocalTime() + ")";
    }
}