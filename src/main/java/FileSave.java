import java.io.*;
import java.util.ArrayList;


public class FileSave {
    private static final String path = "src/main/java/data/SavedTaskList.txt";
    private static final File storageFile = new File(path);

    public static ArrayList<Task> load(ArrayList<Task> tasks) {
        try {
            if (!storageFile.exists()) {
                storageFile.createNewFile();
                System.out.println("File does not exist, but I have created a new file for you!");
            } else {
                FileReader munch = new FileReader(path);
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

    public static void save(ArrayList<Task> tasks) {
        try {
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            } else {
                storageFile.delete();
                storageFile.createNewFile();
            }
            FileWriter munch = new FileWriter(path);
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
