public class Query {
    protected String input;

    public Query(String input) {
        this.input = input;
    }

    static boolean isNumeric(String str) {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    static QueryType queryType(String input) {
        if (input.equals("list")) {
            return QueryType.list;
        }
        String[] inputArr = input.split(" ");
        if (inputArr.length == 2 && isNumeric(inputArr[1])) {
            if (inputArr[0].equals("mark")) {
                return QueryType.mark;
            } else if (inputArr[0].equals("unmark")) {
                return QueryType.unmark;
            } else if (inputArr[0].equals("delete")) {
                return QueryType.delete;
            }
        }
        else if (inputArr[0].equals("todo")) {
            return QueryType.todo;
        }
        else if (inputArr[0].equals("deadline")) {
            return QueryType.deadline;
        }
        else if (inputArr[0].equals("event")) {
            return QueryType.event;
        }
        return QueryType.invalid;
    }
}
