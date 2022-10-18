package System.Cart;

import java.util.UUID;

public class BusinessCard extends CustomizableProduct {
    private BusinessCardColor businessCardColor;
    private PaperMaterial paperMaterial;
    private Sides businessCardSides;
    private int paperStock;

    public BusinessCard(UUID productId, BusinessCardColor businessCardColor,
                        Orientation businessCardOrientation,
                        PaperMaterial paperMaterial, Sides businessCardSides, int paperStock) {
        super(paperMaterial, Type.BUSINESS_CARD, productId,
                businessCardOrientation,
                initializeBusinessCardPrice(paperMaterial, businessCardSides, paperStock),
                90, 50, businessCardColor);

        this.businessCardColor = businessCardColor;
        if (paperMaterial == null || businessCardColor == null || businessCardSides == null) {
            throw new IllegalArgumentException("parameter can not be null");
        }

        this.paperMaterial = paperMaterial;
        this.businessCardSides = businessCardSides;
        this.paperStock = paperStock;
    }

    public PaperMaterial getPaperMaterial() {
        return this.paperMaterial;
    }

    public BusinessCardColor getBusinessCardColor() {
        return this.businessCardColor;
    }

    public Sides getSides() {
        return this.businessCardSides;
    }

    public int getPaperStock() {
        return this.paperStock;
    }

    private static int initializeBusinessCardPrice(PaperMaterial paperMaterial, Sides businessCardSides, int paperStock) {
        switch (paperMaterial) {
            case LINEN:
                if (businessCardSides == Sides.SINGLE_SIDED_BUSINESS_CARD) {
                    return 110;
                } else {
                    return 140;
                }
            case LAID:
                if (businessCardSides == Sides.SINGLE_SIDED_BUSINESS_CARD) {
                    return 120;
                } else {
                    return 150;
                }
            case SMOOTH:
                if (businessCardSides == Sides.SINGLE_SIDED_BUSINESS_CARD) {
                    return 100;
                } else {
                    return 130;
                }
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }

}
