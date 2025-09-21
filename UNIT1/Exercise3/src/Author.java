import java.util.HashSet;
import java.util.Set;

/*/ Todas las clases estan en el mismo archivo para facilitar la correcion /*/
/*/ clases enumeradas /*/
enum PaintType {
    OIL,
    PASTEL,
    WATERCOLOR
}

enum StyleType {
    NEOCLASSICISM,
    GRECORROMAN,
    CUBIST
}

enum MaterialType {
    BRONZE,
    IRON,
    MARBLE
}

/*/ Main public class of the file /*/
public class Author {
    private String name;
    private String nacionality;
    private Set<Artwork> artworksSet = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    // Functions with managing purposes of the collection of artworks
    public void addArtwork(Artwork artwork) {
        boolean artworkCheck = true; // aqui se comprueba la validez del objeto artwork
        if (artworkCheck)
            artworksSet.add(artwork);
    }

    public void removeArtwork(Artwork artwork) {
        if (artworksSet.contains(artwork))
            artworksSet.remove(artwork);
    }
}

class Artwork {
    private String title;
    private Museum museum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

class Painting extends Artwork {
    private PaintType type;
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

class Sculpture extends Artwork {
    private MaterialType material;
    private StyleType style;
}

class Room {
    private String name;
    private Artwork artwork;
    private Museum location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    public Museum getLocation() {
        return location;
    }

    public void setLocation(Museum museum) {
        this.location = museum;
    }
}

class Museum {
    private String name;
    private String address;
    private String city;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
