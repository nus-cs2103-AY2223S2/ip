public class Deadlines extends Task {
    public String end;

    public Deadlines(String[] content) throws DukeException {
        super(content[0]);
        if (content[0].isEmpty()) {
            throw new DukeException("OOPS!!! The description of deadline cannot be empty!");
        }
        String[] ends = content[1].split(" ", 2);
        if (!ends[0].equals("by")) {
            throw new DukeException("OOPS!!! Use 'by' to indicate end date");
        }
        if (ends.length == 1) {
            throw new DukeException("OOPS!!! Deadline requires some end description.");
        }
        end = ends[1];
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + end + ")";
    }


}
