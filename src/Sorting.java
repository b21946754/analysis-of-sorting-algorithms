public class Sorting {
    public static String[] functionNames = {"combSort", "gnomeSort", "shakerSort", "stoogeSort", "bitonicSort"};
    // A switch to decide which sorting algorithm to use, static but useful for looping
    public static void sort(Element[] elements, int algNumber) {
        switch (algNumber) {
            case 0 :
                Sorting.combSort(elements);
                break;
            case 1 :
                Sorting.gnomeSort(elements);
                break;
            case 2 :
                Sorting.shakerSort(elements);
                break;
            case 3 :
                Sorting.stoogeSort(elements);
                break;
            case 4 :
                Sorting.bitonicSort(elements);
                break;
        }
    }

    public static void swap (Element[] array, int a, int b) {
        Element temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void combSort (Element[] input) {
        int gap = input.length;
        double shrink = 1.3;
        boolean sorted = false;

        while (!sorted) {
            if (gap > 1) { // 1 is the lower bound for gap size
                gap = (int)(gap / shrink);
            }
            if (gap <= 1) { // When gap size reaches 1, mark sorted before every iteration of list
                sorted = true;
            }
            for (int i = 0; i + gap < input.length; i++) { // Iterate once
                // If the two items are not in order, swap them and mark this whole iteration unsorted
                if (input[i].number > input[i + gap].number) {
                    swap(input, i, i + gap);
                    sorted = false;
                }
            }
        }
    }

    public static void gnomeSort (Element[] input) {
        int pos = 0;
        while (pos != input.length) {
            if (pos == 0 || input[pos].number >= input[pos - 1].number) {
                pos += 1;
            } else {
                swap(input, pos - 1, pos);
                pos -= 1;
            }
        }
    }

    public static void shakerSort (Element[] input) {
        boolean sorted;
        do {
            sorted = true;
            for (int i = 0; i < input.length - 2; i++) { // Iterate forward
                if (input[i].number > input[i + 1].number) {
                    swap(input, i, i + 1);
                    sorted = false;
                }
            }
            if (sorted) // Intermediate sort check
                break;
            sorted = true;
            for (int i = input.length - 2; i > 0; i--) { // Iterate backwards
                if (input[i].number > input[i + 1].number) {
                    swap(input, i, i + 1);
                    sorted = false;
                }
            }
        } while (!sorted);
    }

    // Wrapper method for recursive method : stoogeSort(Element[], int, int)
    public static void stoogeSort (Element[] input) {
        stoogeSort(input, 0, input.length - 1);
    }

    public static void stoogeSort (Element[] input, int i, int j) {
        if (input[i].number > input[j].number) {
            swap(input, i, j);
        }
        if (j - i + 1 > 2) {
            int t = (j - i + 1) / 3;
            stoogeSort(input, i, j - t);
            stoogeSort(input, i + t, j);
            stoogeSort(input, i, j - t);
        }
    }

    private static void compAndSwap (Element[] arr, int i, int j, boolean dir) { // Used in bitonicSort
        if (arr[i].number < arr[j].number && !dir || arr[i].number > arr[j].number && dir) {
            swap(arr, i, j);
        }
    }

    private static void bitonicMerge (Element[] arr, int low, int cnt, boolean dir) { // Used in bitonicSort
        if (cnt > 1) {
            int k = cnt / 2;
            for (int i = low; i < low + k; i++) {
                compAndSwap(arr, i, i + k, dir);
            }
            bitonicMerge(arr, low, k, dir);
            bitonicMerge(arr, low + k, k, dir);
        }
    }

    public static void bitonicSort (Element[] input, int low, int cnt, boolean dir) {
        if (cnt > 1) {
            int k = cnt / 2;

            bitonicSort(input, low, k, true);
            bitonicSort(input, low + k, k, false);

            bitonicMerge(input, low, cnt, dir);
        }
    }

    public static void bitonicSort (Element[] input) { // Wrapper for bitonicSort(Element[], int, int, boolean)
        bitonicSort(input, 0, input.length, true);
    }
}
