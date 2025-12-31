package com.github.phoswald.sample.sample;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
@Path("/rest/sample")
public class SampleResource {

    @Inject
    @ConfigProperty(name = "app.sample.config")
    String sampleConfig;

    @GET
    @Path("/time")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTime() {
        String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        return Response.ok(now + "\n").build();
    }

    @GET
    @Path("/config")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getConfig() {
        return Response.ok(sampleConfig + "\n").build();
    }

    @POST
    @Path("/echo-xml")
    @Consumes(MediaType.TEXT_XML)
    @Produces(MediaType.TEXT_XML)
    public Response postEchoXml(EchoRequest request) {
        EchoResponse response = new EchoResponse();
        response.setOutput("Received " + request.getInput());
        return Response.ok(response).build();
    }

    @POST
    @Path("/echo-json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postEchoJson(EchoRequest request) {
        EchoResponse response = new EchoResponse();
        response.setOutput("Received " + request.getInput());
        return Response.ok(response).build();
    }
}
