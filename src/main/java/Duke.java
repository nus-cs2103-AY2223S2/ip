import duke.exceptions.OutOfIndexException;
import duke.exceptions.VagueInputException;
import duke.exceptions.WrongBooleanException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Duke {
    public static void main(String[] args) {
        String greeting = formatStr("Hello! I'm Muse!\n"
                + "What can I do for you?");
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        String textDir = System.getProperty("user.dir")+"/duke.txt";

        try {
            File file = new File(textDir);
            TaskList tasks = new TaskList();
            if (!file.exists()) {
                System.out.println(formatStr("Oh dear! There is no save file. Let me create one for you."));
                System.out.println("........CREATING......."); 
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(textDir, true));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while (br.ready()) {
                line = br.readLine();
                tasks.addLine(line);
            }

            Ui.handleInputs(sc, tasks, pw);
            pw.print("");
            pw.close();
            PrintWriter clearer = new PrintWriter(textDir);
            clearer.close();
            PrintWriter reWriter = new PrintWriter(new FileWriter(textDir, true));
            for (int i = 0; i < tasks.getSize(); i++) {
                reWriter.write(tasks.getTask(i).printRecord());
            }
            reWriter.close();
            String goodbyeMessage = formatStr("Bye. Come back again!");  
            System.out.println(goodbyeMessage);
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

