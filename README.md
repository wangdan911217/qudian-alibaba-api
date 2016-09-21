##Qudian Alibaba API

对支付宝授权登录、获取用户信息、获取学生信息接口、阿里云oss图片上传进行了简单的封装。

>支付宝
 
1. 配置类

		qudian.alipay.api.config.AlipayApiConfig
		com.alipay.api.AlipayClient
		
2. 异常类
			
		qudian.alipay.api.exceptions.OnlineRuntimeException 
		
3. 工具类
	
		qudian.alipay.api.utils.AlipayUtils
		qudian.alipay.api.utils.StringUtils
		
4. 接口类
		
		qudian.alipay.api.iservice.IAlipayApiService
		qudian.alipay.api.iservice.IAlipayLocator
		
5. 实现类
	
		qudian.alipay.api.services.AlipayApiService
		qudian.alipay.api.services.AlipayLocatorService

>阿里云

1. 配置类
	
		qudian.aliyun.api.config.AliyunApiConfig
		com.aliyun.oss.OSSClient
		
2. 工具类
		
		qudian.aliyun.api.utils.AliyunUtils
		
3. 接口类
		
		qudian.aliyun.api.iservice.IAliyunApiService
		
4. 实现类

		qudian.aliyun.api.services.AliyunService