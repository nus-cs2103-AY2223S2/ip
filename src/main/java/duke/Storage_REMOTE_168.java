package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public boolean isExistFile() {
        return new java.io.File(filePath).exists();
    }


    public File load() {
        // Idea for the following code snippet to create a file is referenced from:
        // https://stackoverflow.com/questions/6142901/how-to-create-a-file-in-a-directory-in-java
        if (!isExistFile()) {
            Path desiredPath = Paths.get(filePath);
            try {
                Files.createDirectories(desiredPath.getParent());
            } catch (IOException err) {
                System.out.println(err);
            }

            try {
                Files.createFile(desiredPath);
            } catch (FileAlreadyExistsException err) {
                System.err.println("already exists: " + err.getMessage());
            } catch (IOException err) {
                System.out.println(err);
            }

            try {
                Files.write(Paths.get(filePath), "0".getBytes());
            } catch (IOException err) {
                System.out.println(err);
            }

            File createdFile = new File(filePath);
            return createdFile;
        } else {
            File savedFile = new File(filePath);
            return savedFile;
        }
    }

    public void addToFile(TaskList tasks) {
        // Idea for the following code snippet is taken from:
        // https://stackoverflow.com/questions/1053467/how-do-i-save-a-string-to-a-text-file-using-java
        ArrayList<Task> lstOfItems = tasks.getTasks();
        try {
            ArrayList<String> lst = new ArrayList<>();
            lst.add(String.valueOf(lstOfItems.size()));
            for (int i = 0; i < lstOfItems.size(); i++) {
                Task current = lstOfItems.get(i);
                lst.add(current.parse());
            }
            Files.write(Paths.get(filePath), lst);
        } catch (IOException err) {
            System.out.println(err);
        }
    }
}
