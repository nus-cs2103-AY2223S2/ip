import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.io.File;
import java.util.Scanner;

public class Storage {
    private final Path filePath;
    
    public Storage(Path filePath) {
        this.filePath = filePath;
    }
    
    public TaskList load() throws IOException {
        File saveFile = filePath.toFile();
        Path parentPath = filePath.getParent();
        File parentFolder = parentPath.toFile();
        if (!parentFolder.exists()) {
            parentFolder.mkdir();
        }
        if(!saveFile.exists()) {
            saveFile.createNewFile();
        }
        return loadSave(saveFile);
    }
    private static TaskList loadSave(File saveFile) {
        TaskList tasks = new TaskList();
        try {
            Scanner saveReader = new Scanner(saveFile);
            while (saveReader.hasNextLine()) {
                String [] taskInfo = saveReader.nextLine().split(";");
                switch (taskInfo[0]) {
                    case "T":
                        tasks.add(new Task(taskInfo[1], taskInfo[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(taskInfo[1], taskInfo[2], taskInfo[3]));
                        break;
                    case "E":
                        tasks.add(new Event(taskInfo[1], taskInfo[2], taskInfo[3], taskInfo[4]));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("BLUB! Couldn't find save file!");
        }
        return tasks;
    }
    public void save(TaskList tasks) {
        try {
            File saveFile = filePath.toFile();
            FileWriter writer = new FileWriter(saveFile);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).saveFormat()+ "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("BLUB! Couldn't save tasks!");
        }
    }
}
