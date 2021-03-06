package harkat.jussi.loppuharkkaproto.pages;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;

import java.util.ArrayList;

/**
 * Created by Henri on 28.3.2018.
 */

public class DatePickerPage extends Page {
    public static final String DATE_DATA_KEY = "date";

    public DatePickerPage (ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return DatePickerFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("Päivämäärä", mData.getString(DATE_DATA_KEY), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(DATE_DATA_KEY));
    }
}
