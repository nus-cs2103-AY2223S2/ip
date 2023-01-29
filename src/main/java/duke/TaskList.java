package duke;

import duke.task.*;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> storage;


    public TaskList(List<String> lines){
        this.storage = new ArrayList<>(100);
        if (lines.size() != 0){
            fillStorage(lines);
        }
    }

    private void fillStorage(List<String> lines){
        for (String line: lines){
            String[] arguments = line.split (" | ");
            switch(arguments[1]){
                case "T":
                    storeTodoTask(arguments);
                    break;
                case "D":
                    storeDeadlineTask(arguments);
                    break;
                case "E":
                    storeEventTask(arguments);
                    break;
            }
        }
    }

    private void storeTodoTask(String[] args){
        Task addTask = new ToDo(args[3].trim());
        if (args[2].contains("X")){
            addTask.markAsDone();
        }

        storage.add(addTask);
    }

    private void storeDeadlineTask(String[] args){
        String desc =  args[3].substring(0, args[3].indexOf("(by:")).trim();
        String deadline = args[3].substring(args[3].indexOf("(by:") +
                    "(by:".length(), args[3].indexOf(")")).trim();

        Task addTask = new Deadline(desc, deadline);
        if (args[2].contains("X")){
            addTask.markAsDone();
        }

        storage.add(addTask);
    }


    private void storeEventTask(String[] args){
        String desc = args[3].substring(0, args[3].indexOf("(from:")).trim();
        String from = args[3].substring(args[3].indexOf("(from:") +
                "(from:".length(), args[3].indexOf("to:")).trim();
        String to = args[3].substring(args[3].indexOf("to:") +
                "to:".length(), args[3].indexOf(")")).trim();

        Task addTask = new Events(desc, from, to);
        if (args[2].contains("X")){
            addTask.markAsDone();
        }

        storage.add(addTask);
    }

    public void generate(){
        System.out.println("_____________________________________\n");
        for (Task task : storage){
            System.out.println(storage.indexOf(task) + " | " + task.toString() + "\n");
        }
        System.out.println("_____________________________________\n");
    }


    public void add(Task task) throws DukeException{
        try {
            storage.add(task);
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("Invalid Index given!");
        }
    }

    public void remove(int index) throws DukeException{
        try {
            storage.remove(index);
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("Invalid Index given!");
        }
    }

    public void markTask(int index) throws DukeException{
        try {
            storage.get(index).markAsDone();
            System.out.println("_____________________________________\n" +
                    "Nice! I've marked this task as done:\n" +
                    storage.get(index).toString() + "\n" +
                    "_____________________________________\n");
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("Invalid Index given!");
        }
    }

    public void unMarkTask(int index) throws DukeException{
        try {
            storage.get(index).unMark();
            System.out.println("_____________________________________\n" +
                    "Ok. I've marked this task as not done yet:\n" +
                    storage.get(index).toString() + "\n" +
                    "_____________________________________\n");
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("Invalid Index given!");
        }
    }

    public ArrayList<Task> loadTaskList(){
        return this.storage;
    }

    public String taskCount(){
        if (this.storage.size() == 1){
            return "1 task ";
        } else {
            return this.storage.size() + " tasks ";
        }
    }
}
