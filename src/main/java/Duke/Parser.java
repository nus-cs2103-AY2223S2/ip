package Duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;

/**
 * A class that makes sense of all the commands and executes them
 * as the appropriate function.
 */
public class Parser {

    public static Boolean parserStatus = true; //comment

    /**
     * Makes sense of the string command and takes suitable action.
     * @param str takes in the command
     */
    public static String makeSense(String str) {
        String[] forAssertion = str.split(" ");
        String key = str.split(" ", 2)[0];
        String toGive="";
        switch (key) {
        case "bye":
            toGive = pBye();
            break;
            // need to switch off service
        case "list":
            assert forAssertion.length == 1 : "forAssertion doesn't have a length of 1";
            toGive = TaskList.printList();
            break;
        case "mark":
            assert forAssertion.length == 2 : "forAssertion doesn't have a length of 2";
            toGive = pMark(Integer.parseInt(str.split(" ", 2)[1])); // function handles index
            break;
        case "unmark":
            assert forAssertion.length == 2 : "forAssertion doesn't have a length of 2";
            toGive = pUnmark(Integer.parseInt(str.split(" ", 2)[1])); // function handles index
            break;
        case "delete":
            assert forAssertion.length == 2 : "forAssertion doesn't have a length of 2";
            toGive = pDelete(Integer.parseInt(str.split(" ", 2)[1]));
            break;
        case "find":
            toGive = pFind(str.split(" ", 2)[1]);
            break;
        default:
            toGive = pAdd(str);
        }
        return toGive;
    }

    public static String pBye() {
        parserStatus = false;
        return "Bye mortal, I will get back to destroying" +
                " galaxies";
    }

    public static String pMark(int i) {
        String str = TaskList.mark(i);
        Storage.storeData();
        return str;
    }

    public static String pUnmark(int i) {
        String str = TaskList.unmark(i);
        Storage.storeData();
        return str;
    }

    public static String pDelete(int i) {
        String str = TaskList.delete((i));
        Storage.storeData();
        return str;
    }

    public static String pFind(String str) {
        return TaskList.find(str);
    }

    public static String pAdd(String str) {
        String ret;
        try {
            ret = TaskList.add_to_list(str);
            Storage.storeData();
        }
        catch (InvalidCommandException e) {
            ret = "the command is invalid";
        }
        catch (NoDescriptionException e) {
            ret = "the task needs to have a description";
        }

        return ret;

    }

}
