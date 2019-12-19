package pt.ubi.di.pdm.ubi5stars;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class inicio extends Activity {

    private AdView mAdView;

    EditText edit_user;

    private static final String FILE_USER_NAME = "User_name.txt";

    String nome_pre_def = "Anónimo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        edit_user = (EditText) findViewById(R.id.writename);
        load_name(FILE_USER_NAME);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }

    @Override
    protected  void onResume(){
        super.onResume();
        load_name(FILE_USER_NAME);
    }


    //Quando a activity é aberta ou reaberta apresenta o ultimo nome guardado
    public void load_name(String FILE_NAME) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("");
            }

            if(sb.toString().equals("")){
                edit_user.setText(nome_pre_def);
            }else{
                edit_user.setText(sb.toString());
            }


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
    }



    @Override

    public void onBackPressed(){


        setContentView(R.layout.inicio);
        final AlertDialog.Builder builder= new AlertDialog.Builder(inicio.this);
        builder.setMessage("Are you sure want to do this?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }



    public void startActivity2(View v){

        save_name(FILE_USER_NAME);

        Intent iActivity=new Intent(this,MainActivity.class);
        startActivity(iActivity);





    }

    //Funcao que guarda o nome num ficheiro
    public void save_name(String FILE_NAME) {
        String text = edit_user.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            edit_user.getText().clear();
            // Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




}
