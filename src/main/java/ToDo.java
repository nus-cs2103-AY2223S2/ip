public class ToDo extends Task {
    public ToDo(String str, boolean checked) {
        super(str, checked);
    }

    public static ToDo createTask(String[] taskLine) {
        boolean check;
        check = taskLine[1].equals("1");
        return new ToDo(taskLine[2], check);
    }
    @Override
    public String getTaskType() {
        return "T";
    }
    @Override
    public String storeTaskString() {
        return this.getTaskType() + " | " + this.getCheckedString() + " | " + this.getStr();
    }
    @Override
    public String toString() {
        boolean checked = this.isChecked();
        String str = this.getStr();
        if (checked) {
            return "[T][X] " + str;
        } else{
            return "[T][ ] " + str;
        }
    }
}
