package duke;

import java.io.*;

public class Storage {
    private String path;
    public Storage(String path) {
        this.path = path;
    }

    public void saveList (DukeList dukeList) {
        try {
            FileOutputStream fos = new FileOutputStream("./data/Duke.Duke.DukeList.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dukeList);
            oos.flush();
            oos.close();
            fos.close();
            System.out.println(new TextBorder("Sweet, seeya."));
        } catch (IOException e) {
            System.out.println("Hold on, something's wrong with your input");
        }

    }

    public DukeList retrieveList () {
        try {
            if ((new File("./data/Duke.Duke.DukeList.ser")).exists()) {
                FileInputStream fis = new FileInputStream("./data/Duke.Duke.DukeList.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    return (DukeList) ois.readObject();

                } catch (IOException e) {
                    System.out.println("Creating new save");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Hey sorry man, something's off bout you're save data.");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Yo something's up with your I/O, gotta get it checked before doing this.");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Hey I can't find your object class.");
            System.out.println(e);
        }
        return new DukeList();
    }
}
