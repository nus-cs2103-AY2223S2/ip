package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final String TEMP_PATH = "./data/temp.txt";
    private File file;
    private BufferedWriter bw;
    private String path;

    /**
     * Constructor for Storage
     * @param path
     * @throws IOException
     */
    public Storage(String path) throws IOException {
        file = new File(path);
        boolean isFileExist = file.exists();
        if (!isFileExist) {
            makeFile(file);
        }
        this.path = path;
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * Function to make the directory and the file based on the file path
     *
     * @param file the file that wants to be created
     * @throws IOException
     */
    public void makeFile(File file) throws IOException {
        file.mkdirs();
        file.delete();
        boolean isSucceed = file.createNewFile();
        assert isSucceed;
    }

    /**
     * Loading the previous user data
     *
     * @return ArrayList that contains tasks from previous session
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> arr = new ArrayList<>(100);
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String savedTask;
            while ((savedTask = bf.readLine()) != null) {
                String[] taskFragment = savedTask.split(" / ", 5);
                if (taskFragment[0].equals("T")) {
                    ToDo toDo = new ToDo(taskFragment[2]);
                    if (taskFragment[1].equals("1")) {
                        toDo.mark();
                    }
                    arr.add(toDo);
                } else if (taskFragment[0].equals("D")) {
                    Deadline deadline = new Deadline(taskFragment[2], taskFragment[3]);
                    if (taskFragment[1].equals("1")) {
                        deadline.mark();
                    }
                    arr.add(deadline);
                } else if (taskFragment[0].equals("E")) {
                    Event event = new Event(taskFragment[2], taskFragment[3], taskFragment[4]);
                    if (taskFragment[1].equals("1")) {
                        event.mark();
                    }
                    arr.add(event);
                }
            }
            bf.close();
            return arr;
        } catch (Exception e) {
            return new ArrayList<Task>(100);
        }
    }

    /**
     * Modifying the user data when marking a task
     *
     * @param idx the index of task
     * @throws IOException
     */
    public void mark(int idx) throws IOException {
        File fileTemp = new File(TEMP_PATH);
        if (!fileTemp.exists()) {
            makeFile(fileTemp);
        }
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMP_PATH));
        BufferedReader br = new BufferedReader(new FileReader(path));
        int idx2 = 0;
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            if (idx2 == idx) {
                currentLine = currentLine.replaceFirst("0", "1");
            }
            bw2.write(currentLine + System.getProperty("line.separator"));
            idx2++;
        }
        bw2.close();
        br.close();
        bw.close();
        file.delete();
        boolean isSucceed = fileTemp.renameTo(file);
        assert isSucceed:
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * Modifying the data when user un-marking a task
     *
     * @param idx the index of task
     * @throws IOException
     */
    public void unmark(int idx) throws IOException {
        File fileTemp = new File(TEMP_PATH);
        if (!fileTemp.exists()) {
            makeFile(fileTemp);
        }
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMP_PATH));
        BufferedReader br = new BufferedReader(new FileReader(path));
        int idx2 = 0;
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            if (idx2 == idx) {
                currentLine = currentLine.replaceFirst("1", "0");
            }
            bw2.write(currentLine + System.getProperty("line.separator"));
            idx2++;
        }
        bw2.close();
        br.close();
        bw.close();
        file.delete();
        boolean isSucceed = fileTemp.renameTo(file);
        assert isSucceed:
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * Modifying the data when user deleting a task
     *
     * @param idx the index of the task
     * @throws IOException
     */
    public void delete(int idx) throws IOException {
        File fileTemp = new File(TEMP_PATH);
        if (!fileTemp.exists()) {
            makeFile(fileTemp);
        }
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMP_PATH));
        BufferedReader br = new BufferedReader(new FileReader(path));
        int idx2 = 0;
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            if (idx2 == idx) {
                idx2++;
                continue;
            }
            bw2.write(currentLine + System.getProperty("line.separator"));
            idx2++;
        }
        bw2.close();
        br.close();
        bw.close();
        file.delete();
        boolean isSucceed = fileTemp.renameTo(file);
        assert isSucceed:
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    /**
     * Modifying the data when adding a ToDo
     *
     * @param task the name of the task
     * @throws IOException
     */
    public void todo(String task) throws IOException {
        bw.write("T / 0 / " + task + "\n");
        bw.flush();
    }

    /**
     * Modifying the data when user adding a deadline
     *
     * @param task the name of the task
     * @param date the date of task with format (yyyy-mm-dd)
     * @throws IOException
     */
    public void deadline(String task, String date) throws IOException {
        bw.write("D / 0 / " + task + " / " + date + "\n");
        bw.flush();
    }

    /**
     * Modifying the data when user adding an event
     *
     * @param task the name of the task
     * @param from the beginning date of event with format (yyyy-mm-dd)
     * @param to   the end date of event with format (yyyy-mm-dd)
     * @throws IOException
     */
    public void event(String task, String from, String to) throws IOException {
        bw.write("E / 0 / " + task + "/ " + from + "/ " + to + "\n");
        bw.flush();
    }

    public void bye() throws IOException {
        bw.close();
    }

    public void replaceToDo(int idx, String task) throws IOException {
        File fileTemp = new File(TEMP_PATH);
        if (!fileTemp.exists()) {
            makeFile(fileTemp);
        }
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMP_PATH));
        BufferedReader br = new BufferedReader(new FileReader(path));
        int idx2 = 0;
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            if (idx2 == idx) {
                idx2++;
                bw.write("T / 0 / " + task + "\n");
            }
            bw2.write(currentLine + System.getProperty("line.separator"));
            idx2++;
        }
        bw2.close();
        br.close();
        bw.close();
        file.delete();
        boolean isSucceed = fileTemp.renameTo(file);
        assert isSucceed:
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    public void replaceDeadline(int idx, String task, String date) throws IOException {
        File fileTemp = new File(TEMP_PATH);
        if (!fileTemp.exists()) {
            makeFile(fileTemp);
        }
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMP_PATH));
        BufferedReader br = new BufferedReader(new FileReader(path));
        int idx2 = 0;
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            if (idx2 == idx) {
                idx2++;
                bw.write("D / 0 / " + task + " / " + date + "\n");
            }
            bw2.write(currentLine + System.getProperty("line.separator"));
            idx2++;
        }
        bw2.close();
        br.close();
        bw.close();
        file.delete();
        boolean isSucceed = fileTemp.renameTo(file);
        assert isSucceed:
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    public void replaceEvent(int idx, String task, String from, String to) throws IOException {
        File fileTemp = new File(TEMP_PATH);
        if (!fileTemp.exists()) {
            makeFile(fileTemp);
        }
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMP_PATH));
        BufferedReader br = new BufferedReader(new FileReader(path));
        int idx2 = 0;
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            if (idx2 == idx) {
                idx2++;
                bw.write("E / 0 / " + task + "/ " + from + "/ " + to + "\n");
            }
            bw2.write(currentLine + System.getProperty("line.separator"));
            idx2++;
        }
        bw2.close();
        br.close();
        bw.close();
        file.delete();
        boolean isSucceed = fileTemp.renameTo(file);
        assert isSucceed:
        bw = new BufferedWriter(new FileWriter(file, true));
    }

}
