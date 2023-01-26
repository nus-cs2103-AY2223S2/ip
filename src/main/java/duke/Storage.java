package duke;

import java.io.*;

public class Storage {
    private static final String SAVED_FILE_PATH = "./savedTasks.txt";
    private static final String IO_EXCEPTION_MESSAGE = "I/O error occurred while retrieving list from save.";
    private static final String FILE_HANDLING_EXCEPTION_MESSAGE = "File error occurred while retrieving list from save.";
    private String saveFilePath;

    Storage() {
        saveFilePath = SAVED_FILE_PATH;
    }

    public TaskList recoverList() throws DukeException {
        File saveFile = new File(saveFilePath);
        if (!saveFile.exists()) {
            return new TaskList();
        }

        try (FileInputStream fileInputStream = new FileInputStream(saveFilePath);
             ObjectInputStream objectInputStream
                     = new ObjectInputStream(fileInputStream)) {
            return (TaskList) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new DukeException(FILE_HANDLING_EXCEPTION_MESSAGE);
        } catch (IOException e) {
            throw new DukeException(IO_EXCEPTION_MESSAGE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTaskChangesToFile(TaskList tasks) throws DukeException {
        try (FileOutputStream fileOutputStream
                     = new FileOutputStream(SAVED_FILE_PATH);
             ObjectOutputStream objectOutputStream
                     = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(tasks);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            throw new DukeException(FILE_HANDLING_EXCEPTION_MESSAGE);
        } catch (IOException e) {
            throw new DukeException(IO_EXCEPTION_MESSAGE);
        }

    }

    // TODO: Wrapper for try-with involving read write to file (Fn)

}
