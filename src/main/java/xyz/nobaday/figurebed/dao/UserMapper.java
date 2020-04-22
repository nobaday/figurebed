package xyz.nobaday.figurebed.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.nobaday.figurebed.entity.User;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User findByUsername(String username);

}
