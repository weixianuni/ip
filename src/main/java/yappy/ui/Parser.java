package yappy.ui;

import yappy.command.*;
import yappy.exception.YappyException;
import yappy.task.Deadline;
import yappy.task.Event;
import yappy.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     *
     * @param command
     * @return
     * @throws YappyException
     */
    public static Command parse(String command) throws YappyException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            if (!command.trim().contains(" ")) {
                throw new YappyException("\t Please specify the index of the task to mark as completed!");
            } else {
                try {
                    return new MarkCommand(Integer.parseInt(command.split(" ")[1]) - 1);
                } catch (NumberFormatException e) {
                    throw new YappyException("\t Please input an integer index!");
                }
            }
        } else if (command.startsWith("unmark")) {
            if (!command.trim().contains(" ")) {
                throw new YappyException("\t Please specify the index of the task you wish to mark as uncompleted!");
            } else {
                try {
                    return new UnmarkCommand(Integer.parseInt(command.split(" ")[1]) - 1);
                } catch (NumberFormatException e) {
                throw new YappyException("\t Please input an integer index!");
                }
            }
        } else if (command.startsWith("delete")) {
            if (!command.trim().contains(" ")) {
                throw new YappyException("\t Please specify the index of the task you wish to delete!");
            } else {
                try {
                    return new DeleteCommand(Integer.parseInt(command.split(" ")[1]) - 1);
                } catch (NumberFormatException e) {
                    throw new YappyException("\t Please input an integer index!");
                }

            }
        } else {
            if (command.startsWith("todo")) {
                if (!command.trim().contains(" ")) {
                    throw new YappyException("\t Please specify the todo task!");
                } else {
                    String description = command.substring("todo".length()).trim().split("/from")[0];
                    return new AddCommand(new ToDo(description, false));
                }
            } else if (command.startsWith("event")) {
                if (!command.trim().contains(" ")) {
                    throw new YappyException("\t Please specify the event task and start and end dates!");
                } else {
                    String description = command.substring("event".length()).trim().split("/from")[0];
                    String fromAndTo = command.split("/from")[1];
                    LocalDateTime from = LocalDateTime.parse(fromAndTo.split("/to")[0].strip(), FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(fromAndTo.split("/to")[1].strip(), FORMATTER);
                    return new AddCommand(new Event(description, false, from, to));
                }
            } else if (command.startsWith("deadline")) {
                if (!command.trim().contains(" ")) {
                    throw new YappyException("\t Please specify the deadline task and deadline!");
                } else {
                    String description = command.substring("deadline".length()).trim().split("/by")[0].strip();
                    LocalDateTime by = LocalDateTime.parse(command.split("/by")[1].strip(), FORMATTER);
                    return new AddCommand(new Deadline(description, false, by));
                }
            } else {
                throw new YappyException("\t Sorry!! I do not know what that command is.");
            }
        }
    }
}
