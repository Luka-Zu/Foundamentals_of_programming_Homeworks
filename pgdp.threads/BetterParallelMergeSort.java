package pgdp.threads;
import java.util.concurrent.RecursiveAction;

public class BetterParallelMergeSort extends RecursiveAction {
    private Comparable[] arr, helper;
    private int left, right;
    private final static int threshold = 2*2*2*2*2*2*2*2*2*2*2*2*2-1;
    private BetterParallelMergeSort(Comparable[] array, int low, int high) {
        this.helper = new Comparable[array.length];
        this.arr = array;
        this.right = high;
        this.left = low;
    }
    public BetterParallelMergeSort(Comparable[] array) {
        this(array,  0, array.length - 1);
    }
    @Override
    protected void compute() {
        boolean goToThreads = !(right - left <= threshold);
       // int check = high - low;
        if(goToThreads){
            final int mid =  left + right / 2;
            invokeAll(new BetterParallelMergeSort(arr, left, mid));
            invokeAll(new BetterParallelMergeSort(arr, mid + 1, right));
            MergeSort.merge(arr, helper, left, mid, right);
        }else if(right> left){
            MergeSort.mergesort(arr, helper, left, right);
        }
    }

}
