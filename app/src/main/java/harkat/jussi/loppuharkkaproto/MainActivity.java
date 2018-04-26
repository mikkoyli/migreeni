package harkat.jussi.loppuharkkaproto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LineGraphSeries<DataPoint> series;
    GraphView graph;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logButton = (Button) findViewById(R.id.MigreeniLoki);
        logButton.setOnClickListener(this);

        initializeLineGraphView();

    }

    private DataPoint[] getData() {
        DatabaseHandler dbhandler = new DatabaseHandler(this);
        final List<Migreeni> list = dbhandler.getMigraines();
        DataPoint[] dp =new DataPoint[list.size()];

        Log.d("JALAJALA", "LIST IS NOT EMPTY");
        for (int i = 0; i < list.size(); i++) {

            Date result = null;
            DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
            try {
                result = df.parse(list.get(i).getDate());
            } catch (ParseException e) {
                e.printStackTrace();
                Log.e("JALAJALA", "Error with date parsing");
            }
            long unixTime = 0;
            int intensity = 0;
            unixTime = result.getTime() / 1000;
            intensity = Integer.parseInt(list.get(i).getPainIntensity());
            Log.d("JALAJALA", "date: " + result + " intens: " + intensity);
             // todo: tähän pitäisi i:n tilalle laittaa päivämäärä, parsinta ei vissiin toimi ihan oikein
            dp[i] = new DataPoint(i, intensity);
            Log.d("JALAJALA", "new dp: " + dp[i].toString());
            }

        return dp;
    }

    public void initializeLineGraphView() {
        DatabaseHandler dbhandler = new DatabaseHandler(this);
        final List<Migreeni> list = dbhandler.getMigraines();
        graph = (GraphView) findViewById(R.id.graph);

        // debugging datapoints
        /*DataPoint[] DPs = getData();
        Log.d("JALAJALA", "datapoints: " + DPs.length);
        for (int i = 0; i < DPs.length; i++) {
            Log.d("JALAJALA", DPs[i].toString());
            Log.d("JALAJALA", "i: " + i);
        }*/
        if (list != null) {
            Log.d("JALAJALA", "NOT NULL");
            series = new LineGraphSeries<DataPoint>(getData());
            graph.addSeries(series);
        } else {
            Log.d("JALAJALA", "IS NULL");

        }

        // tämän pitäisi renderödi x akselille päivämäärät sdf:n mukaan
        /*graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return sdf.format(new Date((long)value));
                }else{
                    return super.formatLabel(value, isValueX);
                }

            }
        });*/
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.MigreeniLoki:
                Intent intent = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(intent);
                break;

        }
    }
}
