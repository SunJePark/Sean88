package System.Cart;

import java.util.UUID;

public class Stamp extends Product {
    private StampSize stampSize;
    private String stampText;

    public Stamp(UUID productId, Color stampColor,
                 StampSize stampSize, String text) {
        super(Type.STAMP, productId, initializeStampPrice(stampSize),
                initializeStampWidth(stampSize),
                initializeStampHeight(stampSize),
                stampColor);
        if (stampColor != Color.RED &&
                stampColor != Color.GREEN &&
                stampColor != Color.BLUE) {
            throw new IllegalArgumentException("unavailable color type");
        }

        this.stampSize = stampSize;
        if (text == null || stampSize == null) {
            throw new IllegalArgumentException("parameter can not be null");
        }
        this.stampText = text;
    }

    public StampSize getStampSize() {
        return this.stampSize;
    }

    public String getStampText() {
        return this.stampText;
    }

    private static int initializeStampHeight(StampSize stampSize) {
        switch (stampSize) {
            case STAMP_SIZE_40_X_30:
                return 30;
            case STAMP_SIZE_50_X_20:
                return 20;
            case STAMP_SIZE_70_X_40:
                return 40;
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }

    private static int initializeStampWidth(StampSize stampSize) {
        switch (stampSize) {
            case STAMP_SIZE_40_X_30:
                return 40;
            case STAMP_SIZE_50_X_20:
                return 50;
            case STAMP_SIZE_70_X_40:
                return 70;
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }

    private static int initializeStampPrice(StampSize stampSize) {
        switch (stampSize) {
            case STAMP_SIZE_40_X_30:
            case STAMP_SIZE_50_X_20:
                return 2300;
            case STAMP_SIZE_70_X_40:
                return 2600;
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }
}
