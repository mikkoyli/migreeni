package harkat.jussi.loppuharkkaproto.pages;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;

import java.util.ArrayList;

/**
 * Created by Henri on 28.3.2018.
 */

public class EndTimePage extends Page {
    public static final String ENDTIME_DATA_KEY = "endtime";

    public EndTimePage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return EndTimeFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("Kohtauksen loppu", mData.getString(ENDTIME_DATA_KEY), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(ENDTIME_DATA_KEY));
    }
}
