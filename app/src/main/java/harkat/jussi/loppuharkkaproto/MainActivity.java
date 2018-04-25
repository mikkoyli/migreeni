package harkat.jussi.loppuharkkaproto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    BarGraphSeries<DataPoint> series;
    GraphView graph;

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
        final List<String> _list = new ArrayList<>();

        DataPoint[] dp =new DataPoint[list.size()];
        for (int i = 0;i<list.size() ; i++) {
            _list.add(list.get(i).getDate());
            Log.d("JALAJALA", list.get(i).getDate());

            String dateraw = list.get(i).getDate();
            Date result = null;
            DateFormat df = new SimpleDateFormat("dd.MM.YYYY");
            try {
                result =  df.parse(dateraw);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.e("JALAJALA","Error with date parsing");
            }

            // todo: tässä pitäisi vissiin jotenki laittaa se migreeni datapointtiin? new SimpleDateFormat("yyyy-MM-dd").parse(newStringDate)
            dp[i] = new DataPoint(result, i);

        }
        return dp;
    }

    public void initializeLineGraphView() {
        graph = (GraphView) findViewById(R.id.graph);
        // esimerkki: barchart
        /*BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });*/
        series = new BarGraphSeries<DataPoint>(getData());
        graph.addSeries(series);

        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        series.setSpacing(25);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);

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
