package duke.command;

import java.io.IOException;

import duke.exception.InvalidIndexException;
import duke.exception.MissingContentException;
import duke.task.Task;

/**
 * Handles data and formats into tasklist
 */
public class TaskList {
    private String[] arr;

    public TaskList() {
        arr = new String[100];
    }

    /**
     * Initialize a new TaskList object
     * @param arr
     */
    public TaskList(String[] arr) {
        this.arr = arr;
    }

    /**
     * Returns the tasklist in array
     * @return the tasklist in array
     * @throws IOException if array does not exist
     */
    public String[] readTaskList() throws IOException {
        try {
            try {
                return this.arr;
            } catch (IndexOutOfBoundsException e) {
                throw new MissingContentException();
            }
        } catch (MissingContentException e) {
            System.out.println(e.getMessage());
        }
        return this.arr;
    }

    /**
     * Lists tasks inside tasklist.
     *
     * @print task one-by-one.
     */
    public void list() {
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == null) {
                break;
            }
            System.out.println(String.format("%d.%s", j + 1, arr[j]));
        }
    }

    /**
     * Returns the real length of the array.
     * Maximum index in which array at such index is not null.
     *
     * @return the first null index.
     */
    public int validLen() {
        int len = 0;
        while (arr[len] != null) {
            len++;
        }
        return len;
    }

    /**
     * Returns new task list.
     * Marks task at given index as done.
     *
     * @param num index at which task need to be marked as done.
     * @return new task list with task marked.
     */
    public TaskList mark(int num) throws IOException {
        try {
            try {
                if (arr[num] != null) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    String original = arr[num];
                    arr[num] = new Task(String.valueOf(original.charAt(1)),
                            original.substring(7), true).toString();
                    System.out.println(arr[num]);
                }
                return new TaskList(arr);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
        } catch (InvalidIndexException e) {
            System.out.println(e.getMessage());
        }
        return new TaskList(arr);
    }

    /**
     * Returns new task list.
     * Unmarks task at given index as done.
     *
     * @param num1 index at which task need to be marked as undone.
     * @return new task list with task unmarked.
     * @throw InvalidIndexException if array at specific index is null
     */
    public TaskList unmark(int num1) throws IOException {
        try {
            try {
                if (arr[num1] != null) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    String original = arr[num1];
                    Task newTask = new Task(String.valueOf(original.charAt(1)),
                            original.substring(7), false);
                    arr[num1] = newTask.toString();
                    System.out.println(arr[num1]);

                    return new TaskList(arr);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
        } catch (InvalidIndexException e) {
            System.out.println(e.getMessage());
        }
        return new TaskList(arr);
    }

    /**
     * Checks if element at given index of array is valid or not.
     *
     * @param index at which content need to be checked.
     * @return boolean for content validity.
     * @throw InvalidIndexException if array at specific index is null
     */
    public boolean checkValidIndex(int index) {
        return (arr[index] != null);
    }

    /**
     * Returns new task list.
     * Deletes task at given index.
     *
     * @param num1 index at which task need to be deleted.
     * @return new task list with task deleted.
     * @throw InvalidIndexException if array at specific index is null if array at specific index is null
     */
    public TaskList delete(int num1) throws IOException {
        try {
            try {
                if (arr[num1] != null) {
                    System.out.println("Noted. I've removed this task:");
                    String original = arr[num1];
                    System.out.println(original);
                    int trace = num1;
                    String[] originalList = new String[100];
                    for (int k = 0; k < 100; k++) {
                        originalList[k] = arr[k];
                    }

                    arr[trace] = arr[trace + 1];
                    trace++;

                    while ((trace >= 1) && (originalList[trace - 1] != null)) {
                        arr[trace] = originalList[trace + 1];
                        trace++;
                    }
                }
                return new TaskList(arr);
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException();
            }
        } catch (InvalidIndexException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }


    /**
     * Returns new task list.
     * Adds task to the back.
     *
     * @param task needed to be added.
     * @return new task list with task added.
     */
    public TaskList add(Task task) {
        int len = this.validLen();
        arr[len] = task.toString();
        System.out.println(task.toString());
        return new TaskList(arr);
    }

    /**
     * Prints out tasks that contain the given keyword.
     *
     * @param keyWord given keyword.
     * @print tasks that contain the given keyword in task list.
     */
    public void findWord(String keyWord) {
        int trace = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            } else if (arr[i].contains(keyWord)) {
                trace++;
                System.out.println(String.format("%d. %s", trace, arr[i]));
            }
        }
    }

    /**
     * Checks if there is any task that contains the given keyword.
     *
     * @param keyWord given keyword.
     * @return true if there is any task contains the keyword and false otherwise.
     */
    public boolean checkWord(String keyWord) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            } else if (arr[i].contains(keyWord)) {
                return true;
            }
        }
        return false;
    }
}
