/**
 * @author nae (Noah Ertz) - naertz
 * CIS-175 - Fall 2021
 * Nov 16, 2021
 */

package computerbuilding.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="customer_quote_request")
public class CustomerQuoteRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_quote_request_id")
	private long id;
	@NonNull
	@Column(name="first_name")
	private String firstName;
	@NonNull
	@Column(name="last_name")
	private String lastName;
	@NonNull
	@Column(name="email")
	private String email;
	@Column(name="phone_number")
	private String phoneNumber;
	@NonNull
	@Column(name="computer_needs_description")
	private String computerNeedsDescription;
	@Column(name="budget")
	private double budget;

	/*

	public CustomerQuoteRequest(String firstName, String lastName, String email, String phoneNumber, String computerNeedsDescription, double budget) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.computerNeedsDescription = computerNeedsDescription;
		this.budget = budget;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setComputerNeedsDescription(String computerNeedsDescription) {
		this.computerNeedsDescription = computerNeedsDescription;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getComputerNeedsDescription() {
		return computerNeedsDescription;
	}

	public double getBudget() {
		return budget;
	}

	@Override
	public String toString() {
		return "CustomerQuoteRequest [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", computerNeedsDescription=" + computerNeedsDescription + ", budget=" + budget + "]";
	}
	*/
}
