
public class Main {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Searcher searcher = new Searcher();
        Experiment experiment = new Experiment();
        System.out.println("массив из 10 элементов");
        int[] demo = sorter.generateRandomArray(10);
        System.out.print("Неотсортированный: ");
        sorter.printArray(demo);

        int[] demoBubble = sorter.copyArray(demo);
        sorter.basicSort(demoBubble);
        System.out.print("После Bubble Sort: ");
        sorter.printArray(demoBubble);

        int[] demoQuick = sorter.copyArray(demo);
        sorter.advancedSort(demoQuick);
        System.out.print("После Quick Sort:  ");
        sorter.printArray(demoQuick);

        int target = demo[3];
        int foundAt = searcher.search(demo, target);
        System.out.println("Linear Search для " + target + " -> индекс " + foundAt);
        System.out.println();

        experiment.runAllExperiments();
    }
}
