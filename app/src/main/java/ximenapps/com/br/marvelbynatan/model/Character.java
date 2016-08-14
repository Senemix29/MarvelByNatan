package ximenapps.com.br.marvelbynatan.model;

/**
 * Created by Natan on 04/08/2016.
 */
public class Character {
    private String name;
    private String description;
    private Image thumbnail;

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Character{" + '\n' +
                "name = " + name + '\n' +
                "description = " + description + '\n' +
                "thumbnail = " + thumbnail +
                '}'+'\n';
    }
}
