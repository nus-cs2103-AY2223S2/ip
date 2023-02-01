import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

class DukeException extends Exception {

    public DukeException(String errorMessage){
        super(errorMessage);
    }
}
public class Duke {

    private static List<Task> toDoList = new ArrayList<>();

    private static void startUp() {
        String name = "todo bot";
        System.out.println("Hello from " + name);
        System.out.println("------------------------------------");
        System.out.println("I can help you take care of your daily todos :)");
        System.out.println("There are 3 types of tasks I can handle.");
        System.out.println("------------------------------------");
        System.out.println("1. normal todo, format: ");
        System.out.println("   todo task");
        System.out.println("2. deadline, format: ");
        System.out.println("   deadline task /by yyyy-mm-dd");
        System.out.println("3. event, format: ");
        System.out.println("   event task /from yyyy-mm-dd /to yyyy-mm-dd");
        System.out.println("------------------------------------");

    }
    private static boolean talk() throws DukeException{
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


    private static void operationHandler(String task) throws DukeException{
        String[] inpArr = task.split(" ");
        if (inpArr[0].equals("list")) {
            list();
        } else if (inpArr[0].equals("mark")) {
            toDoList.get(Integer.parseInt(inpArr[1]) - 1).markDone();
        } else if (inpArr[0].equals("unmark")) {
            toDoList.get(Integer.parseInt(inpArr[1]) - 1).markUndone();
        } else {
            // add tasks
            if (inpArr[0].equals("todo")) {
                if (task.length() == 4) {
                    throw new DukeException("Description cannot be empty!");
                }
                ToDo newToDo = new ToDo(task.substring(5), task);
                toDoList.add(newToDo);
            } else if (inpArr[0].equals("deadline")) { // need to handle exception
                String[] processedString = stringProcessor(true, task.substring(9));
                Deadline newDeadline = new Deadline(processedString[0], task,
                        LocalDate.parse(processedString[1]));
                toDoList.add(newDeadline);
            } else if (inpArr[0].equals("event")){ // need to handle exception
                String[] processedString = stringProcessor(false, task.substring(6));
                Event newEvent = new Event(processedString[0], task, LocalDate.parse(processedString[1]),
                        LocalDate.parse(processedString[2]));
                toDoList.add(newEvent);
            } else if (inpArr[0].equals("delete")){ // need to handle exception
                toDoList.remove(Integer.parseInt(inpArr[1])-1);
            } else {
                throw new DukeException("Invalid Input!");
            }
        }
    }

    private static void listReader(){
        try {
            File myObj = new File("data/duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArr = data.split("@");
                fileInpProcessor(dataArr);
            }
            System.out.println("loaded your past list *.*");
            list();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Starting a fresh list :)");
            File dir = new File("data");
            boolean dirCreated = dir.mkdir();
            //dir.createNewFile();
            File file = new File("data/duke.txt");
            try {
                if (dir.createNewFile()) {
                    System.out.println("File created: " + dir.getName());
                }
            } catch (IOException err) {
                System.out.println("An error occurred.");
                err.printStackTrace();
            }
        }
    }

    private static void fileInpProcessor(String[] dataArr){
        try {
            //if (dataArr[0].equals("todo ")) {
            operationHandler(dataArr[0]);
            /*} else if (dataArr[0].equals("deadline ")) {
                operationHandler("deadline" + dataArr[2] + "/by" + dataArr[3]);
            } else if (dataArr[0].equals("event ")) {
                operationHandler("event" + dataArr[2] + "/from" + dataArr[3] + "/to" + dataArr[4]);
            }*/
            if (dataArr[1].equals(" 1 ")) {
                operationHandler("mark " + Integer.toString(toDoList.size()));
            }
        } catch (DukeException e){
            System.out.println(e.toString());
        }
    }

    private static String[] stringProcessor(boolean isDeadline, String s){ // isDeadline = false meaning isEvent
        if (isDeadline){
            String[] processedArr = new String[2];
            String tempString = "";
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i) == '/') {
                    processedArr[1] = s.substring(i+4);
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
    private static void list(){
        for (int i = 0; i < toDoList.size(); i++){
            System.out.print(Integer.toString(i+1) + ". ");
            toDoList.get(i).printTask();
        }
    }

    public static void main(String[] args) {
        startUp();
        boolean isRunning = true;
        listReader();
        while (isRunning) {
            try {
                isRunning = talk();
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }

        try {
            FileWriter myWriter = new FileWriter("data/duke.txt");
            for (int i=0; i<toDoList.size(); i++) {
                Task tempTask = toDoList.get(i);
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
