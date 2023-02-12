public class Event extends Task {
    private final String start;
    private final String end;
    private Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }
    public static Event generate(String input) throws DukeException {
        if (input.trim().equals("event")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a event cannot be empty.\n");
        }
        String[] inputLine = input.split(" ", 2);
        if (inputLine.length < 2) {
            throw new DukeException("\t ☹ OOPS!!! The description of a event cannot be empty.\n");
        }
        String[] startEndTime = inputLine[1].split(" /from ");
        if (startEndTime.length < 2) {
            throw new DukeException("\t ☹ OOPS!!! The start time of a event cannot be empty.\n");
        }
        String[] dateTime = startEndTime[1].split(" /to ");
        if (dateTime.length < 2) {
            throw new DukeException("\t ☹ OOPS!!! The end time of a event cannot be empty.\n");
        }
        return new Event(startEndTime[0], false, DateTime.dateFormatter(dateTime[0]), DateTime.dateFormatter(dateTime[1]));
    }

    public static Event generateTask(String[] taskLine) {
        boolean isDone = taskLine[1].equals("1");
        return new Event(taskLine[2], isDone, taskLine[3], taskLine[4]);
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
    @Override
    public String getTaskType() {
        return "E";
    }
    @Override
    public String storeTaskString() {
        return this.getTaskType() + " | " + this.getMarkedString() + " | " + this.getDescription() + " | " + this.getStart() + " | " + this.getEnd();
    }

    @Override
    public String toString() {
        String str = this.getDescription();
        boolean checked = this.isDone();
        String startTime = this.getStart();
        String endTime = this.getEnd();
        if (checked) {
            return "[E][X] " + str + " (from: " + startTime +
                    " to: " + endTime + ")";
        } else {
            return "[E][ ] " + str + " (from: " + startTime +
                    " to: " + endTime + ")";
        }
    }
}