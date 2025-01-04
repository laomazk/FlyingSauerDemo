package com.magic.fs;

import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * </p>
 *
 * @author mazikai
 * @date 2025/1/4
 */
public class Test2 {
    public static void main(String[] args) throws IOException, FontFormatException {
        File temp = File.createTempFile("tmp_espos_sample", ".html");
        temp.deleteOnExit();
        OutputStream tmpStreamxhtml = new FileOutputStream(temp);
        String html = readHtmlFile("D:\\workspace\\FlyingSauerDemo\\src\\main\\resources\\test.html");
//        String html = readHtmlFile("/root/worspace/FlyingSauerDemo/test.html");
        System.out.println("html = " + html);

        tmpStreamxhtml.write(html.getBytes(StandardCharsets.UTF_8));
        tmpStreamxhtml.close();
        String tmpFileURL = temp.toURI().toURL().toExternalForm();

        // if you want to include image or other external, include at tmpFileURL directory...
        String tmpBaseURL =  temp.getParent();
        Java2DRenderer render = new Java2DRenderer(tmpFileURL, tmpBaseURL, 576); // 576 is max printer area of the printer, you can configure to your
        BufferedImage image = render.getImage();

//        File output = new File("/root/png/css.png");
        File output = new File("css.png");
        ImageIO.write(image, "png", output);


    }

    public static String readHtmlFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine())!= null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
