package cl.com.tenpo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CALLS_HISTORY", schema = "public")
public class CallsHistoryEntity implements Serializable
{
	@Id
	@SequenceGenerator(name = "CALLS_HISTORY_SEQ", sequenceName = "CALLS_HISTORY_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "CALLS_HISTORY_SEQ")
	@Column(name="CALLS_HISTORY_ID")
	private Integer id;

	@Column(name = "REQUEST")
	private String request;

	@Column(name = "RESPONSE")
	private String response;

	@Column(name = "DATE_AND_HOUR")
	private LocalDateTime dateAndHour;

	@PrePersist
	private void assignDateAndHour(){
		this.dateAndHour=LocalDateTime.now();
	}
}