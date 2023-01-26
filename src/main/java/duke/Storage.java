package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final File file;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public ArrayList<String> loadRecord() throws IOException {
        Scanner sc = new Scanner(this.file);
        ArrayList<String> records = new ArrayList<>();
        while (sc.hasNext()) {
            records.add(sc.nextLine());
        }
        sc.close();
        return records;
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toString() + "\n");
        }
        fw.close();
    }
}