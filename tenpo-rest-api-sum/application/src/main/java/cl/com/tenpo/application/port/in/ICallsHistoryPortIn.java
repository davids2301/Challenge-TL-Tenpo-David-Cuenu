package cl.com.tenpo.application.port.in;

import cl.com.tenpo.domain.configuration.annotations.PortIn;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.model.CallsHistoryModel;

import java.util.List;

@PortIn
public interface ICallsHistoryPortIn
{
    /**
     * Method in charge of return calls history paginated
     * @param pageNumber, Page number where user wish locate
     * @param numberOfRecords, Amount records wish by api that call the backend
     * @param descendingOrder, Wish sort records in order descending
     * @param sortBy, By which field do you want to sort the records
     * @author David Cuenu
     */
    List<CallsHistoryModel> callsHistoryPaginated(Integer pageNumber, Integer numberOfRecords, Boolean descendingOrder, String sortBy) throws TenpoException;
}