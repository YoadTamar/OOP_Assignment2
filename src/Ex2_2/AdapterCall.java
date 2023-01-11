package Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class AdapterCall<T> extends FutureTask<T> implements Comparable<AdapterCall<T>> {

    private int p;

    public AdapterCall(Callable<T> callable , int p) {
        super(callable);
        this.p = p;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }


    @Override
    public int compareTo(AdapterCall<T> other) {
        return Integer.compare(this.p , other.p);
    }

    @Override
    public String toString() {
        return "" + p ;
    }
}
