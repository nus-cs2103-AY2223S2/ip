package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String TEMP_PATH = "./data/temp.txt";
    private File file;
    private BufferedWriter bw;
    private String path;

    public Storage(String path) throws IOException {
        file = new File(path);
        boolean isFileExist = file.exists();
        if (!isFileExist) {
            makeFile(file);
        }
        this.path = path;
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    public void makeFile(File file) throws IOException {
        file.mkdirs();
        file.delete();
        file.createNewFile();
    }

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
        boolean successful = fileTemp.renameTo(file);
        bw = new BufferedWriter(new FileWriter(file, true));
    }

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
        boolean successful = fileTemp.renameTo(file);
        bw = new BufferedWriter(new FileWriter(file, true));
    }

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
        boolean successful = fileTemp.renameTo(file);
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    public void todo(String task) throws IOException {
        bw.write("T / 0 / " + task + "\n");
        bw.flush();
    }

    public void deadline(String task, String date) throws IOException {
        bw.write("D / 0 / " + task + " / " + date + "\n");
        bw.flush();
    }

    public void event(String task, String from, String to) throws IOException {
        bw.write("E / 0 / " + task + "/ " + from + "/ " + to + "\n");
        bw.flush();
    }

    public void bye() throws IOException {
        bw.close();
    }
}
