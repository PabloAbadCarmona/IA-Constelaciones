/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package constelacionesmultiplayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author PabloGuillermo
 */
public class ConstelacionesLienzo extends JComponent {

    class Nodo {

        int x;
        int y;
        int n;

        public Nodo(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }

    class Arista {
        int x1;
        int y1;
        int x2;
        int y2;
        ArrayList<Nodo> nodos;
        Nodo inicio;
        Nodo fin;
        int color;

        public Arista(Nodo inicio, Nodo fin, int color) {
            this.inicio = inicio;
            this.fin = fin;
            this.color = color;
        }
        public Arista(int x1, int y1, int x2, int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }
    }
    ArrayList<Nodo> nodos = new ArrayList<>();
    ArrayList<Arista> aristas = new ArrayList<>();
    Arista [] segmentos;
    
    public ConstelacionesLienzo() {
        setPreferredSize(new Dimension(400, 400));
        segmentos = new Arista[10];
    }
    public static final int BLANCA = 0;
    public static final int OSCURA = 1;

    private double distancia(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow(y1 - y2, 2));
    }
    
    //Código heradado de anterior proyecto
    public boolean insertarArista(int ni, int nf, int color) {
        Nodo ini = null;
        Nodo fin = null;
        for (Nodo nodo : nodos) {
            if (nodo.n == ni) {
                ini = nodo;
            }
            if (nodo.n == nf) {
                fin = nodo;
            }
        }
        if (ini == null || fin == null) {
            return false;
        }
        aristas.add(new Arista(ini, fin, color));
        return true;

    }
    
    //Método para el proyecto
    public boolean insertarArista(int x1,int y1, int x2, int y2, int color){
        //CASO INICIAL
        Arista a = new Arista(x1, y1, x2, y2);
        if(contar(segmentos)<2){
            int contNodos=0;
            for(Nodo n: nodos){
                if(pertenece(n, a)){
                    contNodos++;
                }
            }
            if(contNodos>=2) return true;
            return false;
        }
        //CASO MENOR QUE 5
        
        //CASO IGUAL A 5
        
        
        return true;
    }
    public void eliminarArista(int x1,int y1, int x2, int y2){
    
    }
    
    private boolean pertenece(Nodo n, Arista a){
        
        if(a.x1==n.x){
            if(a.y1==n.y) return true;
            return false;
        }
        if(a.x2==n.x){
            if(a.y2==n.y) return true;
            return false;
        }
        if(a.y1==n.y){
            if(a.x1==n.x) return true;
            return false;
        }
        if(a.y2==n.y){
            if(a.x2==n.x) return true;
            return false;
        }
        
        if(n.x>Math.max(a.x1,a.x2)) return false;
        if(n.y>Math.max(a.y1,a.y2)) return false;
        if(n.x<Math.min(a.x1,a.x2)) return false;
        if(n.y<Math.min(a.y1,a.y2)) return false;
        
        if((a.x2-n.x)/(a.y2-n.y)==(a.y1-n.y)/(a.x1-n.x)) return true; 
        return false;
    }

    public boolean insertarNodo(int x, int y, int n) {
        for (Nodo nodo : nodos) {
            if (distancia(x, y, nodo.x, nodo.y) < 40) {
                return false;
            }
        }
        nodos.add(new Nodo(x, y, n));
        System.out.println(n + " " + x + " " + y);
        return true;
    }
    
    //Si un punto genérico (x,y) se encuentra en las inmediaciones de un nodo
    public int perteneceNodo(int x, int y) {
        int r = 0;
        for (Nodo nodo : nodos) {
            if (distancia(x, y, nodo.x + 2, nodo.y + 2) < 20) {
                r = nodo.n;
            }
        }
        return r;
    }

    @Override
    public void paintComponent(Graphics g) {


        for (Arista arista : aristas) {
            int x1, y1, x2, y2;

            x1 = arista.inicio.x + 2;                    
            y1 = arista.inicio.y + 2; 
            x2 = arista.inicio.x + 2 + (int)(75 * ((arista.fin.x - arista.inicio.x) / distancia(arista.inicio.x, arista.inicio.y, arista.fin.x, arista.fin.y)));
            y2 = arista.inicio.y + 2 + (int)(75 * ((arista.fin.y - arista.inicio.y) / distancia(arista.inicio.x, arista.inicio.y, arista.fin.x, arista.fin.y)));
            if (arista.color == BLANCA) {
                g.setColor(Color.WHITE);

            } else {
                g.setColor(Color.BLACK);
            }
            g.drawLine(x1, y1, x2, y2);
            System.out.println("Arista dibujada");
            System.out.println(arista.inicio.x + " " + arista.inicio.y + " " + arista.fin.x + " " + arista.fin.y);
            System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
            g.setColor(Color.BLACK);
        }

        for (Nodo nodo : nodos) {
            g.setColor(Color.YELLOW);
            g.fillArc(nodo.x, nodo.y, 4, 4, 0, 360);                      
        }

    }
    
    public int contar(Object [] vector){
        int contador =0;
        for(Object o: vector){
            if(o == null) continue;
            contador++;
        }
        
        return contador;
        
    }
    
}
