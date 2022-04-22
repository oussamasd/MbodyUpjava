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
public class ImageAct {
    
    private int id ; 
    private String url ;
    private int idact ;

    public ImageAct(String url , int idact) {
        this.url = url;
        this.idact = idact;
    }

    public ImageAct() {
    }

    public ImageAct(int id, String url , int idact) {
        this.id = id;
        this.url = url;
        this.idact = idact;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIdact(int idact) {
        this.idact = idact;
    }

    public int getIdact() {
        return idact;
    }

    @Override
    public String toString() {
        return "ImageAct{" + "id=" + id + ", url=" + url + ", idact=" + idact + '}';
    }
    
    

  
    
}
