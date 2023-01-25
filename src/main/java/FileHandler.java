import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private String path;
    public FileHandler(String relativePath) {
        path = relativePath;
    }

    public void initialize(ArrayList<Task> og) throws FileNotFoundException {
        File f = new File(path);
        if (f.exists()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String str = sc.nextLine();
                char type = str.charAt(1);
                char doneStatus = str.charAt(4);
                boolean isDone = doneStatus == 'X' ? true : false;
                Task toInsert = null;
                switch (type) {
                case 'T':
                    toInsert = new ToDo(str.substring(7), isDone);
                    break;
                case 'D':
                    String[] separated = str.substring(7).split("\\(");
                    toInsert = new Deadline(separated[0], separated[1].substring(4, separated[1].length() - 1), isDone);
                    break;
                case 'E':
                    String[] separated2 = str.substring(7).split("\\(");
                    String duration = separated2[1].substring(5, separated2[1].length() - 1);
                    String[] durationArr = duration.split("to: ");
                    toInsert = new Event(separated2[0], durationArr[0], durationArr[1], isDone);
                    break;
                }
                og.add(toInsert);
            }
            sc.close();
        }
    }

    public void load(ArrayList<Task> arrChanged) throws IOException {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }

        f.getParentFile().mkdirs();
        f.createNewFile();


        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < arrChanged.size(); i++) {
            fw.write(arrChanged.get(i).toString());
            fw.write("\n");
        }
        fw.close();
    }
}
