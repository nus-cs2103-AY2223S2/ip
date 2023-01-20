import java.io.FileWriter;
import java.io.IOException;

public class Deadlines extends Task{
    private final String deadline;
    public Deadlines(String description, boolean isDone,String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline);
    }

    @Override
    public void writeToString(String filePath) throws DukeWriteException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(String.format("deadline %s-%s-%s\n", this.description, this.isDone, this.deadline));
            fw.close();
        } catch (IOException ioException) {
            throw new DukeWriteException(this.toString());
        }
    }
}
