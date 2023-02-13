package munch;
import AddTasks.Task;
import AddTasks.Todo;

import java.io.*;
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

    /**
     * Loads the task objects stored in a file and store it in an ArrayList.
     * If file has not been created, creates a new file.
     *
     * @param tasks Stores all the task objects obtained from the file into the arrayList tasks.
     * @param paths The path of the object file where the task objects are stored in.
     * @return An arrayList consisting of all the task objects previously stored in a file.
     */
    public static ArrayList<Task> load(ArrayList<Task> tasks, String paths) {
        try {
            File f = new File(paths); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String nextLine = s.nextLine();
                System.out.println(nextLine);
                Task task = new Task(nextLine);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Apologies, file cannot be read!");
        }
        return tasks;
    }

    /**
     * Stores the task objects in an arrayList into a file.
     * If file has not been created, creates a new file.
     *
     * @param tasks Stores all the task objects in tasks into a file.
     * @param paths The path of the object file where the task objects are stored in.
     */

    public static void save(ArrayList<Task> tasks, String paths) {
        try {
            File storageFile = new File(paths);
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            } else {
                storageFile.delete();
                storageFile.createNewFile();
            }
            FileWriter fw = new FileWriter(paths);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toString());
                fw.write('\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Apologies, an error occurred.");
        }
    }

//    public static Task convertStringToTask(String str) {
//        Task task = null;
//        if (str.contains("[T]")) {
//            String separator = "[T][ ] ";
//            int sepPos = str.indexOf(separator);
//            String todoDescription = str.substring(sepPos + separator.length());
//            task = new Todo(todoDescription);
//        } else if (str.contains("[D]")) {
//            String separator1 = "[D][ ] ";
//            String separator2 = " (By: ";
//            int sepPos1 = str.indexOf(separator1);
//            int sepPos2 = str.indexOf(separator2);
//            String des = str.substring(sepPos1 + separator1.length(), sepPos2);
//            String date =
//            task = new Todo(todoDescription);
//        } else if (str.contains("[E]")) {
//
//        }
//        return task;
//    }
}
