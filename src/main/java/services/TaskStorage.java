package services;

import types.data.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TaskStorage {
    private final ArrayList<Task> tasks = new ArrayList<>(50);
    private BufferedWriter bw;

    public TaskStorage() {
        try {
            String folder = "./data/";
            Path folder_p = Path.of(folder);
            if (!Files.exists(folder_p)) {
                Files.createDirectory(folder_p);
            }
            bw = new BufferedWriter(new FileWriter(folder + "tasks.txt", true));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        try {
            bw.append(String.valueOf(t));
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void markByNo(int n) {
        this.tasks.get(n - 1).setDone();
        try {
            bw.write("");
            for (Task i : tasks) {
                bw.append(String.valueOf(i));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void unmarkByNo(int n) {
        this.tasks.get(n - 1).setUndone();
        try {
            bw.write("");
            for (Task i : tasks) {
                bw.append(String.valueOf(i));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void deleteByNo(int n) {
        this.tasks.remove(n - 1);
        try {
            bw.write("");
            for (Task i : tasks) {
                bw.append(String.valueOf(i));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTaskByNo(int n) {
        return this.tasks.get(n - 1);
    }
}
