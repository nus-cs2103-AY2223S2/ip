import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;

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

            String input = sc.nextLine();
            while (!input.equals("bye")) {
                String[] splitArr = input.split(" ");
                try {
                    if (input.equals("list")) {
                        System.out.println(formatStr(tasks.listThings()));
                    } else if (splitArr[0].equals("mark") || splitArr[0].equals("unmark")) {
                        if ((Integer.parseInt(splitArr[1])) > tasks.getSize()) {
                            throw new OutOfIndexException("Help! \n" +
                                    "The number has to be within range of our task-list!\n" +
                                    "try again.");
                        }
                        if (splitArr[0].equals("mark") &&
                                tasks.getTask(Integer.parseInt(splitArr[1]) - 1).
                                        getMark() != false) {
                            throw new WrongBooleanException("Hey! \n" +
                                    "This is already done. You can't mark it again. :0 \n" +
                                    "try again.");
                        } else if (splitArr[0].equals("unmark") &&
                                tasks.getTask(Integer.parseInt(splitArr[1])).
                                        getMark() != true) {
                            throw new WrongBooleanException("Hey! \n" +
                                    "This is undone. You can't mark it undone again. :0 \n" +
                                    "try again.");
                        }
                        tasks.mark(splitArr[0], Integer.parseInt(splitArr[1]) - 1);
                    } else if (splitArr[0].equals("delete")) {
                        if ((Integer.parseInt(splitArr[1])) > tasks.getSize()) {
                            throw new OutOfIndexException("Help! \n" +
                                    "The number has to be within range of our task-list!\n" +
                                    "Please try again!");
                        }
                        Task newTask = tasks.getTask(Integer.parseInt(splitArr[1]) - 1);
                        tasks.removeTask(Integer.parseInt(splitArr[1]) - 1);
                        System.out.println(formatStr(tasks.deleteReport(newTask)));
                    } else if (splitArr[0].equals("todo")) {
                        Todo newTodo = new Todo(input);
                        tasks.addTask(newTodo);
                        System.out.println(formatStr(tasks.addReport(newTodo)));
                        pw.write(newTodo.printRecord());
                    } else if (splitArr[0].equals("deadline")) {
                        Deadline newDead = new Deadline(input);
                        tasks.addTask(newDead);
                        System.out.println(formatStr(tasks.addReport(newDead)));
                        pw.write(newDead.printRecord());
                    } else if (splitArr[0].equals("event")) {
                        Event newEvent = new Event(input);
                        tasks.addTask(newEvent);
                        System.out.println(formatStr(tasks.addReport(newEvent)));
                        pw.write(newEvent.printRecord());
                    } else {
                        throw new VagueInputException("Oh no! What do you mean? \n" +
                                "I'm confused. Please specify... @.@");
                    }
                } catch (VagueInputException ex) {
                    System.out.println(formatStr(ex.getMessage()));
                    input = sc.nextLine();
                    continue;
                } catch (OutOfIndexException ex) {
                    System.out.println(formatStr(ex.getMessage()));
                    input = sc.nextLine();
                    continue;
                } catch (WrongBooleanException ex) {
                    System.out.println(formatStr(ex.getMessage()));
                    input = sc.nextLine();
                    continue;
                }
                input = sc.nextLine();
            }
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

