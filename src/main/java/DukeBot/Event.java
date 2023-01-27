package DukeBot;

import java.util.ArrayList;
import java.util.Objects;

public class Event extends Task{
    private static final String typeToString = "E";
    private final String startDate;
    private final String endDate;

    public Event(String task, String startDate, String endDate) {
        super(task);
        this.type = Types.EVENT;
        this.startDate = startDate;
        this.endDate = endDate;
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
    }
}
