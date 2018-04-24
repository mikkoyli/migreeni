package harkat.jussi.loppuharkkaproto;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;
import com.tech.freak.wizardpager.ui.ReviewFragment;
import com.tech.freak.wizardpager.ui.StepPagerStrip;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import harkat.jussi.loppuharkkaproto.pages.DatePickerPage;
import harkat.jussi.loppuharkkaproto.pages.EndTimePage;
import harkat.jussi.loppuharkkaproto.pages.PainPage;
import harkat.jussi.loppuharkkaproto.pages.StartTimePage;

public class MigreeniLoki extends AppCompatActivity implements
        PageFragmentCallbacks, ReviewFragment.Callbacks, ModelCallbacks {

    private void restartFirstActivity(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private Migreeni migreeni;

    private ViewPager mPager;
    private MyPagerAdapter mPagerAdapter;

    private boolean mEditingAfterReview;

    private AbstractWizardModel mWizardModel = new SandwichWizardModel(this);

    private boolean mConsumePageSelectedEvent;

    private Button mNextButton;
    private Button mPrevButton;

    private List<Page> mCurrentPageSequence;
    private StepPagerStrip mStepPagerStrip;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        migreeni = new Migreeni();

        setContentView(R.layout.activity_migreeni_loki);

        Toolbar toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            mWizardModel.load(savedInstanceState.getBundle("model"));
        }

        mWizardModel.registerListener(this);

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        mStepPagerStrip = findViewById(R.id.strip);
        mStepPagerStrip
                .setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {
                    @Override
                    public void onPageStripSelected(int position) {
                        position = Math.min(mPagerAdapter.getCount() - 1,
                                position);
                        if (mPager.getCurrentItem() != position) {
                            mPager.setCurrentItem(position);
                        }
                    }
                });

        mNextButton = findViewById(R.id.next_button);
        mPrevButton = findViewById(R.id.prev_button);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mStepPagerStrip.setCurrentPage(position);

                if (mConsumePageSelectedEvent) {
                    mConsumePageSelectedEvent = false;
                    return;
                }

                mEditingAfterReview = false;
                updateBottomBar();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mPager.getCurrentItem() == mCurrentPageSequence.size()) {
                    try {
                        DatabaseHandler dbhandler = new DatabaseHandler(getApplicationContext());
                        dbhandler.addMigraine(migreeni);
                        dbhandler.close();
                    } catch (Exception e) {
                    }
                    restartFirstActivity("Migreeni tallennettu");
                } else {

                    try {
                        Page p = mWizardModel.getCurrentPageSequence().get(mPager.getCurrentItem());
                        tallennaMigreeni(p);
                    } catch (Exception e) {
                    }

                    if (mEditingAfterReview) {
                        mPager.setCurrentItem(mPagerAdapter.getCount() - 1);
                    } else {
                        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                    }
                }
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });

        onPageTreeChanged();
        updateBottomBar();
    }

    @Override
    public void onPageTreeChanged() {
        mCurrentPageSequence = mWizardModel.getCurrentPageSequence();
        recalculateCutOffPage();
        mStepPagerStrip.setPageCount(mCurrentPageSequence.size() + 1); // + 1 =
        // review
        // step
        mPagerAdapter.notifyDataSetChanged();
        updateBottomBar();
    }

    private void updateBottomBar() {
        int position = mPager.getCurrentItem();
        if (position == mCurrentPageSequence.size()) {
            mNextButton.setText(R.string.finish);
            mNextButton.setBackgroundResource(R.drawable.finish_background);
            mNextButton.setTextAppearance(this, R.style.TextAppearanceFinish);
        } else {
            mNextButton.setText(mEditingAfterReview ? R.string.review
                    : R.string.next);
            mNextButton
                    .setBackgroundResource(R.drawable.selectable_item_background);
            TypedValue v = new TypedValue();
            getTheme().resolveAttribute(android.R.attr.textAppearanceMedium, v,
                    true);
            mNextButton.setTextAppearance(this, v.resourceId);
            mNextButton.setEnabled(position != mPagerAdapter.getCutOffPage());
        }

        mPrevButton
                .setVisibility(position <= 0 ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWizardModel.unregisterListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("model", mWizardModel.save());
    }

    @Override
    public AbstractWizardModel onGetModel() {
        return mWizardModel;
    }

    @Override
    public void onEditScreenAfterReview(String key) {
        for (int i = mCurrentPageSequence.size() - 1; i >= 0; i--) {
            if (mCurrentPageSequence.get(i).getKey().equals(key)) {
                mConsumePageSelectedEvent = true;
                mEditingAfterReview = true;
                mPager.setCurrentItem(i);
                updateBottomBar();
                break;
            }
        }
    }

    private String regguls(Bundle b){
        String str = "";
        Set<String> keys = b.keySet();
        Iterator<String> it = keys.iterator();
        try {
            while (it.hasNext()) {
                String _key = it.next();
                str = b.get(_key).toString();
                str = str.substring(1, str.length() - 1);
            }
        } catch (Exception e) {
        }
        return str;
    }
    public void tallennaMigreeni(Page p) {
        Bundle bundle = p.getData();
        String key = p.getKey();
        switch (key) {
            case "Päivämäärä":
                migreeni.setDate(bundle.getString(DatePickerPage.DATE_DATA_KEY));
                break;
            case "Kohtauksen alku":
                migreeni.setStartTime(bundle.getString(StartTimePage.STARTTIME_DATA_KEY));
                break;
            case "Kohtauksen loppu":
                migreeni.setEndTime(bundle.getString(EndTimePage.ENDTIME_DATA_KEY));
                break;
            case "Kivun taso":
                migreeni.setPainIntensity(bundle.getString(PainPage.PAIN_DATA_KEY));
                break;
            case "Kohtauksen tyyppi":
                migreeni.setType(regguls(bundle));
                break;
            case "Kivun sijainti":
                migreeni.setPainLocation(bundle.getString("_"));
                break;
            case "Kuvaile itse:Kuvaile kivun sijaintia":
                migreeni.setPainLocation(bundle.getString("_"));
                break;
            case "Kivun tyyppi":
                migreeni.setType2(regguls(bundle));
                break;
            case "Kyllä:Kuvaile muita oireita":
                migreeni.setOtherSymptoms(bundle.getString("_"));
                break;
            case "Otettu:Lääkkeen nimi ja annostus":
                migreeni.setMedicineTaken(bundle.getString("_"));
                break;
            case "Otettu:Lääkkeen vaikutus":
                migreeni.setMedicineEffect(bundle.getString("_"));
                break;
            case "Sijainti kohtauksen alkaessa":
                migreeni.setLocation(bundle.getString("_"));
                break;
            case "Ennakko-oireet":
                migreeni.setPresymptoms(regguls(bundle));
                break;
            case "Kyllä:Kuvaile muita ennakko-oireita":
                migreeni.setPresymptoms2(bundle.getString("_"));
                break;
            case "Laukaisevat tekijät":
                migreeni.setTriggers(regguls(bundle));
                break;
            case "Kyllä:Kuvaile muita laukaisevia tekijöitä":
                migreeni.setTriggers2(bundle.getString("_"));
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageDataChanged(Page page) {
        if (page.isRequired()) {
            if (recalculateCutOffPage()) {
                mPagerAdapter.notifyDataSetChanged();
                updateBottomBar();
            }
        }
    }

    @Override
    public Page onGetPage(String key) {
        return mWizardModel.findByKey(key);
    }

    private boolean recalculateCutOffPage() {
        // Cut off the pager adapter at first required page that isn't completed
        int cutOffPage = mCurrentPageSequence.size() + 1;
        for (int i = 0; i < mCurrentPageSequence.size(); i++) {
            Page page = mCurrentPageSequence.get(i);
            if (page.isRequired() && !page.isCompleted()) {
                cutOffPage = i;
                break;
            }
        }

        if (mPagerAdapter.getCutOffPage() != cutOffPage) {
            mPagerAdapter.setCutOffPage(cutOffPage);
            return true;
        }

        return false;
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        private int mCutOffPage;
        private Fragment mPrimaryItem;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (i >= mCurrentPageSequence.size()) {
                return new ReviewFragment();
            }

            return mCurrentPageSequence.get(i).createFragment();
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO: be smarter about this
            if (object == mPrimaryItem) {
                // Re-use the current fragment (its position never changes)
                return POSITION_UNCHANGED;
            }

            return POSITION_NONE;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position,
                                   Object object) {
            super.setPrimaryItem(container, position, object);
            mPrimaryItem = (Fragment) object;
        }

        @Override
        public int getCount() {
            return Math.min(mCutOffPage + 1, mCurrentPageSequence == null ? 1
                    : mCurrentPageSequence.size() + 1);
        }

        public void setCutOffPage(int cutOffPage) {
            if (cutOffPage < 0) {
                cutOffPage = Integer.MAX_VALUE;
            }
            mCutOffPage = cutOffPage;
        }

        public int getCutOffPage() {
            return mCutOffPage;
        }
    }
}