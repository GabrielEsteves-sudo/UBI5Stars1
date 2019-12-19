package pt.ubi.di.pdm.ubi5stars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Pesquisa extends Activity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        list = new ArrayList<>();
        list.add("ARTE URBANA  :  WOOL ON RESIDENCE_DOA OA ");
        list.add(" WOOL ON RESIDENCE_DOA OA ");
        list.add("ARTE URBANA  :  WOOL OFF FEST ADRES ");
        list.add(" WOOL OFF FEST ADRES ");
        list.add("ARTE URBANA  :  WOOL ON RESIDENCE MR.DHEO ");
        list.add(" WOOL ON RESIDENCE MR.DHEO ");
        list.add("ARTE URBANA  :  WOOL 2011 | Btoys (es) ");
        list.add(" WOOL 2011 | Btoys (es) ");
        list.add("ARTE URBANA  :  PASTE UP ");
        list.add(" PASTE UP ");
        list.add("ARTE URBANA  :  WOOL ON RESIDENCE_HALFSTUDIO");
        list.add(" WOOL ON RESIDENCE_HALFSTUDIO");
        list.add("ARTE URBANA  :  WOOL ARM COLLECTIVE ");
        list.add(" WOOL ARM COLLECTIVE ");
        list.add("ARTE URBANA  :  EXTRA WOOL SAMINA ");
        list.add(" EXTRA WOOL SAMINA ");
        list.add("ARTE URBANA  :  WOOL ON RESIDENCE_L is not an artist ");
        list.add(" WOOL ON RESIDENCE_L is not an artist ");
        list.add("ARTE URBANA  :  WOOL ON RESIDENCE_L is not an artist ");
        list.add(" WOOL ON RESIDENCE_L is not an artist ");
        list.add("ARTE URBANA  :  WOOL ON RESIDENCE_Tamara Alves ");
        list.add(" WOOL ON RESIDENCE_Tamara Alves ");
        list.add("ARTE URBANA  :  Insta. �O observat�rio � _ dojla  WOOL �19 ");
        list.add(" Insta. �O observat�rio � _ dojla  WOOL �19 ");
        list.add("ARTE URBANA  :  EXTRA WOOL PANT�NIO ");
        list.add(" EXTRA WOOL PANT�NIO ");
        list.add("ARTE URBANA  :  WOOL OFF FEST MARIO BELEM COVILHA ");
        list.add(" WOOL OFF FEST MARIO BELEM COVILHA ");
        list.add("ARTE URBANA  :  BOSOLETTI ");
        list.add(" BOSOLETTI ");
        list.add("ZONA DE LAZER  :  JARDIM DO GOLDRA ");
        list.add(" JARDIM DO GOLDRA ");
        list.add("ZONA DE LAZER  :  JARDIM DO LAGO ");
        list.add(" JARDIM DO LAGO ");
        list.add("TRANSPORTES  :  CENTRAL DE CAMIONAGEM ");
        list.add(" CENTRAL DE CAMIONAGEM ");
        list.add("TRANSPORTE  :  ESTACAO DE COMBOIOS ");
        list.add(" ESTACAO DE COMBOIOS ");
        list.add("HOSPITAL  :  CENTRO HOSPITALAR COVA DA BEIRA ");
        list.add(" CENTRO HOSPITALAR COVA DA BEIRA ");
        list.add("ZONA DESPORTIVA  :  COMPLEXO DESPORTIVO ");
        list.add(" COMPLEXO DESPORTIVO ");
        list.add("ZONA DE LAZER  :  JARDIM P�BLICO ");
        list.add(" JARDIM P�BLICO ");
        list.add("HOTEL  :  HOTEL PURAL� ");
        list.add(" HOTEL PURAL� ");
        list.add("HOTEL  :  HOTEL SANTA EUFEMIA ");
        list.add(" HOTEL SANTA EUFEMIA ");
        list.add("HOTEL  :  HOTEL DONA MARIA ");
        list.add(" HOTEL DONA MARIA ");
        list.add("IGREJA  :  IGREJA SANTA MARIA MAIOR ");
        list.add(" IGREJA SANTA MARIA MAIOR ");
        list.add("MONUMENTO  :  RELOGIO ");
        list.add(" RELOGIO ");
        list.add("IGREJA  :  CAPELA S�O MARTINHO ");
        list.add(" CAPELA S�O MARTINHO ");
        list.add("ESCOLA  :  BIBLIOTECA ");
        list.add(" BIBLIOTECA ");
        list.add("MONUMENTO  :  ARCO DA UBI ");
        list.add(" ARCO DA UBI ");
        list.add("IGREJA  :  IGREJA ENGENHARIAS ");
        list.add(" IGREJA ENGENHARIAS ");
        list.add("IGREJA  :  IGREJA DE S�O SILVESTRE ");
        list.add(" IGREJA DE S�O SILVESTRE ");
        list.add("IGREJA  :  IGREJA DA MISERICORDIA ");
        list.add(" IGREJA DA MISERICORDIA ");
        list.add("MONUMENTO  :  POLARINHO ");
        list.add(" POLARINHO ");
        list.add("MONUMENTO  :  CHAFARIZ ");
        list.add(" CHAFARIZ ");
        list.add("IGREJA  :  IGREJA NOSSA SENHORA DA CONCEI��O ");
        list.add(" IGREJA NOSSA SENHORA DA CONCEI��O ");
        list.add("MUSEU  :  MUSEU DE ARTE SACRA ");
        list.add(" MUSEU DE ARTE SACRA ");
        list.add("MONUMENTO  :  JANELA ");
        list.add(" JANELA ");
        list.add("MONUMENTO  :  C�MARA MUNICIPAL ");
        list.add(" C�MARA MUNICIPAL ");
        list.add("ZONA DE LAZER  :  SERRA DA ESTRELA ");
        list.add(" SERRA DA ESTRELA ");
        list.add("ESCOLA  :  FACULDADE CIENCIAS SOCIAS E HUMANAS ");
        list.add(" FACULDADE CIENCIAS SOCIAS E HUMANAS ");
        list.add("ESCOLA  :  FACULDADE ENGENHARIAS ");
        list.add(" FACULDADE ENGENHARIAS ");
        list.add("ESCOLA  :  FAL E FC ");
        list.add(" FAL E FC ");
        list.add("MUSEU  :  MUSEU DOS LANIFICIOS ");
        list.add(" MUSEU DOS LANIFICIOS ");
        list.add("ESCOLA  :  FACULDADE CIENCIAS DA SAUDE ");
        list.add(" FACULDADE CIENCIAS DA SAUDE ");
        list.add("ZONA COMERCIAL  :  SERRA SHOPPING ");
        list.add(" SERRA SHOPPING ");
        list.add("ZONA DESPORTIVA  :  PAVILH�O DESPORTIVO ");
        list.add(" PAVILH�O DESPORTIVO ");
        list.add("MONUMENTO  :  NOSSA SENHORA DA CONCEI��O ");
        list.add(" NOSSA SENHORA DA CONCEI��O ");
        list.add("ESCOLA  :  REITORIA ");
        list.add(" REITORIA ");
        list.add("MONUMENTO  :  MIRADOURO DA REITORIA ");
        list.add(" MIRADOURO DA REITORIA ");
        list.add("MONUMENTO  :  PONTE DO SINEIRO ");
        list.add(" PONTE DO SINEIRO ");
        list.add("IGREJA  :  CAPELA DE SANTA CRUZ ");
        list.add(" CAPELA DE SANTA CRUZ ");



        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(Pesquisa.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Object o = listView.getItemAtPosition(position);

                System.out.println(o.toString());

                int po = list.indexOf(o);

                System.out.println(po+"xxxx "+((po/2)+1));

                monu_abre(po);


            }
        });


    }
    private void monu_abre(int po) {

        Intent intent = new Intent(this, MonumentosActivity.class);
        intent.putExtra("monumento", String.valueOf((po/2)+1));
        startActivity(intent);

    }
}