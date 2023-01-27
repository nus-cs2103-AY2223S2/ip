package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class is to handle loading/saving tasks from/in the file.
 */
public class Storage {
    private final File file;

    /**
     * Store user input. Create directory if it does not exist/cannot
     * be found in the given file path.
     *
     * @param filePath Where the file should be stored.
     * @throws IOException If file cannot be created.
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Load all records from the file.
     *
     * @return Previous records that store inside an array list.
     * @throws IOException If records in the file cannot be loaded.
     */
    public ArrayList<String> loadRecord() throws IOException {
        Scanner sc = new Scanner(this.file);
        ArrayList<String> records = new ArrayList<>();
        while (sc.hasNext()) {
            records.add(sc.nextLine());
        }
        sc.close();
        return records;
    }

    /**
     * Write/update to the file.
     *
     * @param tasks Tasks that needed to be written to the file.
     * @throws IOException Update cannot be written to the file.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toString() + "\n");
        }
        fw.close();
    }
}