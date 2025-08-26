import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;



public class Yappy {

    static int numOfItems;
    static ArrayList<Task> library = new ArrayList<>();
    static Path storagePath = Paths.get("data", "Yappy.txt");


    public static void printFormat(String[] messages) {
        System.out.println("\t____________________________________________________________");
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void loadTasks(String input) throws YappyException{
        String[] params = input.split("\\|");
        switch (params[0]) {
        case "T":
            library.add(new ToDo(params[1]));
            break;
        case "E":
            library.add(new Event(params[1], params[2]));
            break;
        case "D":
            library.add(new Deadline(params[1], params[2]));
        default:
            throw new YappyException("Incorrect format in Yappy.txt file!");
        }
    }

    private static void writeToFile(File filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void readInput(String inputLine) throws YappyException {
        if (inputLine.equals("list")) {

            String[] messages = new String[numOfItems + 1];
            messages[0] = "\t\sHere are the tasks in your list:";
            for (int i = 0; i < numOfItems; i++) {
                int num = i + 1;
                Task task = library.get(i);
                messages[i + 1] = "\t\s" + num + "." + task.toString();
            }
            printFormat(messages);
        } else if (inputLine.startsWith("mark")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task you wish to mark as completed!");
            } else if (Integer.parseInt(inputLine.split(" ")[1]) - 1 > numOfItems) {
                throw new YappyException("Please specify a valid task index.\n\t\sNow you have " + numOfItems + (numOfItems==1 ? " task ": " tasks ") + "in the list.");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            library.get(index).setCompleted();
            printFormat(new String[]{"\t\sAlrighty I have marked this task as completed:" + "\n\t\s\s" + library.get(index).toString()});

        } else if (inputLine.startsWith("unmark")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task you wish to mark as uncompleted!");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            library.get(index).setUncompleted();
            printFormat(new String[]{"\t\sAlrighty I have marked this task as not completed yet:" + "\n\t\s\s" + library.get(index).toString()});

        } else if (inputLine.startsWith("delete")) {
            if (!inputLine.trim().contains(" ")) {
                throw new YappyException("Please specify the index of the task you wish to delete");
            }
            int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
            library.remove(index);
            numOfItems--;
            printFormat(new String[]{"\t\sAlrighty I have removed the following task:" + "\n\t\s\s" + library.get(index).toString(), "\t\sNow you have " + + numOfItems + (numOfItems==1 ? " task ": " tasks ") + "in the list."});
        } else {
            String line = "";

            if (inputLine.startsWith("todo")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the todo task!");
                }
                String description = inputLine.substring("todo".length()).trim();
                line = "T | " + description;
                library.add(numOfItems, new ToDo(description));
            } else if (inputLine.startsWith("event")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the event task and time specifications!");
                }
                String description = inputLine.substring("event".length()).trim().split("/from")[0];
                String when = inputLine.split("/from")[1];
                line = "E | " + description + " | " + when;
                library.add(numOfItems, new Event(description, when));
            } else if (inputLine.startsWith("deadline")) {
                if (!inputLine.trim().contains(" ")) {
                    throw new YappyException("Please specify the deadline task and deadline!");
                }
                String description = inputLine.substring("deadline".length()).trim().split("/by")[0].strip();
                String by = inputLine.split("/by")[1].strip();
                line = "D | " + description + " | " + by;
                library.add(numOfItems, new Deadline(description, by));
            } else {
                throw new YappyException("Sorry!! I do not know what that command is.");
            }

            numOfItems++;

            File file = storagePath.toFile();
            try {
                writeToFile(file, line);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            printFormat(new String[]{"\t\sAdded the following task:\n\t\s\s\s" + library.get(numOfItems - 1).toString(),
                    "\t\sNow you have " + numOfItems + (numOfItems==1 ? " task ": " tasks ") + "in the list."});
        }
    }



    public static void main(String[] args){

        File file = storagePath.toFile();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                loadTasks(line);
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
            }
            try {
                readInput(inputLine);
            } catch (YappyException e) {
                printFormat(new String[]{"\t\s" + e.getMessage()});
            }
        }

        printFormat(new String[]{"\tBye. Hope to see you again soon!"});

    }
}
