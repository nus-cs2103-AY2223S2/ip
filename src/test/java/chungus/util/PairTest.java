package chungus.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PairTest {
    @Test
    public void itWorks() {
        Integer[] xs = { 1, 2, 3 };
        String[] ys = { "a", "b", "c" };
        for (int i = 0; i < Math.min(xs.length, ys.length); i++) {
            Pair<Integer, String> pair = new Pair<>(xs[i], ys[i]);
            assertEquals(xs[i], pair.first());
            assertEquals(ys[i], pair.second());

            pair = Pair.of(xs[i], ys[i]);
            assertEquals(xs[i], pair.first());
            assertEquals(ys[i], pair.second());
        }
    }
}
