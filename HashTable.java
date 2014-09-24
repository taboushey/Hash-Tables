package hashtables;

/*
 * @author Tabetha Boushey
 * Date: 7/3/2013
 * CSCI232 Lab 2
 * Class: HashTable
 */
public class HashTable {

    private DataItem[] hashArray; // arrayholds hash table
    private static final DataItem GHOST = new DataItem(-1, "ghost");
    private int counter;
    private float loadFactor;
    private int threshold;

    // Constructs a new, empty hashtable with the specified initial
    // capacity and the specified load factor
    public HashTable(int arraySize, float loadFactor) {
        if ((arraySize <= 0) || (loadFactor <= 0.0)) {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        hashArray = new DataItem[arraySize];
        threshold = (int) (arraySize * loadFactor);
    }

    // Constructs a new, empty hashtable with the specified initial
    // capacity and default load factor
    public HashTable(int arraySize) {
        this(arraySize, 0.80f);
    }

    public int size() {// Returns the number of keys in the hashtable
        return counter;
    }

    public boolean isEmpty() { // tests if the hashtable is empty
        return counter == 0;
    }

    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < hashArray.length; j++) {
            if (hashArray[j] != null) {
                System.out.print(j + ": " + hashArray[j].toString() + " ");
            } else {
                System.out.print(j + "** ");
            }
        }
        System.out.println("");
    }

    public int hashFunction(int key) {

        return key % hashArray.length;
    }

    public void insert(DataItem item) { // insert a DataItem
        int key = item.getKey(); // extract key
        int hashIndex = hashFunction(key); // hash key until empty cell or -1
        System.out.println("About to insert: " + item.toString());
        displayTable();
        System.out.println("HashIndex: " + hashIndex);
        
        while (hashArray[hashIndex] != null || GHOST.equals(hashArray[hashIndex])) {
            ++hashIndex; // go to next cell
            hashIndex %= hashArray.length; // wraparound if necessary
        }
        hashArray[hashIndex] = item; // insert item
        counter = counter + 1;
        if (counter >= threshold) {
            rehash();
        }
        System.out.println("Inserted: " + item.getStringValue() + "\n-----------------------------------------------");
    }

    public void rehash() {
        System.out.println("Rehashing...");
        DataItem oldHashArray[] = hashArray.clone();
        for (int i = 0; i < oldHashArray.length; i++) {
            if (GHOST.equals(oldHashArray[i])) {
                oldHashArray[i] = null;
                counter--;
            }
        }
        hashArray = new DataItem[oldHashArray.length * 2];
        threshold = (int) (hashArray.length * loadFactor);
        for (int i = 0; i < oldHashArray.length; i++) {
            if (oldHashArray[i] != null) {
                insert(oldHashArray[i]);
            }
        }
        
        System.out.println("rehash old capacity: " + oldHashArray.length + ", new capacity: " + hashArray.length
                + ", thresh: " + threshold + "counter: " + counter);
    }

    public DataItem delete(int key) { // delete a DataItem
        int hashValue = hashFunction(key); // hash the key

        while (hashArray[hashValue] != null || GHOST.equals(hashArray[hashValue])) {
            if (hashArray[hashValue].getKey() == key) {
                DataItem temp = hashArray[hashValue]; // save item
                hashArray[hashValue] = GHOST; // delete item
                return temp; // return item
            }
            ++hashValue; // go to next cell
            hashValue %= hashArray.length; // wraparound if necessary
        }
        return null; // can't find item
    }

    public DataItem find(int key) { // find item with key
        int hashValue = hashFunction(key); // hash the key

        while (hashArray[hashValue] != null || GHOST.equals(hashArray[hashValue])) {
            if (hashArray[hashValue] != null && !GHOST.equals(hashArray[hashValue])) {
                return hashArray[hashValue];
            }
            ++hashValue; // go to next cell
            hashValue %= hashArray.length; // wraparound if necessary
        }
        return null; // can't find item
    }
}
