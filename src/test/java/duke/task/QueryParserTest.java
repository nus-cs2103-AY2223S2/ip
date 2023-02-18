package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.query.QueryParser;

public class QueryParserTest {
    @Test
    public void parseQuery_noParamNoArg() {
        String rawQuery = "command";
        assertEquals("command : ", QueryParser.parseQuery(rawQuery).toString());
    }

    @Test
    public void parseQuery_shortParam() {
        String rawQuery = "command param";
        assertEquals("command : param", QueryParser.parseQuery(rawQuery).toString());
    }

    @Test
    public void parseQuery_longParam() {
        String rawQuery = "command pam param, pam pam param";
        assertEquals("command : pam param, pam pam param", QueryParser.parseQuery(rawQuery).toString());
    }

    @Test
    public void parseQuery_paramKeywordNoArg() {
        String rawQuery = "command pam param, pam pam param /keyword";
        assertEquals("command : pam param, pam pam param", QueryParser.parseQuery(rawQuery).toString());
    }

    @Test
    public void parseQuery_paramKeywordArg() {
        String rawQuery = "command pam param, pam pam param /keyword hi";
        assertEquals("command : pam param, pam pam param /keyword:hi", QueryParser.parseQuery(rawQuery).toString());
    }

    @Test
    public void parseQuery_paramDuplicateKeyword() {
        String rawQuery = "command pam param, pam pam param /keyword nice /keyword nice2";
        assertEquals("command : pam param, pam pam param /keyword:nice2", QueryParser.parseQuery(rawQuery).toString());
    }

    @Test
    public void parseQuery_paramTwoKeywordArg() {
        String rawQuery = "command pam param, pam pam param /keyword1 hi /keyword2 bye";
        assertEquals("command : pam param, pam pam param /keyword2:bye /keyword1:hi",
                QueryParser.parseQuery(rawQuery).toString());
    }
}
