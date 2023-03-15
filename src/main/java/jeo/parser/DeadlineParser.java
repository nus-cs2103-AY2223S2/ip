package jeo.parser;

import jeo.command.Command;
import jeo.command.DeadlineCommand;
import jeo.exception.JeoException;
import jeo.task.Deadline;

import java.util.ArrayList;
import java.util.Arrays;

import static jeo.parser.JeoParser.BY_PREFIX;
import static jeo.parser.JeoParser.TAG_PREFIX;

public class DeadlineParser implements Parser {

    @Override
    public Command parse(String[] splitInput) throws JeoException {
        String description = "";
        String by = "";
        String tagsString = "";
        int splitInputLength = splitInput.length;
        if (splitInputLength == 1 || splitInput[1].equals(BY_PREFIX) || splitInput[1].equals(TAG_PREFIX)) {
            throw new JeoException("Please enter a task description after deadline.", "deadline");
        }
        if (!Arrays.asList(splitInput).contains(BY_PREFIX)) {
            throw new JeoException("Missing the /by prefix!", "deadline");
        }
        int indexOfFirstByPrefix = 0;
        for (int i = 1; i < splitInputLength; i++) {
            boolean argIsByPrefix = splitInput[i].equals(BY_PREFIX);
            if (argIsByPrefix) {
                description = ParserUtil.parseSubArrayString(splitInput, 1, i);
                indexOfFirstByPrefix = i;
                break;
            }
        }
        if (indexOfFirstByPrefix+1 == splitInputLength || indexOfFirstByPrefix+2 == splitInputLength) {
            throw new JeoException("Missing valid date after prefix /by", "deadline");
        }
        by = splitInput[indexOfFirstByPrefix+1] + " " + splitInput[indexOfFirstByPrefix+2];
        if (indexOfFirstByPrefix+3 != splitInputLength && splitInput[indexOfFirstByPrefix+3].equals(TAG_PREFIX)) {
            tagsString = String.join("\\", ParserUtil.parseTags(
                    splitInput, indexOfFirstByPrefix+3, "deadline"));
        } else if (indexOfFirstByPrefix+3 != splitInputLength) {
            throw new JeoException("Invalid command format!", "deadline");
        } else {
            tagsString = String.join("\\", new ArrayList<>());
        }
        return new DeadlineCommand(new Deadline(description, by, tagsString));
    }
}
