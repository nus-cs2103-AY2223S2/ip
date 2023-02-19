public class Deadline extends Task{
    String tag = "D";
    protected String by;

    public Deadline() {
        super.tag = tag;
    }

    @Override
    public void genDscp(String input) throws InvalidDeadline {
        if (input.isBlank()) {
            throw new InvalidDeadline();
        }
        int byId = input.indexOf("/by");
        if (byId == -1) {
            throw new InvalidDeadline();
        }
        this.by = input.substring(byId + 4);
        String description = String.format("%s (by: %s)", input.substring(0, byId - 1), this.by);
        super.description = description;
    }

    //Override toString
}
