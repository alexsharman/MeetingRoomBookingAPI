package com.conference.api.domain;


import static javax.persistence.TemporalType.DATE;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>
 * <strong>Booking</strong> is the model/entity class that represents a hotel
 * booking.
 * </p>
 *
 * @author Gavin King
 * @author Dan Allen
 */
@Entity
@Table(name = "tbl_bookings")
public class Booking {

    public static final String BY_USERNAME = "Booking.byUsername";

    private Long id;
    private User user;
    private Room room;
    private Date checkinDate;
    private Date checkoutDate;
    private String creditCardNumber;
//    private CreditCardType creditCardType;
    private String creditCardName;
    private int creditCardExpiryMonth;
    private int creditCardExpiryYear;
    private boolean smoking;
    private int beds;

    public Booking() {
        super();
    }

    public Booking(Room room, User user, int daysFromNow, int nights) {
        super();
        this.room = room;
        this.user = user;
//        this.creditCardName = user.getFullname();
        this.smoking = false;
        this.beds = 1;
        setReservationDates(daysFromNow, nights);
        creditCardExpiryMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Temporal(DATE)
    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date datetime) {
        this.checkinDate = datetime;
    }

    @NotNull
    @ManyToOne
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @NotNull
    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    // @Size(min = 1, max = 3)
    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    @NotNull(message = "Credit card number is required")
    @Size(min = 16, max = 16, message = "Credit card number must 16 digits long")
    @Pattern(regexp = "^\\d*$", message = "Credit card number must be numeric")
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

//    @NotNull(message = "Credit card type is required")
//    @Enumerated(EnumType.STRING)
//    public CreditCardType getCreditCardType() {
//        return creditCardType;
//    }
//
//    public void setCreditCardType(CreditCardType creditCardType) {
//        this.creditCardType = creditCardType;
//    }

    @NotNull(message = "Credit card name is required")
    @Size(min = 3, max = 70, message = "Credit card name is required")
    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    /**
     * The credit card expiration month, represented using a 1-based numeric
     * value (i.e., Jan = 1, Feb = 2, ...).
     *
     * @return 1-based numeric month value
     */
    public int getCreditCardExpiryMonth() {
        return creditCardExpiryMonth;
    }

    public void setCreditCardExpiryMonth(int creditCardExpiryMonth) {
        this.creditCardExpiryMonth = creditCardExpiryMonth;
    }

    /**
     * The credit card expiration year.
     *
     * @return numberic year value
     */
    public int getCreditCardExpiryYear() {
        return creditCardExpiryYear;
    }

    public void setCreditCardExpiryYear(int creditCardExpiryYear) {
        this.creditCardExpiryYear = creditCardExpiryYear;
    }

//    @Transient
//    public String getDescription() {
//        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
//        return room == null ? null : room.getName() + ", " + df.format(getCheckinDate()) + " to "
//                + df.format(getCheckoutDate());
//    }
//
//    @Transient
//    public BigDecimal getTotal() {
//        return room.getPrice().multiply(new BigDecimal(getNights()));
//    }

    @Transient
    public int getNights() {
        return (int) (checkoutDate.getTime() - checkinDate.getTime()) / 1000 / 60 / 60 / 24;
    }

    /**
     * Initialize the check-in and check-out dates.
     *
     * @param daysFromNow Number of days the stay will begin from now
     * @param nights      Length of the stay in number of nights
     */
    public final void setReservationDates(int daysFromNow, int nights) {
        Calendar refDate = Calendar.getInstance();
        refDate.set(refDate.get(Calendar.YEAR), refDate.get(Calendar.MONTH), refDate.get(Calendar.DAY_OF_MONTH)
                + daysFromNow, 0, 0, 0);
        this.checkinDate = refDate.getTime();
        refDate.add(Calendar.DAY_OF_MONTH, nights);
        this.checkoutDate = refDate.getTime();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Booking other = (Booking) obj;

        if ((this.id == null) ? (other.getId() != null) : !this.id.equals(other.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (this.id == null ? 0 : this.id.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Booking(" + user + ", " + room + ")";
    }
}

