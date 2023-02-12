package duke;

import exception.UnknownFileTypeException;

import java.io.*;

public class Storage {
    private String listPath;
    private String archivePath;
    private Ui ui;
    public Storage(String listPath, String archivePath, Ui ui) {
        this.listPath = listPath;
        this.archivePath = archivePath;
        this.ui = ui;
    }

    public String getFilePath (String fileType) throws UnknownFileTypeException {
        switch (fileType) {
            case "list" :
                return listPath;
            case "archive" :
                return archivePath;
            default:
                throw new UnknownFileTypeException("Not sure what file you want");
        }
    }

    public void save(DukeList dukeList, String fileType) {
        try {
            String path = getFilePath(fileType);

            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dukeList);
            oos.flush();
            oos.close();
            fos.close();
            ui.addStatement("Sweet, seeya.");
            assert ui.getStatements().size() > 0 : "Number of statements in Ui should be more than 0";
        } catch (IOException e) {
            ui.addStatement("Hold on, something's wrong with your input");
        } catch (UnknownFileTypeException e) {
            ui.addStatement(e.getMessage());
        }

    }

    public DukeList retrieve(String fileType) {

        try {
            String path = getFilePath(fileType);

            if ((new File(path)).exists()) {
                FileInputStream fis = new FileInputStream(path);
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
        } catch (UnknownFileTypeException e) {
            ui.addStatement(e.getMessage());
        }
        return new DukeList(ui);
    }
}
