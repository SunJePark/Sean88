package System.Cart.app;


import System.Cart.Banner;
import System.Cart.BannerMaterial;
import System.Cart.BannerSize;
import System.Cart.BusinessCard;
import System.Cart.BusinessCardColor;
import System.Cart.Calendar;
import System.Cart.CalendarSize;
import System.Cart.CalendarType;
import System.Cart.Color;
import System.Cart.ImageAperture;
import System.Cart.Orientation;
import System.Cart.PaperMaterial;
import System.Cart.ShippingMethod;
import System.Cart.Sides;
import System.Cart.Stamp;
import System.Cart.StampSize;
import System.Cart.TextAperture;
import System.Cart.Type;

import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        System.out.println("===========================");
        Stamp s0 = new Stamp(UUID.randomUUID(),
                Color.RED, StampSize.STAMP_SIZE_40_X_30, "new stamp");
        assert (s0.getColor() == Color.RED);
        assert (s0.getDisplayName().equals("Stamp (40 mm x 30 mm)"));
        assert (s0.getStampSize() == StampSize.STAMP_SIZE_40_X_30);
        assert (s0.getWidth() == 40);
        assert (s0.getHeight() == 30);
        assert (s0.getPrice() == 2300);
        assert (s0.getStampText().equals("new stamp"));
        assert (s0.getType() == Type.STAMP);
        assert (s0.getRed() == 0xFF);
        assert (s0.getShippingMethod() == ShippingMethod.SHIP);
        assert (s0.getBlue() == 0x00);
        System.out.println("40x30, Red Stamp TEST OK");
        System.out.println("===========================");
        Stamp g0 = new Stamp(UUID.randomUUID(),
                Color.GREEN, StampSize.STAMP_SIZE_50_X_20, "new stamp");
        assert (g0.getColor() == Color.GREEN);
        assert (g0.getDisplayName().equals("Stamp (50 mm x 20 mm)"));
        assert (g0.getStampSize() == StampSize.STAMP_SIZE_50_X_20);
        assert (g0.getWidth() == 50);
        assert (g0.getHeight() == 20);
        assert (g0.getPrice() == 2300);
        assert (g0.getStampText().equals("new stamp"));
        assert (g0.getType() == Type.STAMP);
        assert (g0.getGreen() == 0x80);
        assert (g0.getShippingMethod() == ShippingMethod.SHIP);
        assert (g0.getRed() == 0x00);
        System.out.println("50x20, Green Stamp TEST OK");
        System.out.println("===========================");
        Stamp b0 = new Stamp(UUID.randomUUID(),
                Color.BLUE, StampSize.STAMP_SIZE_70_X_40, "new stamp");
        assert (b0.getColor() == Color.BLUE);
        assert (b0.getDisplayName().equals("Stamp (70 mm x 40 mm)"));
        assert (b0.getStampSize() == StampSize.STAMP_SIZE_70_X_40);
        assert (b0.getWidth() == 70);
        assert (b0.getHeight() == 40);
        assert (b0.getPrice() == 2600);
        assert (b0.getStampText().equals("new stamp"));
        assert (b0.getType() == Type.STAMP);
        assert (b0.getGreen() == 0x00);
        assert (b0.getShippingMethod() == ShippingMethod.SHIP);
        assert (b0.getRed() == 0x00);
        assert (b0.getRed() == 0x00);
        System.out.println("70x40, Blue Stamp TEST OK");
        System.out.println("===========================");
        Calendar c = new Calendar(UUID.randomUUID(), CalendarType.WALL_CALENDAR);
        assert (c.getCalendarSize() == CalendarSize.CALENDAR_SIZE_400_X_400);
        assert (c.getColor() == Color.WHITE);
        assert (c.getRed() == 0xFF);
        assert (c.getHeight() == 400);
        assert (c.getWidth() == 400);
        assert (c.getDisplayName().equals("Wall Calendar"));
        assert (c.getCalendarType() == CalendarType.WALL_CALENDAR);
        assert (c.getPrice() == 1000);
        assert (c.getShippingMethod() == ShippingMethod.SHIP);
        c.setShippingMethod(ShippingMethod.PICK_UP);
        assert (c.getShippingMethod() == ShippingMethod.PICK_UP);
        System.out.println("400x400, Wall Calendar TEST OK");
        System.out.println("===========================");
        Calendar nn = new Calendar(UUID.randomUUID(), CalendarType.MAGNET_CALENDAR);
        assert (nn.getCalendarSize() == CalendarSize.CALENDAR_SIZE_100_X_200);
        assert (nn.getColor() == Color.WHITE);
        assert (nn.getRed() == 0xFF);
        assert (nn.getHeight() == 200);
        assert (nn.getWidth() == 100);
        assert (nn.getDisplayName().equals("Magnet Calendar"));
        assert (nn.getCalendarType() == CalendarType.MAGNET_CALENDAR);
        assert (nn.getPrice() == 1500);
        assert (nn.getShippingMethod() == ShippingMethod.SHIP);
        nn.setShippingMethod(ShippingMethod.PICK_UP);
        nn.setShippingMethod(ShippingMethod.SHIP);
        assert (nn.getShippingMethod() == ShippingMethod.SHIP);
        System.out.println("100x200, Magnet Calendar TEST OK");
        System.out.println("===========================");
        Calendar dc = new Calendar(UUID.randomUUID(), CalendarType.DESK_CALENDAR);
        assert (dc.getCalendarSize() == CalendarSize.CALENDAR_SIZE_200_X_150);
        assert (dc.getColor() == Color.WHITE);
        assert (dc.getRed() == 0xFF);
        assert (dc.getHeight() == 150);
        assert (dc.getWidth() == 200);
        assert (dc.getDisplayName().equals("Desk Calendar"));
        assert (dc.getCalendarType() == CalendarType.DESK_CALENDAR);
        assert (dc.getPrice() == 1000);
        assert (dc.getShippingMethod() != ShippingMethod.PICK_UP);
        dc.setShippingMethod(ShippingMethod.SHIP);
        dc.setShippingMethod(ShippingMethod.SHIP);
        assert (dc.getShippingMethod() == ShippingMethod.SHIP);
        System.out.println("200x150, Desk Calendar TEST OK");
        System.out.println("===========================");
        Banner gg = new Banner(UUID.randomUUID(),
                (short) 0xff, (short) 0x00, (short) 0x00,
                Orientation.LANDSCAPE,
                BannerSize.BANNER_SIZE_1000_X_500,
                BannerMaterial.GLOSS);
        assert (gg.getBannerSize() == BannerSize.BANNER_SIZE_1000_X_500);
        assert (gg.getBannerMaterial() == BannerMaterial.GLOSS);
        assert (gg.getColor() == Color.RED);
        assert (gg.getHeight() == 500);
        assert (gg.getWidth() == 1000);
        System.out.println(gg.getDisplayName());
        assert (gg.getDisplayName().equals("Gloss Banner (1000 mm x 500 mm)"));
        assert (gg.getPrice() == 5000);
        assert (gg.getShippingMethod() == ShippingMethod.SHIP);
        System.out.println(g0.getProductId());
        System.out.println("1000x500, Gloss Banner TEST OK");
        System.out.println("===========================");
        BusinessCard gbc = new BusinessCard(UUID.randomUUID(),
                BusinessCardColor.WHITE, Orientation.LANDSCAPE, PaperMaterial.LINEN,
                Sides.SINGLE_SIDED_BUSINESS_CARD, 200);
        assert (gbc.getSides() == Sides.SINGLE_SIDED_BUSINESS_CARD);
        assert (gbc.getColor() == Color.WHITE);
        System.out.println(gbc.getDisplayName());
        assert (gbc.getDisplayName().equals("Linen Business Card"));
        assert (gbc.getPaperMaterial() == PaperMaterial.LINEN);
        assert (gbc.getPrice() == 110);
        assert (gbc.getHeight() == 50);
        assert (gbc.getWidth() == 90);
        assert (gbc.getType() == Type.BUSINESS_CARD);
        assert (gbc.getShippingMethod() == ShippingMethod.SHIP);
        System.out.println(gbc.getProductId());
        TextAperture txt = new TextAperture("parkpark", 0, 0, 1, 1);
        gbc.addAperture(txt);
        ImageAperture img = new ImageAperture("design.printit.com/pocu/1212.jpg", 0, 0, 2, 2);
        gbc.addAperture(img);
        ImageAperture img2 = new ImageAperture("design.printit.com/pocu/12222.jpg", 0, 0, 3, 3);
        gbc.addAperture(img2);
        assert (gbc.getPrice() == 125);
        gbc.removeAperture(img2);
        assert (gbc.getPrice() == 120);
        System.out.println("Linen Business Card Test OK");
        System.out.println("===========================");
        Banner nBB = new Banner(UUID.randomUUID(), (short) 0xff, (short) 0xff, (short) 0xff,
                Orientation.LANDSCAPE, BannerSize.BANNER_SIZE_1000_X_500, BannerMaterial.GLOSS);
        ImageAperture mmm = new ImageAperture("dldld.co.kr", 0, 0, 1, 1);
        nBB.addAperture(mmm);
        System.out.println("===========================");
    }
}
