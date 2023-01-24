import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String dataFilePath;

    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        // Create the directories if they do not exist
        try {
            File targetFile = new File(dataFilePath);
            File parent = targetFile.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTasks(TaskList tasks) throws Exception {
        if (!tasks.isEmpty()) {
            FileWriter writer = new FileWriter(dataFilePath);
            writer.write(tasks.toEncodedString());
            writer.close();
        }
    }

    // Read the data file, returns a list of each line
    public ArrayList<String[]> load() throws DukeException {
        File targetFile = new File(dataFilePath);
        ArrayList<String[]> fileCommands = new ArrayList<>();

        try {
            if (!targetFile.createNewFile()){
                Scanner scanner = new Scanner(targetFile);
                while (scanner.hasNext()) {
                    fileCommands.add(Parser.parseFileCommand(scanner.nextLine()));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return fileCommands;
    }
}
