
/**
 * This is the main entry to the deduplication program.
 * @author Dennis Yiaile
 */

 public class Program {

    /**
     * This is the entry method to the entire program.
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No command line arguments supplied");
        } else {
            for (String fileName : args) {
                
                Dataset dataStored = new Dataset(fileName);
                System.out.println(dataStored.getIncidents().size());
                System.out.println(dataStored.allPairsDeduplication().size());
                System.out.println(dataStored.hashLinearDeduplication().size());
                System.out.println(dataStored.hashDoubleDeduplication().size());
                System.out.println(dataStored.builtinSortDeduplication().
                    size());
                System.out.println(dataStored.quickSortDeduplication().size());
            }
        }
    }
}