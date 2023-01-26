import javax.xml.crypto.Data;
import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import java.util.Objects;
import java.util.stream.Stream;

public class DatabaseWriter {
    private static final String folderPath = "data";
    private static final String filePath = "data/duke.txt";
    private static Path path = Paths.get(filePath);

    public DatabaseWriter(Path path) {
        this.path = path;
    }

    public void addToDb(Task t) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String formattedTask = t.toStringDb();
            fw.write(formattedTask + '\n');
            fw.close();
        } catch (IOException exception) {
            System.out.println("Error while reading database.");
        }
    }

    public void removeFromDb(int index) {
        try {
            List<String> lines = Files.readAllLines(path);
            String line = lines.set(index, "");
        } catch (IOException | NullPointerException e) {
            System.out.println("File exception has occured.");
        }
    }

    public void setDone(int index) {
        try {
            List<String> lines = Files.readAllLines(path);
            String line = lines.get(index);
            String newLine = replaceChar(line, 2, 'X');
            lines.set(index, newLine);
        } catch (IOException | NullPointerException e) {
            System.out.println("File exception has occured.");
        }
    }

    public void setUndone(int index) {
        try {
            List<String> lines = Files.readAllLines(path);
            String line = lines.get(index);
            String newLine = replaceChar(line, 2, ' ');
            lines.set(index, newLine);
        } catch (IOException | NullPointerException e) {
            System.out.println("File exception has occured.");
        }
    }

    public static String replaceChar(String s, int index, char newValue) {
        StringBuilder string = new StringBuilder(s);
        string.setCharAt(index, newValue);
        return string.toString();
    }

}
