package test.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Archive<T extends MaterialeBiblioteca> {
    private List<T> archive = new ArrayList<>();

    Archive(T ... args) {
        for (T m : args) {
            archive.add(m);
        }
    }

    public List<T> getList() {
        return (this.archive);
    }

    public void addMaterial(T material) {
        this.archive.add(material);
    }
}
