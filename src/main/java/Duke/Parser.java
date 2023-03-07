package Duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;

public class Parser {

    public static Boolean parserStatus = true; //comment

    /**
     * Makes sense of the string command and takes suitable action.
     * @param str takes in the command
     */
    public static String makeSense(String str) {
        String key = str.split(" ", 2)[0];
        String toGive="";
        switch (key) {
        case "bye":
            toGive = "Bye mortal, I will get back to destroying" +
                    " galaxies";
            parserStatus = false;
            break;
            // need to switch off service
        case "list":
            TaskList.printList();
        case "mark":
            int num1 = Integer.parseInt(str.split(" ", 2)[1]);
            toGive = TaskList.mark(num1); // function handles index
            Storage.storeData();
            break;
        case "unmark":
            int num2 = Integer.parseInt(str.split(" ", 2)[1]);
            toGive = TaskList.unmark(num2);
            Storage.storeData();
            break;
        case "delete":
            int num3 = Integer.parseInt(str.split(" ", 2)[1]);
            toGive = TaskList.delete((num3));
            Storage.storeData();
            break;
        case "find":
            String keyword = str.split(" ", 2)[1];
            toGive = TaskList.find(keyword);
        default:
            try {
                toGive = TaskList.add_to_list(str);
                Storage.storeData();
            }
            catch (InvalidCommandException e) {
                toGive = "the command is invalid";
            }
            catch (NoDescriptionException e) {
                toGive = "the task needs to have a description";
            }
        }
        return toGive;
    }
}
