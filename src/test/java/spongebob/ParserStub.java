package spongebob;

import spongebob.command.AddCommand;
import spongebob.command.Command;
import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;

public class ParserStub {
    public static Command parse(String input) throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException, SpongebobEventOverlapException {
        if (input.equals("todo do ip")) {
            return new AddCommand(new String[]{"todo", "do ip"});
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
