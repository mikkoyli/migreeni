package harkat.jussi.loppuharkkaproto;

/**
 * Created by Jussi on 17.3.2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MigreeniLoki extends AppCompatActivity implements View.OnClickListener {

    private List<Migreeni> migraineList = new ArrayList<Migreeni>();
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_migreeni_loki);

        Button backToMain = (Button) findViewById(R.id.backToMain);
        backToMain.setOnClickListener(this);
        Button newMig = (Button) findViewById(R.id.newMigraine);
        newMig.setOnClickListener(this);

        populateMigraineList();
        populateListView();
        viewClickedMigraine();

        db = new DatabaseHandler(this);
        //db.addMigraine(migraineList.get(0)); //migreenin tallennus
        // db.getMigraines(); // migreenien listaaminen

    }

    private void viewClickedMigraine() {
        ListView list = (ListView) findViewById(R.id.MigreeniLista);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position,
                                    long id) {
                Migreeni clickedMigraine = migraineList.get(position);
                String message = "Näytetään tarkemmat tiedot migreenistä "+ clickedMigraine.getDate();
                Toast.makeText(MigreeniLoki.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateMigraineList() {
        migraineList.add(new Migreeni("17.3.2018", 25, 10,
                "Aurallinen","näköharhat","hammas, otsa",
                "oksu, valoherkkyys", "helvetisti herskaa",
                "baari", "stressi ja punkku", "Ei", R.drawable.pain10));

        migraineList.add(new Migreeni("17.2.2018", 25, 2, "Auraton",
                "valoherkkyys","pää","oksu",
                "", "home", "koksu ja liikunta", "Ei", R.drawable.pain2));
    }

    private void populateListView() {
        ArrayAdapter<Migreeni> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.MigreeniLista);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Migreeni> {
        public MyListAdapter() {
            super(MigreeniLoki.this, R.layout.migraine_list, migraineList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //katotaan että itemviewissä ei ole null
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.migraine_list, parent, false);
            }
            //etsitään migreeni listasta positionilla
            Migreeni migreeni = migraineList.get(position);
            // lisätään ne listaan
            //lisää kuva
            ImageView imageView =  (ImageView)itemView.findViewById(R.id.painIcon);
            imageView.setImageResource(migreeni.getIconID());
            //lisää päivä
            TextView dateText = (TextView) itemView.findViewById(R.id.logDate);
            dateText.setText(migreeni.getDate());
            //lisää päivä
            TextView painText = (TextView) itemView.findViewById(R.id.logPain);
            String painString = String.valueOf(migreeni.getPainIntensity());
            painText.setText(painString);
            //lisää päivä
            TextView typeText = (TextView) itemView.findViewById(R.id.logType);
            typeText.setText(migreeni.getType());
            return itemView;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backToMain:
                finish();
                break;

            case R.id.newMigraine:

                //Intent intent = new Intent(getApplicationContext(), oldMigraineWizard.class);
                Intent intent = new Intent(getApplicationContext(), WizardManager.class);
                startActivity(intent);
                break;
        }
    }
}
