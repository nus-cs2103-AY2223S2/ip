public class Deadline extends Task {

    public Deadline(String desc) {
        // add stuff here to account for time
        super(String.format("%s (by: %s)",
                desc.substring(0, desc.indexOf("/by") - 1),
                desc.substring(desc.indexOf("/by") + 4)), "D");
    }

}