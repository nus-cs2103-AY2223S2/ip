import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    public void save(String data) throws IOException {
        FileWriter fw = new FileWriter(storagePath);
        fw.write(data);
        fw.close();
    }

    public void read(TaskList taskList, Ui ui) throws FileNotFoundException {
        File f = new File(storagePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String taskString = s.nextLine();
            String[] parts = taskString.split("/");

            try {
                if (parts[0].equals("D")) {
                    taskList.add(Deadline.parseDeadlineStringArray(parts));
                } else if (parts[0].equals("E")) {
                    taskList.add(Event.parseEventStringArray(parts));
                } else if (parts[0].equals("T")) {
                    taskList.add(ToDo.parseToDoStringArray(parts));
                }
            } catch (DateTimeParseException e) {
                ui.showMessage("☹ OOPS!!! Failed to load a task from storage!");
            }

        }
    }
}
