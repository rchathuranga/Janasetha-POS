package lk.janasetha.thogakade.repository.custom;

import lk.janasetha.thogakade.model.Batch;
import lk.janasetha.thogakade.repository.CrudDAO;

import java.sql.Date;
import java.util.List;

public interface BatchDAO extends CrudDAO<Batch,Integer> {
    public List<Batch> getBatchesByDate(Date date) throws Exception;

    public List<Batch> getBatchesForView() throws Exception;
}
