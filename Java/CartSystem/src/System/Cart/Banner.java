package System.Cart;

import java.util.UUID;

public class Banner extends CustomizableProduct {
    private BannerMaterial bannerMaterial;
    private BannerSize bannerSize;

    public Banner(UUID productId, short red, short green, short blue,
                  Orientation bannerOrientation,
                  BannerSize bannerSize,
                  BannerMaterial bannerMaterial) {
        super(bannerMaterial, Type.BANNER,
                productId, bannerOrientation,
                initializeBannerPrice(bannerMaterial, bannerSize),
                initializeBannerWidth(bannerSize),
                initializeBannerHeight(bannerSize),
                red, green, blue);

        if (bannerSize == null || bannerMaterial == null) {
            throw new IllegalArgumentException("parameter can not be null");
        }

        this.bannerMaterial = bannerMaterial;
        this.bannerSize = bannerSize;
    }

    public BannerMaterial getBannerMaterial() {
        return this.bannerMaterial;
    }

    public BannerSize getBannerSize() {
        return this.bannerSize;
    }

    private static int initializeBannerPrice(BannerMaterial bannerMaterial, BannerSize bannerSize) {
        switch (bannerSize) {
            case BANNER_SIZE_1000_X_500:
                if (bannerMaterial == BannerMaterial.GLOSS) {
                    return 5000;
                } else {
                    return 5100;
                }
            case BANNER_SIZE_1000_X_1000:
                if (bannerMaterial == BannerMaterial.GLOSS) {
                    return 5200;
                } else {
                    return 5300;
                }
            case BANNER_SIZE_2000_X_500:
                if (bannerMaterial == BannerMaterial.GLOSS) {
                    return 5300;
                } else {
                    return 5400;
                }
            case BANNER_SIZE_3000_X_1000:
                if (bannerMaterial == BannerMaterial.GLOSS) {
                    return 6000;
                } else {
                    return 6100;
                }
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }

    private static int initializeBannerWidth(BannerSize bannerSize) {
        switch (bannerSize) {
            case BANNER_SIZE_1000_X_500:
            case BANNER_SIZE_1000_X_1000:
                return 1000;
            case BANNER_SIZE_2000_X_500:
                return 2000;
            case BANNER_SIZE_3000_X_1000:
                return 3000;
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }

    private static int initializeBannerHeight(BannerSize bannerSize) {
        switch (bannerSize) {
            case BANNER_SIZE_2000_X_500:
            case BANNER_SIZE_1000_X_500:
                return 500;
            case BANNER_SIZE_1000_X_1000:
            case BANNER_SIZE_3000_X_1000:
                return 1000;
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }
}
