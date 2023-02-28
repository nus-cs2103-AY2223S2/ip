package seedu.duke;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Storage {
    protected FileWriter fw = null;
    protected FileReader fr = null;
    protected String fileName;
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList<Task>();

        try {
            Parser parser = new Parser();
            fr = new FileReader(this.fileName);
            String task = "";
            int ch;
            while ((ch=fr.read()) != -1)
                task += (char)ch;
            fr.close();

            String[] lines = task.split("\n");
            String[] taskSplit;
            for (int i = 0; i < lines.length; i++) {
                taskSplit = lines[i].split(" ");
                String taskLine = getTask(taskSplit);
                String[] split = taskLine.split(" ");

                if (taskSplit[1].equals("T")){
                    list.add(new ToDo(taskLine));
                }

                if (taskSplit[1].equals("D")){
                    addDeadline(split, list, 1);
                }

                if (taskSplit[1].equals("E")){
                    addEvent(split, list, 1);
                }

                if (taskSplit[3].equals("Y")) {
                    list.get(list.size() - 1).isDone = true;
                }
            }


        } catch (FileNotFoundException fe) {
            System.out.println("File not found...creating the file");
            fw = new FileWriter("duke.txt");
        } catch(ArrayIndexOutOfBoundsException a) {
//            System.out.println("array...creating the file");
            fw = new FileWriter("duke.txt");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        } finally {
//            System.out.println("err...creating the file");
            fw = new FileWriter("duke.txt");
        }
        return list;
    }

    public String getTask(String[] taskSplit){
        String description = "";
        if (taskSplit[1].equals("T")) {
            description = "";
        }
        if (taskSplit[1].equals("D")) {
            description = "deadline";
        }
        if (taskSplit[1].equals("E")) {
            description = "event";
        }
        if(taskSplit[1].equals("T")) {
            for (int j = 5; j < taskSplit.length; j++) {
                description += taskSplit[j] + " ";
            }

        } else {
            for (int j = 5; j < taskSplit.length; j++) {
                description += " " + taskSplit[j];
            }
        }
        return description;

    }

    public void updateFile(TaskList tasks) throws IOException {
        fw = new FileWriter(this.fileName);
        for (int i = 0; i < tasks.list.size(); i++) {
            fw.write(tasks.list.get(i).toString()+'\n');
        }
        fw.flush();
    }

    public void close() throws IOException {
        fw.close();
        fr.close();
    }

    public void addEvent(String[] echoSplit,ArrayList<Task> list, int print){
        String task = "";
        int fromI = 0;
        int toI = 0;
        String from = "";
        String to = "";

        for (int i = 1; i < echoSplit.length; i++) {

            if (echoSplit[i].equals("/from") || echoSplit[i].equals("from:")) {
                fromI = i;
            }
            if (echoSplit[i].equals("/to") || echoSplit[i].equals("to:")) {
                toI = i;

                for (int j = fromI + 1; j < toI; j++) {
                    if (j == toI - 1) {
                        from += echoSplit[j];
                    } else {
                        from += echoSplit[j] + " ";
                    }
                }
                for (int j = toI + 1; j < echoSplit.length; j++) {
                    if (j == echoSplit.length - 1) {
                        to += echoSplit[j];
                    } else {
                        to += echoSplit[j] + " ";
                    }
                }
                for (int j = 1; j < fromI; j++) {
                    task += echoSplit[j] + " ";
                }
                break;
            }


        }
        list.add(new Event(task, from, to));
        if (print == 0)
            System.out.println("    -------------------------------------------\n    "
                    + "added: " + task
                    +"\n    -------------------------------------------");
    }
    public void addDeadline(String[] echoSplit, ArrayList<Task> list, int print){
        String task = "";
        String date = "";

        for (int i = 1; i < echoSplit.length; i++) {
            if (echoSplit[i].equals("/by") || echoSplit[i].equals("by:")){

                for (int j = 1; j < i; j++) {
                    if (j == i-1) {
                        task += echoSplit[j];
                    } else {
                        task += echoSplit[j] + " ";
                    }
                }
                for (int j = i + 1; j < echoSplit.length; j++) {
                    if (j == echoSplit.length - 1) {
                        date += echoSplit[j];
                    } else {
                        date += echoSplit[j] + " ";
                    }
                }

                list.add(new Deadline(task, date));
                if (print == 0) {
                    System.out.println(echoSplit[i+1]);
                    System.out.println("    -------------------------------------------\n    "
                            + "added: " + task
                            +"\n    -------------------------------------------");
                }

            }
        }
    }




}
