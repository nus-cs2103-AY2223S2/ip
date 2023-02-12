package duke;
import duke.command.*;
import duke.task.*;
import java.io.*;
import java.util.*;

/**
 * handles the loading and storing onto external file
 */
public class Storage {
    private String filePath;
    private File save;
    private static ArrayList<Task> list;

    public Storage(String filePath) {
        this.list = new ArrayList<>();
        this.save = new File(filePath);
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        this.save = new File(filePath);
        if(save.exists()) {  
                try { 
                    BufferedReader reader = new BufferedReader(new FileReader(save));
                    String line = reader.readLine();
                    String[] parmArr = line.split("\\|");
                    List<String> parm = Arrays.asList(parmArr);
                    while (line != null) {
                        parmArr = line.split("\\|");
                        parm = Arrays.asList(parmArr);
                        CommandEnum command = CommandEnum.fromTag(parm.get(0));
                        boolean isMark = parm.get(1) == "1";
                        String description = parm.get(2);
                        switch (command) {
                        case TODO:
                            list.add(new Todo(description, isMark));
                            break;
                        case DEADLINE:
                            String date = parm.get(3);
                            list.add(new Deadline(description, date, isMark));
                            break;
                        case EVENT:
                            String from = parm.get(3);
                            String to = parm.get(4);
                            list.add(new Event(description, from, to, isMark));
                            break;
                        default:
                            break;
                        }
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (Exception e) {
                    throw new DukeException("save file is blank");
                }
                return this.list;

        } else {
                throw new DukeException("save file is blank");
            
        }
    }

    public void save(TaskList taskList) throws DukeException, IOException {
        this.list = taskList.getList();
        save.delete();
        File saver = new File("./duke.txt");
        FileWriter mySaveWriter = new FileWriter(saver, false);
        try {
            for (Task i: list) {
                String tag = i.getTag();
                String mark = i.getStatusIcon();
                if (mark == "X") {
                    mark = "1";
                } else {
                    mark = "0";
                }
                String description = i.getDescription();
                String date = i.getDate();
                mySaveWriter.write(tag + "|" + mark + "|" + description + "|" + date + "\n");
            }
            mySaveWriter.flush();
            mySaveWriter.close();
        } catch (Exception e) {
            throw new DukeException(e);
        }
    }
}
