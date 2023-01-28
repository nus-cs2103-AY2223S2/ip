import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeReader {
    private static ArrayList<Task> storage = new ArrayList<Task>();

    private static void loadDukeList(String filePath) throws FileNotFoundException {
        DukeFile f = new DukeFile(filePath); // create a File for the given file path
        Scanner s = new Scanner(DukeFile.getFile()); // create a Scanner using the File as the source
        while (s.hasNext()) {
            // convert text to Task
            String[] input = s.nextLine().split(" \\| ");
            String type = input[0];
            String mark = input[1];
            Task x = null;
            if (type.equals("T")) {
                x = new Todo(input[2]);
            } else if (type.equals("D")) {
                x = new Deadline(input[2], input[3]);
            } else if (type.equals("E")) {
                x = new Event(input[2], input[3], input[4]);
            }
            if (mark.equals("1")) {
                x.mark();
            }
            storage.add(x);
        }
    }

    public static ArrayList<Task> retrieveDukeList() {
        return storage;
    }

    public static void main(String[] args) {
        try {
            loadDukeList("../../../../data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data.");
        }
    }
}
