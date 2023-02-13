package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// class Storage - handles the loading of data when duke is run and
// saving of data when duke is closed
class Storage {
    private String filePath;

    public Storage(String s) {
        this.filePath = s;
    }

    public ArrayList<Task> load() {
        assert this.filePath.length() > 0 : "this file path should not be empty";
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();
        File dir = new File(System.getProperty("user.dir") + "/data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            Scanner saveFile = new Scanner(file);
            System.out.println("    Saved data found, welcome back!");
            while (saveFile.hasNextLine()) {
                loadSaved(saveFile.nextLine(), tasks);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("    No saved data not found, new file will be created");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

    public static void loadSaved(String s, ArrayList<Task> tasks) {
        if (s.substring(0, 1).equals("T")) {
            Todo newTodo = new Todo(s.substring(8));
            if (s.substring(4, 5).equals("1")) {
                newTodo.toggleMarked();
            }
            tasks.add(newTodo);

        } else if (s.substring(0, 1).equals("D")) {
            String marked = s.substring(4, 5);
            String task = s.substring(8);
            Deadline newDeadline = new Deadline(task.substring(0, task.indexOf("|") - 1),
                    task.substring(task.indexOf("|") + 2));
            if (marked.equals("1")) {
                newDeadline.toggleMarked();
            }
            tasks.add(newDeadline);

        } else {
            String marked = s.substring(4, 5);
            String task = s.substring(8);
            String desc = task.substring(0, task.indexOf("|") - 1);
            task = task.substring(task.indexOf("|") + 2);
            Event newEvent = new Event(desc, task.substring(0, task.indexOf("|") - 1),
                    task.substring(task.indexOf("|") + 2));
            tasks.add(newEvent);
        }
    }

    public void saveData(TaskList t) {
        ArrayList<Task> tasks = t.getTasks();
        assert tasks.size() >= 0 : "Number of tasks should not be negative";
        try {
            FileWriter fWriter = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fWriter.write(task.toSavedString());
                if (i != tasks.size() - 1) {
                    fWriter.write("\n");
                }
            }
            fWriter.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}

