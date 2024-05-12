/**
 * Hold single row from the CSV.
 * @author Dennis Yiaile
 */
public class Incident implements Comparable<Incident> {
    // initialize parameters
    /** Person's date of birth. */
    public int birth;
    /** Person's sex & race. */
    public String sexrace;
    /** Person's eye and hair color. */
    public String eyehair;
    /** Person's build. */
    public String build;
    /** Person's weight. */
    public int weight;
    /** Person's height in inches. */
    public int height;
    /** Persons height in feet. */
    public int height2;

    /**
     * Create a constructor for each person.
     * @param birth - date of birth
     * @param sexrace  - sex & race
     * @param eye - eye and hair color
     * @param build - build
     * @param weight - weight
     * @param height - height
     */
    Incident(int birth, String sexrace, String eyehair,
        String build, int weight, int height, int height2) {
        this.birth = birth;
        this.sexrace = sexrace;
        this.eyehair = eyehair;
        this.build = build;
        this.weight = weight;
        this.height = height;
        this.height2 = height2;
    }

    /**
     * Check whether two items are considered duplicates.
     * @param o - another string to compare
     * @return - true if equals else false
     */
    public boolean equals(Object o) {
        Incident otherIncident = (Incident) o;
        Boolean equalDate = this.birth == otherIncident.birth;
        Boolean equalWeight = this.weight == otherIncident.weight;
        Boolean equalHeights = this.height == otherIncident.height;
        Boolean equalHeight2 = this.height2 == otherIncident.height2;
        Boolean equalSexRace = this.sexrace.equals(otherIncident.sexrace);
        Boolean equaleyehair = this.eyehair.equals(otherIncident.eyehair);
        Boolean equalbuild = this.build.equals(otherIncident.build);
        return equalDate && equalWeight && equalHeights && equalHeight2 
            && equalSexRace && equalbuild && equaleyehair;
    }

    /**
     * Generate hashCode for the key.
     * @return int - hashKey
     */
    @Override
    public int hashCode() {
        return this.key().hashCode();
    }

    /**
     * Get string representation of a a person's info.
     * @return - String representation of an item
     */
    public String key() {
        return sexrace + eyehair + build + Integer.toString(birth) +
            Integer.toString(weight) + Integer.toString(height) + 
            Integer.toString(height2);
    }
    
    /**
     * Compare incidents for sorting purposes.
     * @param otherIncident - other key to compare
     * @return - int based on comparison
     */
    @Override
    public int compareTo(Incident otherIncident) {
        String otherKey = otherIncident.key();
        return this.key().compareTo(otherKey);
    }
}