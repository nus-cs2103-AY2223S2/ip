public class Deadlines extends Task{
    private String time;
    public Deadlines(String taskText, String time) {
        super(taskText);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.time);
    }

    public String writeFile() {
        return String.format("D|%s|%s|%s", this.getCurrentStatus(), this.getTaskText(), this.time);
    }
}
