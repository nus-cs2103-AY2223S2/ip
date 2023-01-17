public class Events extends Task {
    private String begin;
    private String end;

    public Events(String[] content) throws DukeException {
        super(content[0]);
        if (content[0].isEmpty()) {
            throw new DukeException("OOPS!!! The description of event cannot be empty!");
        }
        if (content.length != 3) {
            throw new DukeException("OOPS!!! Events require specific start and end description.");
        }
        // Do some error handling here
        String[] begins = content[1].split(" ", 2);
        if (!begins[0].equals("from")) {
            throw new DukeException("OOPS!!! Use 'from' to start date.");
        }
        if (begins.length == 1) {
            throw new DukeException("OOPS!!! Events require specific start description.");
        }
        this.begin = begins[1];

        String[] ends = content[2].split(" ", 2);
        if (!ends[0].equals("to")) {
            throw new DukeException("OOPS!!! Use 'to' to end date.");
        }
        if (ends.length == 1) {
            throw new DukeException("OOPS!!! Event requires some end description.");
        }
        this.end = ends[1];
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
