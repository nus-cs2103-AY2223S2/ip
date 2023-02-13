package duke;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> allTasks;

    /**
     * constructor of TaskList
     * Initialises allTasks too so that tasks can be stored
     */
    public TaskList(){
        allTasks =  new ArrayList<Task>();
    }

    /**
     * gets the number of tasks
     * @return the number of task in the allTasks
     */

    public int getNumberOfTask() {
        return allTasks.size();
    }

    /**
     * get the task of in the list and concat them into a string
     * @return the entire task in string form
     */
    public String printList() {
        String printedList = "";
        for (int i = 0; i < allTasks.size(); i++) {
            int index = i + 1;
            printedList = printedList.concat(index + ". "+ allTasks.get(i).toString()) + "\n";
        }

        if (allTasks.size() == 0) {
            return "Your list is currently empty :)";
        }

        return printedList;
    }

    /**
     * adds a task into the list
     * @param task is a Task object to be added into the list
     */
    public void addTask(Task task) {
        allTasks.add(task);
    }

    /**
     * goes to the index where the task is and calls mark() to mark the task
     * @param index of task to be mark
     */
    public void markTask(int index) {
        allTasks.get(index - 1).mark();
    }

    /**
     * goes to the index where the task is and calls unmark() to unmark the task
     * @param index of task to be mark
     */
    public void unmarkTask(int index) {
        allTasks.get(index - 1).unmark();
    }

    /**
     * goes to the index and remove task
     * @param index of the task to be deleted
     */
    public void deleteTask(int index, Ui ui) {
//        ui.printText(" " + allTasks.get(index-1).toString());
        allTasks.remove(index-1);
    }

    /**
     * get the allTasks variable
     * @return allTasks
     */
    public ArrayList<Task> getTasks() {
        return allTasks;
    }

    /**
     * finds tasks that contains the word
     * @param word to find tasks
     * @return string of all the tasks that contains word
     */
    public String findTaskWithWord (String word) {
        String tasksWithWord = "Here are the matching tasks in your list:\n";
        int index = 0;
        for (int i = 0; i < allTasks.size(); i++) {
            if (allTasks.get(i).getItem().contains(word)) {
                index++;
                tasksWithWord = tasksWithWord.concat(i + ". "+ allTasks.get(i).toString()) + "\n";
            }
        }

        if (index == 0) {
            return "Unable to find task with the word: " + word;
        }

        return tasksWithWord;
    }


    /**
     * Adds note to the task
     * @param index of the task to add the note
     * @param note is the message to be added
     * @return the task string
     */
    public String addNoteToTask(int index, String note) {
        Task selectedTask = allTasks.get(index);
        selectedTask.addNote(note);

        return selectedTask.toString();
    }

    public String viewAllTasksWithNotes() {
        String output = "";
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);

            if (!task.getIsNoteBlank()) {
                int index = i + 1;
                output = output + index + ". " + task + "\nNotes:\n" + task.getNote() + "\n\n";
            }
        }
        return output;
    }

    public String viewSingleTaskWithNote(int index) {
        Task task = allTasks.get(index);
        if (task.getIsNoteBlank()) {
            return "Task does not contain a note yet!";
        } else {
            return task + "\n\nNotes:\n" + task.getNote();
        }
    }
}
