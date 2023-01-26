import java.time.LocalDate;
public class Deadline extends Task{

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        System.out.println(by);
        this.by = LocalDate.parse(by.substring(1, by.length()));
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + "(by: " + by + ")");
    }
}