package xyz.nobaday.figurebed.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Figure implements Serializable {

    private static final long serialVersionUID = -7301124438637128783L;

    private int id;
    private int userid;
    private String figurename;
    private String url;
    private double size;
    private Date uploadTime;

    @Override
    public String toString() {
        return "Figure{" +
                " id=" + id +
                " userid=" + userid +
                " figurename=" + figurename +
                " url=" + url +
                " size=" + size +
                " uploadTime=" + uploadTime +
                "}";
    }
}
