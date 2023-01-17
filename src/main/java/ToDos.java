public class ToDos extends Task {

    public ToDos(String content) throws DukeException {
        super(content);
        if (content.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public String getTypeIcon() {
        return "T";
    }

}
