package news.com.newstechapplication.adapters;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import news.com.newstechapplication.R;
import news.com.newstechapplication.fragments.AIFragment;
import news.com.newstechapplication.fragments.CyberSecurityFragment;
import news.com.newstechapplication.models.AINews;
import news.com.newstechapplication.models.CyberSecurityNews;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES =
            new int[]{R.string.text_cyber_security, R.string.ai, R.string.ai};
    private final Context mContext;
    private final ArrayList<AINews> aiNewsArrayList;
    private final ArrayList<CyberSecurityNews> cyberSecurityNewsArrayList;

    public TabsPagerAdapter(Context context, FragmentManager fm, ArrayList<AINews> aiNewsArrayList, ArrayList<CyberSecurityNews> cyberSecurityNewsArrayList) {
        super(fm);
        mContext = context;
        this.aiNewsArrayList = aiNewsArrayList;
        this.cyberSecurityNewsArrayList = cyberSecurityNewsArrayList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CyberSecurityFragment(cyberSecurityNewsArrayList);
            case 1:

                return new AIFragment(aiNewsArrayList);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

}
