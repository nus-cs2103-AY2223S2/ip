import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate deadline;
    private char type = 'D';
    public Deadline(String description, LocalDate date){
        super(description);
        this.deadline = date;
    }
    public void setDeadline(LocalDate date){
        this.deadline = date;
    }
    public LocalDate getDeadline(){
        return this.deadline;
    }
    @Override
    public char getType() {
        return type;
    }
    @Override
    public String toString(){
        return super.toString() + "(by: "+ deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
