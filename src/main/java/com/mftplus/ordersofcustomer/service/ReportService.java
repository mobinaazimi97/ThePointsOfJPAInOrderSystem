package com.mftplus.ordersofcustomer.service;

import com.mftplus.ordersofcustomer.dto.ReportSummary;
import com.mftplus.ordersofcustomer.entity.Report;
import com.mftplus.ordersofcustomer.serviceAbstract.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestScoped
public class ReportService implements Service<Report, Long> {

    @PersistenceContext(unitName = "mft")
    private EntityManager em;

    @Transactional
    public Report generateMonthlyReport(int year, int month) {

        Report report = new Report();
        report.setYear(year);
        report.setMonth(month);
        report.setGeneratedAt(LocalDateTime.now());

        em.persist(report);

        return report;
    }

    @Override
    public void save(Report report) {
        em.persist(report);
    }

    @Override
    public void edit(Report report) {
        em.persist(report);
    }

    @Override
    public void remove(Long id) {
        em.remove(em.find(Report.class, id));
    }

    @Transactional
    @Override
    public Report findById(Long id) {
        return em.find(Report.class, id);
    }

    @Override
    public List<Report> findAll() {
        return em.createQuery(
                        "SELECT r FROM reportEntity r ORDER BY r.year DESC, r.month DESC", Report.class)
                .getResultList();
    }

    public Long getOrderCount(Report report) {
        LocalDate from = LocalDate.of(report.getYear(), report.getMonth(), 1);
        LocalDate to = from.plusMonths(1);

        return em.createQuery(
                        "SELECT COUNT(o) FROM orderEntity o WHERE o.createdAt >= :from AND o.createdAt < :to",
                        Long.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getSingleResult();
    }

    @Transactional
    public Double getTotalSales(Report report) {
        LocalDateTime from = LocalDateTime.of(report.getYear(), report.getMonth(), 1, 0, 0, 0);
        LocalDateTime to = from.plusMonths(1);

        Double result = em.createQuery(
                        "SELECT SUM(oi.quantity * oi.price) FROM orderItemEntity oi " +
                                "WHERE oi.order.createdAt >= :from AND oi.order.createdAt < :to",
                        Double.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getSingleResult();

        return result != null ? result : 0.0;
    }

    @Transactional
    public ReportSummary getReportSummary(Long reportId) {
        Report report = em.find(Report.class, reportId);
        if (report == null) {
            return null;
        }

        LocalDateTime from = LocalDateTime.of(report.getYear(), report.getMonth(), 1, 0, 0, 0);
        LocalDateTime to = from.plusMonths(1);

        em.flush();

        Long orderCount = em.createQuery(
                        "SELECT COUNT(o) FROM orderEntity o WHERE o.createdAt >= :from AND o.createdAt < :to",
                        Long.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getSingleResult();

        Double totalSales = em.createQuery(
                        "SELECT SUM(oi.quantity * oi.price) FROM orderItemEntity oi " +
                                "WHERE oi.order.createdAt >= :from AND oi.order.createdAt < :to",
                        Double.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getSingleResult();

        if (totalSales == null) totalSales = 0.0;

        return new ReportSummary(orderCount, totalSales);
    }

}
