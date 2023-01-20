public class Duration {
    private String from;
    private String to;

    public Duration(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "(from: " + this.from + " to: " + this.to + ")";
    }

    public String formatForSave() {
        return this.from + "<>" + this.to;
    }
}
