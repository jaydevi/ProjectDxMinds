package in.jaydevi.projectdxminds.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

import in.jaydevi.projectdxminds.model.Article;
import in.jaydevi.projectdxminds.model.NewsResponse;
import in.jaydevi.projectdxminds.utils.AppConstants;
import in.jaydevi.projectdxminds.utils.ConstantMethods;

public class NewsActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Article>> articleList;

    public NewsActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Article>> getAllArticles() {
        if (articleList == null) {
            articleList = new MutableLiveData<>();

            getArticlesFromServer();
        }
        return articleList;
    }

    private void getArticlesFromServer() {
        if (ConstantMethods.isConnectedToInternet(context)) {
            String url = AppConstants.BASE_URL;
            AndroidNetworking.get(url)
                    .setPriority(Priority.MEDIUM)
                    .setTag("getArticlesFromServer")
                    .build()
                    .getAsObject(NewsResponse.class, new ParsedRequestListener<NewsResponse>() {
                        @Override
                        public void onResponse(NewsResponse response) {
                           if (response.getStatus().equalsIgnoreCase("ok"))
                            articleList.setValue(response.getArticles());
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });

        } else {
            Toast.makeText(context,"Internet not available!",Toast.LENGTH_SHORT).show();
        }
    }
}
