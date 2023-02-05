package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class for Ui object.
 * This class handles the interface that the user interacts with.
 * 
 * @author Bryan Tan
 */
public class Ui {
    private final String intro = "Hi! I'm Duke! :)\nHow may I help?";
    private final String outro = "Goodbye!";
    private TaskList tList;
    private Storage savedList;

    /**
     * Initialises the user interface.
     * 
     * @throws IOException when file path is invalid.
     */
    public void initialise() throws IOException {
        System.out.println();
        System.out.println();
        System.out.println(this.intro);
        System.out.println();
        Storage saved = new Storage("./dukeSaved.txt");
        this.savedList = saved;
        this.tList = new TaskList();

        if(saved.isSaved()) {
            this.tList.setList(saved.load());
            if(this.tList.size() > 0) {
                System.out.println("Duke: Previously saved list available!");
            } else {
                System.out.println("Duke: No previously saved list found.");
            }
        } else {
            System.out.println("Duke: No previously saved list found.");
        }
    }

    /**
     * Saves list of tasks in current session to hard drive.
     * 
     * @throws IOException when file to write to is not found.
     */
    public void save() throws IOException {
        savedList.save(this.tList.getList());
    }

    public String getIntro() {
        return this.intro;
    }

    public String getOutro() {
        return this.outro;
    }

    /**
     * Prints goodbye messgae.
     */
    public void end() {
        System.out.print("Duke: ");
        System.out.println(this.outro);
    }

    public ArrayList<Task> getList() {
        return this.tList.getList();
    }

    /**
     * Resets the interface to a state ready for user input.
     */
    public void reset() {
        System.out.println();
        System.out.print("User: ");
    }
    
    /**
     * Adds a task to the current list of tasks.
     * 
     * @param t Task to be added.
     */
    public void addToList(Task t) {
        this.tList.add(t);
        System.out.print("Duke: ");
        System.out.println("added " + t.getTask() + "!");
        System.out.println("Now you have " + tList.size() + " tasks in the list.");
        reset();
    }

    /**
     * Handles the exception when access or manipulation of an empty is attempted.
     */
    public void emptyErr() {
        System.out.println("Duke: Error!! List empty! Try again.");
        reset();
    }

    /**
     * Prints out all tasks created so far.
     */
    public void viewList() {
        System.out.println("Here's your list of tasks:");
        System.out.println();
        for(int i  = 0; i < this.tList.size(); i++) {
        System.out.println(i+1 + ". " + this.tList.get(i));
        }
        reset();
    }

    /**
     * Marks specified task as done and informs user.
     * 
     * @param num Index no. of task in the list to be marked as done
     */
    public void markTask(int num) {
        if(num >= this.tList.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            this.tList.get(num).mark();
            System.out.println("Duke: Nice It's marked!");
            System.out.println(this.tList.get(num));
        }
        reset();
    }

    /**
     * Unmarks specified task as undone and informs user.
     * 
     * @param num Index no. of task in the list to be marked as undone.
     */
    public void unmarkTask(int num) {
        if(num >= this.tList.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            this.tList.get(num).unmark();
            System.out.println("Duke: Ok! It's unmarked!");
            System.out.println(this.tList.get(num));
        }
        reset();
    }

    /**
     * Creates a ToDo object.
     * 
     * @param task String array containing descriptions of the task.
     * @return ToDo object.
     */
    public ToDo makeToDo(String[] task) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < task.length; i++) {
            sb.append(task[i] + " ");
        }
        return new ToDo(sb.toString());
    }

    /**
     * Creates Event object.
     * 
     * @param task String array containing descriptions of the event.
     * @return Event object.
     * @throws DateTimeParseException if user inputs date and time in the wrong format.
     */
    public Event makeEvent(String[] task) throws DateTimeParseException {
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        StringBuilder desc = new StringBuilder();
        boolean isDescripton = true;
        boolean first = false;
        boolean second = false;

        for(int i = 1; i < task.length; i++) {
            if(task[i].equalsIgnoreCase("/from")) {
                first = true;
                isDescripton = false;
                continue;
            }
            if(task[i].equalsIgnoreCase("/to")) {
                second = true;
                first = false;
                continue;
            }
            if(first) {
                start.append(task[i] + " ");
            }
            if(second) {
                end.append(task[i] + " ");
            }
            if(isDescripton) {
                desc.append(task[i] + " ");
            }
        }
        return new Event(desc.toString(), start.toString(), end.toString());
    }

    /**
     * Creates a task with given deadline.
     * 
     * @param task String array containing descriptions of the event.
     * @return Deadline object.
     * @throws DateTimeParseException if user inputs date and time in the wrong format.
     */
    public Deadline makeDeadline(String[] task) throws DateTimeParseException {
        StringBuilder desc = new StringBuilder();
        StringBuilder by = new StringBuilder();
        boolean isDesc = true;

        for(int i = 1; i < task.length; i++) {
            if(task[i].equalsIgnoreCase("/by")) {
                isDesc = false;
                continue;
            }
            if(isDesc) {
                desc.append(task[i] + " ");
            } else {
                by.append(task[i]);
                if (i != task.length - 1) {
                    by.append(" ");
                }
            }
        }
        return new Deadline(desc.toString(), by.toString());
    }

    /**
     * Deletes a specified task.
     * 
     * @param num Index no. of task in the list to be deleted.
     */
    public void delete(int num) {
        if(num >= this.tList.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            System.out.println("Duke: Ok! Following task is removed: ");
            System.out.println(this.tList.get(num));
            this.tList.remove(num);
        }
        reset();
    }

    /**
     * Prints list of tasks containing specified keyword.
     * 
     * @param keyword keyword to search with.
     */
    public void find(String keyword) {
        int counter = 1;
        boolean printedHeader = false;
        boolean hasMatch = false;

        for (int i = 0; i < this.tList.size(); i++) {
            Task curr = this.tList.get(i);
            if (curr.getTask().contains(keyword)) {
                hasMatch = true;

                if (!printedHeader) {
                    System.out.println();
                    System.out.println("Duke: Here's your list of tasks relating to " + "\"" + keyword + "\"" + ":");
                    printedHeader = true;
                }
                System.out.println(counter + ") " + curr.toString());
                counter++;
            }
        }

        if (!hasMatch) {
            System.out.println("Duke: Sorry! No matching tasks found. Please try again.");
        }

        reset();
    }

    public void clearList() {
        ArrayList<Task> newEmptyList = new ArrayList<>();
        this.tList.setList(newEmptyList);
        System.out.println("Duke: Ok! All tasks deleted.");
        reset();
    }
}
