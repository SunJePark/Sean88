package System.Cart;

import java.util.ArrayList;
import java.util.UUID;

public class CustomizableProduct extends Product {
    private Orientation orientation;
    private ArrayList<Aperture> apertures;


    protected CustomizableProduct(BannerMaterial bannerMaterial, Type type, UUID productId,
                                  Orientation orientation,
                                  int price, int width, int height,
                                  short red, short green, short blue) {
        super(bannerMaterial, type, productId, price,
                initializeWidth(orientation, width, height),
                initializeHeight(orientation, width, height),
                red, green, blue);
        if (orientation == null) {
            throw new IllegalArgumentException("parameter can not be null");
        }
        this.orientation = orientation;
        this.apertures = new ArrayList<>();
    }

    protected CustomizableProduct(PaperMaterial paperMaterial, Type type, UUID productId,
                                  Orientation orientation,
                                  int price, int width, int height,
                                  BusinessCardColor businessCardColor) {
        super(paperMaterial, type, productId, price,
                initializeWidth(orientation, width, height),
                initializeHeight(orientation, width, height),
                businessCardColor);
        if (orientation == null) {
            throw new IllegalArgumentException("parameter can not be null");
        }

        this.orientation = orientation;
        this.apertures = new ArrayList<>();
    }

    public ArrayList<Aperture> getAperturesOrNull() {
        return this.apertures;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public Aperture getApertureTextOrNull(UUID apertureId) {
        for (int i = 0; i < apertures.size(); ++i) {
            if (apertures.get(i).getApertureId() == apertureId) {
                return apertures.get(i);
            }
        }
        return null;
    }

    public void addAperture(Aperture aperture) {
        if (aperture == null) {
            return;
        }
        int x = aperture.getX();
        int y = aperture.getY();
        int width = aperture.getWidth();
        int height = aperture.getHeight();
        if (x < 0 || y < 0 || width < 1 || height < 1 ||
                x + width < 1 || y + height < 1 ||
                x + width > super.getWidth() || y + height > super.getHeight() ||
                width > super.getWidth() || width > super.getHeight() ||
                x >= super.getWidth() || y >= super.getHeight()) {
            return;
        }
        this.apertures.add(aperture);
        super.setPrice(super.getPrice() + 5);
    }

    public void removeAperture(Aperture aperture) {
        if (apertures.contains(aperture)) {
            this.apertures.remove(aperture);
            super.setPrice(super.getPrice() - 5);
        }
    }

    private static int initializeWidth(Orientation orientation, int width, int height) {
        if (orientation == Orientation.PORTRAIT) {
            return height;
        } else {
            return width;
        }
    }

    private static int initializeHeight(Orientation orientation, int width, int height) {
        if (orientation == Orientation.PORTRAIT) {
            return width;
        } else {
            return height;
        }
    }
}
