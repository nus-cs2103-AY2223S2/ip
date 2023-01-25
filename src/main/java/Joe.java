public class Joe {
    static TaskList taskList = new TaskList();
    static TaskParser parser = new TaskParser(taskList);

    public Joe() {
        taskList = new TaskList();
    }


    String handleResponse(String input) {
        return parser.parse(input);
    }

    public static void printNewLine() {
        String newline = "\t____________________________________________________________";
        System.out.println(newline);
    }
}
