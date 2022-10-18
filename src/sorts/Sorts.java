package sorts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author seb_c
 */
public class Sorts extends JFrame implements ActionListener{
    JLabel label = new JLabel("Introduzca el largo de la lista: ");
    JTextField input = new JTextField();
    JTextArea screen = new JTextArea();
    JScrollPane scrll = new JScrollPane();
    JButton sec = new JButton(), join = new JButton(), exec = new JButton();
    String scrn;
    int lar = getRandomNumber(0,20);
    
    
    public Sorts(){
        setTitle("Sorts");
        setSize(500,500);
        label.setBounds(100, 30, 200, 20);
        add(label);
        
        input.setBounds(300, 30, 100, 20);
        add(input);
        
        screen.setBounds(100, 65, 300, 320);
        screen.setDisabledTextColor(Color.DARK_GRAY);
        screen.disable();
        add(screen);
        
        sec.setBounds(100, 400, 100, 20);
        sec.setText("Secuencial");
        sec.addActionListener(this);
        add(sec);
        
        join.setBounds(201, 400, 100, 20);
        join.setText("Fork/Join");
        join.addActionListener(this);
        add(join);
        
        exec.setBounds(301, 400, 100, 20);
        exec.setText("Executor");
        exec.addActionListener(this);
        add(exec);
            
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        scrn="";
        if(e.getSource() == sec){ //Para secuencial
            int mid = (lar-1)/2;
            
            //Usuario define el largo de la lista
            lar = Integer.parseInt(input.getText());
            int[] l = new int[lar];
            
            //Bubble b = new Bubble(lar);
            merge m = new merge(l,lar);
            
            //Inicia contador de tiempo de ejecucion
            long startTime = System.currentTimeMillis();

            //Llena la lista y prepara la string para la impresion en la pantalla
            //b.llenar();
            
            m.llenar();
            scrn = "Lista desordenada:\n";
            for (int i = 0; i < lar; i++) {
                //scrn += b.getNumeros(i);
                scrn += m.getElemento(i);
                scrn += " ";
            }
            
            //Ordena la lista y añade a la string para la impresion en pantalla
            //b.ordenar();    //Ejecuta el metodo de ordenamiento
            m.mergeSort(l, 0, lar-1);
            
            scrn += "\n\nLista ordenada:\n";
            for (int i = 0; i < lar; i++) {
                //scrn += b.getNumeros(i);
                scrn += m.getElemento(i);
                scrn += " ";
            }
            
            //Termina contador de tiempo de ejecucion
            long stopTime = System.currentTimeMillis();
            
            //Añade a string tiempo de ejecución
            scrn += "\n\nTiempo de ejecución: " + (stopTime-startTime)+ "ms";
            //Plasma en text area
            screen.setText(scrn);
        }
        
        if(e.getSource() == join){
            //Usuario define el largo de la lista
            lar = Integer.parseInt(input.getText());
            ArrayList<Integer> l = new ArrayList<>();

            //Randomiza
            for (int i = 0; i < lar; i++) {
                l.add(getRandomNumber(0,lar));
            }
            
            scrn+="Lista desordenada: \n";
            scrn+=String.valueOf(l);
            scrn+="\n\n";
            
             //Inicia contador de tiempo de ejecucion
            long startTime = System.currentTimeMillis();
            
            //Ordena
            l=MergeForkExecutor.forkjoin(l, lar);
            
            //Termina contador de tiempo de ejecucion
            long stopTime = System.currentTimeMillis();
            
            scrn+="Lista ordenada: \n";
            scrn+=String.valueOf(l);
            scrn+="\n\n";

            scrn += "\nTiempo de ejecución: " + (stopTime-startTime)+ "ms";
            screen.setText(scrn);
        }
        if(e.getSource() == exec){        
            //Usuario define el largo de la lista
            lar = Integer.parseInt(input.getText());
            ArrayList<Integer> l = new ArrayList<>();
            
            for (int i = 0; i < lar; i++) {
                l.add(getRandomNumber(0,lar));
            }
            
            scrn+="Lista desordenada: \n";
            scrn+=String.valueOf(l);
            scrn+="\n\n";
            
            //Inicia contador de tiempo de ejecucion
            long startTime = System.currentTimeMillis();
            
            l = MergeForkExecutor.exec(l, lar);
            
            //Termina contador de tiempo de ejecucion
            long stopTime = System.currentTimeMillis();
            
            scrn+="Lista ordenada: \n";
            scrn+=String.valueOf(l);
            scrn+="\n\n";
            
            scrn += "\nTiempo de ejecución: " + (stopTime-startTime)+ "ms";
            screen.setText(scrn);
        }
    }
    
    public static void main(String[] args) {
        new Sorts();
    }   
}
