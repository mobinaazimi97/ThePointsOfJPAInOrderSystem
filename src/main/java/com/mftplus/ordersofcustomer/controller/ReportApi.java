package com.mftplus.ordersofcustomer.controller;

import com.mftplus.ordersofcustomer.dto.ReportSummary;
import com.mftplus.ordersofcustomer.entity.Report;
import com.mftplus.ordersofcustomer.service.ReportService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReportApi {

    @Inject
    private ReportService reportService;

    @GET
    public List<Report> getAllReports() {
        return reportService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getReportById(@PathParam("id") Long id) {
        Report report = reportService.findById(id);
        if (report == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(report).build();
    }

    @POST
    @Transactional
    @Path("/generate")
    public Response generateReport(@QueryParam("year") int year,
                                   @QueryParam("month") int month) {

        Report report = reportService.generateMonthlyReport(year, month);
        return Response.ok(report).build();
    }

    @GET
    @Path("/{id}/summary")
    public Response getReportSummary(@PathParam("id") Long id) {

        ReportSummary summary = reportService.getReportSummary(id);
        return Response.ok(summary).build();
    }

}
