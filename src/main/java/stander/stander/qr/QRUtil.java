package stander.stander.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class QRUtil {

    public static void makeQR(String url, String path) {
        try {

            QRCodeWriter writer = new QRCodeWriter();
            url = new String(url.getBytes("UTF-8"), "ISO-8859-1");
            BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE, 500, 500);

            int qrColor = 0xff020202;
            int backgroundColor = 0xFFFFFFFF;

            MatrixToImageConfig config = new MatrixToImageConfig(qrColor, 0xFFFFFFFF);

            BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_BYTE_BINARY);
            BufferedImage qrimage = MatrixToImageWriter.toBufferedImage(matrix, config);


            ImageIO.write(qrimage, "jpg", new File(path));

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}

