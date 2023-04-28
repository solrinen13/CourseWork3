package my.works.courseworknumber3.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SockShipment {
    private Color color;
    private SockSize size;
    private int cottonPart;
    private int quantity;



    public String toLabelString() {
        return this.color + ";" + this.size + ";" + this.cottonPart + "%";
    }
}
