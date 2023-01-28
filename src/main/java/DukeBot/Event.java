package DukeBot;

<<<<<<< HEAD
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task{
    private static final String typeToString = "[E]";
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
=======
import java.util.ArrayList;
import java.util.Objects;

public class Event extends Task{
    private static final String typeToString = "E";
    private final String startDate;
    private final String endDate;
>>>>>>> branch-Level-7

    public Event(String task, String startDateTime, String endDateTime) throws DateTimeException {
        super(task);
        this.type = Types.EVENT;
        this.startDateTime = LocalDateTime.parse(startDateTime);
        this.endDateTime = LocalDateTime.parse(endDateTime);
    }

    public Event(String[] data) {
        super(data[2]);
        this.completed = Objects.equals(data[1], "X");
        this.startDate = data[3];
        this.endDate = data[4];
    }

    @Override
    public String status() {
        String status = this.completed ? "[X]" : "[ ]";
<<<<<<< HEAD
        return typeToString + status + details + " (from:" + this.startDateTime + " to:" + this.endDateTime + ")";
=======
        return "[" + typeToString + "]" + status + this.details + " (from:" + this.startDate + " to:" + this.endDate + ")";
    }

    @Override
    public ArrayList<String> data() {
        ArrayList<String> data = new ArrayList<>();
        data.add(typeToString);
        data.add(this.completed ? "X" : " ");
        data.add(this.details);
        data.add(this.startDate);
        data.add(this.endDate);
        return data;
>>>>>>> branch-Level-7
    }
}
