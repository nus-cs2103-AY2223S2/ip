package duke;
import java.time.LocalDate;

public class Event extends Task{

    private LocalDate startDate;
    private LocalDate endDate;
    public Event(String name, String inpString, LocalDate startDate, LocalDate endDate){
        super(name, inpString);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Output the event task (with dates) for user to see.
     */
    @Override
    public void printTask(){
        System.out.print("[E]");
        super.printTask();
        System.out.println("(from: " + startDate + " to: " + endDate + ")");

    }

}
