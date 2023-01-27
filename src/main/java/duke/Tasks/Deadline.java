package duke.Tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline  extends  Task{
    protected LocalDateTime doneBy;

    public Deadline(String description, LocalDateTime doneBy){
        super(description);
        this.doneBy = doneBy;
    }
    @Override
    public String toString(){
        return String.format("  [D]%s (by: %s)", super.toString(), this.doneBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a")));
    }
    @Override
    public String changeFormat() {

        return String.format("D %s " + "/ " + this.doneBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm" )) , super.changeFormat());

    }
}