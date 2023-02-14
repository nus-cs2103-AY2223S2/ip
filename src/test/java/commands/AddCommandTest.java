package commands;

import dukeexceptions.IllegalCommandException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class AddCommandTest {

    @Test
    public void invalidKeywordTest() {
        try {
            AddCommand addCommand = new AddCommand("bob", new ArrayList<>());
        } catch (IllegalCommandException e){
            assertEquals("Invalid keyword for AddCommand", e.getMessage());
        }
    }
}
