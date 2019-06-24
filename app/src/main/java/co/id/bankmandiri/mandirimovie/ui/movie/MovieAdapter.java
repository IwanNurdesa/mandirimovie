package co.id.bankmandiri.mandirimovie.ui.movie;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.Constant;
import co.id.bankmandiri.mandirimovie.R;
import co.id.bankmandiri.mandirimovie.data.model.Genre;
import co.id.bankmandiri.mandirimovie.data.model.Movie;
import co.id.bankmandiri.mandirimovie.ui.moviedetail.MovieDetailActivity;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieVH> {

    private Context mContext;
    private List<Movie> movies;

    @Inject
    public MovieAdapter() {

    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVH holder, int position) {
        holder.tvTitle.setText(movies.get(position).getTitle());
        Picasso.get().load(Constant.BASE_IMAGE_URL + movies.get(position)
                .getPosterPath()).into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MovieVH extends RecyclerView.ViewHolder {

        LinearLayout llMain;
        ImageView ivMovie;
        TextView tvTitle;

        public MovieVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivMovie = itemView.findViewById(R.id.iv_movie);
            llMain = itemView.findViewById(R.id.ll_main);

            llMain.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("movie_id", movies.get(getAdapterPosition()).getId());
                mContext.startActivity(intent);
            });
        }
    }
}
