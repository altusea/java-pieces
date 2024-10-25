package moe.nova.playground;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.hutool.core.io.file.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExcelExportTest {

    public static class DemoData {

        @ExcelProperty("字符串标题")
        private String string;

        @ExcelProperty("日期标题")
        private Date date;

        @ExcelProperty("数字标题")
        private Double doubleData;
        /**
         * 忽略这个字段
         */
        @ExcelIgnore
        private String ignore;

        public DemoData() {
        }

        public DemoData(String string, Date date, Double doubleData, String ignore) {
            this.string = string;
            this.date = date;
            this.doubleData = doubleData;
            this.ignore = ignore;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Double getDoubleData() {
            return doubleData;
        }

        public void setDoubleData(Double doubleData) {
            this.doubleData = doubleData;
        }

        public String getIgnore() {
            return ignore;
        }

        public void setIgnore(String ignore) {
            this.ignore = ignore;
        }
    }

    public static void main(String[] args) throws IOException {
        var demoDateList = List.of(
                new DemoData("A", new Date(), 1.0, ""),
                new DemoData("A", new Date(), 1.0, ""),
                new DemoData("A", new Date(), 1.0, ""),
                new DemoData("A", new Date(), 1.0, "")
        );
        File file = FileUtil.createTempFile("test", ".xlsx", true);
        System.out.println(file.getAbsolutePath());
        EasyExcel.write(file, DemoData.class).sheet("模板").doWrite(demoDateList);
    }
}
