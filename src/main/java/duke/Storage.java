package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage that reads and writes to the saved data file in local storage.
 */
public class Storage {
    private String filePath;

    /**
     * Class constructor of Storage.
     * @param filePath the path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads TaskList from the saved data file.
     * @return TaskList containing the tasks saved in the local data file.
     * @throws FileNotFoundException If data file does not exist.
     */
    public TaskList load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<String> rawData = new ArrayList<>();
        TaskList tasks = new TaskList();
        while (sc.hasNext()) {
            rawData.add(sc.nextLine());
        }
        if (rawData.isEmpty()) {
            sc.close();
            return tasks;
        }
        for (int i = 0; i < rawData.size(); i++) {
            String[] taskData = rawData.get(i).split("\\|");
            String eventType = taskData[0];
            String isDone = taskData[1];
            String title = taskData[2];
            String dateTime = "";
            if (taskData.length > 3) {
                dateTime = taskData[3];
            }
            switch(eventType) {
            case "T":
                try {
                    tasks.addToDo(title);
                    if (isDone.equals("1")) {
                        tasks.getTask(i + 1).markDone();
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "D":
                try {
                    tasks.addDeadline(title, dateTime);
                    if (taskData[1].equals("1")) {
                        tasks.getTask(i + 1).markDone();
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "E":
                String[] duration = dateTime.split("-");
                String fromDateTime = duration[0];
                String toDateTime = duration[1];
                try {
                    tasks.addEvent(title, fromDateTime, toDateTime);
                    if (isDone.equals("1")) {
                        tasks.getTask(i + 1).markDone();
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            default:
                break;
            }

        }
        sc.close();
        return tasks;
    }

    /**
     * Saves the data in Duke that is running to the local data file.
     * @param dukeData the data to be saved in string
     * @throws IOException If file cannot be written into the data file.
     */
    public void writeToFile(String dukeData) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(dukeData);
        fw.close();
    }
}
