public class Deadlines extends Task{
    private String deadline = "";
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline);
    }
}
