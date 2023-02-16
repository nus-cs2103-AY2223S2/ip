import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final String LINE = "----------------------------------------------";
    private static final String PATH = "./data/duke.txt";
    private static final String TEMPPATH = "./data/temp.txt";
    public static void main(String[] args) throws EmptyTaskException, InvalidRequestException, IOException {
        File file = new File(PATH);
        boolean isFileExist = file.exists();
        if (!isFileExist) {
            file.mkdirs();
            file.delete();
            file.createNewFile();
        }
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String savedTask;
            while ((savedTask = bf.readLine()) != null) {
                String[] taskFragment = savedTask.split(" / ", 5);
                if (taskFragment[0].equals("T")) {
                    ToDo toDo = new ToDo(taskFragment[2]);
                    if (taskFragment[1].equals("1")) {
                        toDo.mark();
                    }
                    list.add(toDo);
                } else if (taskFragment[0].equals("D")) {
                    Deadline deadline = new Deadline(taskFragment[2], taskFragment[3]);
                    if (taskFragment[1].equals("1")) {
                        deadline.mark();
                    }
                    list.add(deadline);
                } else if (taskFragment[0].equals("E")) {
                    Event event = new Event(taskFragment[2], taskFragment[3], taskFragment[4]);
                    if (taskFragment[1].equals("1")) {
                        event.mark();
                    }
                    list.add(event);
                }
            }
            bf.close();
        }catch (Exception e) {
            return;
        }
        System.out.println(LINE);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(LINE+"\n");
        try {
            boolean isReWrite = false;
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            if(!list.isEmpty()) {
                System.out.println(LINE);
                System.out.println("\tSaved Tasks From Last Session");
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println("\t" + (i + 1) + ". " + task.toString());
                }
                System.out.println(LINE + "\n");
            }
            while (true){
                if (isReWrite) {
                    bw = new BufferedWriter(new FileWriter(file, true));
                    isReWrite = false;
                }
                String command = scanner.nextLine();
                String[] splittedCmd = command.split(" ", 2);
                if (command.equals("bye")){
                    bw.close();
                    System.out.println(LINE);
                    System.out.println("\tBye. Hope to see you again soon!");
                    System.out.println(LINE+"\n");
                    break;
                }
                else if (command.equals("list")) {
                    System.out.println(LINE);
                    for (int i = 0; i < list.size(); i++){
                        Task task = list.get(i);
                        System.out.println("\t" + (i + 1) + ". " + task.toString());
                    }
                    System.out.println(LINE+"\n");
                }
                else if (splittedCmd[0].equals("mark")) {
                    try {
                        System.out.println(LINE);
                        int idx = Integer.parseInt(splittedCmd[1]) - 1;
                        Task task = list.get(idx);
                        System.out.println("\tOK, I've marked this task as done:");
                        task.mark();
                        System.out.println("\t" + task.toString());
                        System.out.println(LINE + "\n");
                        list.set(idx, task);
                        File file2 = new File(TEMPPATH);
                        boolean isTempExist = file.exists();
                        if (!isTempExist) {
                            file2.createNewFile();
                        }
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMPPATH));
                        BufferedReader br = new BufferedReader(new FileReader(PATH));
                        int idx2 = 0;
                        String currentLine;
                        while ((currentLine = br.readLine()) != null) {
                            if (idx2 == idx) {
                                currentLine = currentLine.replaceFirst("0", "1");
                            }
                            bw2.write(currentLine + System.getProperty("line.separator"));
                            idx2++;
                        }
                        bw2.close();
                        br.close();
                        bw.close();
                        isReWrite = true;
                        file.delete();
                        boolean successful = file2.renameTo(file);
                    }catch (Exception e){
                        System.out.println("\tOOPS!!! Please input the index.\n" + LINE + "\n");
                    }
                }
                else if (splittedCmd[0].equals("unmark")) {
                    try {
                        System.out.println(LINE);
                        int idx = Integer.parseInt(splittedCmd[1]) - 1;
                        Task task = list.get(idx);
                        System.out.println("\tNice! I've unmarked this task as not done yet:");
                        task.unmark();
                        System.out.println("\t" + task.toString());
                        System.out.println(LINE + "\n");
                        list.set(idx, task);
                        File file2 = new File(TEMPPATH);
                        boolean isTempExist = file.exists();
                        if (!isTempExist) {
                            file2.createNewFile();
                        }
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMPPATH));
                        BufferedReader br = new BufferedReader(new FileReader(PATH));
                        int idx2 = 0;
                        String currentLine;
                        while ((currentLine = br.readLine()) != null) {
                            if (idx2 == idx) {
                                currentLine = currentLine.replaceFirst("1", "0");
                            }
                            bw2.write(currentLine + System.getProperty("line.separator"));
                            idx2++;
                        }
                        bw2.close();
                        br.close();
                        bw.close();
                        isReWrite = true;
                        file.delete();
                        boolean successful = file2.renameTo(file);
                    } catch (Exception e) {
                        System.out.println("\tOOPS!!! Please input the index.\n" + LINE + "\n");
                    }
                }
                else if (splittedCmd[0].equals("delete")){
                    try {
                        System.out.println(LINE);
                        int idx = Integer.parseInt(splittedCmd[1]) - 1;
                        Task task = list.get(idx);
                        list.remove(idx);
                        System.out.println("\tNoted. I've removed this task:");
                        System.out.println("\t" + task.toString());
                        System.out.println("\t" + "Now you have " + list.size() + " tasks in the list");
                        System.out.println(LINE + "\n");
                        File file2 = new File(TEMPPATH);
                        boolean isTempExist = file.exists();
                        if (!isTempExist) {
                            file2.createNewFile();
                        }
                        BufferedWriter bw2 = new BufferedWriter(new FileWriter(TEMPPATH));
                        BufferedReader br = new BufferedReader(new FileReader(PATH));
                        int idx2 = 0;
                        String currentLine;
                        while ((currentLine = br.readLine()) != null) {
                            if (idx2 == idx) {
                                idx2++;
                                continue;
                            }
                            bw2.write(currentLine + System.getProperty("line.separator"));
                            idx2++;
                        }
                        bw2.close();
                        br.close();
                        bw.close();
                        isReWrite = true;
                        file.delete();
                        boolean successful = file2.renameTo(file);
                    }catch (Exception e){
                        System.out.println("\tOOPS!!! Please input the index.\n" + LINE + "\n");
                    }
                }
                else if (splittedCmd[0].equals("todo")){
                    try {
                        System.out.println(LINE);
                        ToDo todos = new ToDo(splittedCmd[1]);
                        list.add(todos);
                        bw.write("T / 0 / " + splittedCmd[1] + "\n");
                        System.out.println("\t" + "Got it. I've added this task:");
                        System.out.println("\t" + todos.toString());
                        System.out.println("\t" + "Now you have " + list.size() + " tasks in the list");
                        System.out.println(LINE + "\n");
                        bw.flush();
                    }catch(Exception e){
                        System.out.println("\tOOPS!!! The description of a todo cannot be empty.\n" + LINE + "\n");
                    }
                }
                else if (splittedCmd[0].equals("deadline")){
                    try {
                        System.out.println(LINE);
                        String[] request = splittedCmd[1].split("/", 2);
                        String task = request[0];
                        String date = request[1];
                        Deadline deadline = new Deadline(task, date);
                        list.add(deadline);
                        bw.write("D / 0 / " + task + " / " + date + "\n");
                        System.out.println("\t" + deadline.toString());
                        System.out.println("\t" + "Now you have " + list.size() + " tasks in the list");
                        System.out.println(LINE + "\n");
                        bw.flush();
                    }catch(Exception e) {
                        System.out.println("\tOOPS!!! Make sure insert all required input.\n" + LINE + "\n");
                    }
                }
                else if (splittedCmd[0].equals("event")){
                    try {
                        System.out.println(LINE);
                        String[] request = splittedCmd[1].split("/", 3);
                        String task = request[0];
                        String from = request[1];
                        String to = request[2];
                        Event event = new Event(task, from, to);
                        list.add(event);
                        bw.write("E / 0 / " + task + "/ " + from + "/ " + to + "\n");
                        System.out.println("\t" + event.toString());
                        System.out.println("\t" + "Now you have " + list.size() + " tasks in the list");
                        System.out.println(LINE + "\n");
                        bw.flush();
                    }catch(Exception e) {
                        System.out.println("\tOOPS!!! Make sure insert all required input.\n" + LINE + "\n");
                    }
                }
                else {
                    System.out.println(LINE);
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means\n" + LINE + "\n");
                }
            }
        } catch (Exception e) {
            return;
        }

    }
}
