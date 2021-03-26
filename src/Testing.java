import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Testing {
    ArrayList<Element[]> allInputs;

    public void createAllInputs (int start, int end, int count, int bound) { // Generates random arrays
        allInputs = new ArrayList<>();
        for (int i = start; i < end; i *= count) {
            Element[] elements = Element.createArrayOfRandom(i, bound);
            allInputs.add(elements);
        }
    }

    public void createAllInputs (int start, int size, int count, boolean dir) { // Generates sorted arrays
        allInputs = new ArrayList<>();
        for (int i = start; i < size; i *= count) {
            Element[] temp = Element.createSortedArray(i, dir);
            allInputs.add(temp);
        }
    }

    // Runs on one algorithm with random inputs. Alg number is number in Sorting.sort() algorithm
    public void testAlg (ArrayList<Element[]> allInputs, int algNumber, FileWriter fw) {
        try {
            fw.write(Sorting.functionNames[algNumber] + ","); // Visual
            for (Element[] elementsOrig : allInputs) {
                Element[] elements = new Element[elementsOrig.length]; // Create a new array
                System.arraycopy(elementsOrig, 0, elements, 0, elementsOrig.length); // Copy the contents of the array to new array

                long start = System.nanoTime(); // Save the starting time
                Sorting.sort(elements, algNumber); // Run sorting
                long elapsed = System.nanoTime() - start; // Calculate elapsed time
                long elapsedMicro = elapsed / 1000; // Microsecond conversion

                if (!Element.checkSorted(elements)) {
                    System.out.println("Algorithm does not work!");
                    Element.displayArray(elements);
                    System.exit(1);
                }

                System.out.println(Sorting.functionNames[algNumber] + " Elapsed time for input of size " + elements.length + " : " + elapsed);
                fw.write(elapsedMicro + ",");
            }
            fw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runTest (String fileName) { // Runs testAlg for all inputs
        try {
            FileWriter fw = new FileWriter(fileName);

            printSizes(fw);

            for (int i = 0; i < 5; i++) {
                testAlg(allInputs, i, fw);
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSizes (FileWriter fw) { // Print the input sizes and write to the top of the file
        try{
            fw.write("Algorithm,");
            System.out.println("Algorithm,");
            for (Element[] elements : allInputs) {
                System.out.print(elements.length + ",");
                fw.write(elements.length + ",");
            }
            fw.write("\n");
            System.out.println("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Checks for stability on an algorithm. algNumber is the key in Sorting.sort()
    public void stabilityTest (Element[] input, int algNumber) {
        Element[] elements = new Element[input.length];
        System.arraycopy(input, 0, elements, 0, input.length);

        Sorting.sort(elements, algNumber);
        if (Element.checkSorted(elements)) {
            String isStable = Element.checkStable(elements) ? "Stable" : "Unstable";

            System.out.println(Sorting.functionNames[algNumber] + " Algorithm is " + isStable);
        }
    }
}
