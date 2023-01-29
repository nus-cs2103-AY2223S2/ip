package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Task {
    protected String taskName;
    protected boolean completed = false;
    protected String taskType = "T";
    protected static int numTasks = 0;
    protected boolean deleted = false;

    public Task(String taskName, boolean completed) {
        this.taskName = taskName;
        this.completed = completed;
        numTasks++;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        numTasks++;
    }

    public void setCompletion(boolean completion) {
        this.completed = completion;
        if (completion) {
            System.out.println("    Nice! I've marked this task as done:\n" + "     "+ this);
        } else {
            System.out.println("    OK, I've marked this task as not done yet:\n" + "       " + this);
        }
    }

    public String displayType() {
        return String.format("[%s]", this.taskType);
    }

    public String displayMark() {
        if (this.completed) {
            return "X";
        }
        return " ";
    }


    public static void saveTaskData(Task t, int isAppend) {
        String PATH = "src/main/data";
        File directory = new File("src/main/data/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter fw = new FileWriter("src/main/data/seedu.duke.txt", true);
            String description = String.format("%s|%d|%s\n", t.taskType, isAppend, t.taskName);
            fw.write(description);
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save information into the file");
        }
    }

    public String saveTaskString() {
        return String.format("%s|%s|%b", this.taskType, this.taskName, this.completed);
    }

    @Override
    public String toString() {
        return String.format("  %s[%s] %s", this.displayType(), this.displayMark(), this.taskName);
    }
}
