package hashtables;

import java.io.*;

/*
 * @author Tabetha Boushey
 * Date: 7/3/2013
 * CSCI232 Lab 2
 * Class: HashTableApp
 */
public class HashTableApp {

    public static void main(String[] args) throws IOException {

        DataItem aDataItem;
        int aKey = 0;
        int size = 5;

        HashTable theHashTable = new HashTable(size); // make table

        while (true) { // interact with user
            System.out.print("Enter first letter of show, delete, find, or readFile: ");
            char choice = getChar();

            switch (choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'd':
                    System.out.print("Enter data value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter data value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if (aDataItem != null) {
                        System.out.println("Found " + aKey);
                    } else {
                        System.out.println("Could not find " + aKey);
                    }
                    break;
                case 'r':
                    System.out.println("Printing input from file.");
                    try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) { // read in file
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            if (line.length() != 0) {
                                theHashTable.insert(DataItem.fromString(line));
                            }
                        }
                        br.close();
                    } catch (NumberFormatException ex) {
                        throw ex;
                    }
                    break;
                default:
                    System.out.print("Invalid entry \n");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
