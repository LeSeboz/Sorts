package sorts;

/**
 *
 * @author seb_c
 */
class merge{
    int[] a;
    int largo;
    
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public void llenar(){
        for(int i=0; i<largo; i++){
            a[i]= getRandomNumber(0,largo);
        }
    }
    
    public merge(int[] a, int largo) {
        this.a = a;
        this.largo = largo;
    }
    
    /* Function to merge the subarrays of a[] */
    public void merger(int[] a, int beg, int mid, int end)    
    {    
        int i, j, k;  
        int n1 = mid - beg + 1;    
        int n2 = end - mid;    

        //Arreglos temporales
        int LeftArray[] = new int[n1];
        int RightArray[] = new int[n2];

        //Copiar elementos a arreglos temporales
        for (i = 0; i < n1; i++){
            LeftArray[i] = a[beg + i];
        }
        for (j = 0; j < n2; j++){
            RightArray[j] = a[mid + 1 + j];
        }

        i = 0; /* initial index of first sub-array */
        j = 0; /* initial index of second sub-array */
        k = beg;  /* initial index of merged sub-array */

        while (i < n1 && j < n2)
        {
            if(LeftArray[i] <= RightArray[j])
            {
                a[k] = LeftArray[i];
                i++;
            }
            else
            {
                a[k] = RightArray[j];
                j++;
            }
            k++;
        }    

        while (i<n1)
        {
            a[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j<n2)
        {
            a[k] = RightArray[j];
            j++;
            k++;
        }
    }

    public void mergeSort(int a[], int beg, int end)
    {
        if (beg < end)
        {
            int mid = (beg + end) / 2;
            mergeSort(a, beg, mid);
            mergeSort(a, mid + 1, end);
            merger(a, beg, mid, end);
        }
    }

    /* Function to print the array */
    public int getElemento(int x)
    {
        return a[x];
    }
}