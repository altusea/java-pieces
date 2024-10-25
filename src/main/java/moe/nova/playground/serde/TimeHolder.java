package moe.nova.playground.serde;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Date;

public class TimeHolder {

    private Date date;

    private YearMonth yearMonth;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "TimeHolder{" +
                "date=" + date +
                ", yearMonth=" + yearMonth +
                ", localDate=" + localDate +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
