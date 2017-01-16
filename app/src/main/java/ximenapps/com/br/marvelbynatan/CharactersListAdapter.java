package ximenapps.com.br.marvelbynatan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import retrofit2.http.POST;
import ximenapps.com.br.marvelbynatan.helpers.ItemTouchHelperAdapter;
import ximenapps.com.br.marvelbynatan.model.Character;

/**
 * Created by Natan on 05/08/2016.
 */
public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    List<Character> characters;
    Context context;

    public CharactersListAdapter(List<Character> characters, Context context){
        this.characters = characters;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.characterName.setText(characters.get(position).getName());
        Picasso.with(context)
                .load(characters.get(position).getThumbnail().getUrl())
                .fit()
                .centerCrop()
                .into(holder.characterPicture);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Character prev = characters.remove(fromPosition);
        characters.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        characters.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView characterPicture;
        TextView characterName;

        public ViewHolder(View itemView) {
            super(itemView);
            characterName = (TextView) itemView.findViewById(R.id.character_name);
            characterPicture = (ImageView) itemView.findViewById(R.id.character_picture);
        }
    }
}
