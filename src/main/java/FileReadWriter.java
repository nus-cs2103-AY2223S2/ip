import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileReadWriter {

    public static ArrayList<Task> loadFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> loadData = new ArrayList<>();
        while (s.hasNext()) {
            String[] inputs = s.nextLine().split(" \\| ");
            System.out.println(Arrays.toString(inputs));
            boolean isDone = inputs[1].equals("1") ? true : false;
            switch(inputs[0]) {
                case "T":
                    loadData.add(new Todos(isDone, inputs[2]));
                    break;
                case "D":
                    loadData.add(new Deadlines(isDone, inputs[2], inputs[3]));
                    break;
                case "E":
                    loadData.add(new Events(isDone, inputs[2], inputs[3], inputs[4]));
                    break;
            }
        }
        return loadData;
    }

    public static void dumpFile(String filePath, ArrayList<Task> dumpFile) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String txt = "";
        for (Task t: dumpFile) {
            txt = txt + t.formatText() + "\n";
        }
        fw.write(txt);
        fw.close();
    }

}