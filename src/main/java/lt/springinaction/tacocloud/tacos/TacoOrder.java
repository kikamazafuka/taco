package lt.springinaction.tacocloud.tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table
public class TacoOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private Date placedAt = new Date();

    @NotBlank(message = "enter Name")
    private String deliveryName;

    @NotBlank(message = "enter Street")
    private String deliveryStreet;

    @NotBlank(message = "enter City")
    private String deliveryCity;

    @NotBlank(message = "enter State")
    private String deliveryState;

    @NotBlank(message = "enter ZIP")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        tacos.add(taco);
    }
}
