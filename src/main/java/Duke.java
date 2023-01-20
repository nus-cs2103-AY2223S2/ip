import java.util.*;


public class Duke {

    private static class UserList {
        /**
         * List used by userCmds 'add' and 'list'.
         * Attributes: userList -> contains all the stuff added with 'add'
         * Methods: addToList() -> appends item to the list
         */

        List<String> userList = new ArrayList<>();

        public String toString() {
            String result = "";
            for (int i = 0; i < this.userList.size(); i++){
                result += this.userList.get(i).toString() + "\n";
            }
            return result;
        }

        protected void addToList(String item){
            userList.add(item);
        }
    }

    private static String lineBreak = "_________________________________________________________________\n";
    private static UserList userList = new UserList();

    public static void main(String[] args) {
        greeting();
        takeCmd();
    }

    private static void takeCmd() {
        /**
         * Control flow, logic, checking of the user inputs as commands.
         * Recognized user commands:
         *      bye     -> quit
         *      add     -> add to list
         *      list    -> display
         * Unrecognized commands will simply be echoed then Duke waits next command.
         */
        Scanner userCmd = new Scanner(System.in);
        String cmd = userCmd.nextLine();
        String askForCmd = "Okay. What would you like to do next?\n";
        if (cmd.equals("bye")) {
            exit();
        } else if (cmd.equals("add")) {
            add();
            System.out.println(askForCmd);
            takeCmd();
        } else if (cmd.equals("list")){
            list();
            System.out.println(askForCmd);
            takeCmd();
        } else {
            System.out.println(lineBreak + cmd + "\n" + lineBreak);
            takeCmd();
        }
    }

    private static void greeting() {
        /**
         * Start-up logo and message
         */
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "Type 'bye' to exit!\n" + lineBreak + "What can I do for you today?\n");
    }

    private static void exit() {
        /**
         * userCmd "bye"
         * Exiting Duke.
         */
        System.out.println("Bye. Hope to see you again soon!\n" + lineBreak);
    }

    private static void add() {
        /**
         * userCmd "add"
         * Appends item to userList, then displays it when done.
         */

        Scanner userItem = new Scanner(System.in);
        System.out.println("What would you like to add to the list?\nType 'done' when done adding!");
        String item = userItem.next();
        while (!item.equals("done")) {
            userList.addToList(item);
            System.out.println("Added: " + item + "\n" + lineBreak + "Anything else you would like added to the list?\nType 'done' when done adding!");
            item = userItem.next();
        }
        System.out.println("Your list looks like this:\n" + userList.toString());
    }

    private static void list(){
        /**
         * userCmd "list"
         * Displays userList vertically.
         */

        System.out.println("Your list looks like this:\n" + userList.toString());
    }

}
