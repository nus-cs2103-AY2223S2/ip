import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataHandler {
    public static boolean createDataFile() throws SundayException{
        try {
            File dataFile = new File("data/sunday.txt");
            if (dataFile.createNewFile()) {
                Printer.printBar();
                Printer.printText("It appears we haven't met!");
                Printer.printText("Start typing away your tasks and I'll note them down accordingly :)");
                Printer.printBar();
                return true;
            }
        } catch (IOException e) {
            Printer.printText(e.getMessage());
            throw new SundayException("ERROR: Unable to initialise data file");
        }
        Printer.printBar();
        Printer.printText("It appears we've met! I've restored your task list from our last session.");
        Printer.printBar();
        return false;
    }
    public static void readFromDataFile() throws SundayException{
        try {
            File dataFile = new File("data/sunday.txt");
            Scanner reader = new Scanner(dataFile);
            Printer.setDummyStream();
            int itemIndex = 1;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] strArr = data.split("]");

                switch (strArr[0].charAt(1)) {
                case 'T':
                    State.TODO.execute(strArr[2]);
                    break;
                case 'D':
                    State.DEADLINE.execute(strArr[2].substring(0, strArr[2].length() - 1));
                    break;
                case 'E':
                    State.EVENT.execute(strArr[2].substring(0, strArr[2].length() - 1));
                    break;
                default:
                    throw new SundayException("ERROR 1: unable to read file");
                }

                switch (strArr[1].length()) {
                case 1:
                    State.UNMARK.execute(" " + String.valueOf(itemIndex));
                    break;
                case 2:
                    State.MARK.execute(" " + String.valueOf(itemIndex));
                    break;
                default:
                    throw new SundayException("ERROR 2: unable to read file");
                }
                itemIndex++;
            }
            Printer.setDefaultStream();
            reader.close();
        } catch (FileNotFoundException e) {
            Printer.setDefaultStream();
            throw new SundayException("ERROR: Incorrect data file directory");
        }
    }
    public static void writeToDataFile(String line) {
        try {
            FileWriter myWriter = new FileWriter("data/sunday.txt");
            myWriter.write(line);
            myWriter.close();
            Printer.printBar();
            Printer.printText("I'll save your list for the next session!");
            Printer.printBar();
        } catch (IOException e) {
            Printer.printException(new SundayException("ERROR: Unable to write to data file"));
        }
    }
}