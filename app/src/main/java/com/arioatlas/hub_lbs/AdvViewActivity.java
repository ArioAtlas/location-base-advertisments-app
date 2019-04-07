package com.arioatlas.hub_lbs;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arioatlas.hub_lbs.api.DivarApiClient;
import com.arioatlas.hub_lbs.api.DivarApiInterface;
import com.arioatlas.hub_lbs.models.Divar.DivarAdvInfo;
import com.arioatlas.hub_lbs.models.Divar.DivarAdvInfoMeta;
import com.bumptech.glide.Glide;
import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.loader.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AdvViewActivity extends AppCompatActivity {
    private static final String TAG = "Omid AdvViewActivity";
    DivarAdvInfo advInfo;
    @BindView(R.id.adv_cover) ImageView cover;
    @BindView(R.id.adv_title) TextView title;
    @BindView(R.id.adv_desc_1) TextView desc1;
    @BindView(R.id.adv_desc_2) TextView desc2;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_adv);

        ButterKnife.bind(this);

        String token = getIntent().getStringExtra("token");

        loadDivarAdvInfo(token);
    }


    public void loadDivarAdvInfo(String token){
        DivarApiInterface api = DivarApiClient.getApiClient().create(DivarApiInterface.class);

        Call<DivarAdvInfo> call;

        call = api.getAdvInfo(token);
        call.enqueue(new Callback<DivarAdvInfo>() {
            @Override
            public void onResponse(Call<DivarAdvInfo> call, Response<DivarAdvInfo> response) {
                if(response.isSuccessful() && response.body().getWidgets() !=null){
                    advInfo = response.body();
                    if(advInfo.getWidgets().getImages().length>0){
                        Glide.with(AdvViewActivity.this)
                                .load(advInfo.getWidgets().getImages()[0])
                                .into(cover);
                    }
                    else{
                        Glide.with(AdvViewActivity.this)
                                .load(R.drawable.no_photo_available_1)
                                .into(cover);
                    }

                    title.setText(advInfo.getWidgets().getHeader().getTitle());

                    String metaList = "";
                    for(DivarAdvInfoMeta meta : advInfo.getWidgets().getMetas()){
                        metaList += (meta.getTitle()+": "+meta.getValue()+"\n");
                    }
                    desc1.setText(metaList);

                    desc2.setText(advInfo.getWidgets().getDesc());
                }
                else{
                    Toast.makeText(AdvViewActivity.this,"خطا در پردازش درخواست",Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DivarAdvInfo> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                Toast.makeText(AdvViewActivity.this,"درخواست شما با خطا موجه شد لطفا دوباره تلاش کنید",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    @OnClick(R.id.adv_get_contact)
    public void getContact(){
        Toast.makeText(this,"It will get contact info",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.adv_cover)
    public void showImageAlbum(){
        if(advInfo.getWidgets().getImages().length>0) {
            new StfalconImageViewer.Builder<>(AdvViewActivity.this, advInfo.getWidgets().getImages(), new ImageLoader<String>() {
                @Override
                public void loadImage(ImageView imageView, String image) {
                    Glide.with(AdvViewActivity.this)
                            .load(image)
                            .into(imageView);
                }
            }).show();
        }
        else{
            Toast.makeText(AdvViewActivity.this,"این آگهی فاقد تصویر است",Toast.LENGTH_SHORT).show();
        }
    }

}
