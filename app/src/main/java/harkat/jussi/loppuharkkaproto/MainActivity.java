package harkat.jussi.loppuharkkaproto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button henri = (Button) findViewById(R.id.ButtonHenri);
        henri.setOnClickListener(this);
        Button heppu = (Button) findViewById(R.id.ButtonHeppu);
        heppu.setOnClickListener(this);
        Button jussi = (Button) findViewById(R.id.ButtonJussi);
        jussi.setOnClickListener(this);
        Button mikko = (Button) findViewById(R.id.ButtonMikko);
        mikko.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.ButtonHenri:
                Toast.makeText(MainActivity.this, "Jee hyvä henri anna palaa",
                        Toast.LENGTH_LONG).show();
                // tästä voisi alkaa henrin aktivity
                break;

            case R.id.ButtonHeppu:
                Toast.makeText(MainActivity.this, "Jee hyvä heppu anna palaa",
                        Toast.LENGTH_LONG).show();
                // tästä voisi alkaa hepun aktivity
                break;

            case R.id.ButtonJussi:
                Toast.makeText(MainActivity.this, "Jee hyvä jussi anna palaa",
                        Toast.LENGTH_LONG).show();
                // tästä voisi alkaa jussin aktivity
                break;

            case R.id.ButtonMikko:
                Toast.makeText(MainActivity.this, "Jee hyvä mikko anna palaa",
                        Toast.LENGTH_LONG).show();
                // tästä voisi alkaa mikon aktivity
                break;

        }

    }
}
