import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Save {

    public List<Task> loadTxtFile() throws IOException {
        List<Task> allTasks = new ArrayList<>();
        File file3 = new File("./data/");
        if (!file3.exists()) {
            file3.mkdir();
        }
        File file = new File("./data/duke.txt");
        if (file.exists()) {
            List<String> taskList = new ArrayList<>();
            FileReader file2 = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(file2);
            String taskInformation = bufferedReader.readLine();
            while (taskInformation != null) {
                taskList.add(taskInformation);
                taskInformation = bufferedReader.readLine();
            }
            for (int i = 0; i < taskList.size(); i++) {
                String task = taskList.get(i);
                String[] task1 = task.split(" / ");
                boolean taskStatus = false;
                if (task1[1].equals("[ ]")) {
                    taskStatus = false;
                } else if (task1[1].equals("[X]")){
                    taskStatus = true;
                }
                if (task.startsWith("T")) {
                    Todo todo = new Todo(i + 1, taskStatus, task1[2],
                            taskList.size());
                    allTasks.add(todo);
                } else if (task.startsWith("D")) {
                    Deadline deadline = new Deadline(i + 1, taskStatus, task1[2],
                            task1[3], taskList.size());
                    allTasks.add(deadline);
                } else if (task.startsWith("E")) {
                    String[] taskTiming = task1[3].split("-");
                    Event event = new Event(i + 1, taskStatus, task1[2],
                            taskTiming[0], taskTiming[1], taskList.size());
                    allTasks.add(event);
                }
            }
        }
        return allTasks;
    }

    public void saveListToFile(String command, Task task, List<Task> taskList) throws IOException {
        File file3 = new File("./data/");
        if (!file3.exists()) {
            file3.mkdir();
        }
        File file = new File("./data/duke.txt");
        FileWriter file1 = new FileWriter(file, true);
        BufferedWriter buffer = new BufferedWriter(file1);

        if (command.startsWith("todo")) {
            String content = "T / " + task.getTaskStatus() + " / " + task.getTask() + "\n";
            buffer.write(content);
        } else if (command.startsWith("deadline")) {
            String content = "D / " + task.getTaskStatus() + " / " +
                    task.getTask() + " / " + task.getDeadline() + "\n";
            buffer.write(content);
        } else if (command.startsWith("event")) {
            String content = "E / " + task.getTaskStatus() + " / " +
                    task.getTask() + " / " + task.getEventStartTime() + "-" +
                    task.getEventEndTime() + "\n";
            buffer.write(content);
        } else if (command.startsWith("mark")) {
            FileReader file2 = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(file2);
            String[] str = command.split(" ");
            int taskIndex = Integer.parseInt(str[1]);
            String unchangedTasks = "";
            for (int i = 1; i < taskIndex; i++) {
                unchangedTasks = unchangedTasks + bufferedReader.readLine() + "\n";
            }
            if (task.getTaskType().equals("[T]")) {
                unchangedTasks = unchangedTasks + "T / [X] / " + task.getTask() + "\n";
            } else if (task.getTaskType().equals("[D]")) {
                unchangedTasks = unchangedTasks + "D / [X] / " +
                        task.getTask() + " / " + task.getDeadline() + "\n";
            } else if (task.getTaskType().equals("[E]")) {
                unchangedTasks = unchangedTasks + "E / [X] / " +
                        task.getTask() + " / " + task.getEventStartTime() + "-" +
                        task.getEventEndTime() + "\n";
            }
            bufferedReader.readLine();
            for (int i = taskIndex + 1; i <= taskList.size(); i++) {
                unchangedTasks = unchangedTasks + bufferedReader.readLine() + "\n";
            }
            file.createNewFile();
            file1 = new FileWriter(file);
            buffer = new BufferedWriter(file1);
            buffer.write(unchangedTasks);
        } else if (command.startsWith("unmark")) {
            FileReader file2 = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(file2);
            String[] str = command.split(" ");
            int taskIndex = Integer.parseInt(str[1]);
            String unchangedTasks = "";
            for (int i = 1; i < taskIndex; i++) {
                unchangedTasks = unchangedTasks + bufferedReader.readLine() + "\n";
            }
            if (task.getTaskType().equals("[T]")) {
                unchangedTasks = unchangedTasks + "T / [ ] / " + task.getTask() + "\n";
            } else if (task.getTaskType().equals("[D]")) {
                unchangedTasks = unchangedTasks + "D / [ ] / " +
                        task.getTask() + " / " + task.getDeadline() + "\n";
            } else if (task.getTaskType().equals("[E]")) {
                unchangedTasks = unchangedTasks + "E / [ ] / " +
                        task.getTask() + " / " + task.getEventStartTime() + "-" +
                        task.getEventEndTime() + "\n";
            }
            bufferedReader.readLine();
            for (int i = taskIndex + 1; i <= taskList.size(); i++) {
                unchangedTasks = unchangedTasks + bufferedReader.readLine() + "\n";
            }
            file.createNewFile();
            file1 = new FileWriter(file);
            buffer = new BufferedWriter(file1);
            buffer.write(unchangedTasks);
        } else if (command.startsWith("delete")) {
            FileReader file2 = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(file2);
            String undeletedTasks = "";
            String[] str = command.split(" ");
            int taskIndex = Integer.parseInt(str[1]);
            for (int i = 1; i < taskIndex; i++) {
                undeletedTasks = undeletedTasks + bufferedReader.readLine() + "\n";
            }
            bufferedReader.readLine();
            for (int i = taskIndex; i <= taskList.size(); i++) {
                undeletedTasks = undeletedTasks + bufferedReader.readLine() + "\n";
            }
            file.createNewFile();
            file1 = new FileWriter(file);
            buffer = new BufferedWriter(file1);
            buffer.write(undeletedTasks);
        }
        buffer.close();
    }
}
