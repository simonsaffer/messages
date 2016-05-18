package com.messages.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.messages.core.Point;

public class PointMapper implements ResultSetMapper<Point> {

  @Override
  public Point map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
    return new Point(resultSet.getInt("x"), resultSet.getInt("y"));
  }

}
