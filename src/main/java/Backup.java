import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Backup {
    private final Path backupPath;

    public Backup() {
        String home = System.getProperty("user.dir");
        Path path = java.nio.file.Paths.get(home, "src", "main", "data");
        boolean directoryExists = java.nio.file.Files.exists(path);

        if (!directoryExists) {
            boolean dir = new File(path.toUri()).mkdirs();
            if (!dir) {
                System.out.println("Could not create /data directory.");
            }
        }
        this.backupPath = Path.of(path + "/duke.txt");
    }

    public void writeArray (List<Task> arr) {
        File file = new File(backupPath.toString());
        try {
            boolean fileCreated = file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);
            for (int i = 0; i < arr.size(); i++) {
                fileWriter.write(arr.get(i).toBackup());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> readArray () {
        List<Task> arr = new ArrayList<>();

        try {
            File file = new File(backupPath.toString());
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split("\\|");

                Task task = null;
                line[0] = line[0].strip();

                if (line[0].equals("T")) {
                    task = new Todo(line[2]);
                } else if (line[0].equals("D")) {
                    task = new Deadline(line[2], line[3]);
                } else if (line[0].equals("E")) {
                    task = new Event(line[2], line[3], line[4]);
                }

                if (line[1].strip().equals("1")) {
                    task.setStatus(true);
                }
                arr.add(task);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the backup file");
            e.printStackTrace();
        }

        return arr;
    }
}
