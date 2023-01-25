
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Storage {
    
    private final String filePath;
    private Ui ui;
    private static ArrayList<Task> list = new ArrayList<Task>(100);

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    public void displayList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public void markListItem(int index) {
        list.get(index).toBeMarked();
    }

    public void unmarkListItem(int index) {
        list.get(index).toBeUnmarked();
    }

    public void addTodoItem(String input) {
        list.add(new Todo(input.substring(5, input.length())));
        this.ui.listInfo(list.size());
    }

    public void addDeadlineItem(String first_word, String second_word) {
        list.add(new Deadline(first_word, second_word));
    }

    public void addEventItem(String name, String startTime, String endTime) {
        list.add(new Event(name, startTime, endTime));
    }

    public void deleteListItem(int index) {
        Task temp = list.get(index);
        list.remove(index);
        System.out.println("The Duke has removed this task: " + temp);
        this.ui.listInfo(list.size());
    }


     /** 
      Part of the code extracted from https:/
     /www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java
     */
     
     public void loadFileData() {
        try {
            File file = new File("./data");
            if (file.exists()) {
                File txtFile = new File(filePath);
                FileReader fileReader = new FileReader(txtFile);
                readToFile(fileReader);
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readToFile(FileReader file) {
        try {
            BufferedReader reader = new BufferedReader(file);
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (Task t : list) {
                writer.write(t.toString());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
