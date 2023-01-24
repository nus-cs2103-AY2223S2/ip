import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class TaskFile {
    protected static File f = new File("data\\tasklist.txt");


    private void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File("data\\tasklist.txt");
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data\\tasklist.txt");
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data\\tasklist.txt", true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void createFile(TaskList nowList) {
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Scanner s = null;
            try {
                s = new Scanner(f);
                s.useDelimiter(System.getProperty("line.separator"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (s.hasNext()) {
                String str = s.nextLine();
                System.out.println(str.charAt(4));
                char todo = 'T';
                char event = 'E';
                char deadline = 'D';
                if(Character.compare(str.charAt(4),todo) == 0) {
                    String[] arrOfStr= str.split("] ", 2);
                    ToDoTask currentTask = new ToDoTask(arrOfStr[1]);
                    nowList.enq(currentTask);
                    
                } else if(Character.compare(str.charAt(4),event) == 0) {
                    String[] arrOfStr= str.split("] ", 2);
                    String[] arrOfStrStr= arrOfStr[1].split("\\(From: ", 2);
                    String[] arrOfStrStrStr= arrOfStrStr[1].split("To: ", 2);
                    String[] arrOfStrStrStrStr= arrOfStrStrStr[1].split("\\)", 2);
                    EventTask currentTask = new EventTask(arrOfStrStr[0],arrOfStrStrStr[0],arrOfStrStrStrStr[0]);
                    nowList.enq(currentTask);


                } else if(Character.compare(str.charAt(4),deadline) == 0) {
                    String[] arrOfStr= str.split("] ", 2);
                    String[] arrOfStrStr= arrOfStr[1].split("\\(Before: ", 2);
                    String[] arrOfStrStrStr= arrOfStrStr[1].split("\\)", 2);
                    DeadlineTask currentTask = new DeadlineTask(arrOfStrStr[0],arrOfStrStrStr[0]);
                    nowList.enq(currentTask);

                }
            }

        }

    }

    public static void main(String[] args) {
        File f = new File("data\\tasklist.txt");

        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }

}
