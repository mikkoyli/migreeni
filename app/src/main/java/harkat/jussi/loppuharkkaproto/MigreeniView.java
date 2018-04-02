package harkat.jussi.loppuharkkaproto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MigreeniView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_migreeni_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           Migreeni migreeni = (Migreeni)getIntent().getSerializableExtra("migreeni");
           Log.e("juha",migreeni.getDate());
        }
    }
}
