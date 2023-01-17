public class Events extends Task {
    private String begin;
    private String end;

    public Events(String[] content) {
        super(content[0]);
        // Do some error handling here
        this.begin = content[1].substring(5);
        this.end = content[2].substring(3);
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + begin + "to: " + end + ")";
    }
}
