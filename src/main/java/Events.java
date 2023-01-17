public class Events extends Task {
    protected String fromDetails;
    protected String toDetails;

    Events(String taskName) {
        super(taskName.split("/from ")[0]);
        String[] initialSplit = taskName.split("/from ");
        String[] nextSplit = initialSplit[1].split("/to ");
        this.fromDetails = nextSplit[0];
        this.toDetails = nextSplit[1];
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this.done) {
            toReturn = "[E][X] " + this.name + "(from: " + this.fromDetails + "to: " + this.toDetails + ")";
        } else {
            toReturn = "[E][ ] " + this.name + "(from: " + this.fromDetails + "to: " + this.toDetails + ")";
        }
        return toReturn;
    }
}
