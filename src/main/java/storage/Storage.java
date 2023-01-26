package storage;

import tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void writeMarkingToFile(String filePath, String mark, int line) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
        String s = fileContent.get(line);
        ArrayList<String> taskDetails = new ArrayList<>(Arrays.asList(s.split(" \\| ")));
        taskDetails.set(1, mark);
        String task = "";
        for (int i = 0; i < taskDetails.size() - 1; i++) {
            task += taskDetails.get(i) + " | ";
        }
        task += taskDetails.get(taskDetails.size() - 1);
        fileContent.set(line, task);
        Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
    }

    public void deleteTaskInFile(String filePath, int line) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
        fileContent.remove(line);
        Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
    }

    public ArrayList<Task>readTaskList() throws IOException {
        ArrayList<String> dirsArray = new ArrayList<>(Arrays.asList(filePath.split("/")));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dirsArray.size() - 1; i++) {
            stringBuilder.append(dirsArray.get(i));
        }
        String dirPath = stringBuilder.toString();
        Files.createDirectories(Paths.get(dirPath));
//        File directory = new File("./data");
//        if (! directory.exists()){
//            directory.mkdir();
//        }
        File f = new File(filePath); // create a File for the given file path
        f.createNewFile();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> userTasks = new ArrayList<>();
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskDetails = task.split(" \\| ");
            Task loadTask = new Task("err");
            switch(taskDetails[0]){
                case "T": {
                    loadTask = new ToDo(taskDetails[2]);
                    break;
                }
                case "D": {
                    loadTask = new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]));
                    break;
                }
                case "E": {
                    loadTask = new Event(taskDetails[2], LocalDate.parse(taskDetails[3]), LocalDate.parse(taskDetails[4]));
                    break;
                }
            }
            if (taskDetails[1].equals("1")) {
                loadTask.markTask();
            }
            userTasks.add(loadTask);
        }
        return userTasks;
    }
}
