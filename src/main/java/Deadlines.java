public class Deadlines extends Task {
    public String end;

    public Deadlines(String[] content) throws DukeException {
        super(content[0]);
        if (content[0].isEmpty()) {
            throw new DukeException("OOPS!!! Command should be in the format 'deadline [M] /by [M]'\n" +
                    "The description, [M] cannot be empty.");
        }
        String[] ends = content[1].split(" ", 2);
        if (!ends[0].equals("by") || ends.length == 1) {
            throw new DukeException("OOPS!!! Command should be in the format 'deadline [M] /by [M]'\n" +
                    "The description, [M] cannot be empty.");
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
