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

/**
 * Handles local storage of data.
 *
 * @author Fun Leon
 */
public class Storage {

    /** File where the data is stored locally */
    private File dataFile;

    /**
     * Constructs a storage instance that handles local saved data.
     *
     * @param fileLocation Relative path to the data file.
     */
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
                String s = fileScan.nextLine();
                if (lineCounter != lineNum) {
                    dataString += s;
                    dataString += "\n";
                }
                lineCounter++;
            }
            dataString = dataString.length() == 0 ? dataString
                    : dataString.substring(0, dataString.length() - 1);
            FileWriter fw = new FileWriter(dataFile);
            PrintWriter pw = new PrintWriter(fw);
            if (dataString.length() != 0) {
                pw.println(dataString);
            } else {
                pw.print("");
            }
            pw.close();
        } catch (IOException e) {
            throw new DataFileInteractionException(e.getMessage());
        }
    }

    public void markTask(int taskNum) throws DataFileInteractionException {
        final char mark = 'X';
        final int doneStatusPosition = 2;
        editFileChar(taskNum - 1, doneStatusPosition, mark);
    }

    public void unmarkTask(int taskNum) throws DataFileInteractionException {
        final char unmark = ' ';
        final int doneStatusPosition = 2;
        editFileChar(taskNum - 1, doneStatusPosition, unmark);
    }

    public void editFileChar(int line, int charPosition, char newChar)
            throws DataFileInteractionException {
        try {
            int lineCounter = 0;
            Scanner fileScan = new Scanner(dataFile);
            String dataString = "";
            while (fileScan.hasNext()) {
                if (lineCounter != line) {
                    dataString += fileScan.nextLine();
                } else {
                    String s = fileScan.nextLine();
                    dataString += s.substring(0, charPosition) + newChar
                            + s.substring(charPosition + 1, s.length());
                }
                dataString += "\n";
                lineCounter++;
            }
            // remove last newline character
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
