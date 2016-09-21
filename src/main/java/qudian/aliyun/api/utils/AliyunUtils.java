package qudian.aliyun.api.utils;


import org.apache.commons.codec.binary.Base64;
import qudian.alipay.api.utils.StringUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by suzunshou on 16/9/16.
 */
public class AliyunUtils {
    /**
     * 获取图片文件的base64字符串 如:data:image/png;base64,iVBORw0KG..
     *
     * @param inputStream 输入流
     * @return
     * @throws IOException
     */
    public static StringBuilder getFileBase64StrFromInputStream(InputStream inputStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] bytes = new byte[2048];
        int n;
        while ((n = bis.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, n);
        }

        bis.close();
        inputStream.close();
        return new StringBuilder(new String(byteArrayOutputStream.toByteArray()));
    }

    /**
     * 截取meta信息,内容为;base64,之前,data:之后的部分如:image/png
     *
     * @param fileBase64Str 图片转换成的base64字符串
     * @return
     */
    public static String getMetaFromFileBase64Str(StringBuilder fileBase64Str) {
        return fileBase64Str.substring(fileBase64Str.indexOf(":") + 1, fileBase64Str.indexOf(";"));
    }

    /**
     * 截取文件部分信息,内容为base64,之后的部分
     *
     * @param fileBase64Str 图片转换成的base64字符串
     * @return
     */
    public static StringBuilder getImageBase64StrFromFileBase64Str(StringBuilder fileBase64Str) {
        return fileBase64Str.delete(0, fileBase64Str.indexOf(",") + 1);
    }

    /**
     * 将文件部分的base64串转为byte[]
     *
     * @param imageBase64Str 内容为base64,之后的部分
     * @return
     */
    public static byte[] getBytesFromBase64(StringBuilder imageBase64Str) {
        return Base64.decodeBase64(imageBase64Str.toString());
    }

    /**
     * 从meta获取拓展名
     *
     * @param meta
     * @return
     */
    public static String getSuffixFromMeta(String meta) {
        if (StringUtils.isNotBlank(meta)) {
            return "." + meta.substring(meta.indexOf("/") + 1);
        }
        return null;
    }

}
