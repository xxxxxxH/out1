package net.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class utils {
    /**
     * 复制文件
     *
     * @param source 输入文件
     * @param target 输出文件
     * @return 文件是否复制成功
     */
    public static boolean copy(File source, File target) {
        boolean status = true;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(target);
            fileInputStream = new FileInputStream(source);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public static boolean copyFile(File src, String destPath) {
        boolean result = false;
        if ((src == null) || (destPath== null)) {
            return result;
        }
        File dest= new File(destPath);
        if (dest!= null && dest.exists()) {
            dest.delete(); // delete file
        }
        try {
            dest.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileChannel srcChannel = null;
        FileChannel dstChannel = null;

        try {
            srcChannel = new FileInputStream(src).getChannel();
            dstChannel = new FileOutputStream(dest).getChannel();
            srcChannel.transferTo(0, srcChannel.size(), dstChannel);
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        try {
            srcChannel.close();
            dstChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void addMediaStore(Context context, File targetFile, String path) {

        ContentResolver resolver = context.getContentResolver();
        ContentValues newValues = new ContentValues(5);
        newValues.put(MediaStore.Images.Media.DISPLAY_NAME, targetFile.getName());
        newValues.put(MediaStore.Images.Media.DATA, targetFile.getPath());
        newValues.put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis() / 1000);
        newValues.put(MediaStore.Images.Media.SIZE, targetFile.length());
        newValues.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, newValues);
        MediaScannerConnection.scanFile(context, new String[]{path}, null, null);//刷新相册

    }
}
