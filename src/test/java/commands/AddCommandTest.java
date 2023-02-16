package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dukeexceptions.IllegalCommandException;

public class AddCommandTest {

    @Test
    public void invalidKeywordTest() {
        try {
            AddCommand addCommand = new AddCommand("bob", new ArrayList<>());
        } catch (IllegalCommandException e) {
            assertEquals("Invalid keyword for AddCommand", e.getMessage());
        }
    }
}
