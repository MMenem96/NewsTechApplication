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
import news.com.newstechapplication.models.AINews;

public class AIFragment extends Fragment {

    private RecyclerView rvAiNews;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private NewsAdapter aiAdapter;
    private ArrayList<AINews> aiNewsArrayList;


    public AIFragment() {
        // Required empty public constructor
    }

    public AIFragment(ArrayList<AINews> aiNewsArrayList) {
        super();
        this.aiNewsArrayList = aiNewsArrayList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).setFragmentAI(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ai, container, false);
        rvAiNews = view.findViewById(R.id.rv_news);
        rvAiNews.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        rvAiNews.setLayoutManager(staggeredGridLayoutManager);
        aiAdapter = new NewsAdapter(getContext(), "ai");
        aiAdapter.setAIList(aiNewsArrayList, this);
        rvAiNews.setAdapter(aiAdapter);
        return view;
    }


}
