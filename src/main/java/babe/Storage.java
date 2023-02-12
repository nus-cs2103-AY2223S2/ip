package babe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The <code>Storage</code> class deals with loading tasks from the file and saving tasks in the file.
 */
class Storage {
    /**
     * Default file address where Babe's content will be stored.
     */
    private static String FILE_PATH = "./src/main/java/babe.txt";

    /**
     * BufferedWriter Object for this Babe
     */
    private static BufferedWriter bufferedWriter;

    /**
     * Loads available saved data from fileAddress.
     * Creates a save file if it doesn't exist yet at address specified by fileAddress.
     *
     * @return A boolean value; True if there is an existing save file, false otherwise.
     */
    protected static boolean initializeStorage(TaskList taskList) {
        boolean isFilePresent = true;
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                File file = new File(FILE_PATH);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            isFilePresent = false;
        }
        Storage.load(taskList);
        return isFilePresent;
    }

    private static void load(TaskList taskList) {
        try {
            Scanner scanner = new Scanner(new File(FILE_PATH));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] arr = s.split("\\|");
                String taskType = arr[0].strip();
                Boolean isDone = arr[1].equals("1");
                String desc = arr[2];
                switch (taskType) {
                case "T":
                    taskList.addToDo(desc, isDone);
                    break;
                case "D":
                    String deadline = arr[3];
                    taskList.addDeadline(desc, deadline, isDone);
                    break;
                case "E":
                    String startDate = arr[3];
                    String endDate = arr[4];
                    taskList.addEvent(desc, startDate, endDate, isDone);
                    break;
                default:
                    // Need to add a file corruption error here
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves Tasks stored in Babe's memory to a file specified by fileAddress.
     */
    protected static void save(TaskList taskList) {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH, false));
            String data = "";

            for (int i = 0; i < taskList.length(); i++) {
                data = taskList.getSaveFormat(i);
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
