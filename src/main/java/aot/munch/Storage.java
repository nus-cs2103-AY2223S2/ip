package aot.munch;
import aot.AddTasks.Deadlines;
import aot.AddTasks.Events;
import aot.AddTasks.Task;
import aot.AddTasks.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    private String path;

    /**
     * Constructor for Storage object.
     *
     * @param path The path of the object file where the task objects are stored in.
     */
    public Storage(String path) {
        this.path = path;
    }

    private static String getFilePath() throws IOException {
        try {
            Path file = Paths.get(System.getProperty("user.dir"), "data");
            if (!Files.exists(file)) {
                Files.createDirectory(file);
            }
            Path filePath = Paths.get(file.toString(), "SavedTaskList.txt");
            return filePath.toString();
        } catch(IOException e) {
            return Ui.fileErrorMessage();
        }
    }

    /**
     * Loads the task objects stored in a file and store it in an ArrayList.
     * If file has not been created, creates a new file.
     *
     * @param tasks Stores all the task objects obtained from the file into the arrayList tasks.
     * @return An arrayList consisting of all the task objects previously stored in a file.
     */
    public static ArrayList<Task> load(ArrayList<Task> tasks) {
        try {
            File f = new File(getFilePath());
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                Task task = stringToTask(nextLine);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Apologies, file cannot be read!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Stores the task objects in an arrayList into a file.
     * If file has not been created, creates a new file.
     *
     * @param tasks Stores all the task objects in tasks into a file.
     */

    public static void save(ArrayList<Task> tasks) {
        try {
            File storageFile = new File(getFilePath());
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            } else {
                storageFile.delete();
                storageFile.createNewFile();
            }
            FileWriter fw = new FileWriter(getFilePath());
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toString());
                fw.write('\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Apologies, an error occurred.");
        }
    }

    public static Task stringToTask(String str) {
        Task task = null;
        if (str.contains("[T]")) {
            String separator1 = "[T]";
            int sepPos1 = str.indexOf(separator1);
            String des = str.substring(sepPos1 + separator1.length() + 4);
            task = new Todo(des);
        } else if (str.contains("[D]")) {
            String separator1 = "[D]";
            String separator2 = " (By: ";
            int sepPos1 = str.indexOf(separator1);
            int sepPos2 = str.indexOf(separator2);
            String des = str.substring(sepPos1 + separator1.length() + 4, sepPos2);
            LocalDate date = Parser.wordsToDate(str.substring(sepPos2 + separator2.length(), str.length() - 1));
            task = new Deadlines(des, date);
        } else if (str.contains("[E]")) {
            String separator1 = "[E]";
            String separator2 = " (From: ";
            String separator3 = " | To: ";
            int sepPos1 = str.indexOf(separator1);
            int sepPos2 = str.indexOf(separator2);
            int sepPos3 = str.indexOf(separator3);
            String des = str.substring(sepPos1 + separator1.length() + 4, sepPos2);
            String from = str.substring(sepPos2 + separator2.length(), sepPos3);
            String to = str.substring(sepPos3 + separator3.length(), str.length() - 1);
            LocalDate convertFrom = Parser.wordsToDate(from);
            LocalDate convertTo = Parser.wordsToDate(to);
            task = new Events(des, convertFrom, convertTo);
        }
        if(str.contains("[X]")) {
            assert task != null;
            task.markAsDone();
        }
        return task;
    }
}
