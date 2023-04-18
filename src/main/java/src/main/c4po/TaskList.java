package src.main.c4po;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class TaskList {

    protected ArrayList<Task> taskList;
    protected PriorityQueue<Task> taskListByPriority;

    private HashMap<String,ArrayList<Task>> keywordMap;

    private Integer taskCount = 0;

    /**
     * Creates new instance of Task List, with non-empty input.
     * @param taskListInput
     */
    public TaskList(ArrayList<Task> taskListInput) {
        this.taskList = new ArrayList<>();
        this.keywordMap = new HashMap<>();
        this.taskListByPriority = new PriorityQueue<>();
        for (Task task: taskListInput) {
            this.addItem(task);
        }
        this.taskCount = taskList.size();
    }

    /**
     * Returns a copy of the tasklist priority queue, sorted by priority
     * @return an ArrayListTask sorted
     */
    protected ArrayList<Task> getPrioritySortedTasks() {
        PriorityQueue<Task> newPQ = new PriorityQueue<>();
        ArrayList<Task> sortedPriorityTasks = new ArrayList<>();
        while (!this.taskListByPriority.isEmpty()) {
            Task task = this.taskListByPriority.poll();
            sortedPriorityTasks.add(task);
            newPQ.add(task);
        }
        this.taskListByPriority = newPQ;

        return sortedPriorityTasks;
    }






    /**
     * Creates new instance of Task List, with empty input.
     * clean tasklist and clean keyword map
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.keywordMap = new HashMap<>();
        this.taskListByPriority = new PriorityQueue<>();
    }

    /**
     * Finds tasks with descriptions containing the keywords
     * @param keyword a String which represents the keyword to find
     * Prints the list of found tasks using TaskList.printList()
     */
    public ArrayList<Task> findTask(String keyword) {
        //return tasksFound or ui print
        return keywordMap.get(keyword);
    }

    /**
     * Useful for debugging, shows the keys in the keyword map
     */
    public void showTaskMapKeys() {
        for (String item :  keywordMap.keySet()) {
            Ui.print(item);
        }
    }

    /**
     * Decreases count of tasks
     */
    private void decrementTaskCount() {
        taskCount -= 1;
    }

    /**
     * Adds task to the taskList
     * @param task is a task object
     */
    public void addItem(Task task) {
//        System.out.println("Adding Task");
//        System.out.println(taskListByPriority);
        this.taskList.add(task);
        this.taskListByPriority.add(task);
        this.taskCount += 1;

        //Calls getDescInArray to get keywords to add,
        //then add task to the relevant arraylists
        addItemToKeywordMap(task.getDescInArray(), task);
    }


    /**
     * Adds task to the relevant keywords' list of tasks
     */
    private void addItemToKeywordMap(ArrayList<String> descKeywords, Task task) {
        for (String keyword: descKeywords) {
            if (keywordMap.containsKey(keyword)) {
                //contains key already
                keywordMap.get(keyword).add(task);
            } else {
                ArrayList<Task> newTaskArr = new ArrayList<>();
                newTaskArr.add(task);
                keywordMap.put(keyword, newTaskArr);
            }
        }
    }

    /**
     * Returns item at specified position
     * @param index is position specified - 1
     * @return
     */
    public Task getItem(Integer index) throws BotException {
        assert(index > 0) : "index must be larger than 0";
        try {
            Task res = taskList.get(index - 1);
            return res;
        } catch (Exception e) {
            throw new BotException("No such task");
        }

    }

    /**
     * Deletes an item from the task list with actual index = paramindex - 1
     * @param toDelete is a Task which you want to delete
     * @return
     */
    public boolean deleteItem(Task toDelete) throws BotException {
        try {

            this.removeFromKeywords(toDelete);
            this.taskList.remove(toDelete);
            this.taskListByPriority.remove(toDelete);
            decrementTaskCount();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            throw new BotException("Task may not exist");
        }
    }

    public void removeFromKeywords(Task taskToDelete) {
        for (String keyword :taskToDelete.getDescInArray()) {
            keywordMap.get(keyword).remove(taskToDelete);
        }
    }


    /**
     * Returns String of all tasks, line-byRec-line formatted
     * for writing to file
     * @return
     */
    public String getListFileFormat() {
        StringBuilder build = new StringBuilder();
        for (Task task: taskList) {
            build.append(task.getTaskFileFormat());
            build.append("\n");
        }
        return build.toString();
    }

    /**
     * Marks the task as done or undone
     * @param index specifies which task to toggle mark
     * @param markOption specifies to unmark or mark task
     * @return
     */
    public boolean mark(Integer index, String markOption) {
        assert(index > 0) : "index must be larger than 0";
        if (index > taskList.size()) {
            return false;
        }
        Task task = taskList.get(index - 1);
        if (markOption.equalsIgnoreCase("mark")) {
            task.markDone();
        } else if (markOption.equalsIgnoreCase("unmark")){
            task.markUndone();
        }
        return true;
    }



    /**Gets the size of the task list, in a String.
     * @return String that details size of task list
     */
    public String getTaskCount() {
        return "Now you have " + taskCount.toString() + " tasks in the list.";
    }

    /**
     * Prints all the Tasks in the list with or without index n.o as per specification.
     * This is useful for creating multiple task lists and printing them out together without
     * having a mess of repeated numbering.
     * @param toShowIndex determines if index number in list is printed
     */
    public void printList(boolean toShowIndex) {
        if (taskList.size() == 0) {
            Ui.showNothingInList();
        } else {
            int i = 1;
            if (toShowIndex) {
                Ui.showListPre();
                for (Task task: taskList) {
                    String listInline = task.getTaskInline(i);
                    i++;
                    System.out.println(listInline);
                }
            } else {
                for (Task task: taskList) {
                    String listInline = task.getTaskInline();
                    System.out.println(listInline);
                }
            }
        }
    }





    /**
     * Prints all the Tasks in the list with or without index n.o as per specification.
     * This is useful for creating multiple task lists and printing them out together without
     * having a mess of repeated numbering.
     * @param toShowIndex determines if index number in list is printed
     */
    public String printList(boolean toShowIndex, boolean isStringOutput) {
        StringBuilder printListOutput = new StringBuilder();
        if (taskList.size() == 0) {
            printListOutput.append(Ui.showNothingInList(true));
        } else {
            int i = 1;
            if (toShowIndex) {
                printListOutput.append(Ui.showListPre(true)).append("\n");

                for (Task task: taskList) {
                    String listInline = task.getTaskInline(i);
                    i++;
                    printListOutput.append(listInline).append("\n");
                }
            } else {
                for (Task task: taskList) {
                    String listInline = task.getTaskInline();
                    printListOutput.append(listInline).append("\n");
                }
            }
        }
        return printListOutput.toString();
    }






}
