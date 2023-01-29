package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(File file) {
        this.taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String cur = s.nextLine();

                String[] line = cur.split("\\|");

                switch(line[0].trim()) {
                    case "T":
                        taskList.add(new Todo(line[2].trim()));
                        if (line[1].equals("1")) {
                            taskList.get(taskList.size()-1).markAsDone();
                        }
                        break;
                    case "D":
                        LocalDate time = LocalDate.parse(line[3].trim());
                        taskList.add(new Deadline(line[2].trim()+" ", time));
                        if (line[1].trim().equals("1")) {
                            taskList.get(taskList.size()-1).markAsDone();
                        }
                        break;
                    case "E":
                        taskList.add(new Event(line[2].trim() + " ", line[3].trim()));
                        if (line[1].trim().equals("1")) {
                            taskList.get(taskList.size()-1).markAsDone();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            Ui.FileExceptionUi();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Task delete(int index) {
        Task removed = taskList.remove(index);
        return removed;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }


    public Task mark(int index) {
        Task marked = taskList.get(index);
        marked.markAsDone();
        return marked;
    }

    public Task unmark(int index) {
        Task unmarked = taskList.get(index);
        unmarked.markAsUnDone();
        return unmarked;
    }

    public int getLength() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

}
