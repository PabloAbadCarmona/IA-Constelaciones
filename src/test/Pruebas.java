/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import constelacionesmultiplayer.ConstelacionesLienzo;

/**
 *
 * @author Pablo
 */
public class Pruebas {
    public static void main(String[] args) {
//        Integer [] enteros = new Integer[10];
//        enteros[1]=2;
//        enteros[8]=22;
//        System.out.println(contar(enteros));  
        System.out.println(pertenece(4,4,1,1,5,5));
        
    }
    
    public static int contar(Object [] vector){
        int contador =0;
        for(Object o: vector){
            if(o == null) continue;
            contador++;
        }
        
        return contador;
        
    }
    private static boolean pertenece(int x, int y, int x1,int y1, int x2, int y2){
                
        if(x1==x){
            if(y1==y) return true;
            return false;
        }
        if(x2==x){
            if(y2==y) return true;
            return false;
        }
        if(y1==y){
            if(x1==x) return true;
            return false;
        }
        if(y2==y){
            if(x2==x) return true;
            return false;
        }
        
        if(x>Math.max(x1,x2)) return false;
        if(y>Math.max(y1,y2)) return false;
        if(x<Math.min(x1,x2)) return false;
        if(y<Math.min(y1,y2)) return false;
        
        if((x2-x)/(y2-y)==(y1-y)/(x1-x)) return true; 
        return false;
    }
    
}
