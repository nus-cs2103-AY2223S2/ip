package munch;

import AddTasks.Task;
import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String path;

    /**
     * Constructor for Storage object.
     * @param path The path of the object file where the task objects are stored in.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the task objects stored in a file and store it in an ArrayList.
     * If file has not been created, creates a new file.
     * @param tasks Stores all the task objects obtained from the file into the arrayList tasks.
     * @param paths The path of the object file where the task objects are stored in.
     * @return An arrayList consisting of all the task objects previously stored in a file.
     */
    public static ArrayList<Task> load(ArrayList<Task> tasks, String paths) {
        try {
            File storageFile = new File(paths);
            if (!storageFile.exists()) {
                storageFile.createNewFile();
                System.out.println("File does not exist, but I have created a new file for you!");
            } else {
                FileReader munch = new FileReader(paths);
                BufferedReader munchRead = new BufferedReader(munch);
                String nextLine = munchRead.readLine();
                while (nextLine != null) {
                    tasks.add(new Task(nextLine));
                    nextLine = munchRead.readLine();
                }
                munchRead.close();
                return tasks;
            }
        } catch (IOException e) {
            System.out.println("Apologies, file cannot be read!");
        }
        return tasks;
    }

    /**
     * Stores the task objects in an arrayList into a file.
     * If file has not been created, creates a new file.
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
            FileWriter munch = new FileWriter(paths);
            BufferedWriter munchWrite = new BufferedWriter(munch);
            for(int i = 0; i < tasks.size(); i++) {
                munchWrite.write(tasks.get(i).toString());
                munchWrite.newLine();
            }
            munchWrite.close();
        } catch (IOException e) {
            System.out.println("Apologies, file cannot be read!");
        }
    }
}
