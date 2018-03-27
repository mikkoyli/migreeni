package harkat.jussi.loppuharkkaproto;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.NumberPicker;


import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


/**
 * Created by Jussi on 26.3.2018.
 */

public class WizardFragment1 extends Fragment implements View.OnClickListener {

    private static final String TAG = "migraine_fragment1";
    private IWizardManager iWizardManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.migraine_fragment1, container, false);

        Button newDate = view.findViewById(R.id.log_new_date_button);
        Button newStart = view.findViewById(R.id.log_new_start_button);
        Button newEnd = view.findViewById(R.id.log_new_end_button);
        Button newPain = view.findViewById(R.id.log_new_pain_button);
        Button backToMain = view.findViewById(R.id.log_cancel_button);
        Button nextPage = view.findViewById(R.id.log_next_button);

        newDate.setOnClickListener(this);
        newStart.setOnClickListener(this);
        newEnd.setOnClickListener(this);
        newPain.setOnClickListener(this);
        backToMain.setOnClickListener(this);
        nextPage.setOnClickListener(this);

       return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iWizardManager = (WizardManager)getContext();
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.log_cancel_button:
                ((WizardManager)getActivity()).finish();
                break;

            case R.id.log_new_date_button:

                dateDialog();
                break;

            case R.id.log_new_start_button:

                startTimeDialog();
                break;

            case R.id.log_new_end_button:

                endTimeDialog();
                break;

            case R.id.log_new_pain_button:

                painDialog();
                break;

            case R.id.log_next_button:
                iWizardManager.inflateFragnment(getString(R.string.migraine_fragment2));

                break;

        }
    }

    public void dateDialog() {

        final Button setDate = getView().findViewById(R.id.setDate);
        final DatePicker datePicker = getView().findViewById(R.id.datePicker);
        setDate.setVisibility(VISIBLE);
        datePicker.setVisibility(VISIBLE);


        final TimePicker timePicker = getView().findViewById(R.id.timePicker);
        final NumberPicker painPicker = getView().findViewById(R.id.painPicker);
        final Button setPain = getView().findViewById(R.id.setPain);
        final Button newStart = getView().findViewById(R.id.log_new_start_button);
        final Button newEnd = getView().findViewById(R.id.log_new_end_button);
        final Button newPain = getView().findViewById(R.id.log_new_pain_button);
        final Button setTime = getView().findViewById(R.id.setTime);
        final CheckBox aurallinen = getView().findViewById(R.id.aurallinenBox);
        final CheckBox auraton = getView().findViewById(R.id.auratonBox);
        final TextView date = getView().findViewById(R.id.log_new_dateText);
        final TextView start = getView().findViewById(R.id.log_new_startText);
        final TextView end = getView().findViewById(R.id.log_new_endText);
        final TextView pain = getView().findViewById(R.id.log_new_painText);

        timePicker.setVisibility(INVISIBLE);
        painPicker.setVisibility(INVISIBLE);
        setTime.setVisibility(INVISIBLE);
        setPain.setVisibility(INVISIBLE);

        newStart.setVisibility(INVISIBLE);
        newEnd.setVisibility(INVISIBLE);
        newPain.setVisibility(INVISIBLE);
        aurallinen.setVisibility(INVISIBLE);
        auraton.setVisibility(INVISIBLE);
        date.setVisibility(INVISIBLE);
        start.setVisibility(INVISIBLE);
        end.setVisibility(INVISIBLE);
        pain.setVisibility(INVISIBLE);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                TextView dateText = getView().findViewById(R.id.log_new_dateText);
                dateText.setText(String.format("%d.%d.%d", day, month+1, year));
                setDate.setVisibility(INVISIBLE);
                datePicker.setVisibility(INVISIBLE);
                newStart.setVisibility(VISIBLE);
                newEnd.setVisibility(VISIBLE);
                newPain.setVisibility(VISIBLE);
                aurallinen.setVisibility(VISIBLE);
                auraton.setVisibility(VISIBLE);
                date.setVisibility(VISIBLE);
                start.setVisibility(VISIBLE);
                end.setVisibility(VISIBLE);
                pain.setVisibility(VISIBLE);
            }
        });

    }

    public void startTimeDialog(){

        final TimePicker timePicker = getView().findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        final Button setStartTime = getView().findViewById(R.id.setTime);
        setStartTime.setVisibility(VISIBLE);
        timePicker.setVisibility(VISIBLE);


        final DatePicker datePicker = getView().findViewById(R.id.datePicker);
        final NumberPicker painPicker = getView().findViewById(R.id.painPicker);
        final Button setDate = getView().findViewById(R.id.setDate);
        final Button setPain = getView().findViewById(R.id.setPain);
        final Button newStart = getView().findViewById(R.id.log_new_start_button);
        final Button newEnd = getView().findViewById(R.id.log_new_end_button);
        final Button newPain = getView().findViewById(R.id.log_new_pain_button);
        final Button newDate = getView().findViewById(R.id.log_new_date_button);
        final CheckBox aurallinen = getView().findViewById(R.id.aurallinenBox);
        final CheckBox auraton = getView().findViewById(R.id.auratonBox);
        final TextView date = getView().findViewById(R.id.log_new_dateText);
        final TextView start = getView().findViewById(R.id.log_new_startText);
        final TextView end = getView().findViewById(R.id.log_new_endText);
        final TextView pain = getView().findViewById(R.id.log_new_painText);

        datePicker.setVisibility(INVISIBLE);
        painPicker.setVisibility(INVISIBLE);
        setDate.setVisibility(INVISIBLE);
        setPain.setVisibility(INVISIBLE);

        newStart.setVisibility(INVISIBLE);
        newEnd.setVisibility(INVISIBLE);
        newPain.setVisibility(INVISIBLE);
        newDate.setVisibility(INVISIBLE);
        aurallinen.setVisibility(INVISIBLE);
        auraton.setVisibility(INVISIBLE);
        date.setVisibility(INVISIBLE);
        start.setVisibility(INVISIBLE);
        end.setVisibility(INVISIBLE);
        pain.setVisibility(INVISIBLE);


        setStartTime.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                int minutes = timePicker.getMinute();
                int hours = timePicker.getHour();
                TextView startText = getView().findViewById(R.id.log_new_startText);
                startText.setText(String.format("%02d:%02d", hours, minutes));
                setStartTime.setVisibility(INVISIBLE);
                timePicker.setVisibility(INVISIBLE);
                newStart.setVisibility(VISIBLE);
                newEnd.setVisibility(VISIBLE);
                newPain.setVisibility(VISIBLE);
                newDate.setVisibility(VISIBLE);
                aurallinen.setVisibility(VISIBLE);
                auraton.setVisibility(VISIBLE);
                date.setVisibility(VISIBLE);
                start.setVisibility(VISIBLE);
                end.setVisibility(VISIBLE);
                pain.setVisibility(VISIBLE);
            }
        });
    }

    public void endTimeDialog(){

        final TimePicker timePicker = getView().findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        final Button setEndTime = getView().findViewById(R.id.setTime);
        setEndTime.setVisibility(VISIBLE);
        timePicker.setVisibility(VISIBLE);


        final DatePicker datePicker = getView().findViewById(R.id.datePicker);
        final NumberPicker painPicker = getView().findViewById(R.id.painPicker);
        final Button setDate = getView().findViewById(R.id.setDate);
        final Button setPain = getView().findViewById(R.id.setPain);
        final Button newStart = getView().findViewById(R.id.log_new_start_button);
        final Button newEnd = getView().findViewById(R.id.log_new_end_button);
        final Button newPain = getView().findViewById(R.id.log_new_pain_button);
        final Button newDate = getView().findViewById(R.id.log_new_date_button);
        final CheckBox aurallinen = getView().findViewById(R.id.aurallinenBox);
        final CheckBox auraton = getView().findViewById(R.id.auratonBox);
        final TextView date = getView().findViewById(R.id.log_new_dateText);
        final TextView start = getView().findViewById(R.id.log_new_startText);
        final TextView end = getView().findViewById(R.id.log_new_endText);
        final TextView pain = getView().findViewById(R.id.log_new_painText);

        datePicker.setVisibility(INVISIBLE);
        painPicker.setVisibility(INVISIBLE);
        setDate.setVisibility(INVISIBLE);
        setPain.setVisibility(INVISIBLE);

        newStart.setVisibility(INVISIBLE);
        newEnd.setVisibility(INVISIBLE);
        newPain.setVisibility(INVISIBLE);
        newDate.setVisibility(INVISIBLE);
        aurallinen.setVisibility(INVISIBLE);
        auraton.setVisibility(INVISIBLE);
        date.setVisibility(INVISIBLE);
        start.setVisibility(INVISIBLE);
        end.setVisibility(INVISIBLE);
        pain.setVisibility(INVISIBLE);

        setEndTime.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                int minutes = timePicker.getMinute();
                int hours = timePicker.getHour();
                TextView startText = getView().findViewById(R.id.log_new_endText);
                startText.setText(String.format("%02d:%02d", hours, minutes));
                setEndTime.setVisibility(INVISIBLE);
                timePicker.setVisibility(INVISIBLE);
                newStart.setVisibility(VISIBLE);
                newEnd.setVisibility(VISIBLE);
                newPain.setVisibility(VISIBLE);
                newDate.setVisibility(VISIBLE);
                aurallinen.setVisibility(VISIBLE);
                auraton.setVisibility(VISIBLE);
                date.setVisibility(VISIBLE);
                start.setVisibility(VISIBLE);
                end.setVisibility(VISIBLE);
                pain.setVisibility(VISIBLE);
            }
        });
    }

    public void painDialog(){

        final NumberPicker painPicker = getView().findViewById(R.id.painPicker);
        final Button setPain = getView().findViewById(R.id.setPain);
        painPicker.setMinValue(0);
        painPicker.setMaxValue(10);
        painPicker.setVisibility(VISIBLE);
        setPain.setVisibility(VISIBLE);


        final DatePicker datePicker = getView().findViewById(R.id.datePicker);
        final TimePicker timePicker = getView().findViewById(R.id.timePicker);
        final Button newDate = getView().findViewById(R.id.log_new_date_button);
        final Button newStart = getView().findViewById(R.id.log_new_start_button);
        final Button newEnd = getView().findViewById(R.id.log_new_end_button);
        final Button newPain = getView().findViewById(R.id.log_new_pain_button);
        final Button setTime = getView().findViewById(R.id.setTime);
        final CheckBox aurallinen = getView().findViewById(R.id.aurallinenBox);
        final CheckBox auraton = getView().findViewById(R.id.auratonBox);
        final TextView date = getView().findViewById(R.id.log_new_dateText);
        final TextView start = getView().findViewById(R.id.log_new_startText);
        final TextView end = getView().findViewById(R.id.log_new_endText);
        final TextView pain = getView().findViewById(R.id.log_new_painText);

        datePicker.setVisibility(INVISIBLE);
        timePicker.setVisibility(INVISIBLE);
        setTime.setVisibility(INVISIBLE);

        newDate.setVisibility(INVISIBLE);
        newStart.setVisibility(INVISIBLE);
        newEnd.setVisibility(INVISIBLE);
        newPain.setVisibility(INVISIBLE);
        aurallinen.setVisibility(INVISIBLE);
        auraton.setVisibility(INVISIBLE);
        date.setVisibility(INVISIBLE);
        start.setVisibility(INVISIBLE);
        end.setVisibility(INVISIBLE);
        pain.setVisibility(INVISIBLE);

        setPain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView painText = getView().findViewById(R.id.log_new_painText);
                painText.setText(String.format("%d", painPicker.getValue()));
                painPicker.setVisibility(INVISIBLE);
                setPain.setVisibility(INVISIBLE);

                newStart.setVisibility(VISIBLE);
                newEnd.setVisibility(VISIBLE);
                newPain.setVisibility(VISIBLE);
                newDate.setVisibility(VISIBLE);
                aurallinen.setVisibility(VISIBLE);
                auraton.setVisibility(VISIBLE);
                date.setVisibility(VISIBLE);
                start.setVisibility(VISIBLE);
                end.setVisibility(VISIBLE);
                pain.setVisibility(VISIBLE);
            }
        });
    }
}

