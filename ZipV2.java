import java.io.BufferedReader;
import java.io.FileReader;
public class ZipV2 {
    Node[] data;
    int max = 10000;
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

    public ZipV2(String file) {
        this.data = new Node[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && i < this.max) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
            this.max = i;
        } 
        catch (Exception e) {
        System.out.println(" file " + file + " not found");
        }
    }

    public boolean lookup(Integer wantedCode) {
        for (int index = 0; index < this.max; index++) {
            if (this.data[index].zipCode.equals(wantedCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean binaryLookup(Integer wantedCode) {
        int first = 0;
        int last = this.max -1;
        while (first <= last) {
            // jump to the middle
            int index = (first + last) / 2;
            if (this.data[index].zipCode.equals(wantedCode)) {
                return true;
            }
            if (this.data[index].zipCode.compareTo(wantedCode) < 0) { // && index < last
                // what is the first possible page?
                first = index + 1;
                continue;
            }
            else { //&& index > first (this.postnr[index].zipCode.compareTo(wantedCode) > 0)
                // what is the last possible page?
                last = index - 1;
                continue;
            }
        }
        return false;
    }
}
