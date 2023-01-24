package util;

import java.io.*;
import java.util.Scanner;

public class WriteToFile {
    private static final String FILEPATH = "src/main/java/data/UserTasks.txt";
    public static void appendToFile(String textToAppend) {
        try{
            File file = new File(FILEPATH);
            if(!file.isFile() && !file.isDirectory()) {
                System.out.println("File or folder not found!");
                System.out.println("Please create the file or folder.");
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textToAppend);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
//            System.out.println("IO Error Occurred");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();

        appendToFile(str);
    }

}
