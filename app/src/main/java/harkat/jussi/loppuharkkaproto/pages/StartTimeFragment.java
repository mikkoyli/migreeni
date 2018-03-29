package harkat.jussi.loppuharkkaproto.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

import java.util.Calendar;

import harkat.jussi.loppuharkkaproto.R;

/**
 * Created by Henri on 28.3.2018.
 */

public class StartTimeFragment extends Fragment {
    private static final String ARG_KEY = "key_3";

    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private StartTimePage mPage;
    private TimePicker tp;


    public static StartTimeFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        StartTimeFragment fragment = new StartTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public StartTimeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = (StartTimePage) mCallbacks.onGetPage(mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_start_time, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        tp = rootView.findViewById(R.id.timePicker);
        tp.setIs24HourView(true);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException("Activity must implement PageFragmentCallbacks");
        }

        mCallbacks = (PageFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tp.setOnTimeChangedListener(timeChangedListener);
    }
    private TimePicker.OnTimeChangedListener timeChangedListener =
            new TimePicker.OnTimeChangedListener(){
                @Override
                public void onTimeChanged(TimePicker view, int hours, int minutes) {

                    mPage.getData().putString(StartTimePage.STARTTIME_DATA_KEY,String.format("%02d:%02d", hours, minutes));
                    mPage.notifyDataChanged();
                }
            };




    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);


    }
}
