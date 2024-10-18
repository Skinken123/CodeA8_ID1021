import java.io.BufferedReader;
import java.io.FileReader;

public class ZipHashTableOpenAdressing {
    Node[] data;
    int max = 20000;
    int[] collectData = new int[500];
    public class Node {
        Integer zipCode;
        String cityName;
        Integer population;

        public Node(Integer z, String c, Integer p){
            this.zipCode = z;
            this.cityName = c;
            this.population = p;
        }
    }

    public ZipHashTableOpenAdressing(String file) {
        this.data = new Node[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Integer code = 0;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code = Integer.valueOf(row[0].replaceAll("\\s",""));
                Integer index = getHashcode(code);
                if (data[index] == null) {
                    data[index] = new Node(code, row[1], Integer.valueOf(row[2]));
                    collectData[0]++; // found empty slot instantly
                }
                else{
                    Integer availableIndex = findNextSlot(index);
                    if (availableIndex == -1) System.out.println("Array is full");
                    else data[availableIndex] = new Node(code, row[1], Integer.valueOf(row[2]));
                }
            }
        } 
        catch (Exception e) {
        System.out.println(" file " + file + " not found");
        }
    }

    public Integer findNextSlot(Integer index){
        Integer startingIndex = index;
        Integer amountOfComparisonsBeforeEmptySpot = 0;
        while (data[index] != null) {
            index = (index + 1) % max; 
            amountOfComparisonsBeforeEmptySpot++;
            if(index.equals(startingIndex)) return -1;
        }
        collectData[amountOfComparisonsBeforeEmptySpot]++; // updates amount of times a ceratin amount of comparisons were needed       
        return index;
    }

    public boolean lookup(Integer wantedCode) {
        Integer index = getHashcode(wantedCode);
        Integer startingIndex = index;
        while (data[index] != null) {
            if(data[index].zipCode.equals(wantedCode)) return true;
            else index = (index + 1) % max; 
            if (index.equals(startingIndex)) return false;
        }
        return false;
    }

    public Integer getHashcode(Integer input){
        return (input % 19275); // 17389 18543
    }

    public void printCollisions(){
        Integer totalEntries = 0;
        Integer totalComparisons = 0;
        System.out.println("Number of comparisons before finding an empty spot:");
        for (int i = 0; i < collectData.length; i++) {
            if (collectData[i] > 0) {
                System.out.println(i + " comparisons happend " + collectData[i] + " times");
                totalEntries += collectData[i];
                totalComparisons += (i * collectData[i]);
            }
        }
        System.out.println("Total entries in array: " + totalEntries);
        System.out.println("Total number of comparisons: " + totalComparisons);
    }
    /*
     * When the array is getting filled up collisions will happen before all unique indexes from the modulo operation have
     * been placed in the array. This means that when a collision happens and we search for a new empty slot it can take the
     * place of a unique modulo index before the key with that index has been placed in the array.
     */
}
