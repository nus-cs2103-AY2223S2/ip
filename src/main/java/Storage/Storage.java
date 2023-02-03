package Storage;

import TaskList.TaskList;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import TaskList.TaskList;
import Task.Task;
import Task.ToDo;
import Task.Deadline;
import Task.Event;

public class Storage {
    String path;
    File file;
    PrintWriter output;
    Scanner sc;
    public Storage(String path) throws IOException {
        this.path = path;
        this.file = new File(path);
        this.sc = new Scanner(file);
        boolean test = this.sc.hasNextLine(); // for some reason this
        this.output = new PrintWriter(path);
    }

    public TaskList load() throws IOException {
        if (!file.exists()) {
            System.out.println("cannot find file");
            file.createNewFile();
        }
        TaskList list = new TaskList();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] descriptionAndMarkedStatus = this.getDescriptionAndMarkedStatus(line);
            String description = descriptionAndMarkedStatus[0];
            String markedStatus = descriptionAndMarkedStatus[1];
            boolean marked = markedStatus.equals("X") ? true : false;
            char letter = line.charAt(3);
            Task task;
            if (letter == 'T') {
                task = new ToDo(description, marked);
            } else if (letter == 'D') {
                String[] words = description.split("\\|by: ");
                task = new Deadline(words[0], words[1], marked);
            } else {
                String[] words = description.split("\\|from: ");
                String[] fromTo = words[1].split(" to: ");
                task = new Event(words[0], fromTo[0], fromTo[1], marked);
            }
            list.addTask(task);
        }
        return list;
    }



    public String[] getDescriptionAndMarkedStatus(String description) {
        String[] words = description.split("] ");
        String newDescription = words[1];
        String firstWord = words[0];
        String marked = firstWord.charAt(firstWord.length()-1) + "";
        return new String[]{newDescription, marked};
    }

    public void save(TaskList list) {
        for (int i = 0; i < list.size(); i++) {
            Task task = list.getTask(i);
            this.output.println(i+1 + "." + task.toString());
        }
        this.output.close();
    }
}
