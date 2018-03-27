package harkat.jussi.loppuharkkaproto;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Jussi on 26.3.2018.
 */

public class WizardFragment3 extends Fragment  implements View.OnClickListener {

    private static final String TAG = "migraine_fragment3";
    private IWizardManager iWizardManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.migraine_fragment3, container, false);

        Button backToMain =(Button) view.findViewById(R.id.log_cancel_button);
        Button prevPage =(Button) view.findViewById(R.id.log_prev_button);
        Button saveMigraine =(Button) view.findViewById(R.id.log_finish_button);

        backToMain.setOnClickListener(this);
        prevPage.setOnClickListener(this);
        saveMigraine.setOnClickListener(this);

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

            case R.id.log_prev_button:
                iWizardManager.inflateFragnment(getString(R.string.migraine_fragment2));
                break;

            case R.id.log_finish_button:
                Toast.makeText((WizardManager)getContext(), "tallennetaan migreeni",Toast.LENGTH_SHORT).show();
                ((WizardManager)getActivity()).finish();
                break;
        }

    }
}
