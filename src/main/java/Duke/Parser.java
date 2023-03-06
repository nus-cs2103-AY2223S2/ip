package Duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;

public class Parser {

    public static Boolean parserStatus = true; //comment

    /**
     * Makes sense of the string command and takes suitable action.
     * @param str takes in the command
     */
    public static void makeSense(String str) {
        String key = str.split(" ", 2)[0];
        switch (key) {
        case "bye":
            Ui.giveOutput("Bye mortal, I will get back to destroying" +
                    " galaxies");
            parserStatus = false;
            break;
            // need to switch off service
        case "mark":
            int num1 = Integer.parseInt(str.split(" ", 2)[1]);
            TaskList.mark(num1); // function handles index
            Storage.storeData();
            break;
        case "unmark":
            int num2 = Integer.parseInt(str.split(" ", 2)[1]);
            TaskList.unmark(num2);
            Storage.storeData();
            break;
        case "delete":
            int num3 = Integer.parseInt(str.split(" ", 2)[1]);
            TaskList.delete((num3));
            Storage.storeData();
            break;
        default:
            try {
                TaskList.add_to_list(str);
                Storage.storeData();
            }
            catch (InvalidCommandException e) {
                System.out.println("the command is invalid");
            }
            catch (NoDescriptionException e) {
                System.out.println("the task needs to have a description");
            }


        }
    }
}
