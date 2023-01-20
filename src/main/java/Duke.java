import java.util.*;


public class Duke {

    protected static class Task {
        /**
         * Task class for things added to the UserList
         * Methods:
         *      markAsDone -> mark Task as done if not done and vice versa.
         */

        String name;
        Boolean done = false;

        private Task (String name) {
            this.name = name;
        }

        public String toString() {
            /**
             * Returns "[done] Task"
             */
            char doneX = ' ';
            if (done) {
                doneX = 'X';
            }
            return "[" + doneX + "]" + " " + name;
        }

        public boolean equals(Object obj){
            if (obj instanceof Task) {
                Task objTask = (Task)obj;
                return objTask.name.equals(this.name);
            } else {
                return false;
            }
        }

        private void mark() {
            /**
             * Marks Task as done if not done and vice versa.
             */
            if (done) {
                System.out.println(lineBreak + "OK, '" + this.name + "' has been marked as Not Done.\n");
                done = false;
            } else {
                System.out.println(lineBreak + "OK, '" + this.name + "' has been marked as Done.\n");
                done = true;
            }
        }

    }

    private static class UserList {
        /**
         * List used by userCmds 'add' and 'list'.
         * Methods:
         *      addToList()     -> appends Task to the list
         *      markAsDone(int i)    -> mark Task in i-th index as done.
         */

        public class TaskAlrMarkException extends Exception {
            /**
             * Thrown when the Task is already Done but user wants to mark it again and vice versa.
             */
            public TaskAlrMarkException(String errorMessage) {
                super(errorMessage);
            }
        }

        public class TaskNotInListException extends Exception {
            /**
             * Thrown when Task is not in the userList.
             */
            public TaskNotInListException() {
                super("I can't seem to find this Task in the List.\n");
            }
        }

        List<Task> userList = new ArrayList<>();

        public String toString() {
            /**
             * Returns vertical list of Tasks that is 1-index
             */
            String result = "";
            for (int i = 0; i < this.userList.size(); i++){
                result += (i+1) +". " + this.userList.get(i).toString() + "\n";
            }
            return result;
        }

        private void addToList(Task task) {
            /**
             * Appends Task to userList.
             */
            if (!userList.contains(task)) {
                System.out.println(lineBreak + "Added: " + task.name + "\n" + lineBreak);
                userList.add(task);
            } else {
                System.out.println(lineBreak + "Oops, it seems that you have already added this Task to your list!\n");
            }
        }

        private void mark(String markCmd, int i) throws TaskNotInListException, TaskAlrMarkException {
            /**
             * Mark the i-1 th index Task as done if not done and vice versa.
             * Note list is 0-index but displayed as 1-index, hence i would be 1-index.
             * If i > userList.size(), throw TaskNotInListException.
             * If Task done but "mark" and vice versa, throw TaskAlrMarkException.
             *
             * @param   markCmd     User's command to "mar" or "unmark" a Task as done or not done.
             * @param   i           The index of the Task to be marked. Note that user input i is 1-index, so -1 is needed.
             */
            if (i > userList.size()) {
                throw new TaskNotInListException();
            } else if (markCmd.equals("mark") && userList.get(i-1).done) {
                throw new TaskAlrMarkException(lineBreak + "It appears that this Task has already been marked as Done. Would you like to mark it as Not Done instead?\n");
            } else if (markCmd.equals("unmark") && !userList.get(i-1).done){
                throw new TaskAlrMarkException(lineBreak + "It appears that this Task has already been marked as Not Done. Would you like to mark it as Done instead?\n");
            } else {
                userList.get(i - 1).mark();
            }
        }

        private int len() {
            return userList.size();
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
        String cmd = userCmd.next();
        String askForCmd = lineBreak + "Okay. What would you like to do next?\nIf you want to exit DUKE, type 'bye'!\n";
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
        } else if (cmd.equals("mark") || cmd.equals("unmark")) {
            String markUnmark = cmd;
            if (userCmd.hasNext()){
                try {
                    int listIdx = Integer.parseInt(userCmd.next());
                    mark(markUnmark, listIdx);
                    System.out.println(askForCmd);
                    takeCmd();
                } catch(NumberFormatException e) {
                    System.out.println("Sorry, I don't understand what you want to me to do.\n" + askForCmd);
                    takeCmd();
                }
            } else {
                System.out.println("Sorry, I don't understand what you want to me to do.\n" + askForCmd);
                takeCmd();
            }
        } else {
            System.out.println(askForCmd);
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
        System.out.println("Hello from\n" + logo + lineBreak + "Type 'bye' to exit!\n" + lineBreak + "What can I do for you today?\n");
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
        System.out.println(lineBreak + "What would you like to add to the list?\nType 'done' when done adding!");
        String item = userItem.next();
        while (!item.equals("done")) {
            Task itemTask = new Task(item);
            userList.addToList(itemTask);
            System.out.println("Anything else you would like added to the list?\nType 'done' when done adding!");
            item = userItem.next();
        }
        System.out.println(lineBreak + "Your list looks like this:\n" + userList.toString());
    }

    private static void list(){
        /**
         * userCmd "list"
         * Displays userList vertically.
         */
        if (userList.len() == 0){
            System.out.println(lineBreak + "Oops, it seems that your list is empty!\n");
        } else {
            System.out.println(lineBreak + "Your list looks like this:\n" + userList.toString() + lineBreak);
        }
    }

    private static void mark(String markCmd, int idx){
        try {
            userList.mark(markCmd, idx);
        } catch (UserList.TaskNotInListException e) {
            System.out.println("Sorry, can't find that task.\n");
        } catch (UserList.TaskAlrMarkException e){
            System.out.println("The Task has already been marked.\n");
        }
    }

}
