package task;

import dukeexception.DukeException;

public class ToDo extends Task {
    private ToDo(String str, boolean checked) {
        super(str, checked);
    }

    public static ToDo generate(String[] inputLine) throws DukeException {
        if (inputLine.length < 2) {
            throw new DukeException("Missing description");
        }
        return new ToDo(inputLine[1], false);
    }

    public static ToDo generateTask(String[] taskLine) {
        boolean check = taskLine[1].equals("1");
        return new ToDo(taskLine[2], check);
    }
    @Override
    public String getTaskType() {
        return "T";
    }
    @Override
    public String getStoreTaskString() {
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
