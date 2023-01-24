public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    public static void processEvent(String command, TaskList lst) throws DukeException{
        String info = command.trim();
        if (info.isEmpty()) {
            throw new DukeException("event");
        }
        String[] details = info.split("/");
        if (details.length < 3) {
            throw new DukeException("timing");
        }
        String start = details[1].split(" ", 2)[1];
        String end = details[2].split(" ", 2)[1];
        Event e = new Event(details[0], start, end);
        lst.addTask(e);
        Duke.printLine();
        System.out.println("Got it! I've added: ");
        System.out.println(" " + e.toString());
        lst.printSize();
        Duke.printLine();
    }

    @Override
    public String toFile() {
        int completed = this.completed ? 1 : 0;
        return String.format("E | %d | %s | %s | %s\n", completed, this.taskName, this.start, this.end);
    }

    public static Event toEventFromFileStr(String taskNameData, String doneData,
                                                String startData, String endData) throws DukeException {
        doneData = doneData.trim();
        startData = startData.trim();
        endData = endData.trim();
        taskNameData = taskNameData.trim();
        if (taskNameData.isEmpty()) {
            throw new DukeException("todo");
        }
        if (doneData.isEmpty()) {
            throw new DukeException("missing details");
        }
        if (startData.isEmpty() || endData.isEmpty()) {
            throw new DukeException("timing");
        }
        Event e = new Event(taskNameData, startData, endData);
        boolean completed = Integer.parseInt(doneData) == 1;
        e.setCompleted(completed);
        return e;
    }

    @Override
    public String toString() {
        String s;
        if (this.completed) {
            s = "[E]" + super.toString() +
                    " (from: " + this.start + " to: " + this.end + ")";
        } else {
            s = "[E]"  + super.toString() +
                    " (from: " + this.start + " to: " + this.end + ")";
        }
        return s;
    }

}
