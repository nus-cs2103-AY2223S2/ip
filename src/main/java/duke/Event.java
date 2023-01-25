package duke;

import java.time.LocalDate;

public class Event extends Task{
    public Event(String description){
        super(description);

    }
    public Event(String description, LocalDate deadline) {
        super(description, deadline);

    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
