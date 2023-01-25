package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.Files.createFile;

public class Storage {
    protected String filePath;
    protected String dirPath;
    public Storage(String filePath, String dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;

        // to initiate locations
        File theDir = new File(dirPath);
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        try {
            createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createFile(String filePath) throws IOException {
        File file = new File(filePath);

        file.createNewFile();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> arrayList = new ArrayList<>();
        while (s.hasNext()) {
            String[] parts = s.nextLine().split("/");
            if (parts[0].equals("T")) {
                Task t = new Todo(parts[2]);
                if (parts[1].equals("1")) t.markAsDone();
                arrayList.add(t);

            }
            if (parts[0].equals("D")) {
                Task t = new Deadline(parts[2], LocalDateTime.parse(parts[3]));
                if (parts[1].equals("1")) t.markAsDone();
                arrayList.add(t);
            }
            if (parts[0].equals("E")) {
                Task t = new Event(parts[2], LocalDateTime.parse(parts[3]),
                        LocalDateTime.parse(parts[4]));
                if (parts[1].equals("1")) t.markAsDone();
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.getLength(); i++) {

            Task t = tasks.get(i);
            if (t instanceof Todo) {
                fw.write("T/" + t.getStatusIconBinary() + "/" + t.getDescription());
            }
            if (t instanceof Deadline) {
                fw.write("D/" + t.getStatusIconBinary() + "/" + t.getDescription() + "/" + ((Deadline) t).getBy());
            }
            if (t instanceof Event) {
                fw.write("E/" + t.getStatusIconBinary() + "/" + t.getDescription() + "/" + ((Event) t).getFrom() + "/" + ((Event) t).getTo());
            }

            fw.write(System.lineSeparator());

        }
        fw.close();
    }

}
