public class Event extends Task {
    private String start;
    private String end;
    public Event(String str, boolean checked) {
        super(str, checked);
    }
    public Event(String str, boolean checked, String start, String end) {
        super(str, checked);
        this.start = start;
        this.end = end;
    }

    public static Event createTask(String[] taskLine) {
        boolean check;
        check = taskLine[1].equals("1");
        return new Event(taskLine[2], check, taskLine[3], taskLine[4]);
    }

    public void setStartEnd(String start, String end) {
        this.start = start;
        this.end = end;
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
        return this.getTaskType() + " | " + this.getCheckedString() + " | " + this.getStr() + " | " + this.getStart() + " | " + this.getEnd();
    }


    @Override
    public String toString() {
        String str = this.getStr();
        boolean checked = this.isChecked();
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
