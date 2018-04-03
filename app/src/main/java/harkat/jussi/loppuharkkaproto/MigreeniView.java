package harkat.jussi.loppuharkkaproto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MigreeniView extends AppCompatActivity {

    private TextView migreeniText;
    private Migreeni migreeni;
    private Button b1, b2;
    private void restartFirstActivity(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_migreeni_view);
        migreeniText = findViewById(R.id.migreeniTextView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            migreeni = (Migreeni) getIntent().getSerializableExtra("migreeni");
            Log.e("juha", migreeni.getDate());
            migreeniText.setText(migreeni.toString());
        }
        b1 = findViewById(R.id.buttonBack);
        b2 = findViewById(R.id.buttonDelete);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DatabaseHandler dbhandler = new DatabaseHandler(getApplicationContext());
                    dbhandler.deleteMigraine(migreeni.getID());
                    restartFirstActivity("Migreeni poistettu");
                } catch (Exception e) {
                }
                ;
            }
        });
    }
}
