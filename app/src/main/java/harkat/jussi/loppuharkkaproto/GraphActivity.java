package harkat.jussi.loppuharkkaproto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GraphActivity extends AppCompatActivity implements View.OnClickListener {

    BarGraphSeries<DataPoint> series;
    GraphView graph;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");


    public void initializeLineGraphView() {
        DatabaseHandler dbhandler = new DatabaseHandler(this);
        final List<Migreeni> list = dbhandler.getMigraines();
        graph = (GraphView) findViewById(R.id.graph);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5",
                                                                "6","7", "8", "9", "10",
                                                                "11", "12", "13", "14", "15",
                                                                "16","17", "18", "19", "20",
                                                                "21","22", "23", "24", "25",
                                                                "26","27", "28", "29", "30", "31"});

        staticLabelsFormatter.setVerticalLabels(new String[] {"0", "1", "2", "3", "4", "5", "6",
                                                                "7", "8", "9", "10"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


        // debugging datapoints
        /*DataPoint[] DPs = getData();
        Log.d("JALAJALA", "datapoints: " + DPs.length);
        for (int i = 0; i < DPs.length; i++) {
            Log.d("JALAJALA", DPs[i].toString());
            Log.d("JALAJALA", "i: " + i);
        }*/
        if (list != null) {
            //Log.d("JALAJALA", "NOT NULL");
            series = new BarGraphSeries<DataPoint>(getData());
            Log.d("JALAJALA", "+"+list);
            /*BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(1, 11),
                    new DataPoint(5, 6),
                    new DataPoint(10, 3),
                    new DataPoint(15, 11),
                    new DataPoint(20, 1), // tän rivin tarvii että piirtää oikein :D
                    new DataPoint(20, 2),
                    new DataPoint(25, 6),
                    new DataPoint(31, 11),
            });*/
            series.setDataWidth(0.2);
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
            //long unixTime = 0;
            int intensity = 0;
            //unixTime = result.getTime() / 1000;

            Calendar cal = Calendar.getInstance();
            cal.setTime(result);

            int day = cal.get(Calendar.DAY_OF_MONTH);

            intensity = Integer.parseInt(list.get(i).getPainIntensity());
            Log.d("JALAJALA", "day: " + day + " intens: " + intensity);
            // todo: tähän pitäisi i:n tilalle laittaa päivämäärä, parsinta ei vissiin toimi ihan oikein
            dp[i] = new DataPoint(day, intensity);
            Log.d("JALAJALA", "new dp: " + dp[i].toString());
        }

        return dp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Button buttonGraph = findViewById(R.id.buttonBack);
        buttonGraph.setOnClickListener(this);

        initializeLineGraphView();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.buttonBack:

                finish();
                break;

        }

    }
}
