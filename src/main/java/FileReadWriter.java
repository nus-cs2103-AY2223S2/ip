import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FileReadWriter {

    /**
     * Reads and loads the data from data.txt.
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<Task> loadFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> loadData = new ArrayList<>();
        while (s.hasNext()) {
            String[] inputs = s.nextLine().split(" \\| ");

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

    /**
     * Dumps the ArrayList dumpFile into data.txt.
     * @param filePath
     * @param dumpFile
     * @throws IOException
     */
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