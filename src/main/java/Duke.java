import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DukeException extends Exception {

    public DukeException(String errorMessage){
        super(errorMessage);
    }
}
public class Duke {

    private static List<Task> toDoList = new ArrayList<>();
    private static boolean talk() throws DukeException{
        Scanner myObj = new Scanner(System.in);
        String inp = myObj.nextLine();
        if (inp.equals("")) {
            throw new DukeException("Empty Input!");
        }
        String[] inpArr = inp.split(" ");
        while (!inp.equals("bye")) {
            if (inp.equals("list")) {
                list();
            } else if (inpArr[0].equals("mark")) {
                toDoList.get(Integer.parseInt(inpArr[1]) - 1).markDone();
            } else if (inpArr[0].equals("unmark")) {
                toDoList.get(Integer.parseInt(inpArr[1]) - 1).markUndone();
            } else {
                // add tasks
                if (inpArr[0].equals("todo")) {
                    if (inp.length() == 4) {
                        throw new DukeException("Description cannot be empty!");
                    }
                    ToDo newToDo = new ToDo(inp.substring(5));
                    toDoList.add(newToDo);
                    System.out.println("added >.<");
                } else if (inpArr[0].equals("deadline")) { // need to handle exception
                    String[] processedString = stringProcessor(true, inp.substring(9));
                    Deadline newDeadline = new Deadline(processedString[0], processedString[1]);
                    toDoList.add(newDeadline);
                    System.out.println("added >.<");
                } else if (inpArr[0].equals("event")){ // need to handle exception
                    String[] processedString = stringProcessor(false, inp.substring(6));
                    Event newEvent = new Event(processedString[0], processedString[1], processedString[2]);
                    toDoList.add(newEvent);
                    System.out.println("added >.<");
                } else if (inpArr[0].equals("delete")){ // need to handle exception
                    toDoList.remove(Integer.parseInt(inpArr[1])-1);
                    System.out.println("deleted *.*");
                } else {
                    throw new DukeException("Invalid Input!");
                }
            }
            inp = myObj.nextLine();
            inpArr = inp.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
        return false;
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
        String name = "chatty bot";
        System.out.println("Hello from " + name);
        System.out.println("talk to me :)");
        boolean isRunning = true;
        while (isRunning) {
            try {
                isRunning = talk();
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }



}
