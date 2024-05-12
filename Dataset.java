import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Data storage.
 * @author Dennis Yiaile
 */
public class Dataset {

    // initialize parameters
    /** Array contains all the items. */
    public ArrayList<Incident> allIncidents;

    /**
     * Constructor reads in content of a file.
     * @param fileName - Name of the file to read
     */
    Dataset(String fileName) {
        try {
            allIncidents = new ArrayList<>();
            File file = new File(fileName);
            Scanner input = new Scanner(file);
            input.nextLine();
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] items = line.split(",");
                Incident person = personInfo(items);
                allIncidents.add(person);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: The specified file was not found");
        }
    }

    /**
     * Extract a person's necessary info from a single row.
     * @param inputs - an array of items in a single row
     * @return - return a new instance of Incident.
     */
    public Incident personInfo(String[] inputs) {
        String sex = inputs[80];
        String race = inputs[81];
        int date = Integer.parseInt(inputs[82]);
        int height2 = Integer.parseInt(inputs[84]);
        int height = Integer.parseInt(inputs[85]);
        int weight = Integer.parseInt(inputs[86]);
        String hair = inputs[87];
        String eye = inputs[88];
        String build = inputs[89];

        return new Incident(date, sex + race, eye + hair, 
            build, weight, height, height2);
    }

    /**
     * Create the array list filled in the constructor.
     * @return - ArrayList of Incidents
     */
    public ArrayList<Incident> getIncidents() {
        return allIncidents;
    }

    /**
     * Compare each item to another to remove deduplication.
     * @return - arrayList of allPairsDeduplication
     */
    public ArrayList<Incident> allPairsDeduplication() {
        ArrayList<Incident> allPairs = new ArrayList<>();
        for (Incident incident : allIncidents) {
            boolean duplicate = false;
            for (Incident incident2 : allPairs) {
                if (incident.equals(incident2)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                allPairs.add(incident);
            }   
        }
        return allPairs;
    }

    /**
     * Deduplication using hashtables linear probing.
     * @return - ArrayList of non-depulicated incidents
     */
    public ArrayList<Incident> hashLinearDeduplication() {
        ArrayList<Incident> hashLinear = new ArrayList<>();
        ProbeHashMap<String, Incident> myMap = new ProbeHashMap<>(1000003);
        for (Incident incident : allIncidents) {
            myMap.put(incident.key(), incident);
        }

        for (Entry<String, Incident> entry: myMap.entrySet()) {
            hashLinear.add(entry.getValue());
        }
        
        System.out.println();
        System.out.println("Average number of probes: " + 
            Double.toString(myMap.getAvgProbes()));
        System.out.println("Max number of probes: " + 
            Integer.toString(myMap.getMaxProbes()));
        System.out.println("Load Factor: " + 
            Double.toString(myMap.getLoadFactor()));

        return hashLinear;
    }

    /**
     * Deduplication using hashtables double probing.
     * @return - ArrayList of non-depulicated incidents
     */
    public ArrayList<Incident> hashDoubleDeduplication() {
        ArrayList<Incident> doubleLinear = new ArrayList<>();
        DoubleHashMap<String, Incident> myMap = new DoubleHashMap<>(1000003);
        for (Incident incident : allIncidents) {
            myMap.put(incident.key(), incident);
        }

        for (Entry<String, Incident> entry: myMap.entrySet()) {
            doubleLinear.add(entry.getValue());
        }

        System.out.println();
        System.out.println("Average number of probes: " + 
            Double.toString(myMap.getAvgProbes()));
        System.out.println("Max number of probes: " + 
            Integer.toString(myMap.getMaxProbes()));
        System.out.println("Load Factor: " + 
            Double.toString(myMap.getLoadFactor()));
        
        return doubleLinear;
    }

    /**
     * Apply built-in sort method to remove duplicates.
     * @return - ArrayList of non-depulicated incidents
     */
    public ArrayList<Incident> builtinSortDeduplication() {
        ArrayList<Incident> builtInSorted = new ArrayList<>(allIncidents);
        Collections.sort(builtInSorted);

        ArrayList<Incident> removeDuplicates = new ArrayList<>();
        int i = 0;
        while (i < builtInSorted.size()) {
            while (i < builtInSorted.size() - 1 && 
                builtInSorted.get(i).compareTo(
                    builtInSorted.get(i + 1)) == 0) {
                i++;
            }
            removeDuplicates.add(builtInSorted.get(i));
            i++;
        }

        return removeDuplicates;
    }

    /**
     * Apply quick sort method to remove duplicates.
     * @return - ArrayList of non-depulicated incidents
     */
    public ArrayList<Incident> quickSortDeduplication() {
        ArrayList<Incident> quickSorted =  new ArrayList<>(allIncidents);
        Utils.quickSort(quickSorted);

        ArrayList<Incident> removeDuplicates = new ArrayList<>();
        int i = 0;
        while (i < quickSorted.size()) {
            while (i < quickSorted.size() - 1 && 
            quickSorted.get(i).compareTo(
                quickSorted.get(i + 1)) == 0) {
                i++;
            }
            removeDuplicates.add(quickSorted.get(i));
            i++;
        }
        return removeDuplicates;
    }
}