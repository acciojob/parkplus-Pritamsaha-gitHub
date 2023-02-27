package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
//        Payment payment=new Payment();
//        Reservation reservation=reservationRepository2.findById(reservationId).get();
//        int hour=reservation.getNumberOfHours();
//        Spot spot=reservation.getSpot();
//        int mul=spot.getPricePerHour();
//        int total=hour*mul;
//        if (amountSent<total){
//            throw new Exception("Insufficient Amount");
//        }
//        mode=mode.toUpperCase();
//        if((!mode.equals(PaymentMode.CARD)) ||(!mode.equals(PaymentMode.UPI)) || (!mode.equals(PaymentMode.CASH))){
//            throw new Exception("Payment mode not detected");
//        }
//        if(mode.equals(PaymentMode.CARD)){
//            payment.setPaymentMode(PaymentMode.CARD);
//        }else if(mode.equals(PaymentMode.UPI)){
//            payment.setPaymentMode(PaymentMode.UPI);
//        }else if(mode.equals(PaymentMode.CASH)){
//            payment.setPaymentMode(PaymentMode.CASH);
//        }
//        else{
//            throw new Exception("Payment mode not detected");
//        }
//        payment.setPaymentCompleted(true);
//        payment.setReservation(reservation);
//        reservation.setPayment(payment);
//        reservation.getSpot().setOccupied(true);
//        reservationRepository2.save(reservation);
//        return payment;
        Reservation reservation = reservationRepository2.findById(reservationId).get();
        Payment payment = new Payment();  //This is throwing null because i didn't set payment  while adding eveything in payment
        String payment1 = "CASH",payment2 = "CARD",payment3 = "UPI";
        mode = mode.toUpperCase();
        if(reservation.getNumberOfHours() * reservation.getSpot().getPricePerHour() > amountSent){
            throw new Exception("Insufficient Amount");
        }
        if(mode.equals(payment1)){
            payment.setPaymentMode(PaymentMode.CASH);
        }
        else if(mode.equals(payment2))  {
            payment.setPaymentMode(PaymentMode.CARD);
        }
        else if (mode.equals(payment3)){
            payment.setPaymentMode(PaymentMode.UPI);
        }
        else{
            throw new Exception("Payment mode not detected");
        }
        payment.setPaymentCompleted(true);
        payment.setReservation(reservation);
        reservation.setPayment(payment);
        reservation.getSpot().setOccupied(true);
        reservationRepository2.save(reservation);
        return payment;
    }
}
