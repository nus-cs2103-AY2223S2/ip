package duke;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import duke.exceptions.DukeCannotFindFileException;

public class Storage {
    private final String saveLocation = "data/savestate.txt";
    private final String saveFolder = "data";
    private boolean isFolderCreated = false;
    private boolean isFileCreated = false;

    public Storage() {
        File folder = new File(saveFolder);
        if (folder.exists()) {
            isFileCreated = true;
        }
        File savedFile = new File(saveLocation);
        if (savedFile.exists()) {
            isFolderCreated = true;
        }
    }

    public void saveState(TaskList lst) {
        if (!isFolderCreated) {
            File folder = new File(saveFolder);
            if (!folder.mkdir()) {
                System.out.println("Something went wrong, folder not created");
            } else {
                isFolderCreated = true;
            }
        }
        if (!isFileCreated) {
            File savedFile = new File(saveLocation);
            try {
                savedFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
            isFileCreated = true;
        }

        String data = lst.listToText();
        try {
            FileWriter writer = new FileWriter(saveLocation);
            writer.write(data);
            writer.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public void loadState(TaskList lst) throws DukeCannotFindFileException {
        if (!isFileCreated) {
            throw new DukeCannotFindFileException();
        }
        File savedFile = new File(saveLocation);
        try {
            Scanner scanner = new Scanner(savedFile);
            while (scanner.hasNextLine()) {
                String taskText = scanner.nextLine();
                if (taskText.isEmpty()) {
                    break;
                }
                lst.loadTask(taskText);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
