package Tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        String[] temp = from.split(" ");
        String[] date = temp[0].split("-");
        if (temp.length == 1) {
            this.from = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), 00, 00); 
        } else {
            this.from = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(temp[1].substring(0,2)), Integer.parseInt(temp[1].substring(2)));
        }

        temp = to.split(" ");
        date = temp[0].split("-");
        if (temp.length == 1) {
            this.to = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), 00, 00); 
        } else {
            this.to = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(temp[1].substring(0,2)), Integer.parseInt(temp[1].substring(2)));
        }
    }

    public String getFrom() {
        return this.from.getYear() + "-" + this.from.getMonthValue() + "-" + this.from.getDayOfMonth() + " " + this.from.getHour() + (this.from.getMinute() < 10 ? "0" + this.from.getMinute() : this.from.getMinute());
    }

    public String getTo() {
        return this.to.getYear() + "-" + this.to.getMonthValue() + "-" + this.to.getDayOfMonth() + " " + this.to.getHour() + (this.to.getMinute() < 10 ? "0" + this.to.getMinute() : this.to.getMinute());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.getMonth().toString().substring(0,3) + " " + this.from.getDayOfMonth() + " " + this.from.getYear() + ", " + this.from.toLocalTime() + " | to: " + this.to.getMonth().toString().substring(0,3) + " " + this.to.getDayOfMonth() + " " + this.to.getYear() + ", " + this.to.toLocalTime() + ")";
    }

    public static void main(String[] args) {
        Event temp = new Event("test", "2019-11-1 1600", "2019-11-1 1111");
        System.out.println(temp.getFrom());
    }
}