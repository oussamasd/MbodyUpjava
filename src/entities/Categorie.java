/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lounga
 */
public class Categorie {
    private int id;
    private String type;
    private String tag;

    public Categorie(int id, String type, String tag) {
        this.id = id;
        this.type = type;
        this.tag = tag;
    }

    public Categorie() {
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", type=" + type + ", tag=" + tag + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Categorie(String type, String tag) {
        this.type = type;
        this.tag = tag;
    }

  
        
}
