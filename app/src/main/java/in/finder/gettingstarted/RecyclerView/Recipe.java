package in.finder.gettingstarted.RecyclerView;

/**
 * Created by keviv on 05/06/2016.
 */
public class Recipe {

    private int image_id;
    private String name;

    public Recipe(int image_id, String name){
        this.setImage_id(image_id);
        this.setName(name);
    }
    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
