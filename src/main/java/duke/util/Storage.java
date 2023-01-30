package duke.util;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import duke.util.TaskList;

public class Storage {
    public static void saveProgress(TaskList taskList) {
        File savedFile = new File("MY_GRAND_PLAN.txt");
        System.out.println("[X] FILE CREATED");
        try {
            FileWriter myWriter = new FileWriter("MY_GRAND_PLAN.txt", true);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task currenttask = taskList.getTaskAtIndex(i);
                if(currenttask.isDone) {
                    myWriter.write(currenttask.nature + " | " + "X" + " | " + currenttask.action + currenttask.getAdditionalInfo() + '\n');
                } else {
                    myWriter.write(currenttask.nature + " | " + " " + " | " + currenttask.action + currenttask.getAdditionalInfo() + '\n');
                }
            }
            myWriter.close();
            System.out.println("[X] FINISHED WRITING");
        } catch (IOException e) {
            System.out.println("BEE BOO BOOP...");
        }
    }
    public static TaskList loadProgress(String link) {
        return new TaskList();
    }
}