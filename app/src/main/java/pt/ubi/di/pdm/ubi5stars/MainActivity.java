package pt.ubi.di.pdm.ubi5stars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity {
    float razao =(float) 0.5;


    // These matrices will be used to move and zoom image
    ImageView IMGview;
    ScrollView sw;
    DatabaseReference myRef;
    DatabaseReference databaseLocais;
    List<Locais> LocaisList;
    FirebaseDatabase database;
    LinearLayout LL;
    TextView titulo;
    TextView info;
    SearchView barra;
    String mon;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IMGview = findViewById(R.id.imageMap1);
        LL = findViewById(R.id.LLinfo);
        titulo = findViewById(R.id.nome_monumento);
        info = findViewById(R.id.info_monumento);
        sw = (ScrollView) findViewById(R.id.scroll1);
        sw.bringToFront();
        barra= findViewById(R.id.pesquisa);


        IMGview.setVisibility(View.INVISIBLE);
        IMGview = findViewById(R.id.imageMap2);
        IMGview.setVisibility(View.VISIBLE);
        sw = (ScrollView) findViewById(R.id.scroll2);
        sw.bringToFront();
        LL.bringToFront();
        razao=(float)0.75;
        barra.bringToFront();



        coordenadas_ecra();


    }









    public  void ir_db(String id){
        LocaisList=new ArrayList<>();

        mon=id;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Locais").child(id);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    ++i;
                    String msgContent=(""+datas.getValue());
                    System.out.println("AQUIIIIIIIIII"+msgContent);
                    if(i==3){

                        info.setText(msgContent);
                    }
                    if (i==6){
                        titulo.setText(msgContent);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }



    public void pesquisar(View view) {

        Intent intent = new Intent(this, Pesquisa.class);
        startActivity(intent);

    }



    public void aproximar(View view){

        if (IMGview == findViewById(R.id.imageMap1)){
            IMGview.setVisibility(View.INVISIBLE);
            IMGview = findViewById(R.id.imageMap2);
            IMGview.setVisibility(View.VISIBLE);
            sw = (ScrollView) findViewById(R.id.scroll2);
            sw.bringToFront();
            LL.bringToFront();
            razao=(float)0.75;
            barra.bringToFront();
        }else{
            if (IMGview == findViewById(R.id.imageMap2)){
                IMGview.setVisibility(View.INVISIBLE);
                IMGview = findViewById(R.id.imageMap3);
                IMGview.setVisibility(View.VISIBLE);
                sw = (ScrollView) findViewById(R.id.scroll3);
                sw.bringToFront();
                LL.bringToFront();
                razao=(float)1;
                barra.bringToFront();
            }else{ }
        }

        coordenadas_ecra();
    }

    public void desaproximar(View view){

        if (IMGview == findViewById(R.id.imageMap3)){
            IMGview.setVisibility(View.INVISIBLE);
            IMGview = findViewById(R.id.imageMap2);
            IMGview.setVisibility(View.VISIBLE);
            sw = (ScrollView) findViewById(R.id.scroll2);
            sw.bringToFront();
            LL.bringToFront();
            razao=(float)0.75;
            barra.bringToFront();
        }else{
            if (IMGview == findViewById(R.id.imageMap2)){
                IMGview.setVisibility(View.INVISIBLE);
                IMGview = findViewById(R.id.imageMap1);
                IMGview.setVisibility(View.VISIBLE);
                sw = (ScrollView) findViewById(R.id.scroll1);
                sw.bringToFront();
                LL.bringToFront();
                razao=(float)0.5;
                barra.bringToFront();
            }else{

            }
        }

        coordenadas_ecra();
    }

    public void abrir_monumento(View view) {
        Intent intent = new Intent(this, MonumentosActivity.class);
        intent.putExtra("monumento",mon);
        startActivity(intent);

    }



    public void coordenadas_ecra(){

        IMGview.setOnTouchListener(new View.OnTouchListener() {
            //Esta coisa é para fazer áreas clicáveis
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //CIRCLE :      (x-a)^2 + (y-b)^2 = r^2
                float centerX, centerY, touchX, touchY, radius;
                //centerX = v.getWidth() / 2;
                //centerY = v.getHeight() / 2;
                touchX = event.getX();
                touchY = event.getY();
                //radius = centerX;
                //System.out.println("centerX = "+centerX+", centerY = "+centerY);
                System.out.println("X = "+touchX+", Y = "+touchY);
                //monumento3(touchX,touchY,razao);
                if(!monumento3(touchX,touchY,razao).equals("nada")){
                    LL.setVisibility(View.VISIBLE);
                    //titulo.setText(monumento3(touchX,touchY,razao));
                    //info.setText("Informação sobre "+monumento3(touchX,touchY,razao));
                    //abrir_monumento(v);
                    String x=monumento3(touchX,touchY,razao);
                    ir_db(x);//monumento3


                }else{
                    LL.setVisibility(View.GONE);
                }
                // System.out.println("radius = "+radius);
                /*
                if (Math.pow(touchX - centerX, 2)
                        + Math.pow(touchY - centerY, 2) < Math.pow(radius, 2)) {
                    System.out.println("Inside Circle");
                    return false;
                } else {
                    System.out.println("Outside Circle");
                    return true;
                }
                */

                return (true);
            }
        });
    }

    public boolean estaDentroB(float touchX, float touchY, float radius, float centerX, float centerY){
        if (Math.pow(touchX - centerX, 2)
                + Math.pow(touchY - centerY, 2) < Math.pow(radius, 2)) {
            //System.out.println("Inside Circle");
            return true;
        } else {
            //System.out.println("Outside Circle");
            return false;
        }
    }

    public boolean estaDentroQ(float touchX, float touchY, float mx, float My, float Mx, float my){
        if ((touchX > mx) && (touchX < Mx) && (touchY >my) && (touchY < My) ){
            //System.out.println("Inside Square");
            return true;
        } else {
            //System.out.println("Outside Square");
            return false;
        }
    }

    public String monumento3 (float x, float y, float r){ //r = razao entre o zoom das imagens

        if(estaDentroB(x,y,(float) 43* r, (float) 430.99023* r,(float) 1883.9736* r)){
            System.out.println("Area 26"); return ("1"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 563.9834* r, (float) 2027.9678* r)){
            System.out.println("Area 13"); return ("2"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 524.98926* r,(float) 1771.9863* r)){
            System.out.println("Area 10"); return ("3"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 607.9873* r, (float) 1912.9775* r)){
            System.out.println("Area 11"); return ("4"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)670.9795* r , (float)1610.9814* r )){
            System.out.println("Area 12"); return ("5"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)740.97656 * r, (float)1769.9658* r )){
            System.out.println("Area 25"); return ("6"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)787.9873* r , (float) 1032.9678* r )){
            System.out.println("Area 6"); return ("7"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 953.9834* r , (float) 2057.9697* r )){
            System.out.println("Area 7"); return ("8"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 866.9863* r , (float)2105.9678* r )){
            System.out.println("Area 14"); return ("9"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)1169.9814* r , (float)2039.9863* r )){
            System.out.println("Area 17"); return ("10"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)1251.9854* r , (float)2105.9775* r )){
            System.out.println("Area 20"); return ("11"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)1264.9795* r , (float)2275.9795* r )){
            System.out.println("Area Insta"); return ("12"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)1534.9893* r , (float) 1748.9717* r )){
            System.out.println("Area 2"); return ("13"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)1728.9766* r , (float)1584.9824* r )){
            System.out.println("Area 1"); return ("14"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float)1039.9932* r , (float)1689.9697* r )){
            System.out.println("Area 27"); return ("15"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 2672.9775* r , (float) 1384.9736* r )){
            System.out.println("Area Goldra"); return ("16"); } // OK
        if(estaDentroB(x,y,(float) 92* r, (float) 3181.9805* r , (float) 1625.9795* r )){
            System.out.println("Area Jardim Lago"); return ("17"); } // OK
        if(estaDentroB(x,y,(float) 100* r, (float) 3372.9814* r , (float) 1702.9814* r )){
            System.out.println("Area Autocarro"); return ("18"); } // OK
        if(estaDentroB(x,y,(float) 100* r, (float) 3337.9863* r , (float) 1397.9736* r )){
            System.out.println("Area Comboio"); return ("19"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 3493.9766* r , (float) 2417.9639 * r)){
            System.out.println("Area Hospital"); return ("20"); } // OK
        if(estaDentroB(x,y,(float) 164* r, (float) 2998.9912* r , (float) 2468.9736* r )){
            System.out.println("Area Estádio"); return ("21"); } // OK
        if(estaDentroB(x,y,(float) 179* r, (float) 3044.9873* r , (float) 2064.9678* r )){
            System.out.println("Area Jardim Público"); return ("22"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 2998.9883* r , (float) 2279.9756* r )){
            System.out.println("Area Hotel Puralã"); return ("23"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 2589.9922* r , (float) 1751.9756* r )){
            System.out.println("Area Hotel Santa Eufemia"); return ("24"); } // OK
        if(estaDentroB(x,y,(float) 43* r, (float) 2466.9893* r , (float) 2062.962* r )){
            System.out.println("Area Hotel Trip D.Maria"); return ("25"); } // OK




        if(estaDentroQ(x,y,(float) 795.922* r, (float)1891.9629* r, (float)961.9883* r , (float)1656.9775* r )){
            System.out.println("Area Igreja Santa Maria Maior"); return ("26"); } // OK
        if(estaDentroQ(x,y,(float) 1046.9902* r, (float)2223.9658* r, (float)1218.9775* r , (float)2150.9678* r )){
            System.out.println("Area Relogio Solar"); return ("27"); } // OK
        if(estaDentroQ(x,y, (float)672.9863* r , (float)2621.9639* r, (float)944.9785* r , (float)2381.9824* r )){
            System.out.println("Area Capela S.Martinho"); return ("28"); } // OK
        if(estaDentroQ(x,y, (float)1001.983* r , (float)2488.959* r, (float) 1228.9883* r , (float)2318.9639* r )){
            System.out.println("Area Biblioteca"); return ("29"); } // OK
        if(estaDentroQ(x,y, (float)433.98926* r , (float)2517.963* r, (float)639.9756* r , (float)2312.9824* r )){
            System.out.println("Area Arco"); return ("30"); } // OK
        if(estaDentroQ(x,y, (float) 121.984375* r , (float)2590.961* r, (float) 368.98633* r, (float)2274.9697* r )){
            System.out.println("Area Igreja Engenharias"); return ("31"); } // OK
        if(estaDentroQ(x,y, (float)1456.9883* r , (float)2420.9795* r, (float)1649.9814 * r, (float)2077.9863* r )){
            System.out.println("Area Igreja São Silvestre"); return ("32"); } // OK
        if(estaDentroQ(x,y, (float) 1477.9941* r , (float)2072.9658* r , (float)1766.9785* r , (float) 1763.9756* r )){
            System.out.println("Area Igreja Misericórdia"); return ("33"); } // OK
        if(estaDentroQ(x,y, (float) 1605.9883 * r, (float)1665.9639 * r, (float)1705.9785 * r, (float) 1427.9756* r )){
            System.out.println("Area Pelourinho"); return ("34"); } // OK
        if(estaDentroQ(x,y, (float) 1456.9873* r , (float) 1532.9834 * r, (float) 1589.9805 * r, (float) 1381.9824 * r)){
            System.out.println("Area Chafariz"); return ("35"); } // OK
        if(estaDentroQ(x,y, (float) 1272.9912* r , (float) 1494.9756* r , (float) 1494.9736* r, (float) 1140.9795* r )){
            System.out.println("Area Igreja Nossa Senhora da Conceição"); return ("36"); } // OK
        if(estaDentroQ(x,y, (float) 961.98926 * r, (float) 1395.9639 * r, (float) 1238.9766 * r, (float) 1184.9775 * r)){
            System.out.println("Area Museu Arte Sacra"); return ("37"); } // OK
        if(estaDentroQ(x,y, (float) 929.9834* r , (float) 1645.9756 * r, (float) 1097.9863* r , (float) 1424.9795 * r)){
            System.out.println("Area Janela"); return ("38"); } // OK
        if(estaDentroQ(x,y, (float) 1093.9883* r , (float) 1779.9814* r , (float) 1353.9785* r , (float) 1521.9736* r )){  //RESOLVIDO problemas com os numeros
            System.out.println("Area Camara Municipal"); return ("39"); } // OK
        if(estaDentroQ(x,y, (float) 784.98926* r , (float) 420.97754* r , (float) 1158.9805* r, (float) 134.97128* r )){  //RESOLVIDO parece que há numeros estranhos aqui
            System.out.println("Area Serra da Estrela"); return ("40"); } // OK
        if(estaDentroQ(x,y, (float) 1546.9912* r , (float) 476.96387 * r, (float) 1959.9834 * r, (float) 120.98242 * r )){
            System.out.println("Area Polo IV (Sineiro)"); return ("41"); } // OK
        if(estaDentroQ(x,y, (float) 1585.9893* r , (float) 1260.9658* r , (float) 1975.9863* r , (float) 995.9756* r )){    //RESOLVIDO parec que há numeros estranhos aqui
            System.out.println("Area Fac. Engenharias"); return ("42"); } // OK
        if(estaDentroQ(x,y, (float) 2214.9932* r , (float) 1418.9697* r , (float)2502.9814* r , (float) 1266.9678* r )){
            System.out.println("Area Polo Fac. de Artes e Letras"); return ("43"); } // OK
        if(estaDentroQ(x,y, (float) 2428.9932* r , (float) 1494.959* r , (float) 2695.9756* r , (float) 1396.9609* r )){
            System.out.println("Area Museu dos Lanificios"); return ("44"); }// OK
        if(estaDentroQ(x,y, (float) 3250.9883* r , (float) 2332.963* r , (float) 3651.9746* r , (float) 2106.962* r )){
            System.out.println("Area Faculdade Ciências da Saúde"); return ("45"); } // OK
        if(estaDentroQ(x,y, (float) 2698.9912* r , (float) 1936.9775* r , (float) 2881.9795* r , (float) 1737.9785* r )){
            System.out.println("Area Serra Shopping"); return ("46"); } // OK
        if(estaDentroQ(x,y, (float) 2173.9873* r , (float) 1960.9844* r , (float) 2470.9844* r , (float) 1808.9824* r )){
            System.out.println("Area Pavilhão Desportivo Ubi"); return ("47"); } // OK
        if(estaDentroQ(x,y, (float) 2231.9883* r , (float) 1772.9639* r , (float) 2381.9883* r , (float) 1548.9736* r )){
            System.out.println("Area Jardim Residencias (Estatua)"); return ("48"); } // OK
        if(estaDentroQ(x,y, (float) 1888.9932 * r, (float) 1706.9814 * r, (float) 2239.9844 * r, (float) 1523.9736 * r)){
            System.out.println("Area Reitoria"); return ("49"); } // OK
        if(estaDentroQ(x,y, (float) 1892.9922 * r, (float)1902.9531 * r, (float) 2044.9844 * r, (float) 1719.9697* r )){
            System.out.println("Area Miradouro da Reitoria"); return ("50"); } // OK
        if(estaDentroQ(x,y, (float) 2563.9854 * r, (float) 543.29678 * r, (float) 2815.9824 * r, (float) 378.97852 * r)){  // RESOLVIDO parece me que há numeros estranhos aqui
            System.out.println("Area Ponte Sineiro"); return ("51"); } // OK
        if(estaDentroQ(x,y, (float) 1541.9922 * r, (float) 471.9834* r , (float) 1964.9863* r , (float) 113.975586* r )){  //RESOLVIDO parece me que há valores estranhos aqui
            System.out.println("Area Sineiro"); return ("52"); } // OK


        return ("nada");
    }


}
