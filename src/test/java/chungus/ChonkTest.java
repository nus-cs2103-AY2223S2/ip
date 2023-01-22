package chungus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import chungus.util.Pair;

public class ChonkTest {
    private List<Pair<String, String>> tests = List.of(
            Pair.of("foobar", "6 foobar"),
            Pair.of("foo bar", "7 foo bar"),
            Pair.of("", "0 "),
            Pair.of(" ", "1  "));

    @Test
    public void chonkify() {
        for (Pair<String, String> tt : tests) {
            String in = tt.first();
            String want = tt.second();
            String got = Chonk.chonkify(in);
            assertEquals(want, got);
        }
    }

    @Test
    public void dechonkify_singleChonks() {
        for (Pair<String, String> tt : tests) {
            String in = tt.second();
            String want = tt.first();
            Pair<String, Integer> res = Chonk.dechonkify(in, 0);
            assertEquals(want, res.first());
            assertEquals(in.length(), res.second());
        }
    }

    @Test
    public void dechonkify_multipleChonks() {
        List<Pair<String, List<String>>> tests = List.of(
                Pair.of("3 foo3 bar", List.of("foo", "bar")),
                Pair.of("3 foo7 bar baz", List.of("foo", "bar baz")),
                Pair.of("3 foo0", List.of("foo", "")));
        for (Pair<String, List<String>> tt : tests) {
            String in = tt.first();
            int idx = 0;
            for (String want : tt.second()) {
                Pair<String, Integer> dechonked = Chonk.dechonkify(in, idx);
                assertNotNull(dechonked);
                assertEquals(want, dechonked.first());
                idx = dechonked.second();
            }
        }
    }

    @Test
    public void dechonkify_invalidChonks() {
        List<String> tests = List.of(
                "foobar", // no length value
                "ジャワ",
                "4foo", // no space after length
                "4 foo" // length is too large
        );
        for (String tt : tests) {
            Pair<String, Integer> got = Chonk.dechonkify(tt, 0);
            assertNull(got);
        }
    }
}
