public class Deadlines extends Task{
    public Deadlines(String taskText) {
        super(taskText);
    }

    @Override
    public String toString() {
        return String.format("[D]%s", super.toString());
    }
}
