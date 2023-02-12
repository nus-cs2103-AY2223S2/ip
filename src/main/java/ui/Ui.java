package ui;

import tasks.Task;
import tasks.TaskList;

import java.util.Arrays;
import java.util.List;

public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public void welcomeMsg(){
        System.out.println(logo + "Hello I am duke.Duke\nWhat can I do for you?");
    }
    public void errMsg(String error){
        System.out.println(error);
    }
    public void showLine(){
        System.out.println("___________________");
    }

    /**
     * Return message for mark task
     * @param task mark task
     * @return String message about mark task
     */
    public String markTaskMsg(Task task){
        return "Nice I have marked this task as done:\n" + task.toString();
    }
    /**
     * Return message for unmark task
     * @param task unmark task
     * @return String message about unmark task
     */
    public String unmarkTaskMsg(Task task) {
        return "Ok I have marked as not done:\n" + task.toString();
    }

    /**
     * Returns a message about the newly added task
     * @param task Task object to be added
     * @return String message about task
     */
    public String addTaskMsg(Task task, int count){
        assert count > - 1 : "Number of tasks cannot be negative";
        return "Got it, I have added this task:\n" + task.toString() + "\n" +
                "Now you have " + count + " tasks in the list.";
    }

    /**
     * Return exit message
     * @return String message about exiting
     */
    public String byeMsg(){
        return "Goodbye";
    }

    public String deleteTaskMsg(Task task, int count){
        assert count > -1 : "Number of tasks cannot be negative";
        return "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + count + " tasks in the list";
    }

    public String listTasksMsg(TaskList taskList){
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++){
            list += (i + 1) + "." + taskList.get(i).toString() + "\n";
        }
        return list;
    }

    public String findMsg(String keyword, TaskList taskList){
        String result = "Here are the matching tasks in your list:\n";
        int count = 0;
        for (int i = 0; i < taskList.getSize(); i++){
            Task currTask = taskList.get(i);
            String desc = currTask.getDescription();
            List wordList = Arrays.asList(desc.split(" "));
            if (wordList.contains(keyword)){
                count++;
                result += (count) + "." + currTask.toString() + "\n";
            }
        }
        return result;
    }
}
