import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String path;
    protected final String DATE_TIME_TO_PARSE = "yyyy-MM-dd HH:mm";

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(this.path);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String taskString = sc.nextLine();
            Task currTask = parse(taskString);
            arr.add(currTask);
        }
        sc.close();
        return arr;
    }

    public void save(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(this.path);
        for (Task task: taskList) {
            DateTimeFormatter formatterParse = DateTimeFormatter.ofPattern(DATE_TIME_TO_PARSE);
            StringBuilder sb = new StringBuilder(task.getStatusIcon());
            sb.append("\\");
            sb.append(task.getDescription());
            sb.append("\\");
            // X\desc\T
            if (task instanceof ToDo) {
                sb.append("T");
                fw.write(sb + System.lineSeparator());
            } else if (task instanceof Deadline) { // X\desc\D\By
                sb.append("D");
                sb.append("\\");
                sb.append(((Deadline) task).getDateTimeBy().format(formatterParse));
                fw.write(sb + System.lineSeparator());
            } else {
                sb.append("E");
                sb.append("\\");
                sb.append(((Event) task).getDateTimeFrom().format(formatterParse)); // X\desc\E\From\To
                sb.append("\\");
                sb.append(((Event) task).getDateTimeTo().format(formatterParse));
                fw.write(sb + System.lineSeparator());
            }

        }
        fw.close();
    }

    public Task parse(String str) {
        Task task;
        String[] arr = str.split("\\\\");

        switch (arr[2]) {
            case "T":
                task = new ToDo(arr[1]);
                break;
            case "D":
                task = new Deadline(arr[1], arr[3]);
                break;
            case "E":
                task = new Event(arr[1], arr[3], arr[4]);
                break;
            default:
                throw new IllegalStateException();
        }
        if (arr[0].equals("X")) {
            task.markAsDone();
        }
        return task;
    }
}
