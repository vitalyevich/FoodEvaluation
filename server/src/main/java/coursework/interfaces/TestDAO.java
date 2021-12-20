package coursework.interfaces;

import coursework.models.Measuring;
import coursework.models.Organoleptic;
import coursework.models.Servqual;

import java.util.List;

public interface TestDAO {

    public void saveMeasuringTest(Measuring measurings);
    public void saveOrganolepticTest(Organoleptic organoleptics);
    public void saveServqualTest(Servqual servquals);

    public List<Measuring> viewMeasuringTest(String productName);
    public List<Organoleptic> viewOrganolepticTest(String productName);
    public List<Servqual> viewServqualTest(String productName);
}
