package qudian.aliyun.api.services;

import com.aliyun.oss.model.ObjectMetadata;
import qudian.aliyun.api.config.AliyunApiConfig;
import qudian.aliyun.api.iservice.IAliyunApiService;
import qudian.aliyun.api.utils.AliyunUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by suzunshou on 16/9/16.
 */
public class AliyunServiceImpl implements IAliyunApiService {

    private AliyunApiConfig aliyunApiConfig;

    public void setAliyunApiConfig(AliyunApiConfig aliyunApiConfig) {
        this.aliyunApiConfig = aliyunApiConfig;
    }

    /**
     * 将base64字符串通过输入流传入,并上传到OSS
     *
     * @param inputStream 必须传入纯文本,防止被截断
     * @return
     * @throws IOException
     */
    public String transferInputStreamToAliyunOSS(InputStream inputStream) throws IOException {
        StringBuilder fileBase64Str = AliyunUtils.getFileBase64StrFromInputStream(inputStream);
        String meta = AliyunUtils.getMetaFromFileBase64Str(fileBase64Str);
        StringBuilder imageBase64Str = AliyunUtils.getImageBase64StrFromFileBase64Str(fileBase64Str);
        byte[] bytes = AliyunUtils.getBytesFromBase64(imageBase64Str);
        return transferByteArrayToAliyunOSS(bytes, meta);
    }

    /**
     * 将base64字符串通过输入流传入,并上传到OSS,返回的图片链接带有拓展名
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public String transferInputStreamToAliyunOSSWithSuffix(InputStream inputStream) throws IOException {
        StringBuilder fileBase64Str = AliyunUtils.getFileBase64StrFromInputStream(inputStream);
        String meta = AliyunUtils.getMetaFromFileBase64Str(fileBase64Str);
        StringBuilder imageBase64Str = AliyunUtils.getImageBase64StrFromFileBase64Str(fileBase64Str);
        byte[] bytes = AliyunUtils.getBytesFromBase64(imageBase64Str);
        return transferByteArrayToAliyunOSSWithSuffix(bytes, meta);
    }

    /**
     * 上传字符数组文件到阿里云oss
     *
     * @param bytes 字符数组
     * @param meta  content-type
     * @return
     * @throws IOException
     */
    public String transferByteArrayToAliyunOSS(byte[] bytes, String meta) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(meta);
        String osskey = UUID.randomUUID().toString().replaceAll("-", "");
        aliyunApiConfig.getOssClient().putObject(aliyunApiConfig.getBucketName(), osskey, new ByteArrayInputStream(bytes), metadata);
        return aliyunApiConfig.getFileUrlPrefix() + osskey;
    }

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
    public String transferByteArrayToAliyunOSSWithSuffix(byte[] bytes, String meta) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(meta);
        String osskey = UUID.randomUUID().toString().replaceAll("-", "") + AliyunUtils.getSuffixFromMeta(meta);
        aliyunApiConfig.getOssClient().putObject(aliyunApiConfig.getBucketName(), osskey, new ByteArrayInputStream(bytes), metadata);
        return aliyunApiConfig.getFileUrlPrefix() + osskey;
    }
}
