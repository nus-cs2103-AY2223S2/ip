import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String dataPath = "src/main/java/data.txt";

    public Storage() {
        try {
            File data = new File(Storage.dataPath);
            data.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println("Storage constructor Error in creating new file:" + e);
        }
    }

    public void populate(TaskList tl) {
        //populate the arraylist of taskmanager with values in file
        File file = new File(Storage.dataPath);
        try {
            Scanner sc = new Scanner(file);
            Parser parser = new Parser();
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                parser.parseAndAddStorageTask(str, tl);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Storage populate() data.txt is not found");
        }
    }

    public void add(String str) {
        try {
            Path filePath = Paths.get(Storage.dataPath);
            Files.write(filePath,  (str + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (java.io.IOException e) {
            System.out.println("storage add() Error:" + e);
        }
    }

    public void modify(String str, int line) {
        try {
            Path filePath = Paths.get(Storage.dataPath);
            List<String> lines = Files.readAllLines(filePath);
            lines.set(line - 1, str);
            Files.write(filePath, lines);
        } catch (java.io.IOException e) {
            System.out.println("storage modify() Error:" + e);
        }
    }

    public void delete(int line) {
        try {
            Path filePath = Paths.get(Storage.dataPath);
            List<String> lines = Files.readAllLines(filePath);
            lines.remove(line - 1);
            Files.write(filePath, lines);
        } catch (java.io.IOException e) {
            System.out.println("storage delete() Error:" + e);
        }
    }

    public String getStorageTaskString(Task task) {
        String res = "";
        String completed = task.getStatus() ? "1" : "0";
        if (task instanceof Event) {
            res += "E,";
            res += completed + ",";
            res += task.getTaskName() + ",";
            res += ((Event) task).getFrom() + ",";
            res += ((Event) task).getTo();
        } else if (task instanceof Deadline) {
            res += "D,";
            res += completed + ",";
            res += task.getTaskName() + ",";
            res += ((Deadline) task).getDeadline();
        } else {
            res += "T,";
            res += completed + ",";
            res += task.getTaskName();
        }
        return res;
    }
}
