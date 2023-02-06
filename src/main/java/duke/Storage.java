package duke;

import java.io.*;

public class Storage {
    private String path;
    private Ui ui;
    public Storage(String path, Ui ui) {
        this.path = path;
        this.ui = ui;
    }

    public void saveList (DukeList dukeList, Ui ui) {
        try {
            FileOutputStream fos = new FileOutputStream("./data/Duke.Duke.DukeList.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dukeList);
            oos.flush();
            oos.close();
            fos.close();
            ui.addStatement("Sweet, seeya.");
        } catch (IOException e) {
            ui.addStatement("Hold on, something's wrong with your input");
        }

    }

    public DukeList retrieveList (Ui ui) {
        try {
            if ((new File("./data/Duke.Duke.DukeList.ser")).exists()) {
                FileInputStream fis = new FileInputStream("./data/Duke.Duke.DukeList.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    DukeList savedDukeList = (DukeList) ois.readObject();
                    savedDukeList.setUi(ui);
                    return savedDukeList;

                } catch (IOException e) {
                    ui.addStatement("Creating new save");
                }
            }

        } catch (FileNotFoundException e) {
            ui.addStatement("Hey sorry man, something's off bout you're save data.");
            ui.addStatement(e.toString());
        } catch (IOException e) {
            ui.addStatement("Yo something's up with your I/O, gotta get it checked before doing this.");
            ui.addStatement(e.toString());
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            ui.addStatement("Hey I can't find your object class.");
            ui.addStatement(e.toString());
        }
        return new DukeList(ui);
    }
}
