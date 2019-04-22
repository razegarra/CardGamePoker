/*
 * Módulo de generación de puntajes en base a juego de cartas
 */
package cardgamepoker;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Richard Zegarra
 */
public class GamePunctuation {
    
    List<Card> cards;
    int puntaje = 0;
    
    GamePunctuation(List<Card> cards){
        this.cards = cards;
        //ordenar las cartas
        this.cards.sort(Comparator.comparingInt(Card::getNumber));
        //aplicar puntaje a los manos de altopuntaje
        cartasGrupales();
    }
    
    /*
     * Llama a todas las posibles manos
     */    
    public void cartasGrupales(){
        royalFlush();
        if(puntaje>0){return;}
        straightFlush();
        if(puntaje>0){return;}
        fourOfKind();
        if(puntaje>0){return;}
        fullHouse();
        if(puntaje>0){return;}
        flush();
        if(puntaje>0){return;}
        straight();
        if(puntaje>0){return;}
        threeOfKind();
        if(puntaje>0){return;}
        twoPairs();
        if(puntaje>0){return;}
        onePair();
        if(puntaje>0){return;}
        cartasIndividuales(1);
    }

    public void royalFlush(){
        boolean cumple = false;
        
        if( cards.get(0).getNumber()==10 &&
            cards.get(1).getNumber()==11 &&
            cards.get(2).getNumber()==12 &&
            cards.get(3).getNumber()==13 &&
            cards.get(4).getNumber()==14 &&
            cards.get(0).getSuit().compareTo(cards.get(1).getSuit())==0 &&
            cards.get(1).getSuit().compareTo(cards.get(2).getSuit())==0 &&
            cards.get(2).getSuit().compareTo(cards.get(3).getSuit())==0 &&
            cards.get(3).getSuit().compareTo(cards.get(4).getSuit())==0 ){
            cumple = true;
        }

        if(cumple){
            puntaje = 250000;
        }
    }
    public void straightFlush(){
        boolean cumple = false;
        if( cards.get(0).getNumber()+1==cards.get(1).getNumber() &&
            cards.get(1).getNumber()+1==cards.get(2).getNumber() &&
            cards.get(2).getNumber()+1==cards.get(3).getNumber() &&
            cards.get(3).getNumber()+1==cards.get(4).getNumber() &&
            cards.get(0).getSuit().compareTo(cards.get(1).getSuit())==0 &&
            cards.get(1).getSuit().compareTo(cards.get(2).getSuit())==0 &&
            cards.get(2).getSuit().compareTo(cards.get(3).getSuit())==0 &&
            cards.get(3).getSuit().compareTo(cards.get(4).getSuit())==0 ){
            cumple = true;
        }

        if(cumple){
            puntaje = 220000 + cartasIndividuales(10);
        }
    }
    public void fourOfKind(){
        if(contar(cards.get(0).getNumber(),4)){
            puntaje = 210000 + multiplicarIndividuales(cards.get(0).getNumber(),10) + multiplicarIndividuales(cards.get(4).getNumber(),1);
        } else if(contar(cards.get(1).getNumber(),4)){
            puntaje = 210000 + multiplicarIndividuales(cards.get(1).getNumber(),10) + multiplicarIndividuales(cards.get(0).getNumber(),1);
        }
    }
    
    public void fullHouse(){
         if(contar(cards.get(0).getNumber(),3) || contar(cards.get(2).getNumber(),3)){
             if(contar(cards.get(0).getNumber(),3) && contar(cards.get(3).getNumber(),2)){
                 puntaje = 200000 + multiplicarIndividuales(cards.get(0).getNumber(),10) + multiplicarIndividuales(cards.get(3).getNumber(),1);
             } else if(contar(cards.get(0).getNumber(),2) && contar(cards.get(2).getNumber(),3)){
                 puntaje = 200000 + multiplicarIndividuales(cards.get(2).getNumber(),10) + multiplicarIndividuales(cards.get(0).getNumber(),1);
             }
         } 
    }
    public void flush(){
        boolean cumple = false;
        if( cards.get(0).getSuit().compareTo(cards.get(1).getSuit())==0 &&
            cards.get(1).getSuit().compareTo(cards.get(2).getSuit())==0 &&
            cards.get(2).getSuit().compareTo(cards.get(3).getSuit())==0 &&
            cards.get(3).getSuit().compareTo(cards.get(4).getSuit())==0 ){
            cumple = true;
        }        

        if(cumple){
            puntaje = 190000 + cartasIndividuales(1);
        }
    }
    public void straight(){
        boolean cumple = false;
        if( cards.get(0).getNumber()+1==cards.get(1).getNumber() &&
            cards.get(1).getNumber()+1==cards.get(2).getNumber() &&
            cards.get(2).getNumber()+1==cards.get(3).getNumber() &&
            cards.get(3).getNumber()+1==cards.get(4).getNumber() ){
            cumple = true;
        }
        
        if(cumple){
            puntaje = 180000 + cartasIndividuales(1);
        }
    }
    
    public void threeOfKind(){
        if(contar(cards.get(0).getNumber(),3) || contar(cards.get(1).getNumber(),3) || contar(cards.get(2).getNumber(),3)){
             if(contar(cards.get(0).getNumber(),3)){
                 puntaje = 170000 + multiplicarIndividuales(cards.get(0).getNumber(),10) + multiplicarIndividuales(cards.get(3).getNumber(),1) + multiplicarIndividuales(cards.get(4).getNumber(),1);
             } else if(contar(cards.get(1).getNumber(),2)){
                 puntaje = 170000 + multiplicarIndividuales(cards.get(1).getNumber(),10) + multiplicarIndividuales(cards.get(0).getNumber(),1) + multiplicarIndividuales(cards.get(4).getNumber(),1);
             } else if(contar(cards.get(2).getNumber(),2)){
                 puntaje = 170000 + multiplicarIndividuales(cards.get(2).getNumber(),10) + multiplicarIndividuales(cards.get(0).getNumber(),1) + multiplicarIndividuales(cards.get(1).getNumber(),1);
             }
         } 
    }
    
    public void twoPairs(){
        if(contar(cards.get(0).getNumber(),2) && contar(cards.get(2).getNumber(),2)){
            puntaje = 160000 + multiplicarIndividuales(cards.get(0).getNumber(),10) + multiplicarIndividuales(cards.get(2).getNumber(),10) + multiplicarIndividuales(cards.get(4).getNumber(),1);
        } else if(contar(cards.get(0).getNumber(),2) && contar(cards.get(3).getNumber(),2)){
            puntaje = 160000 + multiplicarIndividuales(cards.get(0).getNumber(),10) + multiplicarIndividuales(cards.get(3).getNumber(),10) + multiplicarIndividuales(cards.get(2).getNumber(),1);
        } else if(contar(cards.get(1).getNumber(),2) && contar(cards.get(3).getNumber(),2)){
            puntaje = 160000 + multiplicarIndividuales(cards.get(1).getNumber(),10) + multiplicarIndividuales(cards.get(3).getNumber(),10) + multiplicarIndividuales(cards.get(0).getNumber(),1);
        }
    }
    
    public void onePair(){
        boolean cumple = false;
        int prePuntaje = 0;
        for(Card c : cards) {
            if(contar(c.getNumber(),2)){
                prePuntaje =+  multiplicarIndividuales(c.getNumber(),10);
                cumple = true;
            } else {
                prePuntaje =+  multiplicarIndividuales(c.getNumber(),1);
            }
        }
        
        if(cumple){
            puntaje = 150000 + prePuntaje;
        }
    }
    
    /*
    * Contabiliza el Nro de repeticiones de un numero dentro de una mano
    */
    public boolean contar(int number, int cant){
        int contar1=0;
        for(Card p : cards){
                if(p.getNumber()==number){
                    contar1++;
                }
            }
        if(contar1==cant){
            return true;
        } else {
            return false;
        }
    }
      
    /*
     * El puntaje por todas las cartas de la mano
     * Se añade multiplicando para las manos mayores y que no interfiera con las individuales
     */
    public int cartasIndividuales(int multiplicando){
        int result = 0;
        for(Card c : cards) {
            result=+ multiplicarIndividuales(c.getNumber(),multiplicando) ;
        }
        return result;
    }
    
    /*
     * El puntaje por carta nunca sera menor a la carta anterior por cinco
     * Se añade multiplicando para las manos mayores y que no interfiera con las individuales
     */
    public int multiplicarIndividuales(int number, int multiplicando){
            switch (number){
              case 2:
                  return (2*multiplicando);
              case 3:
                  return (11*multiplicando);
              case 4:
                  return (16*multiplicando);
              case 5:
                  return (21*multiplicando);
              case 6:
                  return (26*multiplicando);
              case 7:
                  return (31*multiplicando);
              case 8:
                  return (36*multiplicando);
              case 9:
                  return (41*multiplicando);
              case 10:
                  return (46*multiplicando);
              case 11:
                  return (51*multiplicando);
              case 12:
                  return (56*multiplicando);
              case 13:
                  return (61*multiplicando);
              case 14:
                  return (66*multiplicando);
            }
        return 0;
    }
}
