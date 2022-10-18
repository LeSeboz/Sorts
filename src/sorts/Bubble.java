package sorts;

/**
 *
 * @author seb_c
 */
public class Bubble {
    int[] numeros;
    int largo;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public Bubble(int n) {
        numeros = new int[n]; //Define el largo
        this.largo=n;
    }

    public int getNumeros(int x) {
        return numeros[x];
    }

    public int getLargo() {
        return largo;
    }
    
    public void llenar(){
        for(int i=0; i<largo; i++){
            numeros[i]= getRandomNumber(0,100);
        }
    }
    
    public void imprimir(){
        System.out.println("Lista: ");
        for (int i = 0; i < largo; i++) {
            System.out.println(numeros[i]); 
        }
        System.out.println("Fin lista\n");
    }
    
    public void ordenar(){
        int aux;
        for (int i = 0; i < largo-1; i++) {
            for (int j = 0; j < largo-1; j++) {
                if(numeros[j]>numeros[j+1]){
                    aux = numeros[j];
                    numeros[j] = numeros[j+1];
                    numeros[j+1] = aux;    
                }
            }   
        }
    }   
}
