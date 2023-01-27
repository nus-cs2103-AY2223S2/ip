import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private Path filePath;
    private List<String> lines;

    public Storage(Path filePath) throws DukeException{
        this.filePath = filePath;
        try {
            this.lines = Files.lines(this.filePath).collect(Collectors.toList());
        } catch (IOException err){
            this.lines = new ArrayList<>();
            throw new DukeException("Unable to access content of files!");
        }
    }

    public List<String> load() throws DukeException{
        return this.lines;
    }

    public void editFile(ArrayList<Task> taskList) throws DukeException{
        if (taskList.size() > lines.size()){
            try {
                for (int i = lines.size(); i < taskList.size(); i++) {
                    Files.write(this.filePath,
                            (i + " | " + taskList.get(i).toString() + "\n").getBytes(),
                            StandardOpenOption.APPEND);
                }
            } catch (IOException err){
                throw new DukeException("Unable to write to File!");
            }
        } else {
            try {
                for (int i = 0; i < Math.min(taskList.size(), lines.size()); i++) {
                    String lineInQuestion = i + " | " + taskList.get(i).toString() + "\n";
                    if (!lines.get(i).contains(lineInQuestion)){
                        lines.remove(i);
                    } else if (!lines.get(i).equalsIgnoreCase(lineInQuestion)){
                        lines.add(i, lineInQuestion);
                    }

                    Files.write(this.filePath, lines.get(i).getBytes(),
                            StandardOpenOption.APPEND,
                            StandardOpenOption.TRUNCATE_EXISTING);
                }
            } catch (IOException err){
                throw new DukeException("Unable to write to File!");
            }
        }
    }
}
