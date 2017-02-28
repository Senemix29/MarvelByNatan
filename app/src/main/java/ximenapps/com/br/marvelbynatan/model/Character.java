package ximenapps.com.br.marvelbynatan.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Natan on 04/08/2016.
 */
public class Character implements Parcelable {
    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
    private String name;
    private String description;
    private Image thumbnail;

    protected Character(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

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
                '}' + '\n';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }
}
