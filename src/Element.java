import java.util.Random;

public class Element { // The objects that will be sorted
    int number;
    int stabilityNumber;

    protected Element (int number) {
        this.number = number;
    }

    public static Element[] createArrayOfRandom(int size) {
        return createArrayOfRandom(size, 100);
    }

    public static Element[] createArrayOfRandom(int size, int bound) {
        Element[] elements = new Element[size];
        Random random = new Random();
        int stabilityNumber = 0;
        for (int i = 0; i < size; i++) { // Create objects having random numbers and add them to the array
            int randomNumber = random.nextInt(bound + 1);
            Element temp = new Element(randomNumber);
            temp.stabilityNumber = stabilityNumber;
            stabilityNumber++;
            elements[i] = temp;
        }
        return elements;
    }

    public static Element[] createSortedArray (int size, boolean dir) { // Creates sorted array in given order (true : ascending)
        Element[] elements = new Element[size];
        int inc = dir ? 1 : -1;
        int order = 0;
        for (int i = (size - 1) * ((inc + -1) / -2); i < size && i >= 0; i = i + inc) {
            Element temp = new Element(i);
            temp.stabilityNumber = order;
            elements[order] = temp;
            order++;
        }
        return elements;
    }

    public static void displayArray (Element[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.println("Element " + (i + 1) + " | Number : " + arr[i].number
                    + " | Stability Number : " + arr[i].stabilityNumber);
            System.out.println("------------------------");
    }

    public static boolean checkSorted (Element[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].number > arr[i + 1].number) {
                return false;
            }
        }
        return true;
    }
    //Check if elements sharing the same key keep their order of stability keys (dir == true means ascending order)
    public static boolean checkStable (Element[] arr) {
        return checkStable(arr, true);
    }

    public static boolean checkStable (Element[] arr, boolean dir) {
        for (int i = 0; i < arr.length - 1; i++) {
            // If the numbers are same and stability key is decreasing
            if (arr[i].number == arr[i + 1].number && arr[i].stabilityNumber > arr[i + 1].stabilityNumber) {
                return !dir;
            }
        }
        return dir;
    }
}
