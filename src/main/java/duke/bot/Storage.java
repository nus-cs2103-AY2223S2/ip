package duke.bot;

import duke.taskmanager.Deadline;
import duke.taskmanager.Event;
import duke.taskmanager.Tasks;
import duke.taskmanager.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String path;
    public Storage(String str){
        this.path = str;
    }
    public List<Tasks> load() throws DukeException {
        List<Tasks> tasksList = new ArrayList<>();
        try {
            File f = new File("bot/data/tasks.txt");
            Scanner sc = new Scanner(f);
            if(sc.hasNextLine()){sc.nextLine();}
            while(sc.hasNextLine()) {
                String taskDesc = sc.nextLine();
                String[] taskArr = taskDesc.split(" ∵ ");
                switch (taskArr[0]) {
                    case "[T]":
                        Tasks t = new ToDo("todo " + taskArr[2]);
                        tasksList.add(t);
                        if (taskArr[1].equals("[X]")) {
                            t.mark();
                        }
                        break;
                    case "[D]":
                        Tasks d = new Deadline("deadline " + taskArr[2]);
                        tasksList.add(d);
                        if (taskArr[1].equals("[X]")) {
                            d.mark();
                        }
                        break;
                    case "[E]":
                        Tasks e = new Event("deadline " + taskArr[2]);
                        if (taskArr[1].equals("[X]")) {
                            e.mark();
                        }
                        break;
                    default:
                        throw new DukeException();
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            new File(path, "tasks.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tasksList;
    }


}
