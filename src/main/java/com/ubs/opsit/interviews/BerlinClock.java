package com.ubs.opsit.interviews;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BerlinClock implements TimeConverter {

    private static Logger log = LoggerFactory.getLogger(BerlinClock.class);

    public String convertTime(String time) {
        int[] timeArray = Arrays.asList(time.split(":")).stream().mapToInt(Integer::parseInt).toArray();

//        log.debug(Arrays.toString(timeArray));

        String seconds = getSeconds(timeArray[2]);
        String topHours = getTopHours(timeArray[0]);
        String bottomHours = getBottomHours(timeArray[0]);
        String topMinutes = getTopMinutes(timeArray[1]);
        String bottomMinutes = getBottomMinutes(timeArray[1]);

        String[] berlinTime = new String[]{seconds, topHours, bottomHours, topMinutes, bottomMinutes };

//        log.debug(Arrays.toString(berlinTime));

        return Arrays.toString(berlinTime).replaceAll("\\p{P}","").replaceAll("\\s+", "\r\n");
    }

    protected String getSeconds(int seconds) {
        if(seconds % 2 == 0) {
            return "Y";
        } else {
            return "O";
        }
    }

    protected String getTopHours(int hour) {
        int fiveHours = hour / 5;
        return getHours(fiveHours);
    }

    protected String getBottomHours(int hour) {
        int oneHours = hour % 5;
        return getHours(oneHours);
    }

    protected String getHours(int hours) {
        switch(hours) {
            case 0:
                return "OOOO";
            case 1:
                return "ROOO";
            case 2:
                return "RROO";
            case 3:
                return "RRRO";
            case 4:
                return "RRRR";
            default:
                return "OOOO";
        }
    }

    protected String getTopMinutes(int minutes) {
//    	log.debug("Minutes: " + minutes);
        int fifteenMinutes = minutes / 15;
        
//    	log.debug("fifteenMinutes: " + fifteenMinutes);
    	
        switch (fifteenMinutes) {
            case 0:
                return getTenMinutes(minutes) + "OOOOOOOOO";
            case 1:
            	return getTenMinutes(14) + "R" + getTenMinutes(minutes - 15) + "OOOOOO";
            case 2:
                return getTenMinutes(14) + "R" + getTenMinutes(14) + "R" + getTenMinutes(minutes - 30) + "OOO";
            case 3:
            	return getTenMinutes(14) + "R" + getTenMinutes(14) + "R" + getTenMinutes(14) + "R"  + getTenMinutes(minutes - 45);
            default:
                return "OOOOOOOOOOO";
        }
    }

    private String getTenMinutes(int minutes) {
        int fiveMinutes = minutes / 5;
        
//    	log.debug("fiveMinutes: " + fiveMinutes);
    	
        switch (fiveMinutes) {
            case 0:
                return "OO";
            case 1:
                return "YO";
            case 2:
                return "YY";
            case 3:
                return "OO";
            default:
                return "OO";
        }
    }

    protected String getBottomMinutes(int minutes) {
        int oneMinutes = minutes % 5;
        return getMinutes(oneMinutes);
    }

    private String getMinutes(int minutes) {
        switch (minutes) {
            case 0:
                return "OOOO";
            case 1:
                return "YOOO";
            case 2:
                return "YYOO";
            case 3:
                return "YYYO";
            case 4:
                return "YYYY";
            default:
                return "OOOO";
        }
    }

}