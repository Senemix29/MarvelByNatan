package ximenapps.com.br.marvelbynatan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ximenapps.com.br.marvelbynatan.model.Character;
import ximenapps.com.br.marvelbynatan.service.CharactersRestAdapter;

public class MainActivity extends AppCompatActivity implements MainView {
    private final String TAG = getClass().getCanonicalName();
    CharactersRestAdapter charactersAdapter;
    RecyclerView.Adapter charactersListAdapter;
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        charactersAdapter.getCharacters();
    }

    @Override
    public void showCharacters(List<Character> characters) {
        charactersListAdapter = new CharactersListAdapter(characters, getApplicationContext());
        charactersList.setAdapter(charactersListAdapter);

    }
}
