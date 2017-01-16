package ximenapps.com.br.marvelbynatan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

import ximenapps.com.br.marvelbynatan.helpers.SimpleItemTouchHelperCallback;
import ximenapps.com.br.marvelbynatan.model.Character;
import ximenapps.com.br.marvelbynatan.service.CharactersRestAdapter;

public class MainActivity extends AppCompatActivity implements MainView {
    private final String TAG = getClass().getCanonicalName();
    CharactersRestAdapter charactersAdapter;
    CharactersListAdapter charactersListAdapter;
    RecyclerView.LayoutManager charactersListLayoutManager;
    RecyclerView charactersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        charactersList = (RecyclerView) findViewById(R.id.characters_list);

        charactersAdapter = new CharactersRestAdapter(this);
        charactersListLayoutManager = new LinearLayoutManager(getApplicationContext());
        charactersList.setLayoutManager(charactersListLayoutManager);

        charactersAdapter.getCharacters();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showCharacters(List<Character> characters) {
        charactersListAdapter = new CharactersListAdapter(characters, getApplicationContext());
        charactersList.setAdapter(charactersListAdapter);
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(charactersListAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(charactersList);
    }
}
