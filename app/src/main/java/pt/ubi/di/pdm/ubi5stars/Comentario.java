package pt.ubi.di.pdm.ubi5stars;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Comentario {
    private String Coment;
    private String User;
    private String id_mon;
    private Double Rating;


    public Comentario(){
        //this constructor is required
    }


    public Comentario(String Coment, String id_mon, String User, Double Rating) {
        this.Coment = Coment;
        this.User = User;
        this.id_mon = id_mon;
        this.Rating=Rating;
    }

    public String getComent() {
        return Coment;
    }

    public String getUser() {
        return this.User;
    }

    public String getId_mon() {
        return this.id_mon;
    }


    public Double getRating() {
        return Rating;
    }

    @Override
    public String toString(){

        return ("nada"+this.Coment);//Categoria.toString();


    }

}

