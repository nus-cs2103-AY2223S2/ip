package duke.task;

import org.junit.jupiter.api.Test;

import duke.query.Query;
import duke.query.QueryParser;

public class QueryParserTest {
    @Test
    public void testParseQuery() {
        String rawQuery = "command";
        parseAndPrintRawQuery(rawQuery);
    }

    @Test
    public void testParseQueryWithShortParam() {
        String rawQuery = "command param";
        parseAndPrintRawQuery(rawQuery);
    }

    @Test
    public void testParseQueryWithLongParam() {
        String rawQuery = "command pam param, pam pam param";
        parseAndPrintRawQuery(rawQuery);
    }

    @Test
    public void testParseQueryWithKeywordButNoArg() {
        String rawQuery = "command pam param, pam pam param /keyword ";
        parseAndPrintRawQuery(rawQuery);
    }

    @Test
    public void testParseQueryWithOneArgument() {
        String rawQuery = "command pam param, pam pam param /keyword hi";
        parseAndPrintRawQuery(rawQuery);
    }

    @Test
    public void testParseQueryWithDuplicateKeyword() {
        String rawQuery = "command pam param, pam pam param /keyword nice /keyword nice2";
        parseAndPrintRawQuery(rawQuery);
    }

    @Test
    public void testParseQueryWithTwoArguments() {
        String rawQuery = "command pam param, pam pam param /keyword1 hi /keyword2 bye";
        parseAndPrintRawQuery(rawQuery);
    }

    private void parseAndPrintRawQuery(String rawQuery) {
        Query q = QueryParser.parseQuery(rawQuery);
        System.out.println(q);
    }
}
