package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public void createFile(String filePath) throws DukeException {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            if (file.exists()) {
                throw new DukeException("file already exists");
            } else {
                file.getAbsoluteFile().getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("Successfully created new file.");
            }
        } catch (IOException e) {
            throw new DukeException("creating new file");
        }
    }

    public void saveToFile(TaskList lst) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (int i = 0; i < lst.getSize(); i++) {
                String taskText = lst.getTask(i).toFile();
                writer.write(taskText);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(String.format("I wasn't able to write the data into %s", filePath));
        }
    }

    public void loadFileInto(TaskList lst) throws DukeException {
        try {
            Scanner fileData = new Scanner(this.file);
            while (fileData.hasNextLine()) {
                String taskString = fileData.nextLine();
                lst.addTaskFromString(taskString);
            }
            if (fileData != null) {
                fileData.close();
            }
        } catch (FileNotFoundException e) {
            this.createFile(this.filePath);
            this.loadFileInto(lst);
        }
    }
}
