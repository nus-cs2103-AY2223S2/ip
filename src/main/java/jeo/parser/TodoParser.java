package jeo.parser;

import static jeo.parser.JeoParser.TAG_PREFIX;

import java.util.ArrayList;
import java.util.Arrays;

import jeo.command.Command;
import jeo.command.TodoCommand;
import jeo.exception.JeoException;
import jeo.task.ToDo;

/**
 * Parses input arguments and creates a TodoCommand object.
 * @author Goh Jun How
 * @version 0.3
 */
public class TodoParser implements Parser {

    /**
     * Parses the given input arguments and returns a TodoCommand object for execution.
     * @param splitInput user input split by white spaces
     * @return TodoCommand object
     * @throws JeoException Custom error if user input does not conform to the expected format
     */
    @Override
    public Command parse(String[] splitInput) throws JeoException {
        String description = "";
        String tagsString = "";
        int splitInputLength = splitInput.length;
        if (splitInputLength == 1 || splitInput[1].equals(TAG_PREFIX)) {
            throw new JeoException("Please enter a task description after todo.", "todo");
        }
        if (!Arrays.asList(splitInput).contains(TAG_PREFIX)) {
            description = ParserUtil.parseSubArrayString(splitInput, 1, splitInputLength);
            tagsString = String.join("\\", new ArrayList<>());
            assert(!description.isEmpty());
            return new TodoCommand(new ToDo(description, tagsString));
        }
        int indexOfFirstTagPrefix = 0;
        for (int i = 1; i < splitInputLength; i++) {
            if (splitInput[i].equals(TAG_PREFIX)) {
                description = ParserUtil.parseSubArrayString(splitInput, 1, i);
                indexOfFirstTagPrefix = i;
                break;
            }
        }
        ArrayList<String> tags = ParserUtil.parseTags(splitInput, indexOfFirstTagPrefix, "todo");
        tagsString = String.join("\\", tags);
        assert(!description.isEmpty());
        return new TodoCommand(new ToDo(description, tagsString));
    }
}
