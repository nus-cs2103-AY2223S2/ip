package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final File file;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

    public void saveData(ArrayList<Task> tasks) {
        try {
            File userData = new File("userData");
            if (!userData.exists()) {
                userData.mkdir();
            }
            File dukeTxt = new File(userData, "duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            } else {
                dukeTxt.delete();
                dukeTxt.createNewFile();
            }
            FileWriter fw = new FileWriter(dukeTxt);
            BufferedWriter dukeWrite = new BufferedWriter(fw);
            for (int i = 0; i < tasks.size(); i++) {
                dukeWrite.write(tasks.get(i).toString());
                dukeWrite.newLine();
            }
            dukeWrite.close();
        } catch (IOException e) {
            System.out.println("Oh no!!");
        }
    }

    public void loadData(ArrayList<Task> tasks) {
        try {
            File userData = new File("userData");
            if (!userData.exists()) {
                userData.mkdir();
            }
            File dukeTxt = new File(userData, "duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            } else {
                FileReader fw = new FileReader(dukeTxt);
                BufferedReader dukeRead = new BufferedReader(fw);
                String line = dukeRead.readLine();
                while (line != null) {
                    if (line.contains("[D]")) {
                        tasks.add(new Deadline(line.replace("[D]", "")));
                    } else if (line.contains("[T]")) {
                        tasks.add(new ToDo(line.replace("[T]", "")));
                    } else if (line.contains("[E]")) {
                        tasks.add(new Event(line.replace("[E]", "")));
                    } else {
                        tasks.add(new Task(line));
                    }
                    line = dukeRead.readLine();
                }
                dukeRead.close();
            }
        } catch (IOException e) {
            System.out.println("Error: Data loading to the system fails !!");
        }
    }

    public void realTimeSave(ArrayList<Task> tasks) {
        try {
            File dukeTxt = new File("duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            }
            FileWriter fw = new FileWriter(dukeTxt, true);
            BufferedWriter dukeWrite = new BufferedWriter(fw);
            int i = tasks.size() - 1;
            dukeWrite.write(tasks.get(i).toString());
            dukeWrite.newLine();
            dukeWrite.close();
        } catch (IOException e) {
            System.out.println("Oh no!!");
        }
    }

    public void deleteAll(ArrayList<Task> tasks) throws IOException {
        tasks.clear();
        File dukeTxt = new File("duke.txt");
        dukeTxt.delete();
        dukeTxt.createNewFile();
    }

}
