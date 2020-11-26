package in.jaydevi.projectdxminds.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.jaydevi.projectdxminds.R;
import in.jaydevi.projectdxminds.databinding.ItemNewsBinding;
import in.jaydevi.projectdxminds.model.Article;
import in.jaydevi.projectdxminds.ui.NewsDetailActivity;
import in.jaydevi.projectdxminds.utils.AppConstants;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {
    Context context;
    List<Article> articleList;

    public NewsListAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding itemArticleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_news, parent, false);

        return new NewsListAdapter.MyViewHolder(itemArticleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemArticleBinding.setArticle(articleList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NewsDetailActivity.class)
                .putExtra(AppConstants.KEY_NEWS_URL,articleList.get(position).getUrl())
                );
            }
        });
    }


    public void updateList(List<Article> newsList){
        this.articleList = newsList;

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding itemArticleBinding;

        public MyViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            this.itemArticleBinding = itemView;

        }
    }
}

