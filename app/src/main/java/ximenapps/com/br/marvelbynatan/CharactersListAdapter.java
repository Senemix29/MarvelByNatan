package ximenapps.com.br.marvelbynatan;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ximenapps.com.br.marvelbynatan.helpers.ItemTouchHelperAdapter;
import ximenapps.com.br.marvelbynatan.helpers.OnStartDragListener;
import ximenapps.com.br.marvelbynatan.model.Character;

public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private final OnStartDragListener dragStartListener;
    List<Character> characters;
    Context context;

    public CharactersListAdapter(List<Character> characters, Context context, OnStartDragListener dragStartListener) {
        this.characters = characters;
        this.context = context;
        this.dragStartListener = dragStartListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.characterName.setText(characters.get(position).getName());
        Picasso.with(context)
                .load(characters.get(position).getThumbnail().getUrl())
                .fit()
                .centerCrop()
                .into(holder.characterPicture);
        holder.characterDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) ==
                        MotionEvent.ACTION_DOWN) {
                    dragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView characterPicture, characterDrag;
        TextView characterName;

        public ViewHolder(View itemView) {
            super(itemView);
            characterName = (TextView) itemView.findViewById(R.id.character_name);
            characterPicture = (ImageView) itemView.findViewById(R.id.character_picture);
            characterDrag = (ImageView) itemView.findViewById(R.id.character_drag);
        }
    }
}
