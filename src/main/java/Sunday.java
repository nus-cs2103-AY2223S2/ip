import command.Command;
import exceptions.SundayException;
import utilities.Parser;
import utilities.Ui;

/**
 * Sunday is the main class that runs the application.
 *
 * @author Tan Yan-Hao Joshua
 *
 */
public class Sunday {

    public String getResponse(String input) {
        String response = "";
        try {
            String description = input.substring(input.split(" ")[0].length());
            Command command = Parser.parse(input);
            response = command.execute(description);
        } catch (SundayException e) {
            response = Ui.showException(e);
        }
        return response;
    }
}
