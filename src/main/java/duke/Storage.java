package duke;

import duke.exceptions.InvalidDateFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path;
    public Storage(String relativePath) {
        path = relativePath;
    }

    public ArrayList<Task> initialize() throws FileNotFoundException {
        File f = new File(path);
        ArrayList<Task> og = new ArrayList<>();
        if (f.exists()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] keywords = splitString(str);
                String type = keywords[0];
                String doneStatus = keywords[1];
                boolean isDone = doneStatus == "X" ? true : false;
                Task toInsert = null;
                switch (type) {
                case "T":
                    toInsert = new ToDo(keywords[2], isDone);
                    break;
                case "D":
                    try {
                        toInsert = new Deadline(keywords[2], keywords[3], isDone);
                    } catch (InvalidDateFormatException err) {
                        System.out.println(err.getMessage());
                    }
                    break;
                case "E":
                    String[] duration = keywords[3].split(" - ");
                    toInsert = new Event(keywords[2], duration[0], duration[1], isDone);
                    break;
                }
                og.add(toInsert);
            }
            sc.close();
        }
        return og;
    }


    private String[] splitString(String str) {
        return str.split(" \\| ");
    }

    public void addOne() throws IOException {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }

        f.getParentFile().mkdirs();
        f.createNewFile();

        FileWriter fw = new FileWriter(path);
        ArrayList<Task> changed = TaskList.getList();
        fw.write(changed.get(changed.size() - 1).toString());
        fw.write("\n");
        fw.close();
    }

    public void changeOne(int index) throws IOException {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }

        f.getParentFile().mkdirs();
        f.createNewFile();

        Scanner sc = new Scanner(System.in);
        for (int i = 1; i < index; i++) {
            sc.nextLine();
        }

        FileWriter fw = new FileWriter(path);
        ArrayList<Task> changed = TaskList.getList();
        fw.write(changed.get(index - 1).toString());
        fw.write("\n");
        fw.close();
        sc.close();
    }

    public void load() throws IOException {
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }

        f.getParentFile().mkdirs();
        f.createNewFile();


        FileWriter fw = new FileWriter(path);
        ArrayList<Task> changed = TaskList.getList();
        for (int i = 0; i < changed.size(); i++) {
            fw.write(changed.get(i).toString());
            fw.write("\n");
        }
        fw.close();
    }
}
