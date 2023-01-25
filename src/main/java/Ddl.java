import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ddl extends Task {
    private String time;

    public Ddl(String input, String time) {
        super(input);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate output = LocalDate.parse(time, dateFormatter);
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        this.time = output.format(newPattern);

    }


    public String toString() {
        return "   [D]" + super.toString() + " (by: " + this.time + ")";
    }

}
