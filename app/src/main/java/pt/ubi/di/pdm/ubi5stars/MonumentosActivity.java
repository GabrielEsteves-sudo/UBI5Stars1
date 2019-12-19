package pt.ubi.di.pdm.ubi5stars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonumentosActivity extends AppCompatActivity {

    Button backButton;

    List<Locais> LocaisList;
    TextView cat;
    TextView nome;
    TextView infor;
    TextView aval;
    RatingBar rat;
    ImageView img;
   StorageReference mStorageRef;
    String lat ;
    String lng ;
    String a;
    String id_monumento;
    TableLayout table;
    Integer conta_coment;
    List<String> lista_com;
    List<String> lista_nom;
    List<String> lista_rat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monumentos);
        backButton = (Button)this.findViewById(R.id.b_back);

        cat = (TextView)this.findViewById(R.id.categoria_t);
        nome = (TextView) this.findViewById(R.id.nome_t);
        infor = (TextView) this.findViewById(R.id.informacao_texto);
        rat = (RatingBar) this.findViewById(R.id.rating);
        img=(ImageView)findViewById(R.id.imagens);
        aval=(TextView)this.findViewById(R.id.reviwes);
        table = (TableLayout)findViewById(R.id.table_comment);

        lista_com = new ArrayList<String>();
        lista_rat = new ArrayList<String>();
        lista_nom = new ArrayList<String>();

        dar_rating();
        Intent iCameFromActivity1=getIntent();
        //System.out.println("OLA"+iCameFromActivity1.getStringExtra("monumento"));
        a=iCameFromActivity1.getStringExtra("monumento");
        id_monumento=a;




    }


    @Override
    protected  void onResume(){
        super.onResume();
        ir_db(a);
        contar_comentario_monumento(id_monumento);

    }

    public void dar_rating(){

        rat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

              //  txtRatingValue.setText(String.valueOf(rating));

            }
        });


    }

    public void voltar (View view){
        finish();
    }

    public void go (View view ){
        //String lat = "40.277586";
        //String lng = "-7.510034";
        loadNavigationView(lat,lng);
        /*
        Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
        */
    }

    public void ir_avaliar(View view){

        Intent iActivity=new Intent(this,avaliacao.class);
        iActivity.putExtra("id_monumento",a);
        startActivity(iActivity);


    }

   public  void ir_db(String id) {
        LocaisList=new ArrayList<>();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Locais").child(id);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                   ++i;
                    String msgContent=(""+datas.getValue());
                    //System.out.println("AQUIIIIIIIIII"+msgContent);
                    if(i==1){

                        cat.setText(msgContent);
                    }
                    if(i==2){
                        infor.setText(msgContent);
                    }
                    if(i==3){
                        infor.setText(""+msgContent+"\n"+infor.getText());
                    }

                    if(i==4){

                        lat =msgContent;
                    }
                    if (i==5){
                        lng = msgContent;
                    }
                    if(i==6){
                        nome.setText(msgContent);
                    }

                    if(i==7){

                        float j= Float.parseFloat(""+msgContent);
                        rat.setRating(j);
                        aval.setText(msgContent);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


       mStorageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://ubi5stars-9afb6.appspot.com/").child(""+id+".jpg");

       try {
           final File localFile = File.createTempFile("img", "jpg");

           mStorageRef.getFile(localFile)
                   .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                           Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                           img.setImageBitmap(bitmap);
                       }
                   }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception exception) {
                   // Handle failed download
                   // ...
               }
           });
       }catch (IOException e){

            e.printStackTrace();

       }
    }

    public void loadNavigationView(String lat,String lng){
        Uri navigation = Uri.parse("google.navigation:q="+lat+","+lng+"");
        Intent navigationIntent = new Intent(Intent.ACTION_VIEW, navigation);
        navigationIntent.setPackage("com.google.android.apps.maps");
        startActivity(navigationIntent);
    }

    public  void showTableLayout(){
        int rows = lista_com.size();
        //System.out.println("tamanho listAAA"+rows);
        table.removeAllViews();
        table.setStretchAllColumns(true);
        table.bringToFront();

        double div=100;


        for(int i = 0; i < rows; i++){

            TableRow tr =  new TableRow(this);


                TextView txtGeneric = new TextView(this);
                txtGeneric.setTextSize(18);
                txtGeneric.setText(""+lista_nom.get(i).replace("\n","")+" "+lista_rat.get(i).replace("\n","")+"\n"+lista_com.get(i).replace("\n",""));
                div = lista_com.get(i).length()/40;
                //System.out.println("NESTE  "+lista_com.get(i)+"\n");
                tr.addView(txtGeneric);
                tr.bringToFront();
                txtGeneric.setHeight((int) (div*50)+200); txtGeneric.setWidth(20);
                txtGeneric.setTextColor(Color.BLACK);



            table.addView(tr);

        }
    }

    public void contar_comentario_monumento(String mon) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Comentario");
        id_monumento=mon;
        lista_com.clear();
        lista_rat.clear();
        lista_nom.clear();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                int j=0;
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    ++i;
                    String msgContent = ("" + datas.getValue());
                    String s_id = (String) datas.child("id_mon").getValue();
                    String s_rat = String.valueOf(datas.child("rating").getValue());
                    String s_nome = (String) datas.child("user").getValue();
                    s_nome.replace("\n", "");
                    String s_com = (String) datas.child("coment").getValue();
                    //System.out.println(msgContent + "- i" + i + "-" + conta_coment);

                        if(id_monumento.equals(s_id)){
                            ++j;
                            conta_coment=j;
                            //System.out.println(s_id + "- j" + j + "-" + conta_coment);
                            lista_com.add("Comentário : "+s_com);
                            lista_rat.add("Rating : "+s_rat);
                            lista_nom.add("User : "+s_nome);
                            //System.out.println("lista - "+j+"cont :"+msgContent);
                        }

                }
                showTableLayout();
                //System.out.println("PASSOU AUQI");
               conta_coment=j;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

}
