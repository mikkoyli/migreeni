package harkat.jussi.loppuharkkaproto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MigreeniLoki extends AppCompatActivity implements View.OnClickListener {

    ListView MigreeniLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_migreeni_loki);

        Button takaisin = (Button) findViewById(R.id.buttonTakaisinLokista);
        takaisin.setOnClickListener(this);

        Toast.makeText(MigreeniLoki.this, "Jee hyvä jussi anna palaa",
                Toast.LENGTH_LONG).show();
        //ListView listView = new ListView(getApplicationContext(), MigreeniLista)

    }

    @Override
    public void onClick(View view) {
        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(intent);
        finish();
    }
}
