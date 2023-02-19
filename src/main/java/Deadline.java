public class Deadline extends Task{
    String tag = "[D]";
    protected String by;

    public Deadline() {
        super.tag = tag;
    }

    @Override
    public void genDscp(String input) throws InvalidDeadline{
        String dscp = input.replace("deadline ", "");
        if (dscp.isBlank()) {
            throw new InvalidDeadline();
        }
        int byId = dscp.indexOf("/by");
        if (byId == -1) {
            throw new InvalidDeadline();
        }
        this.by = dscp.substring(byId + 4);
        String task = String.format("%s (by: %s)", dscp.substring(0, byId - 1), this.by);
        super.task = task;
    }

    //Override toString
}
