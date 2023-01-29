import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final String directoryPath;
    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
        createFile();
    }

    private void createFile() {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("File unable to be created");
        }
    }

//    private ArrayList<Task> readData() {
//        ArrayList<Task> = new ArrayList<>();
//        File file = new File(filePath);
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNext()) {
//
//        }
//    }

    public void writeData(ArrayList<Task> arrayList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < arrayList.size() - 1; i++) {
                fileWriter.write(arrayList.get(i).toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to save task");
        }
    }
}
