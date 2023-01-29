package command;

import tasks.TaskList;
import tasks.Task;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

public class Find extends Command{
    private String keyword;
    private TaskList taskList;
    public Find(String keyword, TaskList taskList){
        this.keyword = keyword;
        this.taskList = taskList;
    }
    public void execute(){

    }
    public boolean isExit(){
       return false;
    }

    public String searchForTasks(){
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
