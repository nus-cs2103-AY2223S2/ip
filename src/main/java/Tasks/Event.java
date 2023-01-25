package Tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        String[] temp = from.split(" ");
        String[] date = temp[0].split("-");
        if (temp.length == 1) { this.from = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), 00, 00); 
        } else { this.from = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(temp[1].substring(0,2)), Integer.parseInt(temp[1].substring(2))); }

        temp = to.split(" ");
        date = temp[0].split("-");
        if (temp.length == 1) { this.to = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), 00, 00); 
        } else { this.to = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(temp[1].substring(0,2)), Integer.parseInt(temp[1].substring(2))); }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.getMonth().toString().substring(0,3) + " " + this.from.getDayOfMonth() + " " + this.from.getYear() + ", " + this.from.toLocalTime() + " | to: " + this.to.getMonth().toString().substring(0,3) + " " + this.to.getDayOfMonth() + " " + this.to.getYear() + ", " + this.to.toLocalTime() + ")";
    }
}