import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ui {
    
    BufferedReader readingInput;
    Parser currentStatus;

    Ui() {
        this.readingInput = new BufferedReader(new InputStreamReader(System.in));
    }

    public String gettingUserInput() throws IOException {
        String userInput = readingInput.readLine();
        return userInput;
    }

}
