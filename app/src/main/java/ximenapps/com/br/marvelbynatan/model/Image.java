package ximenapps.com.br.marvelbynatan.model;

/**
 * Created by Natan on 05/08/2016.
 */
public class Image {
    private String path;
    private String extension;
    private String url;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl(){
        url = path+"."+extension;
        return url;
    }

    @Override
    public String toString() {
        return "Image{" +
                ", url='" + path+"."+extension+ '\'' +
                '}';
    }
}
