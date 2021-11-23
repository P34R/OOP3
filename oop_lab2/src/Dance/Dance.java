package Dance;

import java.util.ArrayList;

public class Dance implements Comparable<Dance> {
    private int id;
    private String type;
    private String scene;
    private int dancersNumber;
    private Music music;
    private ArrayList<Dancer> dancers=new ArrayList<>();
    private int number;

    public Dance(int id,String type, String scene, int dancersNumber, Music music, ArrayList<Dancer> dancers, int number) {
        this.id=id;
        this.type = type;
        this.scene = scene;
        this.dancersNumber = dancersNumber;
        this.music = music;
        this.dancers = dancers;
        this.number = number;
    }
    @Override
    public String toString() {
        return "Dance{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", scene='" + scene + '\'' +
                ", dancersNumber=" + dancersNumber +
                ", music=" + music +
                ", dancers=" + dancers +
                ", number=" + number +
                '}';
    }
    public Dance(){};
    public void addDancer(Dancer dancer){
        this.dancers.add(dancer);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public int getDancersNumber() {
        return dancersNumber;
    }

    public void setDancersNumber(int dancersNumber) {
        this.dancersNumber = dancersNumber;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public ArrayList<Dancer> getDancers() {
        return dancers;
    }

    public void setDancers(ArrayList<Dancer> dancers) {
        this.dancers = dancers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dance dance = (Dance) o;

        if (id != dance.id) return false;
        if (dancersNumber != dance.dancersNumber) return false;
        if (number != dance.number) return false;
        if (!type.equals(dance.type)) return false;
        if (!scene.equals(dance.scene)) return false;
        if (music != dance.music) return false;
        return dancers.equals(dance.dancers);
    }

    @Override
    public int compareTo(Dance o) {
        return Integer.compare(this.number,o.getNumber());
    }
}
