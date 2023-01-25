package duke.storage;

import duke.tasklist.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File myFoo;
    private String path;

    public Storage() {
        this.path = "data/duke.txt";
        this.myFoo = new File(path);
    }
    public ArrayList<Task> initData() throws IOException {
        boolean isFileExist = new java.io.File(path).exists();
        ArrayList<Task> arrList = new ArrayList<>(); //might be empty
        if (!isFileExist) {
            Path dir = Paths.get(path);
            try {
                Files.createDirectories(dir.getParent());
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }

            try {
                Files.createFile(dir);
            } catch (IOException error) {
                System.out.println(error.getMessage());
            }
        } else {
            try {
                File toLoad = new File(path);
                Scanner fileScanner = new Scanner(toLoad);
                while (fileScanner.hasNextLine()) {
                    String currTask = fileScanner.nextLine();
                    parseTask(arrList, currTask);
                }
            } catch (FileNotFoundException f) {
                System.out.println(f.getMessage());
            }
        }
        return arrList;

    }

    public void parseTask(ArrayList<Task> arrList, String currTask) {
        Task task = null;
        String[] toCheck = null;
        switch (currTask.charAt(1)) {
        case 'T':
            task = new Todo(currTask.substring(7));
            break;
        case 'D':
            toCheck = currTask.split("by: ", 2);
            String desc = toCheck[0].substring(7, toCheck[0].length() - 2);
            String date = toCheck[1].substring(0, toCheck[1].length() - 1);
            task = new Deadline(desc, date);
            break;
        case 'E':
            toCheck = currTask.split("from:", 2);
            String descEvent = toCheck[0].substring(7, toCheck[0].length() - 1);
            String[] fromTo = toCheck[1].split("to:", 2);
            String from = fromTo[0].substring(0, fromTo[0].length()- 1);
            String to = fromTo[1].substring(0, fromTo[1].length()- 1);
            task = new Event(descEvent, from, to);
            break;
        }
        if (currTask.charAt(4) == 'X') {
            try{
                task.changeCompletion();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        arrList.add(task);
    }

    public void saveToFile(TaskList itemList) {
        String path = "data/duke.txt";
        FileWriter fooWriter = null; //init
        try {
            fooWriter = new FileWriter(myFoo, false);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (Task current : itemList.getList()) {
            try{
                fooWriter.write(current.toSave() + "\n");
            }  catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            fooWriter.flush();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
