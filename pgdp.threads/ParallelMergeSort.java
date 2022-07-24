package pgdp.threads;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
public class ParallelMergeSort extends RecursiveAction {
    private Comparable[] arr, helper;
    private int left, right;
    private ParallelMergeSort(Comparable[] array, int low, int high) {
        this.right = high;
        this.left = low;
        this.arr = array;
        helper = new Comparable[arr.length];
    }
    public ParallelMergeSort(Comparable[] array) {
        this(array, 0, array.length - 1);
    }
    @Override
    protected void compute() {
        boolean continueThread = right > left;
        if (continueThread) {
            int t = right + left;
            int mid = t / 2;
            invokeAll( new ParallelMergeSort(arr,mid + 1, right));
            invokeAll(new ParallelMergeSort(arr, left, mid));
            MergeSort.merge(arr, helper, left, mid, right);

        }
    }

}
