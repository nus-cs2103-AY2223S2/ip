import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Parser {

    private static TaskList taskList = new TaskList();
    public static boolean talk() throws DukeException{
        Scanner myObj = new Scanner(System.in);
        String inp = myObj.nextLine();
        if (inp.equals("")) {
            throw new DukeException("Empty Input!");
        }
        while (!inp.equals("bye")) {
            operationHandler(inp);
            System.out.println("done >.<");
            inp = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        return false;
    }

    public static void operationHandler(String task) throws DukeException{
        String[] inpArr = task.split(" ");
        if (inpArr[0].equals("list")) {
            list();
        } else if (inpArr[0].equals("mark")) {
            taskList.markDone(Integer.parseInt(inpArr[1]) - 1);
        } else if (inpArr[0].equals("unmark")) {
            taskList.markUndone(Integer.parseInt(inpArr[1]) - 1);
        } else {
            // add tasks
            if (inpArr[0].equals("todo")) {
                if (task.length() == 4) {
                    throw new DukeException("Description cannot be empty!");
                }
                ToDo newToDo = new ToDo(task.substring(5), task);
                taskList.add(newToDo);
            } else if (inpArr[0].equals("deadline")) { // need to handle exception
                String[] processedString = stringProcessor(true, task.substring(9));
                Deadline newDeadline = new Deadline(processedString[0], task,
                        LocalDate.parse(processedString[1]));
                taskList.add(newDeadline);
            } else if (inpArr[0].equals("event")){ // need to handle exception
                String[] processedString = stringProcessor(false, task.substring(6));
                Event newEvent = new Event(processedString[0], task, LocalDate.parse(processedString[1]),
                        LocalDate.parse(processedString[2]));
                taskList.add(newEvent);
            } else if (inpArr[0].equals("delete")){ // need to handle exception
                taskList.remove(Integer.parseInt(inpArr[1])-1);
            } else {
                throw new DukeException("Invalid Input!");
            }
        }
    }

    private static String[] stringProcessor(boolean isDeadline, String s){ // isDeadline = false meaning isEvent
        if (isDeadline){
            String[] processedArr = new String[2];
            String tempString = "";
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i+1) == '/') {
                    processedArr[1] = s.substring(i+5);
                    break;
                }
                tempString += s.charAt(i);
            }
            processedArr[0] = tempString;
            return processedArr;
        } else {
            String[] processedArr = new String[3];
            String tempString = "";
            //String processedDate = "";
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i) == '/') {
                    //processedDate = s.substring(i+4);
                    String[] processedDate = stringProcessor(true, s.substring(i+6));
                    processedArr[1] = processedDate[0];
                    processedArr[2] = processedDate[1];
                    break;
                }
                tempString += s.charAt(i);
            }
            processedArr[0] = tempString;
            return processedArr;
        }
    }

    public static void list(){
        taskList.print();
    }

    public static void fileInpProcessor(String[] dataArr){
        try {
            Parser.operationHandler(dataArr[0]);
            if (dataArr[1].equals(" 1 ")) {
                Parser.operationHandler("mark " + Integer.toString(taskList.size()));
            }
        } catch (DukeException e){
            System.out.println(e.toString());
        }
    }

    public static void saveFile(){
        try {
            FileWriter myWriter = new FileWriter("data/duke.txt");
            for (int i=0; i<taskList.size(); i++) {
                Task tempTask = taskList.get(i);
                myWriter.write(tempTask.toString());
            }
            myWriter.close();
            System.out.println("Saved your list :).");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
