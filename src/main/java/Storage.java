import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws FileNotFoundException {
        File f = new File(this.filePath);
        Scanner sc = new Scanner(f);
        ArrayList<String> rawData = new ArrayList<>();
        TaskList tasks = new TaskList();
        while (sc.hasNext()) {
            rawData.add(sc.nextLine());
        }
        if (rawData.isEmpty()) {
            System.out.println("There is no task in the list!\n");
            sc.close();
            return tasks;
        }
        for (int i = 0; i < rawData.size(); i++) {
            String[] taskData = rawData.get(i).split("\\|");
            switch(taskData[0]) {
            case "T":
                try {
                    tasks.add(taskData[2], false);
                    if (taskData[1].equals("1")) {
                        tasks.get(i + 1).markDone(false);
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "D":
                try {
                    tasks.add(taskData[2], taskData[3], false);
                    if (taskData[1].equals("1")) {
                        tasks.get(i + 1).markDone(false);
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "E":
                String[] duration = taskData[3].split("-");
                try {
                    tasks.add(taskData[2], duration[0], duration[1], false);
                    if (taskData[1].equals("1")) {
                        tasks.get(i + 1).markDone(false);
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            }
        }
        sc.close();
        return tasks;
    }

    public void writeToFile(String dukeData) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(dukeData);
        fw.close();
    }
}
