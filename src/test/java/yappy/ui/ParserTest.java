package yappy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import yappy.command.ExitCommand;
import yappy.command.ListCommand;
import yappy.exception.YappyException;



public class ParserTest {

    @Test
    public void testParse_exitCommand_returnExitCommand() throws YappyException {
        assertInstanceOf(ExitCommand.class, Parser.parse("bye"));
    }

    @Test
    public void testParse_listCommand_returnListCommand() throws YappyException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void testParseUnknownCommand() {
        YappyException e = assertThrows(YappyException.class, () -> Parser.parse("foobar"));
        assertEquals("\t Sorry!! I do not know what that command is.", e.getMessage());
    }
}
