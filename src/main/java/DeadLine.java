public class DeadLine extends Task {
    private String dateTime;
    public DeadLine(String str, boolean checked) {
        super(str, checked);
    }
    public DeadLine(String str, boolean checked, String dateTime) {
        super(str, checked);
        this.dateTime = dateTime;
    }
    public static DeadLine createTask(String[] taskLine) {
        boolean check;
        check = taskLine[1].equals("1");
        return new DeadLine(taskLine[2], check, taskLine[3]);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }
    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String storeTaskString() {
        return this.getTaskType() + " | " + this.getCheckedString() + " | " + this.getStr() + " | " + this.getDateTime();
    }

    @Override
    public String toString() {
        String str = this.getStr();
        boolean checked = this.isChecked();
        String dateTime = this.getDateTime();
        if (checked) {
            return "[D][X] " + str + " (by: " + dateTime + ")";
        } else {
            return "[D][ ] " + str + " (by: " + dateTime + ")";
        }
    }
}
