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
import com.messages.kafka.PointProducer;

@Path("/points")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PointResource {

  private final PointDAO dao;
  private final PointProducer producer;

  public PointResource(PointDAO dao, PointProducer pointProducer) {
    this.dao = dao;
    this.producer = pointProducer;
    dao.createPointTableIfNotExists();
  }

  @GET
  public List<Point> getAllPoints() {
    return dao.getAllPoints();
  }

  @POST
  public void create(Point point) {
    producer.addNewPoint(point);
  }

}
