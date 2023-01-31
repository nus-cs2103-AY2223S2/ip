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

    public static void convertList(ArrayList<Duke.Task> tasks) {

    }
    public static String formatStr(String str) {
        String returnstr =  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
                            + str + "\n"
                            + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        return returnstr;
    }

    public static class Task {
        protected boolean mark;
        protected String content;

        public Task(String content) {
            this.content = content;
            this.mark = false;
        }

        public Task(String content, boolean alternative) {
            this.content = content;
            this.mark = alternative;
        }

        public boolean getMark() {
            return this.mark;
        }

        public void setMark() {
            this.mark = !this.mark;
            String outputStr;
            if (mark == true) {
                outputStr = "NICE! You finished this: \n"
                        + "[" + markSign(this.mark) + "] " + this.content;
            } else {
                outputStr = "Ok, you have undone this: \n"
                        + "[" + markSign(this.mark) + "] " + this.content;
            }
            System.out.println(formatStr(outputStr));
        }

        public String markSign(boolean markBool) {
            if(markBool == true) return "X";
            else return " ";
        }

        public String toString() {
            return ". [" + markSign(this.mark) + "] " + this.content;
        }

        public String printRecord() {
            return this.toString();
        }
    }

}

