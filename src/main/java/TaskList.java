import items.Deadline;
import items.Event;
import items.Task;
import items.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final File storage;
    private ArrayList<Task> taskList;

    public TaskList(String filePath) {
        this.storage = new File(filePath);
        try {
            refreshTaskList();
        } catch (FileNotFoundException e) {
            System.err.println("Data file could not be found :(");
            e.printStackTrace();
        }
    }

    public void delete(int index){
        Task deleted = this.taskList.remove(index - 1);
        try {
            refreshStorage();
            System.out.println("I have deleted the following task: \n" + deleted.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Seems like something went wrong with the storage file... \n " +
                    "deleted task has been added to back of task list");
            this.taskList.add(deleted);
        }
    }

    public void enumerate(){
        for (int i = 0; i < this.taskList.size(); i++){
            System.out.println((i + 1) + ":" + this.taskList.get(i).toString());
        }
    }

    public void addTask(Task newTask){
        this.taskList.add(newTask);
        try {
            refreshStorage();
            System.out.println("added:\n" + newTask.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Seems like something went wrong with the storage file... \n " +
                    "Please try again later");
            this.taskList.remove(newTask);
        }
    }

    public void markTask(int index){
        Task mark = this.taskList.get(index - 1);
        mark.setDone();
        try {
            refreshStorage();
            System.out.println("I have set the following task to done: \n" + mark.toString());
        } catch (IOException e) {
            System.out.println("Seems like something went wrong with the storage file... \n " +
                    "Please try again later");
            e.printStackTrace();
            mark.setNotDone();
        }
    }

    public void unmarkTask(int index){
        Task unmark = this.taskList.get(index - 1);
        unmark.setNotDone();
        try {
            refreshStorage();
            System.out.println("I have set the following task to not done: \n" + unmark.toString());
        } catch (IOException e) {
            System.out.println("Seems like something went wrong with the storage file... \n " +
                    "Please try again later");
            e.printStackTrace();
            unmark.setDone();
        }
    }

    private void refreshStorage() throws IOException{
        FileWriter clearFile = new FileWriter(this.storage, false);
        clearFile.close();
        FileWriter writer = new FileWriter(this.storage);
        for (int i = 0; i < this.taskList.size(); i++){
            writer.write(this.taskList.get(i).generateStorageForm() + System.lineSeparator());
        }
        writer.close();
    }

    private void refreshTaskList() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.storage);
        this.taskList = new ArrayList<>();

        while(scanner.hasNextLine()){
            String[] parsedInput = scanner.nextLine().split("@");
//            for (int i = 0; i < parsedInput.length; i ++) {
//                System.out.println(i + parsedInput[i]);
//            }
            boolean isDone = parsedInput[2].equals("X");
//            System.out.println(isDone);
            switch (parsedInput[0]){
                case "T":
                    this.taskList.add(new ToDo(parsedInput[1], isDone));
                    break;
                case "D":
                    this.taskList.add(new Deadline(parsedInput[1], isDone, LocalDate.parse(parsedInput[3])));
                    break;
                case "E":
                    this.taskList.add(new Event(parsedInput[1], isDone, parsedInput[3], parsedInput[4]));
                    break;
            }
        }
        scanner.close();
    }





}
