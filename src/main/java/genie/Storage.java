package genie;

import genie.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String PATH = "./data/duke.txt";
    private static FileWriter fw;
    private ArrayList<String> loadedTasks;
    public Storage() {
        this.loadedTasks = new ArrayList<>();
    }
    //@author mandykqh-reused
    //Reused from https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then
    // -create-the-files-in-that-direct
    //with minor modifications
    public TaskList loadData() throws IOException {
        File directory = new File("./data");
        if (!directory.exists()){
            directory.mkdir();
        }
        File file = new File(PATH);
        if (file.createNewFile()) {
            System.out.println("Seems like you're new here. Welcome onboard and let's get started! ^-^");
            return new TaskList();
        } else {
            return readTextFileToList(file);
        }
    }
    //@author
    public TaskList readTextFileToList(File f) throws IOException {
        Scanner fs = new Scanner(f);
        TaskList tasks = new TaskList();
        while (fs.hasNext()) {
            String strTask = fs.nextLine();
            char taskLetter = strTask.charAt(1);
            loadedTasks.add(strTask);
            switch (taskLetter) {
            case 'T':
                tasks.addToDoFromFile(strTask);
                break;
            case 'E':
                tasks.addEventFromFile(strTask);
                break;
            case 'D':
                tasks.addDeadlineFromFile(strTask);
                break;
            }
        }
        saveListToFile(tasks.getTasks()); //TODO save by each task as stopping program/bugs wipes all data
        return tasks;
    }
    /*
    public void saveTaskToFile(genie.task.Task t) throws IOException {
        fw.write(t.toString());
        fw.write("\n");
    }
    */
    public void closeFileWriter() throws IOException {
        fw.close();
    }
    public void saveListToFile(ArrayList<Task> list) throws IOException {
            fw = new FileWriter(PATH);
            for(Task t : list) {
                String formatted = t.toFileFormat(); // format: [X][X] xxx | <from> - <by>
                fw.write(formatted);
                fw.write("\n");
            }
    }
    public ArrayList<String> getLoadedTaskList() {
        return this.loadedTasks;
    }
}
