package moe.nova.util;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWatermark {

    public static void main(String[] args) {
        String excelFilePath = "ExcelWithTextWatermarkBackground.xlsx";
        String watermarkText = "公司绝密 仅供内部使用"; // 你的水印文本

        try {
            addWatermarkToExcel(excelFilePath, watermarkText);
            System.out.println("Excel 文件已生成，带有文本背景水印: " + excelFilePath);
        } catch (IOException e) {
            System.err.println("处理 Excel 文件时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 为 Excel 文件添加文本水印作为工作表背景。
     *
     * @param excelFilePath 要添加水印的 Excel 文件的路径。如果文件不存在，将创建一个新文件。
     * @param watermarkText 要显示的水印文本。
     * @throws IOException 如果在文件操作或图像生成过程中发生错误。
     */
    public static void addWatermarkToExcel(String excelFilePath, String watermarkText) throws IOException {
        XSSFWorkbook workbook;
        try (FileInputStream fis = new FileInputStream(excelFilePath)) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            // 如果文件不存在，则创建一个新的工作簿
            workbook = new XSSFWorkbook();
        }

        // 遍历所有工作表，为每个工作表添加水印
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            addWatermarkToSheet(workbook, sheet, watermarkText);
        }

        // 如果是新创建的工作簿，确保至少有一个Sheet
        if (workbook.getNumberOfSheets() == 0) {
            XSSFSheet sheet = workbook.createSheet("Sheet1");
            addWatermarkToSheet(workbook, sheet, watermarkText);
        }


        // 保存 Excel 文件
        try (FileOutputStream out = new FileOutputStream(excelFilePath)) {
            workbook.write(out);
        } finally {
            workbook.close(); // 确保关闭工作簿
        }
    }

    private static void addWatermarkToSheet(XSSFWorkbook workbook, XSSFSheet sheet, String watermarkText) throws IOException {
        // 1. 生成 BufferedImage
        BufferedImage watermarkImage = generateWatermarkImage(watermarkText);

        // 2. 将 BufferedImage 转换为字节数组 (PNG 格式)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(watermarkImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        // 3. 将图片数据添加到工作簿
        int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

        // 获取图片数据部分
        POIXMLDocumentPart poixmlDocumentPart = workbook.getAllPictures().get(pictureIdx);

        // 4. 为当前工作表设置背景图片
        PackagePartName ppn = poixmlDocumentPart.getPackagePart().getPartName();
        String relType = XSSFRelation.IMAGES.getRelation();

        PackageRelationship pr = sheet.getPackagePart().addRelationship(ppn, TargetMode.INTERNAL, relType, null);

        // 设置背景图片到 sheet
        sheet.getCTWorksheet().addNewPicture().setId(pr.getId());
    }

    /**
     * 根据文本生成水印图片。
     *
     * @param watermarkText 水印文本
     * @return 包含水印文本的 BufferedImage
     */
    private static BufferedImage generateWatermarkImage(String watermarkText) {
        // 图片尺寸可以根据水印文本长度和字体大小调整
        int width = 800;
        int height = 600;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // 设置抗锯齿，使文本更平滑
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // 设置字体和颜色
        Font font = new Font("Microsoft YaHei UI", Font.BOLD, 60); // 或其他支持中文的字体
        g2d.setFont(font);
        // 设置颜色为半透明的灰色
        g2d.setColor(new Color(192, 192, 192, 80)); // R, G, B, Alpha (0-255, 0为完全透明，255为完全不透明)

        // 获取文本的度量信息
        FontMetrics fontMetrics = g2d.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(watermarkText);
        int textHeight = fontMetrics.getHeight();

        // 计算文本绘制的起始位置，使其居中偏上或根据需要调整
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fontMetrics.getAscent();

        // 旋转文本
        // 将原点移动到图片中心进行旋转，然后绘制，再将原点移回来
        g2d.translate(width / 2, height / 2);
        g2d.rotate(Math.toRadians(-30)); // 逆时针旋转 30 度
        g2d.drawString(watermarkText, -textWidth / 2, -textHeight / 2 + fontMetrics.getAscent());

        g2d.dispose(); // 释放 Graphics2D 资源
        return image;
    }
}
