package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager implements Outputable {
    private final File file;

    public StorageManager(String filePath) throws IOException {
        this.file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    @Override
    public void write(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
        writer.write(data);
        writer.close();
    }

    public ArrayList<String> loadFromFile() throws IOException {
        Scanner reader = new Scanner(this.file);
        ArrayList<String> lines = new ArrayList<>();

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            lines.add(data);
        }
        reader.close();

        return lines;
    }
}
