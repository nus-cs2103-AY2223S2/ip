import java.io.FileWriter;
import java.io.IOException;

public class Events extends Task {
    private final String from;
    private final String to;

    public Events(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    @Override
    public void writeToString(String filePath) throws DukeWriteException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(String.format("event %s-%s-%s-%s\n", this.description, this.isDone, this.from, this.to));
            fw.close();
        } catch (IOException ioException) {
            throw new DukeWriteException(this.toString());
        }
    }
}
