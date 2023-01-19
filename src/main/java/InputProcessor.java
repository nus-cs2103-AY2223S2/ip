public class InputProcessor {
    /* Takes in an input,
    1. looks at first word and checks if it is a task
    2. if it is, splits words future
     */
    public static boolean is_toDo(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("todo")) {
                return true;
            }
        }
        return false;
    }

    public static boolean is_Event(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("event")) {
                return true;
            }
        }
        return false;
    }

    public static boolean is_Deadline(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("deadline")) {
                return true;
            }
        }
        return false;
    }

    public static boolean is_Mark(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("mark")) {
                return true;
            }
        }
        return false;
    }

    public static boolean is_Unmark(String input) {
        String[] firstword_Arr = input.split(" ", 2);
        if (firstword_Arr.length == 2 ) {
            if (firstword_Arr[0].equalsIgnoreCase("unmark")) {
                return true;
            }
        }
        return false;
    }

    public static boolean is_List(String input) {
        return input.equalsIgnoreCase("list");
    }

    public static boolean is_Bye(String input) {
        return input.equalsIgnoreCase("bye");
    }




}
