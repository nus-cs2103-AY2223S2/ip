import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {

    public TaskList loadTaskList() {
        File dukeSave = new File("data/duke.txt");
        TaskList toLoad = new TaskList();
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

    public void saveTaskList(TaskList toSave) throws IOException {
        FileWriter dukeWrite = new FileWriter("data/duke.txt");
        for (int i = 0; i < toSave.size(); i++) {
            dukeWrite.write(toSave.get(i).toSaveString() + "\n");
        }
        dukeWrite.close();
    }

}
