package qudian.aliyun.api.config;

import com.aliyun.oss.OSSClient;

/**
 * Created by suzunshou on 16/9/16.
 */
public class AliyunApiConfig {

    /**
     * 阿里云oss客户端
     */
    private OSSClient ossClient;
    /**
     * 存储空间名称
     */
    private String bucketName;
    /**
     * 上传成功后,文件在阿里云服务器访问地址的前缀
     * 如:http://bucketName.oss-cn-hangzhou.aliyuncs.com/   按原图输出
     * 或者:http://bucketName.img-cn-hangzhou.aliyuncs.com/
     * 可对图片指定规则输出,如高100 宽100 绝对质量80,格式jpg
     * http://bucketName.img-cn-hangzhou.aliyuncs.com/图片名称@100h_100w_80q.jpg
     */
    private String fileUrlPrefix;

    public AliyunApiConfig() {

    }

    public AliyunApiConfig(OSSClient ossClient, String bucketName, String fileUrlPrefix) {
        this.ossClient = ossClient;
        this.bucketName = bucketName;
        this.fileUrlPrefix = fileUrlPrefix;
    }

    public OSSClient getOssClient() {
        return ossClient;
    }

    public void setOssClient(OSSClient ossClient) {
        this.ossClient = ossClient;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileUrlPrefix() {
        return fileUrlPrefix;
    }

    public void setFileUrlPrefix(String fileUrlPrefix) {
        this.fileUrlPrefix = fileUrlPrefix;
    }
}
