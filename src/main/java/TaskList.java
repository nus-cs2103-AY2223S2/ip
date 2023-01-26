import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList extends ArrayList<Task> {
    private static final String SAVE_FILE_PATH = "_duke_data.txt";

    public String getStatus() {
        switch (this.size()) {
            case 0:
                return "Now you have no tasks in the list.";
            case 1:
                return "Now you have 1 task in the list.";
            default:
                return String.format("Now you have %d task in the list.", this.size());
        }
    }

    private static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch(IOException e) {
            return "";
        } 
    }

    private static void writeStringToFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content); 
        } catch(IOException e) {
            // Handle the exception
        }
    }

    public String encodeAsString() {
        return this.stream()
            .map(task -> task.encodeAsString())
            .collect(Collectors.joining("\n"));
    }

    public void save() {
        TaskList.writeStringToFile(TaskList.SAVE_FILE_PATH, this.encodeAsString());
    }

    public static TaskList load() throws DukeLoadException {
        String saveFileContents = TaskList.readFile(TaskList.SAVE_FILE_PATH);
        TaskList output = new TaskList();
        if (saveFileContents.isEmpty()) {
            return output;
        }
        
        for (String encodedTask : saveFileContents.split("\n")) {
            Task task = Task.loadFromString(encodedTask);
            output.add(task);
        }

        return output;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "Nothing in the list.";
        }
        
        int listIndex = 1;
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : this) {
            output.append(listIndex + ". " + task.toString() + "\n");
            listIndex++;
        }
        // Removes trailing newline.
        output.setLength(output.length() - 1);
        return output.toString();
    }
}
