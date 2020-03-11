package com.example.final_project_boardgamez;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.final_project_boardgamez.GameData.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameManagerAdapter extends RecyclerView.Adapter<GameManagerAdapter.GameManagerViewHolder> {

    private static final String TAG = GameManagerAdapter.class.getSimpleName();
    private List<Game> mGameDetails;
    private ArrayList<String> mGameTags;
    private OnGameClickListener mGameClickListener;


    final String[] dummyTagData = {     // TODO: Make 2D array to store tags
            "Owns, Family friendly",
            "Wishlist, Party game",
            "Owns",
            "Owns",
            "Has played, Family friendly",
            "Wishlist",
            "Has played",
            "Owns",
            "Owns"
    };                          // Game Crashes if you add more than these 9 Game Tags



    public interface OnGameClickListener {
        void onGameClicked(Game game);
    }

    public GameManagerAdapter(OnGameClickListener listener) {
        mGameClickListener = listener;
        mGameDetails = new ArrayList<>();
        mGameTags = new ArrayList<>(Arrays.asList(dummyTagData));
        Log.d(TAG, "Adapter: Created an ArrayList");
    }

    public void addGame(Game game) {
        Log.d(TAG, "Adapter: Adding Game to ArrayList");
        mGameDetails.add(game);
        notifyDataSetChanged();
    }

    public void updateGameCollection(List<Game> gameList) {
        Log.d(TAG, "Adapter: Updating whole ArrayList with another list");
        mGameDetails = gameList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mGameDetails.size();
    }

    @Override
    public GameManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.game_list_item, parent, false);
        return new GameManagerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameManagerViewHolder holder, int position) {
        Game gameDetails = mGameDetails.get(position);
        //String gameTag = "Tags: " + mGameTags.get(position);
        String gameTag = gameDetails.description;
        Log.d(TAG, "Adapter: Bind Game Name = " + gameDetails.name);

        //holder.mGameDetailsTV.setText(gameDetails.name);
        //holder.mGameTagTV.setText(gameTag);
        //holder.mGameThumbnailIV.setImageResource(R.mipmap.ic_dummy_thumbnail);          // Still Hardcoded Image
        holder.bind(gameDetails, gameTag);
    }


    class GameManagerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mGameDetailsTV;
        private TextView mGameTagTV;
        private ImageView mGameThumbnailIV;

        public GameManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            mGameDetailsTV = itemView.findViewById(R.id.tv_game_details);
            mGameTagTV = itemView.findViewById(R.id.tv_game_tag);
            mGameThumbnailIV = itemView.findViewById(R.id.iv_game_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "In on click");
            Game game = mGameDetails.get(getAdapterPosition());         // When we are ready for real data
            mGameClickListener.onGameClicked(game);
        }

        void bind(Game game, String gameTag) {
            mGameDetailsTV.setText(game.name);
            mGameTagTV.setText(gameTag);
          //  mGameThumbnailIV.setImageResource(R.mipmap.ic_dummy_thumbnail);          // Still Hardcoded Image

            String thumbnail = "https://cf.geekdo-images.com/thumb/img/zm7yYkwNagKXipKe3h_Va0EDp_k=/fit-in/200x150/pic119215.jpg";
            Glide.with(mGameThumbnailIV.getContext())
                    .load(thumbnail).into(mGameThumbnailIV);
        }
    }
}
