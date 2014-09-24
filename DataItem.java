package hashtables;

/*
 * @author Tabetha Boushey
 * Date: 7/3/2013
 * CSCI232 Lab 2
 * Class: DataItem
 */
public class DataItem {

    private int key; // data item (key)
    private String value;

    public DataItem(int number, String string) { //constructor
        key = number;
        value = string;
    }

    public void setKey(int newKey) {
        this.key = newKey;
    }

    public int getKey() {
        return key;
    }

    public void setString(String newValue) {
        this.value = newValue;
    }

    public String getStringValue() {
        return this.value;
    }

    public static DataItem fromString(String line) {

        String tokens[] = line.split(" "); // split up the current line by spaces into an array
        String number = tokens[0]; // make the first spot in the array numbers
        String string = "";
        for (int i = 1; i < tokens.length; i++) {
            string = string + tokens[i] + " "; // set the rest of the array to be string
        }
        DataItem data = null;
        try {
            int value = Integer.parseInt(number);
            data = new DataItem(value, string);
        } catch (NumberFormatException ex) {
            throw ex;
        }
        System.out.print("ID number: " + number + " name: " + string + "\n"); // print out the number and names one each line in the file
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof DataItem) {
            DataItem that = (DataItem) o;
            if (this.getKey() == that.getKey() && this.getStringValue().equals(that.getStringValue())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getKey() + " " + this.getStringValue() + "]";
    }
}

