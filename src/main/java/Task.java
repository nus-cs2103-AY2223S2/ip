import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    protected boolean isDone;

    protected static ArrayList<Task> taskList = new ArrayList<>();

    protected static Integer listSize = 0; //or just increment accordingly

    protected static String delimiter = Pattern.quote(" | ");
    public Integer index;

    //Constructor
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.index = listSize + 1;
        //add to taskList
        taskList.add(this);
        listSize++;
    }

    public static void decrementTaskCount() {
        listSize -= 1;
    }


    /**
     * Appends to end of file, content. Does not override old content
     * @param content
     * @throws IOException
     */
    public static void writeToFile(String content) throws IOException {
        FileWriter fw = new FileWriter(C4PO.fileP, true);
        fw.write(content);
        fw.close();
    }

    /**
     * Writes all tasks to the file, overwriting the old data
     * @throws IOException
     */
    public static void writeToFile() throws IOException {
        StringBuilder build = new StringBuilder();
        for (Task task: taskList) {
            build.append(task.getTaskFileFormat());
            build.append("\n");
        }
        FileWriter fw = new FileWriter(C4PO.fileP);
        fw.write(build.toString());
        fw.close();
    }

    protected String getTaskFileFormat() {
        String statusIcon = this.getStatusIcon();
        String isDone = statusIcon.equals("X") ? "1" : "0";
        return isDone + " | " + this.description;
    }

    /**
     * Loads file data from filePath and creates task objects from line read
     * @param fileP
     * @throws FileNotFoundException
     */
    public static void loadFromFile(String fileP) throws FileNotFoundException {
        File f = new File(fileP); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        //For each line, demultiplex line by line
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineItems = line.split(Task.delimiter);
            String tag = lineItems[0];
            String isDone = lineItems[1];
            boolean isDoneBool = isDone.equals("1");
            String desc = lineItems[2];
            switch (tag) {
            case "D":
                String by = lineItems[3];
                new Deadline(desc, by, isDoneBool);
                break;
            case "E":
                String start = lineItems[3];
                String end = lineItems[4];
                new Event(desc, start, end, isDoneBool);
                break;
            case "T":
                new ToDo(desc, isDoneBool);
                break;
            default:
                System.out.println("Error creating task");
                break;
            }
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }



    public static String mark(Integer index, String markOption) {
        if (index > taskList.size() || index < 0) {
            return "No such item exists in list";
        }
        Task task = taskList.get(index - 1);
        if (markOption.equalsIgnoreCase("mark")) {
            task.markDone();
        } else if (markOption.equalsIgnoreCase("unmark")){
            task.markUndone();
        }
        return task.getTaskInline();
    }

    /**
     * Deletes Task object at index specified and decrements task count
     * @param index
     * @return
     */
    public static String delete(Integer index) {
        if (index > taskList.size() || index < 0 ) {
            return "No such item exists in list";
        }
        Task task = taskList.get(index - 1);
        Task.taskList.remove(index - 1);
        decrementTaskCount();
        String deletedTaskDesc = task.getTaskInline();
        return deletedTaskDesc;
    }

    //Mark as done
    public void markDone() {
        this.isDone = true;
    }

    //Mark undone
    public void markUndone() {
        this.isDone = false;
    }




    //Get inline print of task description with specified index
    public String getTaskInline(Integer index) {
        return index.toString() + ". " + this.getTaskInline();
    }

    public String getTaskInline() {
        String statusIcon = this.getStatusIcon();
        return "[" + statusIcon + "] " + this.description;
    }

    public String toString() {
        return this.description;
    }

    /**Gets the size of the task list, in a String.
     * @return String that details size of task list
     */
    public static String getTaskCount() {
        return "Now you have " + Task.listSize.toString() + " tasks in the list.";
    }

    /**
     * Prints all the Tasks in the list
     */
    public static void printList() {
        if (taskList.size() == 0) {
            System.out.println("Master, there seems to be nothing in your list! Goodness me! Has my circuitry failed me again?!");
        } else {
            System.out.println("Master, here are the items in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Integer val = i+1;
                String listInline = taskList.get(i).getTaskInline(val);

                System.out.println(listInline);
            }
        }
    }

}
