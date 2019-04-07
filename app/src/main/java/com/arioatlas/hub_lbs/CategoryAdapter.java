package com.arioatlas.hub_lbs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arioatlas.hub_lbs.models.Categories;
import com.arioatlas.hub_lbs.viewmodels.AdvertisementViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Categories> cats;
    private Context context;
    private LatLng coord;
    private AdvertisementViewModel viewModel;

    public CategoryAdapter(Context context, List<Categories> cats, AdvertisementViewModel viewModel, LatLng coord) {
        this.cats = cats;
        this.context = context;
        this.coord = coord;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Categories cat = cats.get(i);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.image_rounded_bg_cyan);
        requestOptions.error(R.drawable.ic_launcher_background);
        requestOptions.centerCrop();

        viewHolder.title.setText(cat.getTitle());
        Glide.with(context)
                .load(cat.getCover())
                .apply(requestOptions)
                .into(viewHolder.cover);

        viewHolder.cv.setOnClickListener((view)->{
            Intent intent = new Intent(context,AdvertisementsListActivity.class);
            intent.putExtra("lat",coord.getLatitude());
            intent.putExtra("long",coord.getLongitude());
            intent.putExtra("divarCatId",cats.get(i).getdId());
            intent.putExtra("sheypoorCatId",cats.get(i).getsId());
            intent.putExtra("catName",cats.get(i).getTitle());
            viewModel.setCategoryId(cats.get(i).getdId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.categoryCover) ImageView cover;
        @BindView(R.id.categoryTitle) TextView title;
        @BindView(R.id.categoryItemHolder) CardView cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

    }
}
