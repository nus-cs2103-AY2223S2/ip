import java.io.*;

public class Storage {

    BufferedReader content;
    FileReader fileReader;
    File file;
    String filepath;

    public Storage(String filepath) throws IOException{
        this.filepath = filepath;
        this.file = new File(filepath);
        if (this.file.createNewFile()) {
            System.out.println("Creating File...");
        }
        this.fileReader = new FileReader(this.file);
    }

    public BufferedReader load() {
        if (this.content != null) {
            return content;
        }
        this.content = new BufferedReader(this.fileReader);
        return this.content;
    }

    public void store(TaskList tasks) throws IOException {
        this.file.createNewFile();
        FileWriter writer = new FileWriter(this.filepath);
        for (Task task: tasks) {
            writer.write(task.toString() + "\n");
        }
        writer.close();
    }
}
