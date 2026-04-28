
public class Experiment {
    private final Sorter sorter = new Sorter();
    private final Searcher searcher = new Searcher();
    public long measureSortTime(int[] arr, String type) {
        int[] copy = sorter.copyArray(arr);
        long start = System.nanoTime();
        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(copy);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(copy);
        } else {
            throw new IllegalArgumentException("Unknown sort type: " + type);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.search(arr, target);
        long end = System.nanoTime();
        return end - start;
    }

    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};


        System.out.println("   SORTING AND SEARCHING ALGORITHM PERFORMANCE REPORT");

        for (int size : sizes) {

            int[] randomArr = sorter.generateRandomArray(size);
            int[] sortedArr = sorter.copyArray(randomArr);
            sorter.basicSort(sortedArr);

            System.out.println();
            System.out.println("Array size: " + size + " ");

            long bubbleRandom = measureSortTime(randomArr, "basic");
            long bubbleSorted = measureSortTime(sortedArr, "basic");
            long quickRandom  = measureSortTime(randomArr, "advanced");
            long quickSorted  = measureSortTime(sortedArr, "advanced");

            System.out.printf("Bubble Sort | random  : %,d ns%n", bubbleRandom);
            System.out.printf("Bubble Sort | sorted  : %,d ns%n", bubbleSorted);
            System.out.printf("Quick Sort  | random  : %,d ns%n", quickRandom);
            System.out.printf("Quick Sort  | sorted  : %,d ns%n", quickSorted);

            int target = randomArr[size / 2];
            long searchFound = measureSearchTime(randomArr, target);
            System.out.printf("Linear Search (found)    : %,d ns%n", searchFound);

            long searchMiss = measureSearchTime(randomArr, -1);
            System.out.printf("Linear Search (not found): %,d ns%n", searchMiss);
        }

        System.out.println();
    }
}
