package storage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDos;

/**
 * Storage is a class that help store data in the local file and also read data from local file,
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Storage {
    private final String dataAddress;
    private final String prevDataAddress;

    /**
     * Creates a files to store Duke's data, including the current data file and a file that
     * stores Duke's data before the previous command.
     *
     * @param fileName the target filename that the data should be stored
     */
    public Storage(String fileName) {
        Path currentRelativePath = Paths.get("");
        String currentRelativePathName = currentRelativePath.toAbsolutePath().toString();
        this.dataAddress = currentRelativePathName + "\\data\\" + fileName;
        this.prevDataAddress = currentRelativePathName + "\\data\\" + "prev" + fileName;
        try {
            File fileParent = new File(currentRelativePathName + "\\data");
            if (!fileParent.exists()) {
                fileParent.mkdir();
            }
            File dataFile = new File(dataAddress);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            File prevDataFile = new File(prevDataAddress);
            if (!dataFile.exists()) {
                prevDataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("sth went wrong");
        }
    }

    /**
     * Returns all tasks stored in duke.txt.
     *
     * @return An arrayList of all tasks stored in duke.txt.
     * @throws IOException if the file duke.txt is not found.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> arrayList = new ArrayList<>();
        try {
            File file = new File(dataAddress);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] dataParts = data.split("/");
                String type = dataParts[0];
                switch (type) {
                case "T":
                    arrayList.add(new ToDos(dataParts[2], Integer.valueOf(dataParts[1])));
                    break;
                case "D":
                    arrayList.add(new Deadline(dataParts[2], LocalDate.parse(dataParts[3]),
                            Integer.valueOf(dataParts[1])));
                    break;
                case "E":
                    arrayList.add(new Event(dataParts[2], LocalDate.parse(dataParts[3]) ,
                            LocalDate.parse(dataParts[4]), Integer.valueOf(dataParts[1])));
                    break;
                default:
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        assert arrayList.size() >= 0;
        return arrayList;
    }

    /**
     * Takes in an array of tasks and store in duke.txt
     *
     * @param arrayList an arraylist of tasks that need to be stored in duke.txt
     * @throws IOException if the file duke.txt is not found.
     */
    public void update_data(ArrayList<Task> arrayList) {
        try {
            System.out.println();
            FileInputStream inputStream = new FileInputStream(this.dataAddress);
            FileOutputStream outputStream = new FileOutputStream(this.prevDataAddress);
            int i;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }
            assert arrayList.size() >= 0;
            String data = "";
            FileWriter fw = new FileWriter(this.dataAddress);
            for (Task t: arrayList) {
                data += t.dataFormat() + "\n";
            }
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * undo the previous command by the user by updating the data stored in the storage
     */
    public void undo() {
        try {
            FileInputStream inputStream = new FileInputStream(this.prevDataAddress);
            FileOutputStream outputStream = new FileOutputStream(this.dataAddress);
            int i;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
