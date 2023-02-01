import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDate date; // can do more with this
    public Deadline(String name, String inpString, LocalDate date){
        super(name, inpString);
        this.date = date;
    }

    @Override
    public void printTask(){
        System.out.print("[D]");
        super.printTask();
        System.out.println("(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
