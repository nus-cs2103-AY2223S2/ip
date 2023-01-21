package data;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.*;

public class MyData {
    private final ArrayList<Task> data = new ArrayList<Task>();

    public Task getData(int index) {
        return this.data.get(index);
    }

    public void deleteData(int index) { this.data.remove(index); }

    public void setData(Task command) {
        data.add(command);
    }

    public void markDone(int index) {
        this.data.get(index).markDone();
    }

    public void markUndone(int index) {
        this.data.get(index).markUndone();
    }

    public int len() {
        return this.data.size();
    }

    public void saveToFile() {
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "java", "data", "duke.txt");
        try {
            FileWriter writer = new FileWriter(path.toFile());
            for (int i = 0; i < len(); i++) {
                writer.write(getData(i).toSave() + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadData() throws IOException {
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "java", "data", "duke.txt");
        try {
            Scanner sc = new Scanner(new File(path.toString()));
            int id = 0;
            while (sc.hasNextLine()) {
                String[] arr = sc.nextLine().split(" / ");
                String command = arr[0];
                String marked = arr[1];
                switch (command) {
                    case "T":
                        ToDo todo = new ToDo(arr[2]);
                        setData(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(arr[2], arr[3]);
                        setData(deadline);
                        break;
                    case "E":
                        Event event = new Event(arr[2], arr[3], arr[4]);
                        setData(event);
                        break;
                }
                if (marked.equals("1")) {
                    markDone(id);
                }
                id++;
            }
        } catch (FileNotFoundException e) {
            Files.createFile(path);
        }
    }
}

