package pt.ubi.di.pdm.ubi5stars;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Locais {
    private String Categoria;
    private String Nome;
    private String Descrição;
    private String Informação;
    private Double Rating;
    private Double latitude;
    private Double longitude;


    public Locais(){
        //this constructor is required
    }


    public Locais(String Categoria,String Nome,String Descrição,String Informação,Double Rating,Double longitude,Double latitude) {
        this.Nome = Nome;
        this.Categoria=Categoria;
        this.Descrição=Descrição;
        this.Informação=Informação;
        this.Rating=Rating;
        this.latitude= latitude;
        this.longitude=longitude;

    }

    public String getNome() {
        return Nome;
    }

    public String getCategoria() {
        return this.Categoria;
    }

    public String getDescrição() {
        return this.Descrição;
    }

    public String getInformação() {
        return Informação;
    }
    public Double getRating() {
        return Rating;
    }

    public Double getlatitude() {
        return latitude;
    }

    public Double getlongitude() {
        return longitude;
    }


   @Override
    public String toString(){

        return ("nada"+this.Categoria);//Categoria.toString();


    }

}

