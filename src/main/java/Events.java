import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String stringFrom;
    private final String stringTo;

    public Events(String description,boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.stringFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        this.to = to;
        this.stringTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    public Events(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = null;
        this.stringFrom = from;
        this.to = null;
        this.stringTo = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), stringFrom, stringTo);
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
