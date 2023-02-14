package duke.backend;

import java.io.File;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

class Storage {
    private File prevTasks;

    public Storage(File prevTasks) {
        this.prevTasks = prevTasks;
    }

    public ArrayList<Task> extractTasks() throws IOException {
        Scanner fileSc;
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            fileSc = new Scanner(prevTasks);
            while (fileSc.hasNextLine()) {
                String[] savedData = fileSc.nextLine()
                        .split(" \\| ");
                Task t = new Todo("");
                switch (savedData[0]) {
                case "T":
                    t = new Todo(savedData[2]);
                    break;
                case "D":
                    t = new Deadline(savedData[2], savedData[3]);
                    break;
                case "E":
                    t = new Event(savedData[2], savedData[3], savedData[4]);
                    break;
                default:
                }
                if (savedData[1].equals("0")) {
                    t.unmark();
                } else {
                    t.mark();
                }
                tasks.add(t);
            }
            fileSc.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Unable to retrieve saved tasks!");
            //  Previous task file doesn't exist,
            prevTasks.createNewFile();
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        String encoding = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            switch (t.getClass().getSimpleName()) { //encoding: "E | NAME | FROM | TO"
            case "Todo":
                encoding = encoding + ("T | " + (t.isDone() ? "1" : "0") + " | ");
                encoding = encoding + t.getDescription();
                break;
            case "Deadline":
                encoding = encoding + ("D | " + (t.isDone() ? "1" : "0") + " | ");
                encoding = encoding + t.getDescription() + " | " + ((Deadline) t).getDeadline();
                break;
            case "Event":
                encoding = encoding + ("E | " + (t.isDone() ? "1" : "0" + " | "));
                encoding = encoding + (t.getDescription() + " | " + ((Event) t).getStart() + " | "
                        + ((Event) t).getEnd());
                break;
            default:
            }
            encoding = encoding + "\n";
        }
        try {
            FileWriter fw = new FileWriter(prevTasks);
            System.out.println("saving the following tasks: \n" + encoding);
            fw.write(encoding);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("Sorry, unable to save your tasks right now.");
        }
    }
}
