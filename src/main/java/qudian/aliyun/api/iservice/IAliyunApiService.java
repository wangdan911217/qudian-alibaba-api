package qudian.aliyun.api.iservice;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by suzunshou on 16/9/16.
 */
public interface IAliyunApiService {
    /**
     * 将base64字符串通过输入流传入,并上传到OSS
     *
     * @param inputStream 必须传入纯文本,防止被截断
     * @return
     * @throws IOException
     */
    String transferInputStreamToAliyunOSS(InputStream inputStream) throws IOException;

    /**
     * 将base64字符串通过输入流传入,并上传到OSS,返回的图片链接带有拓展名
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    String transferInputStreamToAliyunOSSWithSuffix(InputStream inputStream) throws IOException;

    /**
     * 上传字符数组文件到阿里云oss
     *
     * @param bytes 字符数组,可参考自定义的AliyunUtils#getBytesFromBase64(java.lang.String)方法
     * @param meta  content-type
     *              可参考自定义的
     *              AliyunUtils#getMetaFromFileBase64Str(java.lang.StringBuilder)方法
     * @return
     * @throws IOException
     */
    String transferByteArrayToAliyunOSS(byte[] bytes, String meta) throws IOException;

    /**
     * 上传字符数组文件到阿里云oss,返回的图片链接带有拓展名
     *
     * @param bytes 字符数组,可参考自定义的AliyunUtils#getBytesFromBase64(java.lang.String)方法
     * @param meta  content-type
     *              可参考自定义的
     *              AliyunUtils#getMetaFromFileBase64Str(java.lang.StringBuilder)方法
     * @return
     * @throws IOException
     */
    String transferByteArrayToAliyunOSSWithSuffix(byte[] bytes, String meta) throws IOException;

}
