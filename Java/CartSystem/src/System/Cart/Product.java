package System.Cart;

import java.util.UUID;

public class Product {
    private final UUID PRODUCT_ID;
    private final Type PRODUCT_TYPE;
    private ShippingMethod shippingMethod;
    private String displayName;
    private Color color;
    private short red;
    private short green;
    private short blue;
    private int width;
    private int height;
    private int price = 0;


    protected Product(Type type, UUID productId,
                      int price, int width, int height,
                      Color color) {
        this.displayName = initializeDisplayNameOrNull(type, width, height);
        this.color = color;
        this.shippingMethod = ShippingMethod.SHIP;
        this.PRODUCT_TYPE = type;
        if (productId == null) {
            throw new IllegalArgumentException("productId can not be null");
        }
        this.PRODUCT_ID = productId;
        this.price = price;
        this.width = width;
        this.height = height;
        updateColorValue(color);
    }

    protected Product(BannerMaterial bannerMaterial, Type type, UUID productId,
                      int price, int width, int height,
                      short red, short green, short blue) {
        this.displayName = initializeBannerDisplayName(bannerMaterial, width, height);
        this.color = updateColorName(red, green, blue);
        this.shippingMethod = ShippingMethod.SHIP;
        this.PRODUCT_TYPE = type;
        if (productId == null) {
            throw new IllegalArgumentException("productId can not be null");
        }
        this.PRODUCT_ID = productId;
        this.price = price;
        this.width = width;
        this.height = height;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    protected Product(PaperMaterial paperMaterial, Type type, UUID productId,
                      int price, int width, int height,
                      BusinessCardColor businessCardColor) {
        this.displayName = initializeBusinessCardDisplayName(paperMaterial);
        this.color = changeBusinessCardColorOrNull(businessCardColor);
        this.shippingMethod = ShippingMethod.SHIP;
        this.PRODUCT_TYPE = type;
        this.PRODUCT_ID = productId;
        if (productId == null) {
            throw new IllegalArgumentException("productId can not be null");
        }
        this.price = price;
        this.width = width;
        this.height = height;
        updateColorValue(this.color);
    }

    public ShippingMethod getShippingMethod() {
        return this.shippingMethod;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Type getType() {
        return this.PRODUCT_TYPE;
    }

    public UUID getProductId() {
        return this.PRODUCT_ID;
    }

    public Color getColor() {
        return this.color;
    }

    public short getRed() {
        return this.red;
    }

    public short getGreen() {
        return this.green;
    }

    public short getBlue() {
        return this.blue;
    }

    public int getPrice() {
        return this.price;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        if (this.shippingMethod != shippingMethod) {
            this.shippingMethod = shippingMethod;
        }
    }

    protected void setPrice(int price) {
        this.price = price;
    }

    private static String initializeBannerDisplayName(BannerMaterial bannerMaterial, int width, int height) {
        StringBuilder toDisplay = new StringBuilder();
        switch (bannerMaterial) {
            case GLOSS:
                toDisplay.append("Gloss Banner");
                break;
            case SCRIM:
                toDisplay.append("Scrim Banner");
                break;
            case MESH:
                toDisplay.append("Mesh Banner");
                break;
            default:
                assert (false) : "unknown type";
                break;
        }
        toDisplay.append(" (" + width + " mm x " + height + " mm)");

        return toDisplay.toString();
    }

    private static String initializeBusinessCardDisplayName(PaperMaterial paperMaterial) {
        switch (paperMaterial) {
            case LINEN:
                return "Linen Business Card";
            case LAID:
                return "Laid Business Card";
            case SMOOTH:
                return "Smooth Business Card";
            default:
                assert (false) : "unknown type";
        }
        return "unknown type";
    }

    private static String initializeDisplayNameOrNull(Type type, int width, int height) {
        switch (type) {
            case STAMP:
                return ("Stamp (" + width + " mm x " + height + " mm)");
            case CALENDAR:
                if (width == 400 && height == 400) {
                    return "Wall Calendar";
                } else if (width == 200 && height == 150) {
                    return "Desk Calendar";
                } else if (width == 100 && height == 200) {
                    return "Magnet Calendar";
                }
                break;
        }
        return "1";

    }

    private void initializeCalendarData(CalendarType calendarType) {
        switch (calendarType) {
            case WALL_CALENDAR:
                this.displayName = "Wall Calendar";
                break;
            case DESK_CALENDAR:
                this.displayName = "Desk Calendar";
                break;
            case MAGNET_CALENDAR:
                this.displayName = "Magnet Calendar";
                break;
            default:
                throw new IllegalArgumentException("unavailable calendar type");
        }
    }

    private void updateColorValue(Color color) {
        switch (color) {
            case RED:
                this.red = 0xff;
                this.green = 0x00;
                this.blue = 0x00;
                break;
            case GREEN:
                this.red = 0x00;
                this.green = 0x80;
                this.blue = 0x00;
                break;
            case BLUE:
                this.red = 0x00;
                this.green = 0x00;
                this.blue = 0xff;
                break;
            case WHITE:
                this.red = 0xff;
                this.green = 0xff;
                this.blue = 0xff;
                break;
            case GRAY:
                this.red = 0xe6;
                this.green = 0xe6;
                this.blue = 0xe6;
                break;
            case IVORY:
                this.red = 0xff;
                this.green = 0xff;
                this.blue = 0xf0;
                break;
            case OTHER_COLOR:
                assert (false) : "unknown type";
                break;
        }
    }

    private Color changeBusinessCardColorOrNull(BusinessCardColor businessCardColor) {
        switch (businessCardColor) {
            case WHITE:
                return Color.WHITE;
            case GRAY:
                return Color.GRAY;
            case IVORY:
                return Color.IVORY;
            default:
                assert (false) : "unknown type";
                break;
        }
        return null;
    }

    private Color updateColorName(short red, short green, short blue) {
        if (red == 0xFF && green == 0x00 && blue == 0x00) {
            return Color.RED;
        } else if (red == 0x00 && green == 0x80 && blue == 0x00) {
            return Color.GREEN;
        } else if (red == 0x00 && green == 0x00 && blue == 0xFF) {
            return Color.BLUE;
        } else if (red == 0xFF && green == 0xFF && blue == 0xFF) {
            return Color.WHITE;
        } else if (red == 0xE6 && green == 0xE6 && blue == 0xE6) {
            return Color.GRAY;
        } else if (red == 0xFF && green == 0xFF && blue == 0xF0) {
            return Color.IVORY;
        } else {
            return Color.OTHER_COLOR;
        }
    }
}
