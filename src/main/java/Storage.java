import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

    }

    public ArrayList<Task> loadData()  {
        ArrayList<Task> list = new ArrayList<>();
        File data = new File(this.filePath);
        try {
            Scanner fileSc = new Scanner(data);
            while(fileSc.hasNextLine()) {
                String fileData = fileSc.nextLine();
                String[] taskData = fileData.split("\\|");
                list.add(readData(taskData));
            }
        } catch(FileNotFoundException e) {
            this.createFile();
        } catch (DukeException e) {
            System.out.println(e);
        }

        return list;
    }

    public void createFile() {
        File dir = new File("./data");
        File newFile = new File(this.filePath);
        dir.mkdir();
        try{
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Task readData(String[] taskData) throws DukeDataException {
        String taskType = taskData[0];
        String taskStatus = taskData[1];
        String taskInfo = taskData[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Task loadTask = null;

        if(taskType.equals("T")) {
            loadTask = new Todo(taskInfo);
        } else if (taskType.equals("D")) {
            LocalDateTime taskTime = LocalDateTime.parse(taskData[3].trim(), formatter);
            loadTask = new Deadlines(taskInfo, taskTime);
        } else if (taskType.equals("E")){
            LocalDateTime taskFrom = LocalDateTime.parse(taskData[3].trim(), formatter);
            LocalDateTime taskTo = LocalDateTime.parse(taskData[4].trim(), formatter);
            loadTask = new Event(taskInfo, taskFrom, taskTo);
        } else {
            throw new DukeDataException();
        }

        if(taskStatus.equals("1")){
            loadTask.changeStatus();
        }
        return loadTask;
    }

    public void storeData(TaskList list) throws IOException{
        FileWriter writer = new FileWriter(this.filePath, false);
        BufferedWriter buffer = new BufferedWriter(writer);
        for(int i = 1; i <= list.listLength(); i++) {
            buffer.write(list.getTask(i).writeFile());
            buffer.newLine();
        }
        buffer.close();
        writer.close();
    }



}
