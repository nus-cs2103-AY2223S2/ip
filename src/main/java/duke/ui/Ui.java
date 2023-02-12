package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {
    /** Output for UI */
    private String output;

    /**
     * Constructs Ui class.
     */
    public Ui() {
        this.output = "";
    }

    /**
     * Store output.
     */
    public void storeOutput(String output){
        if (this.output.isEmpty()) {
            this.output += output;
        } else {
            this.output += "\n" + output;

        }
    }

    /**
     * Displays output.
     */
    public String displayOutput(){
        String temp = output;
        output = "";
        return temp;
    }

    /**
     * Displays loading error when tasks cannot be loaded.
     */
    public void showLoadingError() {
        storeOutput("Error: Unable to load tasks from file.");
    }

    /**
     * Displays Ui line.
     */
    public void showLine() {
        storeOutput("__________________________________________________________");
    }

    /**
     * Displays message to acknowledge task added.
     */
    public void showAdded(Task t) {
        storeOutput("Got it. I've added this task:\n\t  " + t);
    }

    /**
     * Displays message including size of list.
     */
    public void showListSize(TaskList tasks) {
        if (tasks.size() == 1) {
            storeOutput("Now you have " + tasks.size() + " task in the list.");
        } else {
            storeOutput("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    /**
     * Displays all tasks in the list.
     */
    public void showList(TaskList tasks) {
        storeOutput("Here are the tasks in your list:\n" + tasks);
    }

    /**
     * Displays message to acknowledge task marked.
     */
    public void showMarked(Task t) {
        storeOutput("Nice! I've marked this task as done:\n\t  " + t);
    }

    /**
     * Displays message to acknowledge task unmarked.
     */
    public void showUnmarked(Task t) {
        storeOutput("OK, I've marked this task as not done yet:\n\t  " + t);
    }

    /**
     * Displays message to acknowledge task deleted.
     */
    public void showDeleted(Task t) {
        storeOutput("Noted. I've removed this task:\n\t  " + t);
    }

    /**
     * Displays tasks filtered by keywords.
     */
    public void showFilteredByKeywords(String[] keywords, TaskList filteredTasks) {
        int count = filteredTasks.size();
        String keywordsMessage = "";
        for (int i = 0; i < keywords.length; i++) {
            String keyword = keywords[i];
            if (i == keywords.length - 1) {
                keywordsMessage += "'" + keyword + "'";
            } else {
                keywordsMessage += "'" + keyword + "'" + ", ";
            }
        }
        storeOutput("Number of tasks with " + keywordsMessage + ": " + count + "\n" + filteredTasks);
    }

    /**
     * Displays tasks filtered by dates.
     */
    public void showFilteredByDates(LocalDate[] dates, TaskList filteredTasks) {
        int count = filteredTasks.size();
        String datesMessage = "";
        for (int i = 0; i < dates.length; i++) {
            LocalDate date = dates[i];
            if (i == dates.length - 1) {
                datesMessage += date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } else {
                datesMessage += date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", ";
            }
        }
        storeOutput("Number of tasks on " + datesMessage + ": " + count + "\n" + filteredTasks);
    }

    /**
     * Displays error message if any error occurs.
     */
    public void showError(String errorMessage) {
        storeOutput("WOOF!!! " + errorMessage);
    }

    /**
     * Displays help guide.
     */
    public void showHelp() {
        String helpGuide = "Here's what I can do for you...\n\ntodo <description>\ndeadline <description> /by <date/time>\nevent " +
                "<description> /from <date/time> /to <date/time>\nlist\nmark <number>\nunmark <number>\ndelete <number>\n" +
                "filter <keyword>\nfilterdate <date>\nsort\nsortdate\nsorttask\nsortdone";
        storeOutput(helpGuide);
    }

    /**
     * Displays exit message.
     */
    public void showExit() {
        storeOutput("Didn't I do it for you...");
    }
}
