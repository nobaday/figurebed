package xyz.nobaday.figurebed.config;

import com.aliyun.oss.OSSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.nobaday.figurebed.entity.OssProperties;

@Configuration
@ConditionalOnClass({OSSClient.class})
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

    private final OssProperties ossProperties;

    public OssAutoConfiguration (final  OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    @Bean
    public OssClientFactoryBean ossClientFactoryBean() {
        final OssClientFactoryBean factoryBean = new OssClientFactoryBean();
        factoryBean.setEndpoint(this.ossProperties.getEndpoint());
        factoryBean.setAccessKeyId(this.ossProperties.getAccessKeyId());
        factoryBean.setAccessKeySecret(this.ossProperties.getAccessKeySecret());
        return factoryBean;
    }

}
