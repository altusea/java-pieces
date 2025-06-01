package moe.nova.playground;

import cn.idev.excel.FastExcel;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.Date;

public class ExcelTest {

    @Data
    static class DemoData {
        private String string;
        private Date date;
        private Double doubleData;
    }

    static class DemoDataListener implements ReadListener<DemoData> {
        @Override
        public void invoke(DemoData data, AnalysisContext context) {
            IO.println("解析到一条数据" + JSON.toJSONString(data));
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            IO.println("所有数据解析完成！");
        }
    }

    static void main(String[] args) {
        String fileName = "demo.xlsx";
        // 读取 Excel 文件
        FastExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
