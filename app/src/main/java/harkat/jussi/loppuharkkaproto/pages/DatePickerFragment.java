package harkat.jussi.loppuharkkaproto.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

import java.util.Calendar;

import harkat.jussi.loppuharkkaproto.R;

/**
 * Created by Henri on 28.3.2018.
 */

public class DatePickerFragment extends Fragment {
    private static final String ARG_KEY = "key_2";

    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private DatePickerPage mPage;
    private DatePicker mDateView;


    public static DatePickerFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DatePickerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = (DatePickerPage) mCallbacks.onGetPage(mKey);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_date_picker, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        mDateView = rootView.findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        mPage.getData().putString(DatePickerPage.DATE_DATA_KEY,String.format("%d.%d.%d",calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR)));
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

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        mDateView.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker dPicker, int year, int month, int day) {
                mPage.getData().putString(DatePickerPage.DATE_DATA_KEY,String.format("%d.%d.%d",day,month+1,year));
                mPage.notifyDataChanged();
            }

        });

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);


    }
}
