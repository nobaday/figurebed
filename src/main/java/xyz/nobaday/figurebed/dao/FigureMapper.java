package xyz.nobaday.figurebed.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.nobaday.figurebed.entity.Figure;

import java.util.List;

@Mapper
public interface FigureMapper {

    int insertFigure(Figure figure);

    Figure findById(int id);

    List<Figure> selectImage(int offset, int limit);

    int selectFigureRows();

}
