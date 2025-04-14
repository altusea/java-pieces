package moe.nova.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import moe.nova.bean.TimeHolder;
import moe.nova.util.internal.jackson.CustomLocalDateTimeDeserializer;
import org.dromara.hutool.core.date.DateFormatPool;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.ext.javatime.deser.LocalDateDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalTimeDeserializer;
import tools.jackson.databind.ext.javatime.deser.YearMonthDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalTimeSerializer;
import tools.jackson.databind.ext.javatime.ser.YearMonthSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

public final class JacksonObjectMapperFactory {

    private JacksonObjectMapperFactory() {
    }

    public static JsonMapper createJsonMapper() {
        var javaTimeModule = new SimpleModule();
        // LocalDate
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateFormatPool.NORM_DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateFormatPool.NORM_DATE_FORMATTER));
        // LocalDateTime
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateFormatPool.NORM_DATETIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        // LocalTime
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateFormatPool.NORM_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateFormatPool.NORM_TIME_FORMATTER));
        // YearMonth
        javaTimeModule.addSerializer(YearMonth.class, new YearMonthSerializer(DateFormatPool.NORM_MONTH_FORMATTER));
        javaTimeModule.addDeserializer(YearMonth.class, new YearMonthDeserializer(DateFormatPool.NORM_MONTH_FORMATTER));
        return JsonMapper.builder()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                // empty string error
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .changeDefaultPropertyInclusion(old -> old.withValueInclusion(JsonInclude.Include.NON_NULL))
                .addModule(javaTimeModule)
                .build();
    }

    public static void main(String[] args) {
        TimeHolder timeHolder = new TimeHolder();
        timeHolder.setYearMonth(YearMonth.now());
        timeHolder.setLocalDate(LocalDate.now());
        timeHolder.setLocalDateTime(LocalDateTime.now());
        var mapper = createJsonMapper();
        var s = mapper.writeValueAsString(timeHolder);
        System.out.println(s);
        System.out.println("The above json string contains null value: " + s.contains("null"));
        var o = mapper.readValue(s, TimeHolder.class);
        System.out.println(o);
    }

}
