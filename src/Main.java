import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {
    private static String[] paths = {
            "data/rand1.csv",
            "data/rand2.csv",
            "data/100000_Sales_Records.csv",
            "data/temperature2.csv"
    };
    private static int max = 0;

    public static void main(String[] args) {
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<Integer> dataToSort = new ArrayList<>();

        //Different data sizes
        for(int tmp = 0; tmp < paths.length; tmp++){
            String line = "";
            int x = 0;
            int target = 10000;
            int degreeOfUnsortedness = 0;

            try {
                data.clear();
                Runtime runtime = Runtime.getRuntime();
                BufferedReader br = new BufferedReader(new FileReader(paths[tmp]));
                getCSVLineCount(paths[tmp]);
                br.readLine(); //remove first line

                XYSeries bubbleSort = new XYSeries("Bubble Sort");
                XYSeries insertionSort = new XYSeries("Insertion Sort");
                XYSeries selectionSort = new XYSeries("Selection Sort");
                XYSeries mergeSort = new XYSeries("Merge Sort");
                XYSeries quickSort = new XYSeries("Quick Sort");

                XYSeries bubbleSortSpace = new XYSeries("Bubble Sort");
                XYSeries insertionSortSpace = new XYSeries("Insertion Sort");
                XYSeries selectionSortSpace = new XYSeries("Selection Sort");
                XYSeries mergeSortSpace = new XYSeries("Merge Sort");
                XYSeries quickSortSpace = new XYSeries("Quick Sort");

                bubbleSort.add((double)0, (double) 0);
                insertionSort.add((double)0, (double) 0);
                selectionSort.add((double)0, (double) 0);
                mergeSort.add((double)0, (double) 0);
                quickSort.add((double)0, (double) 0);

                bubbleSortSpace.add((double)0, (double) 0);
                insertionSortSpace.add((double)0, (double) 0);
                selectionSortSpace.add((double)0, (double) 0);
                mergeSortSpace.add((double)0, (double) 0);
                quickSortSpace.add((double)0, (double) 0);

                long timeElapsedBubbleSort = 0;
                long timeElapsedInsertionSort = 0;
                long timeElapsedSelectionSort = 0;
                long timeElapsedMergeSort = 0;
                long timeElapsedQuickSort = 0;

                long spaceComplexityBubbleSort = 0;
                long spaceComplexityInsertionSort = 0;
                long spaceComplexitySelectionSort = 0;
                long spaceComplexityMergeSort = 0;
                long spaceComplexityQuickSort = 0;

                long usedMemoryBefore = 0;
                Instant start= Instant.now();
                Instant finish = Instant.now();
                long usedMemoryAfter = 0;

                while(target < max){
                    for(int i = 0; i < 5; i++){
                        while (x <= target) {
                            line = br.readLine();
                            if(line != null){
                                String[] row = line.split(",");
                                data.add(Integer.parseInt(row[0]));
                            }
                            x++;
                        }

                        //Bubble Sort
                        dataToSort.clear();
                        dataToSort.addAll(data);
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        BubbleSort.bubbleSort(dataToSort);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedBubbleSort += Duration.between(start, finish).toMillis();
                        spaceComplexityBubbleSort += usedMemoryAfter - usedMemoryBefore;

                        //Insertion Sort
                        dataToSort.clear();
                        dataToSort.addAll(data);
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        InsertionSort.insertionSort(dataToSort);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedInsertionSort += Duration.between(start, finish).toMillis();
                        spaceComplexityInsertionSort += usedMemoryAfter - usedMemoryBefore;

                        //Selection Sort
                        dataToSort.clear();
                        dataToSort.addAll(data);
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        SelectionSort.selectionSort(dataToSort);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedSelectionSort += Duration.between(start, finish).toMillis();
                        spaceComplexitySelectionSort += usedMemoryAfter - usedMemoryBefore;

                        //Merge Sort
                        dataToSort.clear();
                        dataToSort.addAll(data);
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        MergeSort.mergeSort(dataToSort);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedMergeSort += Duration.between(start, finish).toMillis();
                        spaceComplexityMergeSort += usedMemoryAfter - usedMemoryBefore;

                        //Quick Sort
                        dataToSort.clear();
                        dataToSort.addAll(data);
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        QuickSort.quickSort(dataToSort);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedQuickSort += Duration.between(start, finish).toMillis();
                        spaceComplexityQuickSort += usedMemoryAfter - usedMemoryBefore;
                    }

                    System.out.println("Bubble Sort -- Target: " + target + " Execution Time: " + timeElapsedBubbleSort/5);
                    System.out.println("Insertion Sort -- Target: " + target + " Execution Time: " + timeElapsedInsertionSort/5);
                    System.out.println("Selection Sort -- Target: " + target + " Execution Time: " + timeElapsedSelectionSort/5);
                    System.out.println("Merge Sort -- Target: " + target + " Execution Time: " + timeElapsedMergeSort/5);
                    System.out.println("Quick Sort -- Target: " + target + " Execution Time: " + timeElapsedQuickSort/5);


                    System.out.println("Bubble Sort -- Target: " + target + " Space Complexity: " + spaceComplexityBubbleSort/5);
                    System.out.println("Insertion Sort -- Target: " + target + " Space Complexity: " + spaceComplexityInsertionSort/5);
                    System.out.println("Selection Sort -- Target: " + target + " Space Complexity: " + spaceComplexitySelectionSort/5);
                    System.out.println("Merge Sort -- Target: " + target + " Space Complexity: " + spaceComplexityMergeSort/5);
                    System.out.println("Quick Sort -- Target: " + target + " Space Complexity: " + spaceComplexityQuickSort/5);

                    bubbleSort.add(target, (double)(timeElapsedBubbleSort/5));
                    insertionSort.add(target, (double)(timeElapsedInsertionSort/5));
                    selectionSort.add(target, (double)(timeElapsedSelectionSort/5));
                    mergeSort.add(target, (double)(timeElapsedMergeSort/5));
                    quickSort.add(target, (double)(timeElapsedQuickSort/5));

                    bubbleSortSpace.add(target, (double)(spaceComplexityBubbleSort/5));
                    insertionSortSpace.add(target, (double)(spaceComplexityInsertionSort/5));
                    selectionSortSpace.add(target, (double)(spaceComplexitySelectionSort/5));
                    mergeSortSpace.add(target, (double)(spaceComplexityMergeSort/5));
                    quickSortSpace.add(target, (double)(spaceComplexityQuickSort/5));

                    target += 10000;
                }

                //Generate Graphs
                XYSeriesCollection dataset = new XYSeriesCollection();
                dataset.addSeries(bubbleSort);
                dataset.addSeries(insertionSort);
                dataset.addSeries(selectionSort);
                dataset.addSeries(mergeSort);
                dataset.addSeries(quickSort);

                JFreeChart chart = ChartFactory.createXYLineChart("Execution Time Vs. Data Length for Different Sorting Techniques: Dataset " + (tmp + 1),
                        "Size", "Execution Time", dataset);

                generateChart(dataset, chart, "TimeComplexityDataset" + (tmp + 1) + ".jpeg");

                XYSeriesCollection datasetSpace = new XYSeriesCollection();
                datasetSpace.addSeries(bubbleSortSpace);
                datasetSpace.addSeries(insertionSortSpace);
                datasetSpace.addSeries(selectionSortSpace);
                datasetSpace.addSeries(mergeSortSpace);
                datasetSpace.addSeries(quickSortSpace);

                JFreeChart chartSpace = ChartFactory.createXYLineChart("Memory Usage Vs. Data Length for Different Sorting Techniques: Dataset " + (tmp + 1),
                        "Size", "Memory Used", datasetSpace);

                generateChart(datasetSpace, chartSpace, "SpaceComplexityDataset" + (tmp + 1) + ".jpeg");

                //dataToSort is already sorted here
                //Different degree of unsortedness
                bubbleSort.clear();
                insertionSort.clear();
                selectionSort.clear();
                mergeSort.clear();
                quickSort.clear();

                bubbleSortSpace.clear();
                insertionSortSpace.clear();
                selectionSortSpace.clear();
                mergeSortSpace.clear();
                quickSortSpace.clear();

                ArrayList<ArrayList<Integer>> unsortedList = new ArrayList<>();
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 0, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 10, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 20, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 30, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 40, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 50, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 60, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 70, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 80, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 90, dataToSort.size()));
                unsortedList.add(GenerateUnsortedList.getUnsortedArray(dataToSort, 100, dataToSort.size()));

                for(ArrayList<Integer> list : unsortedList){
                    for(int i = 0; i < 5; i++){
                        //Bubble Sort
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        BubbleSort.bubbleSort(list);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedBubbleSort += Duration.between(start, finish).toMillis();
                        spaceComplexityBubbleSort += usedMemoryAfter - usedMemoryBefore;

                        //Insertion Sort
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        InsertionSort.insertionSort(list);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedInsertionSort += Duration.between(start, finish).toMillis();
                        spaceComplexityInsertionSort += usedMemoryAfter - usedMemoryBefore;

                        //Selection Sort
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        SelectionSort.selectionSort(list);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedSelectionSort += Duration.between(start, finish).toMillis();
                        spaceComplexitySelectionSort += usedMemoryAfter - usedMemoryBefore;

                        //Merge Sort
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        MergeSort.mergeSort(list);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedMergeSort += Duration.between(start, finish).toMillis();
                        spaceComplexityMergeSort += usedMemoryAfter - usedMemoryBefore;

                        //Quick Sort
                        runtime.gc();
                        usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        start = Instant.now();
                        QuickSort.quickSort(list);
                        finish = Instant.now();
                        usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        timeElapsedQuickSort += Duration.between(start, finish).toMillis();
                        spaceComplexityQuickSort += usedMemoryAfter - usedMemoryBefore;
                    }

                    System.out.println("Unsortedness Bubble Sort -- Degree: " + degreeOfUnsortedness + " Execution Time: " + timeElapsedBubbleSort/5);
                    System.out.println("Unsortedness Insertion Sort -- Degree: " + degreeOfUnsortedness + " Execution Time: " + timeElapsedInsertionSort/5);
                    System.out.println("Unsortedness Selection Sort -- Degree: " + degreeOfUnsortedness + " Execution Time: " + timeElapsedSelectionSort/5);
                    System.out.println("Unsortedness Merge Sort -- Degree: " + degreeOfUnsortedness + " Execution Time: " + timeElapsedMergeSort/5);
                    System.out.println("Unsortedness Quick Sort -- Degree: " + degreeOfUnsortedness + " Execution Time: " + timeElapsedQuickSort/5);


                    System.out.println("Unsortedness Bubble Sort -- Degree: " + degreeOfUnsortedness + " Space Complexity: " + spaceComplexityBubbleSort/5);
                    System.out.println("Unsortedness Insertion Sort -- Degree: " + degreeOfUnsortedness + " Space Complexity: " + spaceComplexityInsertionSort/5);
                    System.out.println("Unsortedness Selection Sort -- Degree: " + degreeOfUnsortedness + " Space Complexity: " + spaceComplexitySelectionSort/5);
                    System.out.println("Unsortedness Merge Sort -- Degree: " + degreeOfUnsortedness + " Space Complexity: " + spaceComplexityMergeSort/5);
                    System.out.println("Unsortedness Quick Sort -- Degree: " + degreeOfUnsortedness + " Space Complexity: " + spaceComplexityQuickSort/5);

                    bubbleSort.add(degreeOfUnsortedness, (double)(timeElapsedBubbleSort/5));
                    insertionSort.add(degreeOfUnsortedness, (double)(timeElapsedInsertionSort/5));
                    selectionSort.add(degreeOfUnsortedness, (double)(timeElapsedSelectionSort/5));
                    mergeSort.add(degreeOfUnsortedness, (double)(timeElapsedMergeSort/5));
                    quickSort.add(degreeOfUnsortedness, (double)(timeElapsedQuickSort/5));

                    bubbleSortSpace.add(degreeOfUnsortedness, (double)(spaceComplexityBubbleSort/5));
                    insertionSortSpace.add(degreeOfUnsortedness, (double)(spaceComplexityInsertionSort/5));
                    selectionSortSpace.add(degreeOfUnsortedness, (double)(spaceComplexitySelectionSort/5));
                    mergeSortSpace.add(degreeOfUnsortedness, (double)(spaceComplexityMergeSort/5));
                    quickSortSpace.add(degreeOfUnsortedness, (double)(spaceComplexityQuickSort/5));

                    degreeOfUnsortedness += 10;
                }

                //Generate Graphs
                dataset.removeAllSeries();
                dataset.addSeries(bubbleSort);
                dataset.addSeries(insertionSort);
                dataset.addSeries(selectionSort);
                dataset.addSeries(mergeSort);
                dataset.addSeries(quickSort);

                JFreeChart chartUnsorted = ChartFactory.createXYLineChart("Execution Time Vs. Degree of Unsortedness for Different Sorting Techniques: Dataset " + (tmp + 1),
                        "Size", "Execution Time", dataset);

                generateChart(dataset, chartUnsorted, "UnsortednessTimeComplexityDataset" + (tmp + 1) + ".jpeg");

                datasetSpace.removeAllSeries();
                datasetSpace.addSeries(bubbleSortSpace);
                datasetSpace.addSeries(insertionSortSpace);
                datasetSpace.addSeries(selectionSortSpace);
                datasetSpace.addSeries(mergeSortSpace);
                datasetSpace.addSeries(quickSortSpace);

                JFreeChart chartSpaceUnsorted = ChartFactory.createXYLineChart("Memory Usage Vs. Degree of Unsortedness for Different Sorting Techniques: Dataset " + (tmp + 1),
                        "Size", "Memory Used", datasetSpace);

                generateChart(datasetSpace, chartSpaceUnsorted, "UnsotednessSpaceComplexityDataset" + (tmp + 1) + ".jpeg");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateChart(XYSeriesCollection dataset, JFreeChart chart, String fileName){
        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesPaint(3, Color.BLACK);
        renderer.setSeriesPaint(4, Color.BLUE);

        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));
        renderer.setSeriesStroke(4, new BasicStroke(2.0f));

        plot.setOutlinePaint(Color.GRAY);
        plot.setOutlineStroke(new BasicStroke(1.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        int width = 940;    /* Width of the image */
        int height = 780;   /* Height of the image */

        try{
            File lineChart = new File( "generatedgraphs/" + fileName);
            ChartUtilities.saveChartAsJPEG(lineChart, chart, width ,height);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void getCSVLineCount(String path){
        max = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (br.readLine() != null) {
                max++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}