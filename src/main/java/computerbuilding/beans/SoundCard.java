package computerbuilding.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sound_card")
public class SoundCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sound_card_id")
	private long id;
	@NonNull
	@Column(name = "name")
	private String name;
	@Column(name = "bit_rate")
	private double bit_rate;
	@Column(name = "kHz")
	private double kHz;
	@Column(name = "max_db")
	private int max_db;
	@Column(name = "max_channels")
	private float max_channels;
	@Column(name = "price")
	private float price;
	@OneToMany(targetEntity = CustomerQuoteFulfillment.class, mappedBy = "soundCard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerQuoteFulfillment> customerQuoteFulfillments;
}
	
	

