package pt.ubi.di.pdm.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class avaliacao extends Activity {

    List<Locais> LocaisList;
    String id_mon;
    DatabaseReference databaseComentario;
    EditText e_com;
    RatingBar rat;
    Integer conta_coment;
    double rat2,media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avaliar);

        e_com= (EditText) this.findViewById(R.id.writename);

        rat = (RatingBar) this.findViewById(R.id.rating);
        Intent iCameFromActivity1=getIntent();
        //System.out.println("OLA"+iCameFromActivity1.getStringExtra("id_monumento"));
        id_mon=iCameFromActivity1.getStringExtra("id_monumento");


    }

    //Quando a activity é aberta ou reaberta apresenta o ultimo nome guardado
    public String load_name() {
        String FILE_NAME = "User_name.txt";
        FileInputStream fis = null;
        String text = "";

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();


            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            text = sb.toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text;
    }

    public void adicionar_comentario(View view){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Comentario");
        conta_coment=0;
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    ++i;
                    conta_coment=i;
                    String msgContent=("comentarios ++ "+datas.getValue());
                    //System.out.println(msgContent+"-"+i+"-"+conta_coment);
                }

                databaseComentario = FirebaseDatabase.getInstance().getReference("Comentario");
                //String id = databaseComentario.push().getKey();
                Comentario com1;

                String comentario= e_com.getText().toString();
                String nome_user = load_name() ; //ler do ficheiro onde vai estar guardado
                rat2= rat.getRating();  // obter o rating das estrelas



                com1 = new Comentario(comentario,id_mon, nome_user, rat2 );
                databaseComentario.child(""+conta_coment+" --- "+id_mon).setValue(com1);

                //COMENTARIO GUARDADO NA BD
                //COM O SEGUINTE FORMATO
                //  NUMEROS de COMENTARIOS NA BD --- ID do MONUMENTO REFERENTE




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        //Rating fazer media dos ratings e fazer um set value



        final DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Locais").child(id_mon);

        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;

                for(DataSnapshot datas: dataSnapshot.getChildren()) {
                    ++i;
                    String msgContent = ("" + datas.getValue());

                    if (i ==7 ) {
                        Double j= Double.parseDouble(""+msgContent);
                        media= ((j+rat2)/2);
                        //System.out.println("IIIIII"+media);
                        reference2.getRef().child("rating").setValue(""+media);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        finish();
    }


}