import java.io.*;
import java.util.Scanner;

public class DataHandler {

    private File dataFile;

    DataHandler(String fileLocation) {

        dataFile = new File(fileLocation);
        try {
            dataFile.createNewFile();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addLine(String line) {
        try {
            FileWriter fw = new FileWriter(dataFile, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(line);
            pw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void deleteLine(int lineNum) {
        try {
            int lineCounter = 0;
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
            dataString = dataString.substring(0, dataString.length() - 1);
            FileWriter fw = new FileWriter(dataFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(dataString);
            pw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String[] getData() {
        String outputString = "";
        try {
            Scanner fileScan = new Scanner(dataFile);

            while (fileScan.hasNext()) {
                outputString += fileScan.nextLine() + "\n";
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return outputString.split("\n");
    }

    public void editTaskStatus(int taskNum, boolean isDone){
        String newMark = (isDone ? "X" : " ");
        try {
            int lineCounter = 0;
            Scanner fileScan = new Scanner(dataFile);
            String dataString = "";
            while (fileScan.hasNext()) {
                if (lineCounter != taskNum) {
                    dataString += fileScan.nextLine();
                } else {
                    String s = fileScan.nextLine();
                    dataString += s.substring(0,4) + newMark
                            + s.substring(5,s.length());
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
            System.out.println(e);
        }
    }

}
