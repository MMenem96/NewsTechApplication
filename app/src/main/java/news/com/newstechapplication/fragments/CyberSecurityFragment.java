package news.com.newstechapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import news.com.newstechapplication.R;
import news.com.newstechapplication.activites.MainActivity;
import news.com.newstechapplication.adapters.NewsAdapter;
import news.com.newstechapplication.models.CyberSecurityNews;

public class CyberSecurityFragment extends Fragment {

    private NewsAdapter cyberAdapter;
    private RecyclerView rvCyberNews;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private ArrayList<CyberSecurityNews> cyberSecurityNewsArrayList;

    public CyberSecurityFragment() {
        // Required empty public constructor
    }

    public CyberSecurityFragment(ArrayList<CyberSecurityNews> cyberSecurityNewsArrayList) {
        super();
        this.cyberSecurityNewsArrayList = cyberSecurityNewsArrayList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setFragmentCyber(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cyber_security, container, false);

        rvCyberNews = view.findViewById(R.id.rv_news);
        rvCyberNews.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        rvCyberNews.setLayoutManager(staggeredGridLayoutManager);
        cyberAdapter = new NewsAdapter(getContext(), "cyber");
        cyberAdapter.setCyberList(cyberSecurityNewsArrayList,this);
        rvCyberNews.setAdapter(cyberAdapter);
        return view;
    }



}
