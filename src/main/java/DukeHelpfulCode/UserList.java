package DukeHelpfulCode;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    /**
     * List used by userCmds 'add' and 'list'.
     * Methods:
     *      addToList()     -> appends Task to the list
     *      markAsDone(int i)    -> mark Task in i-th index as done.
     */
    private static String LINEBREAK = "_________________________________________________________________\n";

    public class TaskAlrMarkException extends Exception {
        /**
         * Thrown when the Task is already Done but user wants to mark it again and vice versa.
         */
        public TaskAlrMarkException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class TaskNotInListException extends Exception {
        /**
         * Thrown when Task is not in the userList.
         */
        public TaskNotInListException() {
            super("I can't seem to find this Task in the List.\n");
        }
    }

    List<Task> userList = new ArrayList<>();

    public String toString() {
        /**
         * Returns vertical list of Tasks that is 1-index
         */
        String result = "";
        for (int i = 0; i < this.userList.size(); i++){
            result += (i+1) +". " + this.userList.get(i).toString() + "\n";
        }
        return result;
    }

    public void addToDo(Todo td){
        if (userList.contains(td)){
            System.out.println(LINEBREAK + "Oops, it seems that you have already added this Task to your list!\n");
        } else {
            userList.add(td);
            System.out.println(LINEBREAK + "Got it. I've added this task: \n" + td.toString() + "\nNow you have " + this.len() + " tasks in your list.\n" + LINEBREAK);

        }
    }

    public void addDeadline(Deadline dl){
        if (userList.contains(dl)){
            System.out.println(LINEBREAK + "Oops, it seems that you have already added this Task to your list!\n");
        } else {
            userList.add(dl);
            System.out.println(LINEBREAK + "Got it. I've added this task: \n" + dl.toString() + "\nNow you have " + this.len() + " tasks in your list.\n" + LINEBREAK);
        }
    }

    public void addEvent(Event e){
        if (userList.contains(e)){
            System.out.println(LINEBREAK + "Oops, it seems that you have already added this Task to your list!\n");
        } else {
            userList.add(e);
            System.out.println(LINEBREAK + "Got it. I've added this task: \n" + e.toString() + "\nNow you have " + this.len() + " tasks in your list.\n" + LINEBREAK);
        }
    }

    public void addToList(Task task) {
        /**
         * Appends Task to userList.
         */
        if (!userList.contains(task)) {
            System.out.println(LINEBREAK + "Added: " + task.getName() + "\n" + LINEBREAK);
            userList.add(task);
        } else {
            System.out.println(LINEBREAK + "Oops, it seems that you have already added this Task to your list!\n");
        }
    }

    public void mark(String markCmd, int i) throws TaskNotInListException, TaskAlrMarkException {
        /**
         * Marks the i-1 th index Task as done if not done and vice versa.
         * Note: list is 0-index but displayed as 1-index, hence i would be 1-index.
         * If i > userList.size(), throw TaskNotInListException.
         * If Task done but "mark" and vice versa, throw TaskAlrMarkException.
         *
         * @param   markCmd     User's command to "mar" or "unmark" a Task as done or not done.
         * @param   i           The index of the Task to be marked. Note that user input i is 1-index, so -1 is needed.
         */
        if (i > userList.size()) {
            throw new TaskNotInListException();
        } else if (markCmd.equals("mark") && userList.get(i-1).isDone()) {
            throw new TaskAlrMarkException(LINEBREAK + "It appears that this Task has already been marked as Done. Would you like to mark it as Not Done instead?\n");
        } else if (markCmd.equals("unmark") && !userList.get(i-1).isDone()){
            throw new TaskAlrMarkException(LINEBREAK + "It appears that this Task has already been marked as Not Done. Would you like to mark it as Done instead?\n");
        } else {
            userList.get(i - 1).mark();
        }
    }

    public int len() {
        return userList.size();
    }

}