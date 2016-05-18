package com.messages.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.messages.core.Point;

public interface PointDAO {
  @SqlUpdate("CREATE TABLE IF NOT EXISTS POINT(ID BIGINT PRIMARY KEY AUTO_INCREMENT, X INT NOT NULL, Y INT NOT NULL);")
  void createPointTableIfNotExists();

  @GetGeneratedKeys
  @SqlUpdate("INSERT INTO POINT (x, y) values (:x, :y)")
  long insert(@Bind("x") int x, @Bind("y") int y);

  @SqlQuery("SELECT x, y FROM POINT;")
  @Mapper(PointMapper.class)
  List<Point> getAllPoints();
}
