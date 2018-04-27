package harkat.jussi.loppuharkkaproto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    GraphView graph;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        initializeLineGraphView();
    }
}
