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


/**
 * Storage class for storing saved file path and loading saved file
 */
public class Storage {
    String path;


    /*Constructor for Storage object containing saved file path */
    public Storage(String str){
        this.path = str;
    }

    /*Returns List<Tasks> loaded from saved file path*/
    public List<Tasks> load() throws DukeException {
        List<Tasks> tasksList = new ArrayList<>();
        try {
            File f = new File(path);
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

                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException();
        } catch (Exception ignored) {

        }
        return tasksList;
    }


}
