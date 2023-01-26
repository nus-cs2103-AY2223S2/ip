import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

public class Storage {
    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> arr = new ArrayList<>();
        while(s.hasNextLine()) {
            String[] parts = s.nextLine().split(Pattern.quote(" | "));
            switch (parts[0]) {
                case "T":
                    arr.add(new Todo(parts[2], Boolean.parseBoolean(parts[1])));
                    break;
                case "D":
                    arr.add(new Deadline(parts[2], Boolean.parseBoolean(parts[1]), parts[3].substring(4), FORMAT));
                    break;
                case "E":
                    arr.add(new Event(parts[2], Boolean.parseBoolean(parts[1]), parts[3].substring(6),
                            parts[4].substring(4), FORMAT));
                    break;
            }
        }
        return arr;
    }

    public void saveData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        fw.write(tasks.toString());
        fw.close();
    }
}
