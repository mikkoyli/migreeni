package harkat.jussi.loppuharkkaproto.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

import harkat.jussi.loppuharkkaproto.R;

/**
 * Created by Henri on 28.3.2018.
 */

public class PainFragment extends Fragment {
    private static final String ARG_KEY = "key_5";

    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private PainPage mPage;
    private NumberPicker np;


    public static PainFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        PainFragment fragment = new PainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = (PainPage) mCallbacks.onGetPage(mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_pain, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        np = rootView.findViewById(R.id.painPicker);
        np.setMinValue(0);
        np.setMaxValue(10);

        mPage.getData().putString(PainPage.PAIN_DATA_KEY, String.format("%d", np.getValue()));
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


        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mPage.getData().putString(PainPage.PAIN_DATA_KEY, String.format("%d", newVal));
                mPage.notifyDataChanged();
            }
        });

    }



    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);


    }
}
