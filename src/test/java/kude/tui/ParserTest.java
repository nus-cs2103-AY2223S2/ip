package kude.tui;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void empty() {
        var parser = new Parser("");
        assertEquals("", parser.getCommand());
        assertEquals(Optional.empty(), parser.getArg());
    }

    @Test
    public void one() {
        var parser = new Parser("list");
        assertEquals("list", parser.getCommand());
        assertEquals(Optional.empty(), parser.getArg());

        parser = new Parser("list ");
        assertEquals("list", parser.getCommand());
        assertEquals(Optional.empty(), parser.getArg());
    }

    @Test
    public void namedArgs() {
        var parser = new Parser("cmd arg /arg0 v /arg1 v2");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("v"), parser.getNamedArg("arg0"));
        assertEquals(Optional.of("v2"), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgsNoArg() {
        var parser = new Parser("cmd /arg0 v /arg1 v2");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.empty(), parser.getArg());
        assertEquals(Optional.of("v"), parser.getNamedArg("arg0"));
        assertEquals(Optional.of("v2"), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgsIncomplete() {
        var parser = new Parser("cmd arg /arg0 v /arg1");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("v /arg1"), parser.getNamedArg("arg0"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgsIncompleteWithSpace() {
        var parser = new Parser("cmd arg /arg0 v /arg1 ");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("v /arg1"), parser.getNamedArg("arg0"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgsWithSlashValues() {
        var parser = new Parser("cmd arg /arg0 1/2/3");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("1/2/3"), parser.getNamedArg("arg0"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgsWithSlashValuesAndSpace() {
        var parser = new Parser("cmd arg /arg0 1/2/3 ");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("1/2/3"), parser.getNamedArg("arg0"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }

    @Test
    public void namedArgsWithSlashValuesAndAnotherArg() {
        var parser = new Parser("cmd arg /arg0 1/2/3 /arg1 v");
        assertEquals("cmd", parser.getCommand());
        assertEquals(Optional.of("arg"), parser.getArg());
        assertEquals(Optional.of("1/2/3"), parser.getNamedArg("arg0"));
        assertEquals(Optional.of("v"), parser.getNamedArg("arg1"));
        assertEquals(Optional.empty(), parser.getNamedArg("arg2"));
    }
}
