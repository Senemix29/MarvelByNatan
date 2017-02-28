package ximenapps.com.br.marvelbynatan;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import ximenapps.com.br.marvelbynatan.helpers.OnStartDragListener;
import ximenapps.com.br.marvelbynatan.helpers.SimpleItemTouchHelperCallback;
import ximenapps.com.br.marvelbynatan.model.Character;
import ximenapps.com.br.marvelbynatan.service.CharactersRestAdapter;

public class MainActivity extends AppCompatActivity implements MainView, OnStartDragListener {
    private final String TAG = getClass().getCanonicalName();
    private final String CHARACTERS = "characters";
    CharactersRestAdapter charactersAdapter;
    CharactersListAdapter charactersListAdapter;
    RecyclerView charactersList;
    ItemTouchHelper touchHelper;
    List<Character> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        charactersList = (RecyclerView) findViewById(R.id.characters_list);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        charactersAdapter = new CharactersRestAdapter(this);
        charactersList.setLayoutManager(layoutManager);

        if (savedInstanceState != null && savedInstanceState.get(CHARACTERS) != null) {
            List<Character> characters = (List<Character>) savedInstanceState.get(CHARACTERS);
            showCharacters(characters);
            return;
        }
        charactersAdapter.getCharacters();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(CHARACTERS, (ArrayList<? extends Parcelable>) characterList);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showCharacters(List<Character> characters) {
        this.characterList = characters;
        charactersListAdapter = new CharactersListAdapter(characters, getApplicationContext(), this);
        charactersList.setAdapter(charactersListAdapter);
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(charactersListAdapter);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(charactersList);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }
}
