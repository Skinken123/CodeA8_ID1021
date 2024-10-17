import java.io.BufferedReader;
import java.io.FileReader;

public class ZipHashTableOpenAdressing {
    Node[] data;
    int max = 20000;
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
                if (data[index] == null) data[index] = new Node(code, row[1], Integer.valueOf(row[2]));
                else{
                    Integer availableIndex = findNextSlot(index);
                    data[availableIndex] = new Node(code, row[1], Integer.valueOf(row[2]));
                }
            }
        } 
        catch (Exception e) {
        System.out.println(" file " + file + " not found");
        }
    }

    public Integer findNextSlot(Integer index){
        while (data[index] != null) {
            index++;
        }
        return index;
    }

    public boolean lookup(Integer wantedCode) {
        Integer index = getHashcode(wantedCode);
        while (data[index] != null) {
            if(data[index].zipCode.equals(wantedCode)) return true;
            else index++; 
        }
        return false;
    }

    public Integer getHashcode(Integer input){
        return (input % 17389);
    }
}
