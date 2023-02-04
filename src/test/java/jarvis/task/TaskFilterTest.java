package jarvis.task;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import jarvis.exception.command.InvalidParameterException;

public class TaskFilterTest {
    private static final List<String> KEYWORDS = List.of("hello", "world");
    private static final String FROM_DATE_STRING = "2022-01-01";
    private static final String TO_DATE_STRING = "2023-01-01";
    private static final LocalDate FROM_DATE = LocalDate.parse(FROM_DATE_STRING);
    private static final LocalDate TO_DATE = LocalDate.parse(TO_DATE_STRING);

    private final TaskFilter emptyFilter;
    private final TaskFilter fullFilter;

    public TaskFilterTest() {
        emptyFilter = new TaskFilter();
        fullFilter = new TaskFilter();
        try {
            fullFilter
                    .setAfterDate(FROM_DATE_STRING)
                    .setBeforeDate(TO_DATE_STRING)
                    .addKeywords(String.join(" ", KEYWORDS));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isEmptyTest() {
        assert emptyFilter.isEmpty();
        assert !fullFilter.isEmpty();
    }

    @Test
    public void hasNoKeywordsTest() {
        assert emptyFilter.hasNoKeywords();
        assert !fullFilter.hasNoKeywords();
    }

    @Test
    public void hasNoDatesTest() {
        assert emptyFilter.hasNoDates();
        assert !fullFilter.hasNoDates();
    }

    @Test
    public void afterDateTest() {
        assert emptyFilter.getAfterDate() == null;
        assert fullFilter.getAfterDate() != null;
        assert fullFilter.getAfterDate().isEqual(FROM_DATE);
    }

    @Test
    public void beforeDateTest() {
        assert emptyFilter.getBeforeDate() == null;
        assert fullFilter.getBeforeDate() != null;
        assert fullFilter.getBeforeDate().isEqual(TO_DATE);
    }

    @Test
    public void keywordsTest() {
        assert emptyFilter.getKeywords().isEmpty();
        List<String> testKeywords = fullFilter.getKeywords();
        assert testKeywords.size() == KEYWORDS.size();
        for (int i = 0; i < KEYWORDS.size(); i++) {
            assert KEYWORDS.get(i).equalsIgnoreCase(testKeywords.get(i));
        }
    }
}
