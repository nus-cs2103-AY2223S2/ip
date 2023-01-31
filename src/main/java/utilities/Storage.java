package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import command.Command;
import exceptions.SundayException;

public class Storage {

    private static String getFilepath() throws IOException {
        Path dataDir = Paths.get(System.getProperty("user.dir"), "data");
        if (!Files.exists(dataDir)) {
            Files.createDirectory(dataDir);
        }
        Path saveFilepath = Paths.get(dataDir.toString(), "sunday.txt");
        return saveFilepath.toString();
    }

    public static boolean createDataFile() throws SundayException {
        try {
            String filepath = getFilepath();
            File dataFile = new File(filepath);
            return dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new SundayException("ERROR: Unable to initialise data file");
        }
    }

    public static void readFromDataFile() throws SundayException {
        try {
            String filepath = getFilepath();
            File dataFile = new File(filepath);
            Scanner reader = new Scanner(dataFile);
            Ui.setDummyStream();

            int itemIndex = 1;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] strArr = data.split("]");

                switch (strArr[0].charAt(1)) {
                case 'T':
                    Command.TODO.execute(strArr[2]);
                    break;
                case 'D':
                    Command.DEADLINE.execute(strArr[2].substring(0, strArr[2].length() - 1));
                    break;
                case 'E':
                    Command.EVENT.execute(strArr[2].substring(0, strArr[2].length() - 1));
                    break;
                default:
                    throw new SundayException("ERROR 1: unable to read file");
                }

                switch (strArr[1].length()) {
                case 1:
                    Command.UNMARK.execute(" " + String.valueOf(itemIndex));
                    break;
                case 2:
                    Command.MARK.execute(" " + String.valueOf(itemIndex));
                    break;
                default:
                    throw new SundayException("ERROR 2: unable to read file");
                }
                itemIndex++;
            }
            Ui.setDefaultStream();
            reader.close();
        } catch (IOException e) {
            Ui.setDefaultStream();
            System.out.println(e.getMessage());
            throw new SundayException("ERROR: Unable to read from data file");
        }
    }

    public static boolean writeToDataFile(String line) throws SundayException {
        try {
            String filepath = getFilepath();
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(line);
            myWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new SundayException("ERROR: Unable to write to data file");
        }
    }
}
