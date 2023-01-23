import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String directory;
    protected String filePath;

    public Storage(String directory, String filePath){
        this.directory = directory;
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException{
        ArrayList<Task> al = new ArrayList<>();
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // make a file
        String FULL_FILE = directory  + File.separator + filePath;
        File file = new File(FULL_FILE);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            al = readFile(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return al;
    }

    public static ArrayList<Task> readFile(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|");
                String task = split[0];
                String mark = split[1];
                switch (task) {
                    case "T":
                        Todo todo = new Todo(split[2]);
                        if (mark.equals("1")) {
                            todo.setAsMarked();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(split[2], split[3]);
                        if (mark.equals("1")) {
                            deadline.setAsMarked();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        String[] s = split[3].split("-");
                        String from = s[0].trim();
                        String to = s[1].trim();
                        Event event = new Event(split[2], from, to);
                        if (mark.equals("1")) {
                            event.setAsMarked();
                        }
                        tasks.add(event);
                        break;
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    //overwrites and rewrite to the text file
    public void writeFile(TaskList taskList) {
        try{
            String FULL_FILE = directory  + File.separator + filePath;
            File newFile = new File(FULL_FILE);
            FileWriter fw = new FileWriter(newFile);
            for(int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                String done = task.getStatusIcon().equals("X") ? "1" : "0";
                String write = "|" + done + "|" + task.getDescription();
                if (task instanceof Todo) {
                    write = "T" + write;
                } else if (task instanceof Deadline) {
                    write = "D" + write + "|" + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    write = "E" + write + "|" + ((Event) task).getTime();
                }
                fw.write(write);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e ) {
            System.out.println(e.getMessage());
        }
    }
}