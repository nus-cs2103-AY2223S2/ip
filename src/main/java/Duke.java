import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        try {
            Ui.doGreeting();

            Scanner sc = new Scanner(System.in);
            String textDir = System.getProperty("user.dir")+"/duke.txt";
            File file = new File(textDir);
            TaskList tasks = new TaskList();
            PrintWriter pw = new PrintWriter(new FileWriter(textDir, true));

            Storage.loadData(textDir, file, tasks);
            Parser.handleInputs(sc, tasks, pw);
            Storage.saveData(pw, textDir, tasks);
            Ui.doFarewell();
        }
            catch(IOException e){
                e.printStackTrace();
        }
    }

    public static String formatStr(String str) {
        String returnstr =  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
                            + str + "\n"
                            + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        return returnstr;
    }


}

