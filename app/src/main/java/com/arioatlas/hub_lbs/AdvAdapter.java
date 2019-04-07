package com.arioatlas.hub_lbs;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arioatlas.hub_lbs.models.Divar.DivarAdv;
import com.arioatlas.hub_lbs.models.SimpleAdv;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvAdapter extends RecyclerView.Adapter<AdvAdapter.ViewHolder> {

    private List<SimpleAdv> ads;
    private Context context;

    public AdvAdapter(Context context, List<SimpleAdv> ads) {
        this.ads = ads;
        this.context = context;
    }

    public void updateAdvList(List<SimpleAdv> ads){
        this.ads = ads;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.advertisment_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SimpleAdv adv = ads.get(i);


            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.image_rounded_bg_cyan);
            requestOptions.error(R.drawable.ic_launcher_background);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.centerCrop();

        if(!adv.isCoverAvailable()){
//            viewHolder.cover.setVisibility(View.GONE);
            Glide.with(context)
                    .load(R.drawable.no_photo_available_1)
                    .apply(requestOptions)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            // Hide progress bar
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            // hide progress bar
                            return false;
                        }
                    })
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(viewHolder.cover);
        }
        else {
            Glide.with(context)
                    .load(adv.getImageUrl())
                    .apply(requestOptions)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            // Hide progress bar
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            // hide progress bar
                            return false;
                        }
                    })
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(viewHolder.cover);
//            viewHolder.cover.setVisibility(View.VISIBLE);
        }


        viewHolder.title.setText(adv.getTitle());
        viewHolder.location.setText(adv.getLocationName());
        viewHolder.addedOn.setText(getTimeDiff(adv.getDatetime()));
        viewHolder.price.setText(""+adv.getPrice());

        viewHolder.parent.setOnClickListener((view)->{
            Intent intent = new Intent(context,AdvViewActivity.class);
            intent.putExtra("token",ads.get(i).getToken());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(ads == null){
            return 0;
        }
        return ads.size();
    }

    public String getTimeDiff(long timestamp){
        Calendar calendar = Calendar.getInstance();
        long now = (calendar.getTimeInMillis()/1000)+12600;
        Log.d("ADV ADAPTER", "Now: "+now);
        Log.d("ADV ADAPTER", "LM: "+timestamp);
        int td = (int)(now - timestamp);
        Log.d("ADV ADAPTER", "getTimeDiff: "+td);
        if(td<30){
            return "چند لحظه قبل";
        }
        else if (td<60){
            return td+" ثانیه پیش";
        }
        else if(td<3600){
            return (td/60)+" دقیقه پیش";
        }
        else if(td<86400) {
            return (td/3600)+" ساعت پیش";
        }
        else{
            return (td/86400)+" روز پیش";
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.adv_cover) ImageView cover;
        @BindView(R.id.adv_location) TextView location;
        @BindView(R.id.adv_datetime) TextView addedOn;
        @BindView(R.id.adv_price) TextView price;
        @BindView(R.id.adv_title) TextView title;
        @BindView(R.id.adv_card_holder) CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
