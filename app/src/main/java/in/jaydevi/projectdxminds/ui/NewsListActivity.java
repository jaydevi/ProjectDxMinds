package in.jaydevi.projectdxminds.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import in.jaydevi.projectdxminds.R;
import in.jaydevi.projectdxminds.adapter.NewsListAdapter;
import in.jaydevi.projectdxminds.databinding.ActivityNewsListBinding;
import in.jaydevi.projectdxminds.model.Article;
import in.jaydevi.projectdxminds.viewmodel.NewsActivityViewModel;

public class NewsListActivity extends AppCompatActivity {
    private ActivityNewsListBinding activityNewsListBinding;
    private NewsActivityViewModel newsActivityViewModel;
    private NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewsListBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_list);

        newsActivityViewModel = new ViewModelProvider(this).get(NewsActivityViewModel.class);
        newsActivityViewModel.getAllArticles().observe(this, articles -> {
            newsListAdapter = new NewsListAdapter(this, articles);
            activityNewsListBinding.setNewsAdapter(newsListAdapter);
        });
    }
}