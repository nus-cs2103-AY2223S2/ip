import task.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * This class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /**
     * Default file address where Babe's content will be stored.
     */
    private static String fileAddress = "./src/main/java/babe.txt";

    /**
     * BufferedWriter Object for this Babe
     */
    private static BufferedWriter bufferedWriter;

    /**
     * Loads available saved data from fileAddress.
     * Creates a save file if it doesn't exist yet at address specified by fileAddress.
     */
    public static void initializeStorage(TaskList taskList) {
        if (!Files.exists(Paths.get(fileAddress))) {
            try {
                File file = new File(fileAddress);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Ui.notifyCreateSaveFile();
        }
        Storage.load(taskList);
    }

    private static void load(TaskList taskList) {
        try {
            Scanner scanner = new Scanner(new File(fileAddress));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] arr = s.split("\\|");
                String taskType = arr[0].strip();
                Boolean isDone = arr[1].equals("1");
                String desc = arr[2];
                Task item = null;
                switch (taskType) {
                case "T":
                    item = taskList.addToDo(desc, false);
                    break;
                case "D":
                    String deadline = arr[3];
                    item = taskList.addDeadline(desc, deadline, false);
                    break;
                case "E":
                    String startDate = arr[3];
                    String endDate = arr[4];
                    item = taskList.addEvent(desc, startDate, endDate, false);
                    break;
                default:
                    // Need to add a file corruption error here
                    break;
                }

                if (isDone) {
                    item.mark();
                } else {
                    item.unmark();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves Tasks stored in Babe's memory to a file specified by fileAddress.
     */
    public static void save(TaskList taskList) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileAddress, false));
            String data = "";

            for (int i = 0; i < taskList.length(); i++) {
                data = taskList.get(i).toSaveFormat();
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
