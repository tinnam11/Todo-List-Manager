// This class is a Todo list which allows users to
// add and remove items from the list as well as load
// and save files containing a list

import java.util.*;
import java.io.*;
public class Main {
    public static final boolean EXTENSION_FLAG = true; //class constant to run the extension part

    // the main method will have a scanner and ask for user's
    // to input their choice of what to do in the program until
    // the user decides to quit. The main method should call
    // the different methods depending on the user's input and
    // diagnose an unknown output when the user inputs a string
    // that is not one of the choices
    public static void main(String[] args) throws FileNotFoundException {
        // TODO: Your Code Here
        List<String> toDo = new ArrayList<String>();
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to your TODO List Manager!");
        System.out.println("What would you like to do?");
        if (EXTENSION_FLAG == false){
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs," +
                    "(S)ave TODOs, (Q)uit? ");
            String input = console.nextLine();
            while(!input.equalsIgnoreCase("q")) { //while loop to run the program
                if (input.equalsIgnoreCase("a")){
                    addItem(console,toDo);
                    printTodos(toDo);
                }else if(input.equalsIgnoreCase("m")){
                    markItemAsDone(console, toDo);
                    printTodos(toDo);
                }else if(input.equalsIgnoreCase("l")){
                    loadItems(console, toDo);
                }else if(input.equalsIgnoreCase("s")){
                    saveItems(console, toDo);
                    printTodos(toDo);
                }else { //condition of unknown output
                    System.out.println("Unknown input: " + input);
                    printTodos(toDo);
                }
                System.out.println("What would you like to do?");
                System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs," +
                        "(S)ave TODOs, (Q)uit? ");
                input = console.nextLine(); //reloads the input
            }
        }else{ //methods to call when extension flag is on
            System.out.print("(A)dd TODO, (M)ark TODO as done, (Q)uit? ");
            String input = console.nextLine();
            while (!input.equalsIgnoreCase("q")){ //while loop to run the program
                if (input.equalsIgnoreCase("a")){
                    addWithDueDates(console, toDo);
                    printTodos(toDo);
                } else if(input.equalsIgnoreCase("m")){
                    markItemAsDone(console, toDo);
                    printTodos(toDo);
                } else { //condition of unknown output
                    System.out.println("Unknown input: " + input);
                    printTodos(toDo);
                }
                System.out.println("What would you like to do?");
                System.out.print("(A)dd TODO, (M)ark TODO as done, (Q)uit? ");
                input = console.nextLine();
            }
        }
    }

    // the method prints the list of Todo items
    // Parameters:
    //    - List - The list of items that needs to be printed out
    // Returns: the following method has no return values and just prints out
    //          the todo list. If the list is empty, the program will print that
    //          there is nothing to do. The method would print out in a different
    //          format when the EXTENSION_FLAG is false or true.
    public static void printTodos(List<String> todos) {
        // TODO: Your Code Here
        if (EXTENSION_FLAG == false) {
            if (todos.isEmpty() == true) {
                System.out.println("Today's TODOs:");
                System.out.println("  You have nothing to do yet today! Relax!");
            } else if (todos.isEmpty() == false) {
                System.out.println("Today's TODOs:");
                int count = 1;
                for (int i = 0; i < todos.size(); i++ ){ //loops through the list
                    System.out.println("  " + count + ": " + todos.get(i));
                    count ++;
                }
            }
        }else {
            if (todos.isEmpty() == true){
                System.out.println("Today's TODOs:");
                System.out.println("  You have nothing to do yet today! Relax!");
            }else if (todos.isEmpty() == false){
                System.out.println("Today's TODOs:");
                int count = 1;
                for (int i = 0; i < todos.size(); i+=2){ //loops through List
                    System.out.println("  " + count + ": " + todos.get(i)); //prints task
                    System.out.println("   Due on: " + todos.get(i+1)); //prints due date
                    count ++;
                }
            }
        }
    }

    // the method adds items that user input in the console to the todo
    // list in the desired position
    // Parameters:
    //    - Scanner - scans the item to add and its position
    //    - List - used to find the the range of position
    //                  and to add items to the list
    // Returns: this method has no return values and just adds the items
    //          to the List
    public static void addItem(Scanner console, List<String> todos) {
        System.out.print("What would you like to add? ");
        String itemAdd = console.nextLine();
        if (todos.isEmpty() == true){
            todos.add(itemAdd);
        }else{
            int maxRange = todos.size() + 1;
            System.out.print("Where in the list should it be (1-" + maxRange +
                    ")? (Enter for end): ");
            String numInput = console.nextLine();
            if (numInput.isEmpty()){ //condition where user hits enter
                todos.add(itemAdd);
            }else {
                int indexNum = Integer.parseInt(numInput);
                todos.add(indexNum - 1, itemAdd);
            }
        }
    }

    // the method marks the items as done and removes it from the List
    // depending on what the user chooses to remove
    // Parameters:
    //    - Scanner - scans the integer of the item to remove
    //    - List - used to remove the items from the List
    // Returns: this method has no return values and just removes the items
    //          from the List
    public static void markItemAsDone(Scanner console, List<String> todos) {
        // TODO: Your Code Here
        if (EXTENSION_FLAG == false) {
            if (todos.isEmpty() == true) {
                System.out.println("All done! Nothing left to mark as done!");
            } else{
                System.out.print("Which item did you complete (1-" + todos.size() + ")? ");
                String numInput = console.nextLine();
                int indexNum = Integer.parseInt(numInput);
                todos.remove(indexNum - 1);
            }
        }else { //for when EXTENSION_FLAG is true
            if (todos.isEmpty() == true) {
                System.out.println("All done! Nothing left to mark as done!");
            }else {
                System.out.print("Which item did you complete (1-" + (todos.size()/2) + ")? ");
                String numInput = console.nextLine();
                int indexNum = Integer.parseInt(numInput);
                while (indexNum != 1 && indexNum != todos.size()/2) {
                    System.out.print("Please enter item number in the range (1-" + (todos.size()/2) + ")");
                    numInput = console.nextLine();
                    indexNum = Integer.parseInt(numInput);
                }
                todos.remove((indexNum * 2) - 2); //removes the task
                todos.remove(indexNum); //removes the due date for the task
            }
        }
    }

    // the method loads a .txt file from users input and displays
    // list of todos that is contained in the .txt file.
    // Parameters:
    //    - Scanner - scans the .txt file that needs to be loaded
    //    - List - used as a container to add the list from
    //                  the .txt file to the List
    // Returns: this method has no return values and just overrides the
    //          the List if one is already present with the list
    //          contained in the .txt file
    public static void loadItems(Scanner console, List<String> todos)
            throws FileNotFoundException {
        System.out.print("File name? ");
        String fileName = console.nextLine();
        Scanner fileInput = new Scanner(new File(fileName));
        int countLine = 1;
        int index = 0;
        System.out.println("Today's TODOs:");
        while (fileInput.hasNextLine()){
            String line = fileInput.nextLine();
            System.out.println("  " + countLine + ": " + line);
            countLine++;
            if (index <= todos.size() -1) { //condition to over ride the previous list
                todos.set(index, line); //replaces the previous index within the list
            } else {
                todos.add(index, line); //adds items to the end of the list
            }
            index ++;
        }
    }

    // the method saves the todo list into a .txt file by writing into
    // a txt file that the user could name
    // Parameters:
    //    - Scanner - scans the desired name of the .txt file
    //    - List - used to print the line into the .txt file
    //                  depending on what is present in the list
    // Returns: this method has no return values and just creates
    //          a file and saves the the todo list in the List
    //          to the .txt file.
    public static void saveItems(Scanner console, List<String> todos)
            throws FileNotFoundException {
        System.out.print("File name? ");
        String fileName = console.nextLine();
        PrintStream output = new PrintStream(new File(fileName));
        for (int k = 0; k < todos.size(); k++){
            output.println(todos.get(k));
        }
    }

    // the method is called upon when the EXTENSION_FLAG is true,
    // where it allows users to add multiple tasks with the duedates
    // of each task.
    // Parameters:
    //    - Scanner - scans the number of tasks and due dates to add
    //                into the list
    //    - List - used to save the task and due dates
    // Returns: this method has no return values and just adds the items
    //          with due dates into the List
    public static void addWithDueDates(Scanner console, List<String> todos)
            throws FileNotFoundException {
        System.out.print("How many tasks would you like to add? ");
        String inputString = console.nextLine();
        int numOfItems = Integer.parseInt(inputString);
        int itemNum = 1;
        for (int x = 0; x < numOfItems * 2; x+= 2) { //loop for the task and due date to add
            System.out.print("What is task no. " + itemNum + " that you would like to add? " );
            String task = console.nextLine();
            todos.add(x, task);
            itemNum ++;
            System.out.print("What is the due date (mm/dd/yy) for " + task + "? ");
            String dueDate = console.nextLine();
            while (dueDate.length() != 8) { //while loop for incorrect date format
                System.out.print("Please enter the due date in (mm/dd/yy) format for " + task + ": ");
                dueDate = console.nextLine();
            }
            todos.add(x + 1, dueDate);
        }
    }
}
