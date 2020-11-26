package in.jaydevi.projectdxminds.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class ConstantMethods {
    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView)
                .load(url)
                .into(imageView);
    }



    public static void showSnack(Context context, String msg, View snackbar, boolean forSuccess) {
        Snackbar snackbarObj = Snackbar.make(snackbar, msg, Snackbar.LENGTH_LONG);
        snackbarObj.setDuration(2000);
        snackbarObj.getView().setBackgroundColor(context.getResources().getColor(forSuccess ?
                android.R.color.holo_green_dark : android.R.color.holo_red_dark));
        snackbarObj.show();
    }

    public static boolean isConnectedToInternet(Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            Log.e("isConnectedToInternet", e.getMessage());
            return false;
        }
    }

}
