package duke.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.FileLoadingException;

/**
 * Saves tasks into file
 */
public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Returns the loaded data file whenever Duke restarts.
     *
     * @return array containing content of the existing data file.
     */
    public String[] load() {
        String[] arr = new String[100];
        try {
            File myFile = new File(this.path);
            myFile.getParentFile().mkdirs();
            if (myFile.exists()) {
                Scanner sc = new Scanner(myFile);
                int pointer = 0;
                while (sc.hasNext()) {
                    arr[pointer] = sc.nextLine();
                    pointer++;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return arr;
    }

    /**
     * Overwrites the given data file with new information.
     *
     * @param tasks new task list to overwrite on the given file.
     * @write on given data file.
     */
    public void overwrite(TaskList tasks) throws FileLoadingException {
        String[] arr = tasks.readTaskList();
        File myFile = new File(this.path);
        myFile.getParentFile().mkdirs();
        try {
            FileWriter myWriter = new FileWriter(myFile);
            if (myFile.createNewFile()) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        break;
                    }
                    myWriter.write(arr[i].toString());
                    myWriter.write("\n");
                }
                myWriter.close();
            } else {
                new FileWriter(this.path, false).close();
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == null) {
                        break;
                    }
                    myWriter.write(arr[i].toString());
                    myWriter.write("\n");
                }
                myWriter.close();
            }
        } catch (IOException e) {
            throw new FileLoadingException(this.path);
        }
    }
}
