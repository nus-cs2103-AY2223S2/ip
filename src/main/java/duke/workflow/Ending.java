package duke.workflow;

public class Ending extends Event {
    public Ending() {
        super(true);
    }
    public Event toNext() {
        return this;
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "_".repeat(22) + '\n';
        toPrintOut += "VERY WELL. THE WORLD IS SAFE FROM YOUR PLAN." + '\n';
        toPrintOut += "_".repeat(22) + '\n';
        return toPrintOut;
    }
}