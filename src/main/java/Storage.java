import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Storage {
    File file;
    String filePath;

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.file, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public void clearFile() {
        this.file.delete();
        this.file = new File(this.filePath);
    }

    public void save(TaskList taskList) throws DukeyException {
        this.clearFile();
        Iterator<Task> it = taskList.getIterator();
        it.forEachRemaining(x -> {
            String logString = x.getLogString();
            try {
                this.writeToFile(logString);
            } catch (IOException e) {
                e.getMessage();
            }
        });

    }

    public void load(TaskList taskList) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String taskLogString = fileScanner.nextLine();
            String[] logStringArray = taskLogString.split(",");
            if (logStringArray[0].equals("T")) {
                taskList.addTaskFromSave(ToDo.createToDoFromLog(logStringArray));
            } else if (logStringArray[0].equals("D")) {
                taskList.addTaskFromSave(Deadline.createDeadlineFromLog(logStringArray));
            } else if (logStringArray[0].equals("E")) {
                taskList.addTaskFromSave(Event.createEventFromLog(logStringArray));
            }
        }
    }
}
