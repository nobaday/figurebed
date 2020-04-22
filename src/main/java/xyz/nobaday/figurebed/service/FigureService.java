package xyz.nobaday.figurebed.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.nobaday.figurebed.dao.FigureMapper;
import xyz.nobaday.figurebed.entity.Figure;
import xyz.nobaday.figurebed.entity.OssProperties;
import xyz.nobaday.figurebed.util.GeneralUtils;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

@Service
@EnableConfigurationProperties(OssProperties.class)
public class FigureService {

    private static final Logger logger = LoggerFactory.getLogger(FigureService.class);

    @Autowired
    private FigureMapper figureMapper;

    @Autowired
    private OssProperties ossProperties;

    @Autowired
    private OSSClient ossClient;

    public String upLoad(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String suffix = originalName.substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = GeneralUtils.generateUUID() + suffix;

        try {
            ossClient.putObject(new PutObjectRequest(ossProperties.getBucketName(), fileName, new ByteArrayInputStream(file.getBytes())));
        } catch (Exception e) {
            logger.error(e.toString());
        } finally {
            ossClient.shutdown();
        }

        String url = "http://" + ossProperties.getBucketName() + "." + ossProperties.getEndpoint() + "/" + fileName;
        Figure figure = new Figure();
        figure.setUserid(1);
        figure.setFigurename(originalName);
        figure.setUrl(url);
        figure.setSize(file.getSize());
        figure.setUploadTime(new Date());

        figureMapper.insertFigure(figure);

        return url;
    }

    public int totalImages() {
        return figureMapper.selectFigureRows();
    }

    public List<Figure> pageQuery(int offset, int limit) {
        return figureMapper.selectImage(offset, limit);
    }

}
