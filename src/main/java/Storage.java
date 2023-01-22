import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {

    public ArrayList<Task> loadTaskList() {
        File dukeSave = new File("data/duke.txt");
        ArrayList<Task> toLoad = new ArrayList<>();
        if (dukeSave.exists()) {
            try {
                Scanner fileScan = new Scanner(dukeSave);
                while (fileScan.hasNextLine()) {
                    String toEval = fileScan.nextLine();
                    String[] evalArray = toEval.split("=");
                    switch (evalArray[0]) {
                        case ("T"):
                            ToDo addToDo = new ToDo(evalArray[2]);
                            if (evalArray[1].equals("X")) {
                                addToDo.MarkDone();
                            }
                            toLoad.add(addToDo);
                            break;
                        case ("D"):
                            Deadline addDeadline = new Deadline(evalArray[2], evalArray[3]);
                            if (evalArray[1].equals("X")) {
                                addDeadline.MarkDone();
                            }
                            toLoad.add(addDeadline);
                            break;
                        case("E"):
                            Event addEvent = new Event(evalArray[2], evalArray[3], evalArray[4]);
                            if (evalArray[1].equals("X")) {
                                addEvent.MarkDone();
                            }
                            toLoad.add(addEvent);
                            break;
                    }
                }
            }
            catch (FileNotFoundException err) {
                return toLoad;
            }
        }
        return toLoad;
    }

    public void saveTaskList(ArrayList<Task> toSave) throws IOException {
        FileWriter dukeWrite = new FileWriter("data/duke.txt");
        for (Task task : toSave) {
            dukeWrite.write(task.toSaveString() + "\n");
        }
        dukeWrite.close();
    }

}
