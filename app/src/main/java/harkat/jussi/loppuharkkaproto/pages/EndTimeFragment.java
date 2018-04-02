package harkat.jussi.loppuharkkaproto.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

import harkat.jussi.loppuharkkaproto.R;

/**
 * Created by Henri on 28.3.2018.
 */

public class EndTimeFragment extends Fragment {
    private static final String ARG_KEY = "key_4";

    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private EndTimePage mPage;
    private TimePicker tp;


    public static EndTimeFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        EndTimeFragment fragment = new EndTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public EndTimeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = (EndTimePage) mCallbacks.onGetPage(mKey);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_start_time, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        tp = rootView.findViewById(R.id.timePicker);
        tp.setIs24HourView(true);
        mPage.getData().putString(EndTimePage.ENDTIME_DATA_KEY,String.format("%02d:%02d", tp.getHour(),tp.getMinute()));
        mPage.notifyDataChanged();
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

                    mPage.getData().putString(EndTimePage.ENDTIME_DATA_KEY,String.format("%02d:%02d", hours, minutes));
                    mPage.notifyDataChanged();
                }
            };




    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);


    }
}
