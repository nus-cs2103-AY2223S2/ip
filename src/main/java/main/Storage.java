package main;

import formatters.Formatter;
import sebastianExceptions.CannotLoadDataException;
import sebastianExceptions.CannotWriteDataException;
import sebastianExceptions.DeadlineFormatMismatchException;
import sebastianExceptions.EventFormatMismatchException;

import java.io.*;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Load data from hard disk and convert the String representation into actual tasks.
     * @return a main.TaskList containing all the tasks.
     */
    public TaskList formTaskListFromData() throws CannotLoadDataException{
        TaskList taskList = new TaskList();
        File file = new File("SebastianData.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String curLine;
            while((curLine = br.readLine()) != null) {
                String[] curTask = curLine.split("<>");
                String taskType = curTask[0];
                int isCompleted = Integer.parseInt(curTask[1]);
                String taskDescription = curTask[2];
                switch (taskType) {
                    case "T":
                        taskList.addTodo(isCompleted, taskDescription);
                        break;
                    case "D":
                        taskList.addDeadline(isCompleted,taskDescription, curTask[3]);
                        break;
                    case "E":
                        taskList.addEvent(isCompleted, taskDescription, curTask[3], curTask[4]);
                        break;
                }
            }
            return taskList;
        } catch (IOException | DeadlineFormatMismatchException | EventFormatMismatchException e) {
            throw new CannotLoadDataException();
        }
    }

    /**
     * Save tasks in the taskList into the hard disk.
     * @param taskList a TaskList object
     */
    public void writeToDisk(TaskList taskList) throws CannotWriteDataException{
        if(file == null){
            file = new File("SebastianData.txt");
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(taskList.formatTaskListForSave());
            fw.flush();
        } catch (IOException e) {
            throw new CannotWriteDataException();
        } finally {
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
