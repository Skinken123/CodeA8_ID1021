public class BenchmarkHashTables {
    public static void main(String[] args){
        Zip newZip = new Zip("postnummer.csv");
        ZipV2 newZip2 = new ZipV2("postnummer.csv");
        ZipIndex newZipIndex = new ZipIndex("postnummer.csv");
        ZipHashTableOpenAdressing newZipHashTable = new ZipHashTableOpenAdressing("postnummer.csv");
        newZipIndex.countKeys();
        newZipIndex.findModulos();
        newZipHashTable.printCollisions();
        newZipIndex.collisions(15371);

        //benchmarkLookupSimple(newZip);
        //System.out.println("\n");
        //benchmarkLookupSimpleV2(newZip2);
        //System.out.println("\n");
        //benchmarkLookupSimpleIndex(newZipIndex);
    }

    public static void benchmarkLookupSimple(Zip z){
        for (int i = 0; i < 1000; i++){
            z.lookup("976 43");
        }

        String l1 = "111 15";
        String l2 = "984 99";
        int k = 200;

        long min = Long.MAX_VALUE;
        long total = 0;
        for (int i = 0; i < k*5; i++){
            long temp = runLookupString(l1, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/(k*5) + "\n");

        min = Long.MAX_VALUE;
        total = 0;
        for (int i = 0; i < k; i++){
            long temp = runLookupString(l2, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/k + "\n");

        min = Long.MAX_VALUE;
        total = 0;
        for (int i = 0; i < k; i++){
            long temp = runLookupBinary(l1, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/k + "\n");

        min = Long.MAX_VALUE;
        total = 0;
        for (int i = 0; i < k; i++){
            long temp = runLookupBinary(l2, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/k + "\n");
    }

    public static long runLookupString(String lookup, Zip zip){
        int k = 250;
        long n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            zip.lookup(lookup);
        }
        long n2 = System.nanoTime();
        long result = (n2-n1)/k;
        return result;
    }

    public static long runLookupBinary(String lookup, Zip zip){
        int k = 5000;
        long n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            zip.binaryLookup(lookup);
        }
        long n2 = System.nanoTime();
        long result = (n2-n1)/(k);
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void benchmarkLookupSimpleV2(ZipV2 z){
        for (int i = 0; i < 1000; i++){
            z.lookup(97643);
        }

        Integer l1 = 11115;
        Integer l2 = 98499;
        int k = 200;

        long min = Long.MAX_VALUE;
        long total = 0;
        for (int i = 0; i < k*5; i++){
            long temp = runLookupInteger(l1, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/(k*5) + "\n");

        min = Long.MAX_VALUE;
        total = 0;
        for (int i = 0; i < k; i++){
            long temp = runLookupInteger(l2, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/k + "\n");

        min = Long.MAX_VALUE;
        total = 0;
        for (int i = 0; i < k; i++){
            long temp = runLookupBinaryInteger(l1, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/k + "\n");

        min = Long.MAX_VALUE;
        total = 0;
        for (int i = 0; i < k; i++){
            long temp = runLookupBinaryInteger(l2, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/k + "\n");
    }

    public static long runLookupInteger(Integer lookup, ZipV2 zip){
        int k = 250;
        long n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            zip.lookup(lookup);
        }
        long n2 = System.nanoTime();
        long result = (n2-n1)/k;
        return result;
    }

    public static long runLookupBinaryInteger(Integer lookup, ZipV2 zip){
        int k = 5000;
        long n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            zip.binaryLookup(lookup);
        }
        long n2 = System.nanoTime();
        long result = (n2-n1)/(k);
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void benchmarkLookupSimpleIndex(ZipIndex z){
        for (int i = 0; i < 100000; i++){
            z.lookup(97643);
        }

        Integer l1 = 11115;
        Integer l2 = 98499;
        int k = 20000;

        long min = Long.MAX_VALUE;
        long total = 0;
        for (int i = 0; i < k; i++){
            long temp = runLookupIndex(l1, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/(k) + "\n");

        min = Long.MAX_VALUE;
        total = 0;
        for (int i = 0; i < k; i++){
            long temp = runLookupIndex(l2, z);
            if (temp < min) min = temp;
            total += temp;
        }
        System.out.print(min + " " + total/k + "\n");
    }

    public static long runLookupIndex(Integer code, ZipIndex z){
        int k = 5000;
        long n1 = System.nanoTime();
        for (int i = 0; i < k; i++){
            z.lookup(code);
        }
        long n2 = System.nanoTime();
        long result = ((n2-n1)*100)/(k);
        return result;
    }
}
