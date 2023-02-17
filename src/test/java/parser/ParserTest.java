package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.DukeException;
import response.CreateResponse;
import response.DeadlineResponse;
import response.DeleteResponse;
import response.EventResponse;
import response.FindResponse;
import response.ListResponse;
import response.MarkResponse;
import response.Response;
import response.UnMarkResponse;

/**
 * Class to test all parsing returns the correct response to be executed
 */
public class ParserTest {
    @Test
    public void testListParse() {
        Parser parser = new Parser("list");
        Response actual = parser.parse();
        Response expected = new ListResponse();
        assertEquals(expected, actual);
    }
    @Test
    public void testMarkParse() {
        Parser parser = new Parser("mark 1");
        Response actual = parser.parse();
        Response expected = new MarkResponse("1");
        assertEquals(expected, actual);
    }
    @Test
    public void testUnMarkParse() {
        Parser parser = new Parser("unmark 1");
        Response actual = parser.parse();
        Response expected = new UnMarkResponse("1");
        assertEquals(expected, actual);
    }
    @Test
    public void testDeleteParse() {
        Parser parser = new Parser("delete 1");
        Response actual = parser.parse();
        Response expected = new DeleteResponse("1");
        assertEquals(expected, actual);
    }
    @Test
    public void testTodoParse() {
        Parser parser = new Parser("todo test1");
        Response actual = parser.parse();
        Response expected = new CreateResponse("test1");
        assertEquals(expected, actual);
    }
    @Test
    public void testDeadlineParse() {
        Parser parser = new Parser("deadline test2 /by 2023-01-01");
        Response actual = parser.parse();
        Response expected = new DeadlineResponse("test2 /by 2023-01-01");
        assertEquals(expected, actual);
    }
    @Test
    public void testEventParse() {
        Parser parser = new Parser("event test3 /from 2023-01-01 /to 2023-01-02");
        Response actual = parser.parse();
        Response expected = new EventResponse("test3 /from 2023-01-01 /to 2023-01-02");
        assertEquals(expected, actual);
    }
    @Test
    public void testFindParse() {
        Parser parser = new Parser("find something");
        Response actual = parser.parse();
        Response expected = new FindResponse("something");
        assertEquals(expected, actual);
    }
    @Test
    public void testErrorParse() {
        Parser parser = new Parser("hello");
        DukeException exception = assertThrows(DukeException.class, () -> {
            parser.parse();
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "I'm sorry, but I don't know what that means :-(";
        assertEquals(expectedMessage, actualMessage);
    }
}
