import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    String dueDate;
    Optional<LocalDate> chornoDueDate;

    // Factory method
    public static Deadline create(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String[] parseInfo = parseCmd(commandInput);
        return new Deadline(parseInfo[0], parseInfo[1], false);
    }

    public static String[] parseCmd(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String taskName;
        String dueDate;

        int indexOfBy = commandInput.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DeadlineByNotSpecified("Deadline task requires keyword '/by'");
        } 

        taskName = commandInput.substring(9, indexOfBy - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("Deadline description canont be empty.");
        }

        try {
            dueDate = commandInput.substring(indexOfBy + 4);
            if (dueDate.equals("")) {
                throw new DeadlineByNotSpecified("Due date field cannot be empty");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeadlineByNotSpecified("Due date field cannot be empty.");
        }

        String[] parseInfo = {taskName, dueDate};
        return parseInfo;

    }
    
    public Deadline(String taskName, String dueDate, boolean isDone) {
        super(taskName, "D");
        this.dueDate = dueDate;
        this.completed = isDone;
        this.chornoDueDate = Parser.parseDate(dueDate); 
    }

    @Override
    public String stringFields() {
        String dateString = this.chornoDueDate.isEmpty() ? dueDate : 
        chornoDueDate.get().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        
        return " (by: " + dateString + ")"; 
    }
}
