public class BenchmarkHashTables {
    public static void main(String[] args){
        Zip newZip = new Zip("postnummer.csv");
        ZipV2 newZip2 = new ZipV2("postnummer.csv");
   
        benchmarkLookupSimple(newZip);
        System.out.println("\n");
        benchmarkLookupSimpleV2(newZip2);
    }

    public static void benchmarkLookupSimple(Zip z){
        for (int i = 0; i < 1000; i++){
            z.lookup("976 43");
        }

        String l1 = "111 15";
        String l2 = "984 99";
        int k = 10000;

        long n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            z.lookup(l1);
        }
        long n2 = System.nanoTime();
        long result = (n2-n1)/k;
        System.out.println(result);

        n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            z.lookup(l2);
        }
        n2 = System.nanoTime();
        result = (n2-n1)/k;
        System.out.println(result);

        n1 = System.nanoTime();
        for (int i = 0; i < k*10; i++){
            z.binaryLookup(l1);
        }
        n2 = System.nanoTime();
        result = (n2-n1)/(k*10);
        System.out.println(result);

        n1 = System.nanoTime();
        for (int i = 0; i < k*10; i++){
            z.binaryLookup(l2);
        }
        n2 = System.nanoTime();
        result = (n2-n1)/(k*10);
        System.out.println(result);
    }

    public static void benchmarkLookupSimpleV2(ZipV2 z){
        for (int i = 0; i < 1000; i++){
            z.lookup(97643);
        }

        Integer l1 = 11115;
        Integer l2 = 98499;
        int k = 10000;

        long n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            z.lookup(l1);
        }
        long n2 = System.nanoTime();
        long result = (n2-n1)/k;
        System.out.println(result);

        n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            z.lookup(l2);
        }
        n2 = System.nanoTime();
        result = (n2-n1)/k;
        System.out.println(result);

        n1 = System.nanoTime();
        for (int i = 0; i < k*10; i++){
            z.binaryLookup(l1);
        }
        n2 = System.nanoTime();
        result = (n2-n1)/(k*10);
        System.out.println(result);

        n1 = System.nanoTime();
        for (int i = 0; i < k*10; i++){
            z.binaryLookup(l2);
        }
        n2 = System.nanoTime();
        result = (n2-n1)/(k*10);
        System.out.println(result);
    }
}
