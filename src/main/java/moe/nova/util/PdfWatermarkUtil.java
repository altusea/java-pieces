package moe.nova.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PdfWatermarkUtil {

    /**
     * 为 PDF 文件添加中文文本水印。
     *
     * @param inputPdfPath  输入 PDF 文件的完整路径。
     * @param outputPdfPath 输出水印 PDF 文件的完整路径。
     * @param watermarkText 要添加的水印文本（可以包含中文）。
     * @param fontPath      中文字体文件（.ttf）的完整路径。
     * @throws IOException 如果在处理 PDF 或字体文件时发生错误。
     */
    public static void addChineseTextWatermark(String inputPdfPath, String outputPdfPath, String watermarkText, String fontPath) throws IOException {
        try (PDDocument document = Loader.loadPDF(new File(inputPdfPath))) {
            // 加载中文字体文件
            // 确保字体文件存在且可访问
            PDType0Font chineseFont = PDType0Font.load(document, new File(fontPath));

            for (PDPage page : document.getPages()) {
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true)) {
                    // 设置透明度
                    PDExtendedGraphicsState graphicsState = new PDExtendedGraphicsState();
                    graphicsState.setNonStrokingAlphaConstant(0.2f); // 20% 透明度
                    contentStream.setGraphicsStateParameters(graphicsState);

                    // 设置颜色
                    contentStream.setNonStrokingColor(Color.GRAY); // 较深的灰色可能更适合中文水印

                    // 设置中文字体和大小
                    float fontSize = 16;
                    contentStream.setFont(chineseFont, fontSize);

                    // 获取页面尺寸
                    float pageWidth = page.getMediaBox().getWidth();
                    float pageHeight = page.getMediaBox().getHeight();

                    // 计算水印文本的宽度
                    float textWidth = chineseFont.getStringWidth(watermarkText) / 1000 * fontSize;
                    float textHeight = fontSize; // 近似字体高度

                    contentStream.beginText();
                    // 根据纸张大小添加水印，30度倾斜
                    for (int h = 20; h < pageHeight; h += textHeight * 4) {
                        for (int w = 20; w < pageWidth; w += textWidth) {
                            contentStream.setTextMatrix(Matrix.getRotateInstance(0.3, w, h));
                            contentStream.showText(watermarkText);
                        }
                    }

                    contentStream.endText();
                }
            }
            document.save(outputPdfPath);
        }
    }

    public static void main(String[] args) throws IOException {
        // 定义输入和输出文件路径
        String inputPdf = "original.pdf";
        String outputPdf = "watermarked_chinese_text_3x.pdf";
        // 定义水印文本
        String watermark = "绝密文件 仅供参考 (PDFBox 3.x)";
        // 定义中文字体文件路径
        // 请替换为你的中文字体文件路径，例如 "C:\\Windows\\Fonts\\simhei.ttf" 或 "src/main/resources/fonts/simhei.ttf"
        String fontPath = "C:\\Windows\\Fonts\\simhei.ttf";

        // 为了测试，创建一个简单的 original.pdf 文件
        if (!new File(inputPdf).exists()) {
            try (PDDocument doc = new PDDocument()) {
                doc.addPage(new PDPage());
                doc.save(inputPdf);
            }
        }

        // 检查字体文件是否存在
        File fontFile = new File(fontPath);
        if (!fontFile.exists()) {
            System.err.println("错误：未找到中文字体文件！请将支持中文的 .ttf 字体文件（例如 simhei.ttf）放置在 '" + fontPath + "' 路径下。");
            System.err.println("例如，可以从 Windows 系统中复制 C:\\Windows\\Fonts\\simhei.ttf 到项目指定路径。");
            return;
        }

        // 调用添加水印的方法
        addChineseTextWatermark(inputPdf, outputPdf, watermark, fontPath);
        System.out.println("中文文本水印添加成功 (PDFBox 3.x)！文件已保存到：" + outputPdf);
    }
}