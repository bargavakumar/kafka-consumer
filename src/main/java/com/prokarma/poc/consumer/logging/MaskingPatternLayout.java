package com.prokarma.poc.consumer.logging;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.prokarma.poc.consumer.constants.ConsumerConstant;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MaskingPatternLayout extends PatternLayout {
    private String patternsProperty;
    private Pattern pattern;

    public String getPatternsProperty() {
        return patternsProperty;
    }

    public void setPatternsProperty(String patternsProperty) {
        this.patternsProperty = patternsProperty;
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    private String maskMessage(String msg) {
        final StringBuilder message = new StringBuilder(msg);

        pattern = Pattern.compile(patternsProperty);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            int group = 1;
            while (group <= matcher.groupCount()) {
                if (matcher.group(group) != null) {
                    final int startGrpIndex = matcher.start(group);
                    final int endGrpIndex = matcher.end(group);
                    final int diff = (endGrpIndex - startGrpIndex) + 1;
                    int startIndex = startGrpIndex + diff;
                    int endIndex = message.indexOf(",", startIndex);
                    if (endIndex <= 0) {
                        continue;
                    }
                    // Masks last 4 digits of Customer number, first 4 digits of email and birth date
                    if (matcher.group(group).equals(ConsumerConstant.MASK_CUSTOMER_NUMBER)) {
                        startIndex = endIndex - ConsumerConstant.MASK_CUSTOMER_NUMBER_LAST_DIGITS;
                    } else if (matcher.group(group).equals(ConsumerConstant.MASK_EMAIL)) {
                        endIndex = (startIndex + (endIndex - (endIndex - ConsumerConstant.MASK_EMAIL_DIGITS))) + 1;
                        startIndex += 1;
                    } else if (matcher.group(group).equals(ConsumerConstant.MASK_BIRTHDATE)) {
                        endIndex = (startIndex + (endIndex - (endIndex - ConsumerConstant.MASK_EMAIL_DIGITS_LAST_DIGITS))) + 2;
                        startIndex += 1;
                    }
                    for (int i = startIndex; i < endIndex; i++) {
                        message.setCharAt(i, '*');
                    }
                }
                group++;
            }
        }
        return message.toString();
    }

}
