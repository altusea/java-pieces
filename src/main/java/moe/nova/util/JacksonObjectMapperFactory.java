package moe.nova.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import moe.nova.bean.TimeHolder;
import moe.nova.util.internal.jackson.CustomLocalDateTimeDeserializer;
import org.dromara.hutool.core.date.DatePattern;

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
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DatePattern.NORM_DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DatePattern.NORM_DATE_FORMATTER));
        // LocalDateTime
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        // LocalTime
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DatePattern.NORM_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DatePattern.NORM_TIME_FORMATTER));
        // YearMonth
        javaTimeModule.addSerializer(YearMonth.class, new YearMonthSerializer(DatePattern.NORM_MONTH_FORMATTER));
        javaTimeModule.addDeserializer(YearMonth.class, new YearMonthDeserializer(DatePattern.NORM_MONTH_FORMATTER));
        return JsonMapper.builder()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                // empty string error
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .addModule(javaTimeModule)
                .addModule(new Jdk8Module())
                .build();
    }

    public static void main(String[] args) throws JsonProcessingException {
        TimeHolder timeHolder = new TimeHolder();
        timeHolder.setYearMonth(YearMonth.now());
        timeHolder.setLocalDate(LocalDate.now());
        timeHolder.setLocalDateTime(LocalDateTime.now());
        System.out.println(timeHolder);
        System.out.println(JacksonObjectMapperFactory.createJsonMapper().writeValueAsString(timeHolder));
    }

}
