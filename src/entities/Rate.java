/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author oussa
 */
public class Rate {
    
    private int id_act;
    private int id_use ; 
    private int rating ;

    public int getId_act() {
        return id_act;
    }

    public void setId_act(int id_act) {
        this.id_act = id_act;
    }

    public int getId_use() {
        return id_use;
    }

    public void setId_use(int id_use) {
        this.id_use = id_use;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rate{" + "id_act=" + id_act + ", id_use=" + id_use + ", rating=" + rating + '}';
    }
    
}
