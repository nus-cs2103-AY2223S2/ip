public class Deadline extends Task {
    String byWhen;
    public Deadline(String desc,String byWhen) {
        super(desc,"D");
        this.byWhen = byWhen;
    }

    public String toString() {
        return super.toString() + String.format("[%s]", this.byWhen);
    }
}
