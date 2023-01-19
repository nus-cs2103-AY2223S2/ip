public class Deadline extends Task{
    protected String deadLineTime;

    public Deadline(String item, String type, String time) {
        super(item,type);
        String x[] = time.split(" ", 2);
        deadLineTime =  x[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadLineTime + ")";
    }

}
