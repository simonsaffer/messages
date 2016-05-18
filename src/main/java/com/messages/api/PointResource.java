package com.messages.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.common.collect.ImmutableList;
import com.messages.core.Point;
import com.messages.db.PointDAO;

@Path("/points")
@Produces(MediaType.APPLICATION_JSON)
public class PointResource {

  private final PointDAO dao;

  public PointResource(PointDAO dao) {
    this.dao = dao;
  }

  @GET
  public List<Point> getAllPoints() {
    return ImmutableList.of();
  }
}
