package System.Cart;

import java.util.UUID;

public class Calendar extends Product {
    private CalendarType calendarType;
    private CalendarSize calendarSize;

    public Calendar(UUID productId, CalendarType calendarType) {
        super(Type.CALENDAR, productId, initializeCalendarPrice(calendarType),
                initializeCalendarWidth(calendarType),
                initializeCalendarHeight(calendarType),
                Color.WHITE);
        if (calendarType == null) {
            throw new IllegalArgumentException("parameter can not be null");
        }
        this.calendarType = calendarType;
        this.calendarSize = initializeCalendarSize(calendarType);
    }

    public CalendarType getCalendarType() {
        return this.calendarType;
    }

    public CalendarSize getCalendarSize() {
        return this.calendarSize;
    }

    private CalendarSize initializeCalendarSize(CalendarType calendarType) {
        switch (calendarType) {
            case WALL_CALENDAR:
                return CalendarSize.CALENDAR_SIZE_400_X_400;
            case DESK_CALENDAR:
                return CalendarSize.CALENDAR_SIZE_200_X_150;
            case MAGNET_CALENDAR:
                return CalendarSize.CALENDAR_SIZE_100_X_200;
            default:
                assert (false) : "unknown type";
        }
        return null;
    }

    private static int initializeCalendarWidth(CalendarType calendarType) {
        switch (calendarType) {
            case WALL_CALENDAR:
                return 400;
            case DESK_CALENDAR:
                return 200;
            case MAGNET_CALENDAR:
                return 100;
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }

    private static int initializeCalendarHeight(CalendarType calendarType) {
        switch (calendarType) {
            case WALL_CALENDAR:
                return 400;
            case DESK_CALENDAR:
                return 150;
            case MAGNET_CALENDAR:
                return 200;
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }

    private static int initializeCalendarPrice(CalendarType calendarType) {
        switch (calendarType) {
            case WALL_CALENDAR:
            case DESK_CALENDAR:
                return 1000;
            case MAGNET_CALENDAR:
                return 1500;
            default:
                assert (false) : "unknown type";
                break;
        }
        return 0;
    }
}
