package sorts;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import static java.util.concurrent.ForkJoinTask.invokeAll;
import java.util.concurrent.RecursiveAction;
/**
 *
 * @author seb_c
 */
public class MergeForkExecutor implements Runnable{
    private ArrayList<Integer> li;
    private int st,en;
    public static Runnable me;
    
    //Constructor
    public MergeForkExecutor(ArrayList<Integer> m, int in, int fin){
        this.li=m;
        this.st=in;
        this.en=fin;
    }
   
    //Metodo de calculo de tareas
    @Override
    public void run() {
        divide(st,en);
    }
    
    public static ArrayList<Integer> forkjoin(ArrayList<Integer> l, int lar){
        int[] sort = new int[lar];
        
        for(int i=0; i<lar; i++){
          sort[i] = l.get(i);
        }
        
        //Invoca la pool de fork y ejecuta el metodo de ordenamiento
        ForkJoinMergeSort t = new ForkJoinMergeSort(sort, 0, sort.length - 1);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(t);
        
        for(int i=0;i<lar;i++){
            l.set(i, sort[i]);
        }
        
        return l;
    }
    
    public static ArrayList<Integer> exec(ArrayList<Integer> l, int lar){
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for(int i =0;i<5;i++){
            me = new MergeForkExecutor(l,0,l.size()-1);
        }   
        
        executor.execute(me);
        executor.shutdown();
        while(!executor.isTerminated());
                
        return l;
    }
    
    public void divide(int start,int stop){
        if(start<stop&&(stop-start)>=1){
            int mid = (stop+start)/2;
            divide(start, mid);
            divide(mid+1, stop);
            merge(start, mid, stop);
        }
    }
    
    public void merge(int s,int mi,int e){
        ArrayList<Integer> list = new ArrayList<>();
        int l=s;
        int r=mi+1;
        
        while(l<=mi && r<=e){
            if(li.get(l)<=li.get(r)){
                list.add(li.get(l));
                l++;
            }else{
                list.add(li.get(r));
                r++;
            }
        }
        
        while(l<=mi){
            list.add(li.get(l));
            l++;
        }
        
        while(r<=e){
            list.add(li.get(r));
            r++;
        }
        
        int i=0,j=s;
        
        while(i<list.size()){
            li.set(j, list.get(i++));
            j++;
        }  
    }
      
    public static class ForkJoinMergeSort extends RecursiveAction{
        private final int[] list;
        private final int start;
        private final int end;

        public ForkJoinMergeSort(int[] array, int begin, int end) {
            this.list = array;
            this.start = begin;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start < 2) {
                // Solo cambiar si se tienen dos elementos
                if (list[start] > list[end]) {
                    int tmp = list[end];
                    list[end] = list[start];
                    list[start] = tmp;
                }
            } 
            else {
                // Desbordar para conseguir punto medio
                int mid = (start + end) >>> 1;

                // Invocar metodo recursivo de ordenamiento
                invokeAll(new ForkJoinMergeSort(list, start, mid),
                new ForkJoinMergeSort(list, mid + 1, end));

                // Mezclar ambos lados
                merge(list, start, mid, end);
            }
        }

      void merge(int[] num, int stA, int stB, int endB) {
        int[] ret = new int[endB - stA + 1];
        int i = 0, k = stA, j = stB + 1;
            while (i < ret.length) {
                if (num[k] < num[j]) {
                    ret[i] = num[k];
                    k++;
                } 
                else {
                    ret[i] = num[j];
                    j++;
                }
                i++;
                
                // Si se toca el limite del arreglo se copia el resto
                if (j > endB) {
                    System.arraycopy(num, k, ret, i, stB - k + 1);
                    break;
                }
                if (k > stB) {
                    System.arraycopy(num, j, ret, i, endB - j + 1);
                    break;
                }
            }
        System.arraycopy(ret, 0, num, stA, ret.length);
      }
    }
}
