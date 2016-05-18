package com.messages.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.messages.core.Point;
import com.messages.db.PointDAO;

@Path("/points")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PointResource {

  private final PointDAO dao;

  public PointResource(PointDAO dao) {
    this.dao = dao;
    dao.createPointTableIfNotExists();
  }

  @GET
  public List<Point> getAllPoints() {
    return dao.getAllPoints();
  }

  @POST
  public long create(Point point) {
    long id = dao.insert(point.getX(), point.getY());
    return id;
  }

}
