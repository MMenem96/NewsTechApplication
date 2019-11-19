package news.com.newstechapplication.activites;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import news.com.newstechapplication.R;
import news.com.newstechapplication.adapters.TabsPagerAdapter;
import news.com.newstechapplication.fragments.AIFragment;
import news.com.newstechapplication.fragments.CyberSecurityFragment;
import news.com.newstechapplication.fragments.WebsiteFragment;
import news.com.newstechapplication.models.AINews;
import news.com.newstechapplication.models.CyberSecurityNews;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewPager;
    private ArrayList<AINews> aiNewsArrayList;
    private ArrayList<CyberSecurityNews> cyberSecurityNewsArrayList;
    private boolean isWebViewShown = false;
    private WebsiteFragment websiteFragment;
    private CyberSecurityFragment cyberSecurityFragment;
    private AIFragment aiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        //Read the text file from the asset folder.
        AssetManager assetManager = getAssets();
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        StringBuilder text = new StringBuilder();
        aiNewsArrayList = new ArrayList<AINews>();
        cyberSecurityNewsArrayList = new ArrayList<CyberSecurityNews>();
        AINews aiNews = new AINews();
        CyberSecurityNews cyberSecurityNews = new CyberSecurityNews();
        try {
            inputStreamReader = new InputStreamReader(assetManager.open("news_items.txt"));
            br = new BufferedReader(inputStreamReader);
            String line, categoryName = "";
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
                if (line.contains(":")) {
                    int index = line.indexOf(":");
                    String s = line.substring(index + 1).trim();
                    if (line.contains("category")) {
                        categoryName = s;
                        if (categoryName.equals("cybersecurity")) {
                            cyberSecurityNews.setCategory(s);
                        } else if (categoryName.equals("AI")) {
                            aiNews.setCategory(s);

                        } else {
                            cyberSecurityNews.setCategory(s);
                            aiNews.setCategory(s);

                        }
                    }
                    if (line.contains("photo")) {
                        if (categoryName.equals("cybersecurity")) {
                            cyberSecurityNews.setPhoto(s);
                        } else if (categoryName.equals("AI")) {
                            aiNews.setPhoto(s);

                        } else {
                            cyberSecurityNews.setPhoto(s);
                            aiNews.setPhoto(s);
                        }
                    }
                    if (line.contains("title")) {
                        if (categoryName.equals("cybersecurity")) {
                            cyberSecurityNews.setTitle(s);
                        } else if (categoryName.equals("AI")) {
                            aiNews.setTitle(s);

                        } else {
                            cyberSecurityNews.setTitle(s);
                            aiNews.setTitle(s);
                        }
                    }
                    if (line.contains("website")) {
                        if (categoryName.equals("cybersecurity")) {
                            cyberSecurityNews.setWebsite(s);
                        } else if (categoryName.equals("AI")) {
                            aiNews.setWebsite(s);

                        } else {
                            cyberSecurityNews.setWebsite(s);
                            aiNews.setWebsite(s);
                        }
                    }
                    if (line.contains("date")) {
                        if (categoryName.equals("cybersecurity")) {
                            cyberSecurityNews.setDate(s);
                        } else if (categoryName.equals("AI")) {
                            aiNews.setDate(s);
                        } else {
                            cyberSecurityNews.setDate(s);
                            aiNews.setDate(s);
                            Log.e("date", aiNews.getDate());
                            Log.e("date2", cyberSecurityNews.getDate());
                        }
                    }

                }
                if (line.contains("-") && categoryName.equals("cybersecurity")) {
                    if (cyberSecurityNews.getTitle() != null) {
                        cyberSecurityNewsArrayList.add(cyberSecurityNews);
                        cyberSecurityNews = new CyberSecurityNews();
                    }
                } else if (line.contains("-") && categoryName.equals("AI")) {
                    if (aiNews.getTitle() != null) {
                        aiNewsArrayList.add(aiNews);
                    }
                    aiNews = new AINews();
                } else if (line.contains("-") && categoryName.equals("cybersecurity, AI")) {
                    if (cyberSecurityNews.getTitle() != null && aiNews.getTitle() != null) {
                        cyberSecurityNewsArrayList.add(cyberSecurityNews);
                        cyberSecurityNews = new CyberSecurityNews();
                        aiNewsArrayList.add(aiNews);
                        aiNews = new AINews();
                    }
                }
            }


        } catch (IOException e) {
            Log.e("message: ", e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        //Initialize object from tabs adapter to handle the tab layout with fragments.
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager(), aiNewsArrayList, cyberSecurityNewsArrayList);

        //Initialize the views of main activity
        viewPager = findViewById(R.id.tech_view_pager);
        viewPager.setAdapter(tabsPagerAdapter);

        tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (isWebViewShown) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            switch (viewPager.getCurrentItem()) {
                case 0:
                    ft.remove(websiteFragment);
                    ft.add(R.id.fragmentCyber, new CyberSecurityFragment(cyberSecurityNewsArrayList));
                    ft.commit();
                    break;
                case 1:
                    ft.remove(websiteFragment);
                    ft.add(R.id.fragmentAI, new AIFragment(aiNewsArrayList));
                    ft.commit();
                    break;
            }

            isWebViewShown = false;
        } else {
            super.onBackPressed();
        }
    }

    public void setFragmentWebSiteIsViewed(boolean b, WebsiteFragment websiteFragment) {
        this.websiteFragment = websiteFragment;
        isWebViewShown = b;
    }

    public void setFragmentCyber(CyberSecurityFragment cyberSecurityFragment) {
        this.cyberSecurityFragment = cyberSecurityFragment;
    }

    public void setFragmentAI(AIFragment aiFragment) {
        this.aiFragment = aiFragment;
    }
}
