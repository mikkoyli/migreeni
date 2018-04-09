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

public class StartTimePage extends Page {
    public static final String STARTTIME_DATA_KEY = "starttime";

    public StartTimePage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return StartTimeFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("Kohtauksen alku", mData.getString(STARTTIME_DATA_KEY), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(STARTTIME_DATA_KEY));
    }
}
