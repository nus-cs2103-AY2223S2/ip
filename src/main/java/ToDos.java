import java.io.FileWriter;
import java.io.IOException;

public class ToDos extends Task {
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void writeToString(String filePath) throws DukeWriteException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(String.format("todo %s-%s\n", this.description, this.isDone));
            fw.close();
        } catch (IOException ioException) {
            throw new DukeWriteException(this.toString());
        }
    }
}
