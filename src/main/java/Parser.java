public class Parser {
    public Parser() {

    }

    public static void parseResponse(String response, TaskList taskList) {
        /**
         * @param response the string response given by the user.
         * @param taskList the task list that handles the tasks.
         */
        String firstWord = (response + " ").split(" ", 2)[0];
        try {
            switch (firstWord) {
            case "list":
                taskList.listItems();
                break;
            case "delete":
                taskList.deleteItem(response);
                break;
            case "mark":
            case "unmark":
                taskList.markItem(response, firstWord.equals("mark"));
                break;
            case "todo":
            case "deadline":
            case "event":
                taskList.addItem(firstWord, response);
                break;
            default:
                System.out.println("HUH? What you say?.");
            }
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    public static int parseInt(String s, String source) {
        try {
            return Integer.parseInt(s.strip())-1;
        } catch (NumberFormatException ex) {
            throw new InputFormatException(source, "Haiya this not number. FAILURE.", null);
        }
    }

    public static String[] handleMissingField(String content, String delimiter, String fieldName, String source) {
        /**
         * Handles completely missing fields.
         */
        String[] splitted = content.split(delimiter, 2);
        if (splitted.length <= 1) {
            throw new InputFormatException(source,
                    String.format("Haiya where your %s? FAILURE.", fieldName), null);
        }
        return splitted;
    }

    public static void handleEmptyField(String field, String fieldName, String source) {
        /**
         * The slashes are there but the field is empty.
         */
        if (field.equals("")) {
            throw new InputFormatException(source,
                    String.format("Haiya %s empty. FAILURE.", fieldName), null);
        }
    }
}
