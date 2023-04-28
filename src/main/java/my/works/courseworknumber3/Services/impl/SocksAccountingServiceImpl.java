package my.works.courseworknumber3.Services.impl;

import lombok.Data;
import my.works.courseworknumber3.Model.Color;
import my.works.courseworknumber3.Model.SockSize;
import my.works.courseworknumber3.Services.SocksAccountingService;
import my.works.courseworknumber3.Model.SockShipment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class SocksAccountingServiceImpl implements SocksAccountingService {
    private Map <String, SockShipment> storageMap = new HashMap<>();

//    @PostConstruct
//    private void init(){
//        readFromFile();
//    }
    @Override
    public void addSocks(SockShipment newShipment) {
       String stringKey = newShipment.toLabelString();
        var storageShipment = storageMap.get(stringKey);
        if (newShipment.getQuantity() < 0 || newShipment.getCottonPart() < 0 || newShipment.getCottonPart() > 100){
            throw new RuntimeException();
        }
        if (storageShipment == null) {
            storageMap.put(stringKey,newShipment);

        } else {
            storageShipment.setQuantity(storageShipment.getQuantity()+newShipment.getQuantity());
        }
    }

    @Override
    public Integer getSocksQuantity(Color color, SockSize size, Integer cottonMin, Integer cottonMax){
        var stream = storageMap.values().stream();
        if (size != null ) {
            stream = stream.filter(ship -> ship.getSize() == size);
        }
        if (color != null) {
            stream = stream.filter(ship -> color.equals(ship.getColor()));
        }
        if (cottonMin != null ) {
            if (cottonMin < 0 || cottonMin > 100){
                throw new RuntimeException();
            }
            stream = stream.filter(ship -> ship.getCottonPart() >= cottonMin );
        }
        if ( cottonMax != null ) {
            if (cottonMax < 0 || cottonMax > 100 ){
                throw new RuntimeException();
            }
            stream = stream.filter(ship ->  ship.getCottonPart() <= cottonMax);
        }
        return stream.map(ship -> ship.getQuantity()).reduce((a, b) -> a + b).get();
    }

    @Override
    public void putSocks(SockShipment sockShipment) {
        String stringKey = sockShipment.toLabelString();
        var storageShipment = storageMap.get(stringKey);
        if (storageShipment == null || storageShipment.getQuantity() < sockShipment.getQuantity() || sockShipment.getQuantity() < 0) {
            throw new RuntimeException();
        } else
            storageShipment.setQuantity(storageShipment.getQuantity() - sockShipment.getQuantity());
    }

    @Override
    public void deleteSocks(SockShipment sockShipment) {
        String stringKey = sockShipment.toLabelString();
        var storageShipment = storageMap.get(stringKey);
        if (storageShipment == null || storageShipment.getQuantity() < sockShipment.getQuantity() || sockShipment.getQuantity() < 0) {
            throw new RuntimeException();
        } else
            storageShipment.setQuantity(storageShipment.getQuantity() - sockShipment.getQuantity());
    }

}
