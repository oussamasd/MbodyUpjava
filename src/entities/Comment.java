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
public class Comment {
    
    private int id ;
    private int activity_id ; 
    private String text ; 
    private String dt ; 
    private int id_user ;

    public Comment() {
    }

    public Comment(int activity_id, String text, String dt, int id_user) {
        this.activity_id = activity_id;
        this.text = text;
        this.dt = dt;
        this.id_user = id_user;
    }

    public Comment(int id, int activity_id, String text, String dt, int id_user) {
        this.id = id;
        this.activity_id = activity_id;
        this.text = text;
        this.dt = dt;
        this.id_user = id_user;
    }

   

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", activity_id=" + activity_id + ", text=" + text + ", dt=" + dt + '}';
    }
    
    
    
}
