import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskList {
    private int index;
    private Task[] tasks;
    private String filename;

    public TaskList() {
        this.index = 0;
        this.tasks = new Task[100];
        try {
            this.filename = "data.txt";
            File file = new File(this.filename);
            boolean flag = file.createNewFile();
            if (!flag) {
                try {
                    Scanner scanner = new Scanner(file);
                    String data;
                    while (scanner.hasNextLine()) {
                        data = scanner.nextLine();
                        int len = data.length();
                        String command = data.substring(0, len - 1);
                        String marked = data.substring(len - 1);
                        Task task = Task.makeTask(command);
                        this.addTask(task);
                        if (marked.equals("1")) {
                            task.mark();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Oops!! There was a problem reading past data. Try rebooting me again!");
                }
            }
        } catch (IOException e) {
            System.out.println("Oops!! There was a problem loading past data. Try rebooting me again!");
        }
    }

    public String addTask(Task task) {
        this.tasks[this.index] = task;
        String str = "Got it! This task has been added:\n";
        str += this.printTask(index);
        this.index++;
        String sp = this.index == 1 ? "task" : "tasks";
        str += "You now have " + this.index + " " + sp + " in the list.\n";
        this.save();
        return str;
    }

    public String deleteTask(String deleteIndex) {
        int index = Integer.parseInt(deleteIndex) - 1;
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("Task does not exist!\n");
        }
        String str = "Got it! This task has been removed:\n";
        str += this.printTask(index);
        this.index--;
        String sp = this.index == 1 ? "task" : "tasks";
        str += "You now have " + this.index + " " + sp + " in the list.\n";
        for (int i = index ; i < 99 ; i++) {
            this.tasks[i] = this.tasks[i + 1];
        }
        this.tasks[99] = null;
        this.save();
        return str;
    }

    public String markTask(String markIndex) {
        int index = Integer.parseInt(markIndex) - 1;
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("Task does not exist!\n");
        }
        this.tasks[index].mark();
        String str = "Great job! This task has been marked as done:\n";
        str += printTask(index);
        this.save();
        return str;
    }

    public String unMarkTask(String unmarkIndex) {
        int index = Integer.parseInt(unmarkIndex) - 1;
        if (index < 0 || index > 99 || tasks[index] == null) {
            throw new RuntimeException("Task does not exist!\n");
        }
        this.tasks[index].unMark();
        String str = "Noted! This task has been marked as undone:\n";
        str += printTask(index);
        this.save();
        return str;
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(this.filename);
            for (int i = 0 ; i < this.index ; i++) {
                writer.write(tasks[i].taskToSavedForm() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops!! There was a problem saving this task and changes made to it. Please try again.");
        }
    }

    public String printTask(int index) {
        return tasks[index].toString();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.index; i++) {
            str += (i + 1) + ". " + this.printTask(i);
        }
        return str;
    }
}
