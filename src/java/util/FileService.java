/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.servlet.http.Part;
import java.text.Normalizer;

/**
 *
 * @author hunggt1572004
 */
public class FileService {
    public static boolean isImage(String extension) {
        return extension.equals("jpg") || extension.equals("png");
    }
    
    public static boolean isVideo(String extension) {
        return extension.equals("mp4");
    }
    
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1)
                ? ""
                : fileName.substring(lastDotIndex + 1).toLowerCase();
    }
    
    public static boolean isValidMimeTypeImage(Part filePart) {
        String mimeType = filePart.getContentType();
        return mimeType.equals("image/jpeg")
                || mimeType.equals("image/png");
    }
    
    private boolean isValidMimeTypeVideo(Part filePart) {
        String mimeType = filePart.getContentType();
        return mimeType.equals("video/mp4");
    }
    
    public static String normalizeFileName(String fileName) {
    return Normalizer.normalize(fileName, Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "") // Bỏ dấu
            .replaceAll(" ", "_") // Thay khoảng trắng bằng dấu "_"
            .toLowerCase(); // Đổi thành chữ thường
}
}
