import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ZipIndex {
    Node[] data;
    Integer[] keys = new Integer[9675];
    int max = 15371;
    public class Node {
        Integer zipCode;
        String cityName;
        Integer population;
        Node next;

        public Node(Integer z, String c, Integer p){
            this.zipCode = z;
            this.cityName = c;
            this.population = p;
            this.next = null;
        }
    }

    public ZipIndex(String file) {
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
                    Node current = data[index];
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = new Node(code, row[1], Integer.valueOf(row[2]));
                } 
                this.keys[i++] = code;
            }
        } 
        catch (Exception e) {
        System.out.println(" file " + file + " not found");
        }
    }

    public boolean lookup(Integer wantedCode) {
        Integer index = getHashcode(wantedCode);
        if (this.data[index] != null) {
            Node current = this.data[index];
            while (current != null) {
                if (current.zipCode.equals(wantedCode)) return true;
                else current = current.next;
            }
        }
        return false;
    }

    public Integer getHashcode(Integer input){
        return (input % 15371);
    }

    public void collisions(int mod) {
        int mx = 500;
        int[] data = new int[mod];
        int[] cols = new int[mx];
        
        // keys[] are the zip codes
        for (int i = 0; i < keys.length; i++) {
            Integer index = (this.keys[i] % mod);
            data[index]++;
        }
        int tot = 0;
        for(int i = 0; i< data.length; i++){
            if(data[i] == 1) tot += data[i];
        }
        System.out.println("this is tot: " + tot);

        for(int i = 0; i < mod; i++) {
            if (0 < data[i] && data[i] < mx) {
                cols[data[i]]++;
            }
        }

        System.out.print(mod + ": ");
        for (int i = 1; i < mx; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public void countKeys(){
        Integer count = 0;
        for(Integer i : keys){
            if (i != null) count++;
        }
        System.out.println(count);
    }

    public void findModulos(){
        ArrayList<Integer> listOfModuloOutcomes = new ArrayList<>();
        Integer numberOfColisions = 0;
        Integer modulo = 0;
        for (Integer key : keys){
            modulo = (key % 19275); //17389
            if (listOfModuloOutcomes.contains(modulo)) numberOfColisions++;
            else listOfModuloOutcomes.add(modulo);
        }
        System.out.println("Number of unique indexes: " + listOfModuloOutcomes.size() + " number of collisions: " + numberOfColisions);
    }
}
