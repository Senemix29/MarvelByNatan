package ximenapps.com.br.marvelbynatan.model;

import java.util.List;

/**
 * Created by Natan on 04/08/2016.
 */
public class CharacterDataContainer {
    private int total;
    private List<Character> results;

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
