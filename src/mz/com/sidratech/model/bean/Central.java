package mz.com.sidratech.model.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author celso
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "central")
public class Central extends Entidade implements Serializable {


}