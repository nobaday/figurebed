package xyz.nobaday.figurebed.config;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class OssClientFactoryBean implements FactoryBean<OSSClient>, InitializingBean, DisposableBean {

    private OSSClient ossClient;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    @Override
    public void destroy() throws Exception {
        if (this.ossClient != null) {
            this.ossClient.shutdown();
        }
    }

    @Override
    public OSSClient getObject() throws Exception {
        return this.ossClient;
    }

    @Override
    public Class<?> getObjectType() {
        return ossClient.getClass();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.endpoint, "endpoint can't be null");
        Assert.notNull(this.accessKeyId, "accessKeyId can't be null");
        Assert.notNull(this.accessKeySecret, "accessKeySecret can't be null");
        this.ossClient = (OSSClient) new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public void setAccessKeyId(final String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setAccessKeySecret(final String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
