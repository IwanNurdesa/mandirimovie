package co.id.bankmandiri.mandirimovie.ui.genre;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import co.id.bankmandiri.mandirimovie.R;
import co.id.bankmandiri.mandirimovie.data.model.Genre;
import co.id.bankmandiri.mandirimovie.ui.movie.MovieActivity;

/**
 * Created by Iwan Nurdesa on 21/06/2019
 */
public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreVH> {

    private Context mContext;
    private List<Genre> genres;

    @Inject
    public GenreAdapter() {

    }

    @NonNull
    @Override
    public GenreVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_genre, viewGroup, false);
        return new GenreVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreVH holder, int position) {
        holder.tvTitle.setText(genres.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
        notifyDataSetChanged();
    }

    public class GenreVH extends RecyclerView.ViewHolder {

        LinearLayout llMain;
        TextView tvTitle;

        public GenreVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            llMain = itemView.findViewById(R.id.ll_main);

            llMain.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, MovieActivity.class);
                intent.putExtra("genre_id", genres.get(getAdapterPosition()).getId());
                mContext.startActivity(intent);
            });

        }
    }
}
