package fideline.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import fideline.exception.DataFileInteractionException;
import fideline.exception.DataFileNotFoundException;
import fideline.exception.UnableToCreateDataFileException;

public class Storage {

    private File dataFile;

    public Storage(String fileLocation) {
        dataFile = new File(fileLocation);
    }

    public String load() throws DataFileNotFoundException {
        String outputString = "";
        try {
            Scanner fileScan = new Scanner(dataFile);
            while (fileScan.hasNext()) {
                outputString += fileScan.nextLine() + "\n";
            }
            return outputString;
        } catch (FileNotFoundException e) {
            throw new DataFileNotFoundException();
        }
    }

    public void createDataFile() throws UnableToCreateDataFileException {
        try {
            dataFile.createNewFile();
        } catch (Exception e) {
            throw new UnableToCreateDataFileException(e.getMessage());
        }
    }

    public void addLine(String line) throws DataFileInteractionException {
        try {
            FileWriter fw = new FileWriter(dataFile, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(line);
            pw.close();
        } catch (IOException e) {
            throw new DataFileInteractionException(e.getMessage());
        }

    }

    public void deleteLine(int lineNum) throws DataFileInteractionException {
        try {
            int lineCounter = 1;
            Scanner fileScan = new Scanner(dataFile);
            String dataString = "";
            while (fileScan.hasNext()) {
                if (lineCounter != lineNum) {
                    dataString += fileScan.nextLine();
                    dataString += "\n";
                } else {
                    fileScan.nextLine();
                }
                lineCounter++;
            }
            dataString = dataString.length() == 0 ? dataString
                    : dataString.substring(0, dataString.length() - 1);
            FileWriter fw = new FileWriter(dataFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(dataString);
            pw.close();
        } catch (IOException e) {
            throw new DataFileInteractionException(e.getMessage());
        }
    }


    public void editTaskStatus(int taskNum, boolean isDone) throws DataFileInteractionException {
        String newMark = (isDone ? "X" : " ");
        try {
            int lineCounter = 1;
            Scanner fileScan = new Scanner(dataFile);
            String dataString = "";
            while (fileScan.hasNext()) {
                if (lineCounter != taskNum) {
                    dataString += fileScan.nextLine();
                } else {
                    String s = fileScan.nextLine();
                    dataString += s.substring(0, 2) + newMark
                            + s.substring(3, s.length());
                }
                dataString += "\n";
                lineCounter++;
            }
            dataString = dataString.substring(0, dataString.length() - 1);
            FileWriter fw = new FileWriter(dataFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(dataString);
            pw.close();
        } catch (IOException e) {
            throw new DataFileInteractionException(e.getMessage());
        }
    }

}
