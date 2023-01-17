public class Deadlines extends Task {
    String endsBy;

    Deadlines(String taskName) {
        super(taskName.split("/by ")[0]);
        this.endsBy = taskName.split("/by ")[1];
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this.done) {
            toReturn = "[D][X] " + this.name + "(by: " + endsBy + ")";
        } else {
            toReturn = "[D][ ] " + this.name + "(by: " + endsBy + ")";            
        }
        return toReturn;
    }
}
