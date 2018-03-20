package com.example.utils;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
    private static final Log logger = LogFactory.getLog(DateUtil.class);
    public static String DATE_REGEXP = "^[1-2]\\d{3}(\\-\\d{1,2}){2}( (\\d{1,2}:){2}\\d{1,2})?";
    private static final int MODIFY_TRUNCATE = 0;
    private static final int MODIFY_ROUND = 1;
    private static final int MODIFY_CEILING = 2;
    public static final int SEMI_MONTH = 1001;
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIMEZONE = "GMT+8";
    private static final int[][] fields = new int[][]{{14}, {13}, {12}, {11, 10}, {5, 5, 9}, {2, 1001}, {1}, {0}};

    public DateUtil() {
    }

    public static long getDayDiff(Date dateStart, Date dateEnd) {
        return (dateEnd.getTime() - dateStart.getTime()) / 86400000L;
    }

    public static String toDateTimeString(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss", "GMT+8");
    }

    public static String toDateString(Date date) {
        return format(date, "yyyy-MM-dd", "GMT+8");
    }

    public static String toTimeString(Date date) {
        return format(date, "HH:mm:ss", "GMT+8");
    }

    public static String toWeekDay(Date date) {
        return format(date, "EEEE");
    }

    public static String format(Date date, String format) {
        return format(date, format, "GMT+8");
    }

    public static String format(Date date, String format, String timezone) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            if (StringUtil.isNotEmpty(timezone)) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
            }

            return dateFormat.format(date);
        }
    }

    public static Date parseDay(String dateString) {
        return parse(dateString, "yyyy-MM-dd", "GMT+8");
    }

    public static Date parseDateTime(String dateString) {
        return parse(dateString, "yyyy-MM-dd HH:mm:ss", "GMT+8");
    }

    public static Date parse(String dateString, String format) {
        return parse(dateString, format, "GMT+8");
    }

    public static Date parse(String dateString, String format, String timezone) {
        if (StringUtil.isEmpty(dateString)) {
            return null;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            if (StringUtil.isNotEmpty(timezone)) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
            }

            try {
                return dateFormat.parse(dateString);
            } catch (ParseException var5) {
                throw new RuntimeException("date:" + dateString + ", format:" + format, var5);
            }
        }
    }

    public static boolean isDateTimeString(String str) {
        return isDateTime(str, "yyyy-MM-dd HH:mm:ss");
    }

    public static boolean isDayString(String str) {
        return isDateTime(str, "yyyy-MM-dd");
    }

    private static boolean isDateTime(String str, String format) {
        if (!StringUtil.isEmpty(str) && !StringUtil.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);

            try {
                sdf.parse(str);
                return true;
            } catch (Exception var4) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static Date addTime(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (amount == 0) {
            return date;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static Date addMilliseconds(Date date, int amount) {
        return addTime(date, 14, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return addTime(date, 13, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return addTime(date, 12, amount);
    }

    public static Date addHours(Date date, int amount) {
        return addTime(date, 11, amount);
    }

    public static Date addDays(Date date, int amount) {
        return addTime(date, 5, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return addTime(date, 3, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return addTime(date, 2, amount);
    }

    public static Date addYears(Date date, int amount) {
        return addTime(date, 1, amount);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static Date maxDate(Date date1, Date date2) {
        return date1 == null && date2 != null ? date2 : (date1 != null && date2 == null ? date1 : (date1 != null && date2 != null ? (date1.before(date2) ? date2 : date1) : null));
    }

    private static void modify(Calendar val, int field, int modType) {
        if (val.get(1) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        } else if (field != 14) {
            Date date = val.getTime();
            long time = date.getTime();
            boolean done = false;
            int millisecs = val.get(14);
            if (0 == modType || millisecs < 500) {
                time -= (long) millisecs;
            }

            if (field == 13) {
                done = true;
            }

            int seconds = val.get(13);
            if (!done && (0 == modType || seconds < 30)) {
                time -= (long) seconds * 1000L;
            }

            if (field == 12) {
                done = true;
            }

            int minutes = val.get(12);
            if (!done && (0 == modType || minutes < 30)) {
                time -= (long) minutes * 60000L;
            }

            if (date.getTime() != time) {
                date.setTime(time);
                val.setTime(date);
            }

            boolean roundUp = false;
            int[][] var11 = fields;
            int var12 = var11.length;

            for (int var13 = 0; var13 < var12; ++var13) {
                int[] aField = var11[var13];
                int[] offset = aField;
                int offsetSet = aField.length;

                int min;
                int max;
                for (min = 0; min < offsetSet; ++min) {
                    max = offset[min];
                    if (max == field) {
                        if (modType == 2 || modType == 1 && roundUp) {
                            if (field == 1001) {
                                if (val.get(5) == 1) {
                                    val.add(5, 15);
                                } else {
                                    val.add(5, -15);
                                    val.add(2, 1);
                                }
                            } else if (field == 9) {
                                if (val.get(11) == 0) {
                                    val.add(11, 12);
                                } else {
                                    val.add(11, -12);
                                    val.add(5, 1);
                                }
                            } else {
                                val.add(aField[0], 1);
                            }
                        }

                        return;
                    }
                }

                int var19 = 0;
                boolean var20 = false;
                switch (field) {
                    case 9:
                        if (aField[0] == 11) {
                            var19 = val.get(11);
                            if (var19 >= 12) {
                                var19 -= 12;
                            }

                            roundUp = var19 >= 6;
                            var20 = true;
                        }
                        break;
                    case 1001:
                        if (aField[0] == 5) {
                            var19 = val.get(5) - 1;
                            if (var19 >= 15) {
                                var19 -= 15;
                            }

                            roundUp = var19 > 7;
                            var20 = true;
                        }
                }

                if (!var20) {
                    min = val.getActualMinimum(aField[0]);
                    max = val.getActualMaximum(aField[0]);
                    var19 = val.get(aField[0]) - min;
                    roundUp = var19 > (max - min) / 2;
                }

                if (var19 != 0) {
                    val.set(aField[0], val.get(aField[0]) - var19);
                }
            }

            throw new IllegalArgumentException("The field " + field + " is not supported");
        }
    }

    public static Date truncate(Date date, int field) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar gval = Calendar.getInstance();
            gval.setTime(date);
            modify(gval, field, 0);
            return gval.getTime();
        }
    }

    public static int truncatedCompareTo(Date date1, Date date2, int field) {
        Date truncatedDate1 = truncate(date1, field);
        Date truncatedDate2 = truncate(date2, field);
        return truncatedDate1.compareTo(truncatedDate2);
    }

    public static boolean truncatedEquals(Date date1, Date date2, int field) {
        return truncatedCompareTo(date1, date2, field) == 0;
    }

    public static Date theLastDayInMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        cal.roll(5, -1);
        return cal.getTime();
    }

    public static Date firstDayInMonth(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        c.setTime(date);
        c.set(5, 1);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    public static Date getMondayInWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(7);
        if (1 == dayWeek) {
            cal.add(5, -1);
        }

        cal.setFirstDayOfWeek(2);
        int day = cal.get(7);
        cal.add(5, cal.getFirstDayOfWeek() - day);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date lastDayOfCrtTime() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        c.add(5, -1);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        } else {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);
            XMLGregorianCalendar gc = null;

            try {
                gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            } catch (Exception var4) {
                logger.error("", var4);
            }

            return gc;
        }
    }

    public static Date[] splitDate(Date startDate, Date endDate, int amount, int timeType) {
        if (startDate != null && endDate != null) {
            if (startDate.after(endDate)) {
                Date seconds = startDate;
                startDate = endDate;
                endDate = seconds;
            }

            long var14 = (long) amount;
            switch (timeType) {
                case 5:
                    var14 = (long) (amount * 3600 * 24);
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                case 11:
                default:
                    var14 = (long) (amount * 3600 * 24);
                    break;
                case 10:
                    var14 = (long) (amount * 3600);
                    break;
                case 12:
                    var14 = (long) (amount * 60);
                    break;
                case 13:
                    var14 = (long) amount;
            }

            long dis = (endDate.getTime() - startDate.getTime()) / 1000L;
            long num = (dis + var14 - 1L) / var14;
            Date[] lRet = new Date[(int) (++num)];
            lRet[0] = startDate;

            for (int i = 1; i < lRet.length - 1; ++i) {
                long temp = startDate.getTime() / 1000L + var14 * (long) i;
                lRet[i] = new Date(temp * 1000L);
            }

            lRet[lRet.length - 1] = endDate;
            return lRet;
        } else {
            return new Date[0];
        }
    }

    public static long getDay(String date) {
        Date now = parse(date, "yyMMdd");
        if (now == null) {
            now = Calendar.getInstance().getTime();
        } else {
            now.setSeconds(30);
        }

        Calendar c = Calendar.getInstance();
        c.set(2012, 0, 1, 0, 0, 0);
        return getDayDiff(c.getTime(), now);
    }

    public static long toUnixTimestamp(Date date) {
        return date == null ? 0L : date.getTime() / 1000L;
    }

    public static long timeRange(Date beginTime, Date endTime, int calendarField) {
        if (endTime != null && beginTime != null) {
            long actPassTime = endTime.getTime() - beginTime.getTime();
            switch (calendarField) {
                case 6:
                    actPassTime /= 86400000L;
                    break;
                case 7:
                case 8:
                case 9:
                case 11:
                default:
                    throw new RuntimeException("Not Support Field:" + calendarField);
                case 10:
                    actPassTime /= 3600000L;
                    break;
                case 12:
                    actPassTime /= 60000L;
                    break;
                case 13:
                    actPassTime /= 1000L;
                case 14:
            }

            return actPassTime;
        } else {
            return -9223372036854775808L;
        }
    }

    public static Date fromUnixTimestamp(long time) {
        return new Date(time * 1000L);
    }

    public static class DateIterator implements Iterator {
        private final Calendar endFinal;
        private final Calendar spot;
        private final int field;
        private final int range;

        public DateIterator(Calendar startFinal, Calendar endFinal) {
            this((Calendar) startFinal, (Calendar) endFinal, 5, 1);
        }

        public DateIterator(Date startFinal, Date endFinal) {
            this((Date) startFinal, (Date) endFinal, 5, 1);
        }

        public DateIterator(Calendar startFinal, Calendar endFinal, int calField, int range) {
            this.field = calField;
            this.range = range;
            this.endFinal = endFinal;
            this.spot = startFinal;
            this.spot.add(this.field, -range);
        }

        public DateIterator(Date startFinal, Date endFinal, int calField, int range) {
            this.field = calField;
            this.range = range;
            this.endFinal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
            this.endFinal.setTime(endFinal);
            this.spot = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
            this.spot.setTime(startFinal);
            this.spot.add(this.field, -range);
        }

        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        public Object next() {
            if (this.spot.after(this.endFinal)) {
                throw new NoSuchElementException();
            } else {
                this.spot.add(this.field, this.range);
                return this.spot.before(this.endFinal) ? this.spot.clone() : this.endFinal.clone();
            }
        }

        public Date nextTime() {
            return ((Calendar) this.next()).getTime();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
