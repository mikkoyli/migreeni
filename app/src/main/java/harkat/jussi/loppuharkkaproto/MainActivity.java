package harkat.jussi.loppuharkkaproto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tech.freak.wizardpager.ui.ReviewFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jussi = findViewById(R.id.ButtonJussi);
        jussi.setOnClickListener(this);
        try {
            DatabaseHandler dbhandler = new DatabaseHandler(this);
            ListView listView = findViewById(R.id.migreeniLista);

            final List<Migreeni> list = dbhandler.getMigraines();
            final List<String> _list = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                _list.add("migreenikohtaus\n" + list.get(i).getDate());//tähän lisäksi esim + list.get(i).getKellonAika() tms kakkaa
            }

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, _list);
            listView.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Intent intent = new Intent(MainActivity.this, MigreeniView.class);
                    Migreeni message = list.get(position);
                    intent.putExtra("migreeni", message);
                    startActivity(intent);
                }
            });
            dbhandler.close();
        } catch (Exception e) {
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.ButtonJussi:
                Intent intent = new Intent(getApplicationContext(), MigreeniLoki.class);
                startActivity(intent);
                break;

        }

    }
}
