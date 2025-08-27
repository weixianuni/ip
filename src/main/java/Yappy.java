import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Yappy {

    static ArrayList<Task> TASKS;
    static Path storagePath = Paths.get("src/main/data", "Yappy.txt");

    /**
     *
     * @param messages
     */
    public static void printFormat(String[] messages) {
        System.out.println("\t____________________________________________________________");
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println("\t____________________________________________________________");
    }

    /**
     *
     * @param input
     * @throws YappyException
     */
    public static void loadTask(String input) throws YappyException{
        String[] params = input.split("\\|");
        switch (params[0]) {
        case "T":
            TASKS.add(new ToDo(params[2], Boolean.parseBoolean(params[1])));
            break;
        case "E":
            TASKS.add(new Event(params[2], Boolean.parseBoolean(params[1]), params[3]));
            break;
        case "D":
            TASKS.add(new Deadline(params[2], Boolean.parseBoolean(params[1]), params[3]));
            break;
        default:
            throw new YappyException("Incorrect format in Yappy.txt file!");
        }
    }

    /**
     *
     */
    private static void writeToFile() throws IOException {
        File file = storagePath.toFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bfw = new BufferedWriter(fw);
        for (Task task : TASKS) {
            bfw.write(task.fileString());
        }
        bfw.close();
    }

    /**
     *
     * @param inputLine
     * @throws YappyException
     */
    public static void readInput(String inputLine) throws YappyException {
        if (inputLine.equals("list")) {
            String[] messages = new String[TASKS.size() + 1];
            messages[0] = "\t\sHere are the TASKS in your list:";
            for (int i = 0; i < TASKS.size(); i++) {
                int num = i + 1;
                Task task = TASKS.get(i);
                messages[num] = "\t\s" + num + "." + task.toString();
            }
            printFormat(messages);
        } else if (inputLine.startsWith("mark")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task you wish to mark as completed!");
            } else if (Integer.parseInt(inputLine.split(" ")[1]) - 1 > TASKS.size()) {
                throw new YappyException("Please specify a valid task index.\n\t\sNow you have "
                        + TASKS.size() + (TASKS.size()==1 ? " task ": " TASKS ") + "in the list.");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            TASKS.get(index).setCompleted();
            printFormat(new String[]{"\t\sAlrighty I have marked this task as completed:"
                    + "\n\t\s\s" + TASKS.get(index).toString()});

        } else if (inputLine.startsWith("unmark")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task you wish to mark as uncompleted!");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            TASKS.get(index).setUncompleted();
            printFormat(new String[]{"\t\sAlrighty I have marked this task as not completed yet:"
                    + "\n\t\s\s" + TASKS.get(index).toString()});

        } else if (inputLine.startsWith("delete")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task you wish to delete");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            TASKS.remove(index);
            printFormat(new String[]{"\t\sAlrighty I have removed the following task:"
                    + "\n\t\s\s" + TASKS.get(index).toString(), "\t\sNow you have "
                    + TASKS.size() + (TASKS.size()==1 ? " task ": " TASKS ") + "in the list."});
        } else {
            if (inputLine.startsWith("todo")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the todo task!");
                }
                String description = inputLine.substring("todo".length()).trim();
                TASKS.add(TASKS.size(), new ToDo(description, false));
            } else if (inputLine.startsWith("event")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the event task and time specifications!");
                }
                String description = inputLine.substring("event".length()).trim().split("/from")[0];
                String when = inputLine.split("/from")[1];
                TASKS.add(TASKS.size(), new Event(description, false, when));
            } else if (inputLine.startsWith("deadline")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the deadline task and deadline!");
                }
                String description = inputLine.substring("deadline".length()).trim().split("/by")[0].strip();
                String by = inputLine.split("/by")[1].strip();
                TASKS.add(TASKS.size(), new Deadline(description, false, by));
            } else {
                throw new YappyException("Sorry!! I do not know what that command is.");
            }

            printFormat(new String[]{"\t\sAdded the following task:\n\t\s\s\s" + TASKS.getLast().toString(),
                    "\t\sNow you have " + TASKS.size() + (TASKS.size() == 1 ? " task ": " tasks ") + "in the list."});
        }
    }



    public static void main(String[] args){
    
        TASKS = new ArrayList<>();
        File file = storagePath.toFile();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    loadTask(line);
                }
            }
            printFormat(new String[]{"\t\sHello! Welcome back!\n" + "\t\sWhat can I do for you?"});
        } catch (FileNotFoundException e) {
            printFormat(new String[]{"\t\sHello! I'm Yappy\n" + "\t\sWhat can I do for you?"});
        } catch (YappyException e) {
            printFormat(new String[]{"\t\s" + e.getMessage()});
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

        Scanner input = new Scanner(System.in);
        while (true) {
            String inputLine = input.nextLine();
            if (inputLine.equals("bye")) {
                break;
            } else {
                try {
                    readInput(inputLine);
                } catch (YappyException e) {
                    printFormat(new String[]{"\t\s" + e.getMessage()});
                }
            }
        }

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }

        printFormat(new String[]{"\tBye. Hope to see you again soon!"});

    }
}
