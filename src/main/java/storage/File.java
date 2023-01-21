package storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class File {
    private static FileWriter fileWriter;
    private static final String FILE_NAME = "./data/tasks.txt";
    public static void init() throws IOException {
        java.io.File file = new java.io.File(File.FILE_NAME);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        fileWriter = new FileWriter(File.FILE_NAME);
    }

    public static void saveToFile(String data) throws IOException {
        if (fileWriter == null) {
            // TODO
            throw new Error();
        }

        BufferedWriter writer = new BufferedWriter(File.fileWriter);
        writer.write(data);
        writer.flush();
        writer.close();
    }

    public static ArrayList<String> loadFromFile() throws IOException {
        java.io.File file = new java.io.File(File.FILE_NAME);
        Scanner reader = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            lines.add(data);
        }
        reader.close();

        return lines;
    }
}
