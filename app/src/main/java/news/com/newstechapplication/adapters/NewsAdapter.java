package news.com.newstechapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import news.com.newstechapplication.R;
import news.com.newstechapplication.fragments.AIFragment;
import news.com.newstechapplication.fragments.CyberSecurityFragment;
import news.com.newstechapplication.fragments.WebsiteFragment;
import news.com.newstechapplication.models.AINews;
import news.com.newstechapplication.models.CyberSecurityNews;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {


    private final String currentAdapter;
    private List<AINews> aiNews;
    private List<CyberSecurityNews> cyberSecurityNews;
    private Context context;
    private int[] photosAIID = {R.drawable.ai1, R.drawable.ai2, R.drawable.ai3, R.drawable.ai4, R.drawable.cyber5};
    private int[] photosCyberIDs = {R.drawable.cyber1, R.drawable.cyber2, R.drawable.cyber3, R.drawable.cyber4, R.drawable.cyber5};
    private CyberSecurityFragment cyberSecurityFragment;
    private AIFragment aiFragment;


    public NewsAdapter(Context context, String currentAdapter) {
        this.context = context;
        this.currentAdapter = currentAdapter;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_news_card, parent, false);
        return new NewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.MyViewHolder holder, final int position) {
        if (currentAdapter.equals("cyber")) {
            holder.newsTitle.setText(cyberSecurityNews.get(position).getTitle());
            holder.newsPhoto.setBackground(context.getDrawable(photosCyberIDs[position]));
        } else {
            holder.newsTitle.setText(aiNews.get(position).getTitle());
            holder.newsPhoto.setBackground(context.getDrawable(photosAIID[position]));
        }
        holder.newsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentAdapter.equals("cyber")) {
                    FragmentTransaction ft = cyberSecurityFragment.getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentCyber, new WebsiteFragment(cyberSecurityNews.get(position).getWebsite()), "web");
                    ft.commit();
                } else {
                    FragmentTransaction ft = aiFragment.getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragmentAI, new WebsiteFragment(aiNews.get(position).getWebsite()), "web");
                    ft.commit();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (currentAdapter.equals("cyber")) {
            Log.e("sizeCyber", cyberSecurityNews.size() + "");
            return cyberSecurityNews.size();
        } else {
            Log.e("sizeAi", aiNews.size() + "");
            return aiNews.size();
        }
    }

    public void setAIList(ArrayList<AINews> aiNewsArrayList, AIFragment aiFragment) {
        this.aiNews = aiNewsArrayList;
        this.aiFragment = aiFragment;
    }

    public void setCyberList(ArrayList<CyberSecurityNews> cyberList, CyberSecurityFragment cyberSecurityFragment) {
        this.cyberSecurityNews = cyberList;
        this.cyberSecurityFragment = cyberSecurityFragment;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        CardView newsCard;
        ImageView newsPhoto;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.tv_news_title);
            newsPhoto = itemView.findViewById(R.id.iv_news);
            newsCard = itemView.findViewById(R.id.card_news);
        }
    }


}
