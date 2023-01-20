import java.io.*;

/**
 * Class dedicated to read existing tasks created in the previous Sebastian sessions.
 */
public class DataReader {
    /**
     * Load data from hard disk and convert the String representation into actual tasks.
     * @return a TaskList containing all the tasks.
     */
    public static TaskList formTaskListFromData(){
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
        } catch (IOException e) {
            return null;
        }
    }
}
