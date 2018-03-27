package harkat.jussi.loppuharkkaproto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jussi on 26.3.2018.
 */

public class WizardManager extends AppCompatActivity implements IWizardManager {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.migraine_wizard);

        init();
    }

    private void init() {
        WizardFragment1 fragment = new WizardFragment1();
        doFragmentTransaction(fragment, getString(R.string.migraine_fragment1), false);
    }

    private void doFragmentTransaction(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, tag);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    @Override
    public void inflateFragnment(String fragmentTag) {
        if (fragmentTag.equals(getString(R.string.migraine_fragment1))) {
            WizardFragment1 fragment = new WizardFragment1();
            doFragmentTransaction(fragment, fragmentTag, true);
        }
        else if (fragmentTag.equals(getString(R.string.migraine_fragment2))) {
            WizardFragment2 fragment = new WizardFragment2();
            doFragmentTransaction(fragment, fragmentTag, false);
        }
        else if (fragmentTag.equals(getString(R.string.migraine_fragment3))) {
            WizardFragment3 fragment = new WizardFragment3();
            doFragmentTransaction(fragment, fragmentTag, false);
        }
    }
}
