package xyz.nobaday.figurebed.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = -704810741322320448L;

    private int id;
    private String username;
    private String password;
    private String permission;
    private Date creatTime;

    @Override
    public String toString() {
        return "User{" +
                " id=" + id +
                " username=" + username +
                " password=" + password +
                " permission=" + permission +
                " creaTime=" + creatTime +
                "}";
    }
}
