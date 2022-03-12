package com.book.mcsv.shopping.repository;

import com.book.mcsv.shopping.dto.ShopReportDTO;
import com.book.mcsv.shopping.model.Shop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(LocalDateTime dataInicio, LocalDateTime dataFim, Float valorMinimo) {

        StringBuilder sb = new StringBuilder();
        sb.append("select s ");
        sb.append("from shop s ");
        sb.append("where s.date >= :dataInicio ");
        if (dataFim != null) {
            sb.append("and s.date <= :dataFim ");
        }
        if (valorMinimo != null) {
            sb.append("and s.total >= :valorMinimo ");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("dataInicio", dataInicio);
        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }
        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }

        return query.getResultList();

    }

    @Override
    public ShopReportDTO getReportByDate(LocalDateTime dataInicio, LocalDateTime dataFim) {

        StringBuilder sb = new StringBuilder();
        sb.append("select count(sp.id), avg(sp.total), sum(sp.total) ");
        sb.append("from shopping.shop sp ");
        sb.append("where sp.date >= :dataInicio ");
        sb.append("and sp.date <= :dataFim ");
        Query query = entityManager.createNativeQuery(sb.toString());

        System.out.println("IMPRIMINDO RESULTS :::::>>>>>>> " + sb.toString());

        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);

        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((BigInteger) result[0]).intValue());
        shopReportDTO.setMean((Double) result[1]);
        shopReportDTO.setTotal((Double) result[2]);

        return shopReportDTO;

    }
}
