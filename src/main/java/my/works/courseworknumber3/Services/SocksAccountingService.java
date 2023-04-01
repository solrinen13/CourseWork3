package my.works.courseworknumber3.Services;

import my.works.courseworknumber3.Model.Color;
import my.works.courseworknumber3.Model.SockShipment;
import my.works.courseworknumber3.Model.SockSize;

public interface SocksAccountingService {


    void addSocks(SockShipment newShipment);

    Integer getSocksQuantity(Color color, SockSize size, Integer cottonMin, Integer cottonMax);

    void putSocks(SockShipment sockShipment);

    void deleteSocks(SockShipment sockShipment);
}
