package lucavigano.deliveryapp.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lucavigano.deliveryapp.DTO.PaymentResponse;
import lucavigano.deliveryapp.entities.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    public PaymentResponse createPaymentLink(Order order) throws StripeException {
        Stripe.apiKey=stripeSecretKey;
        SessionCreateParams params= SessionCreateParams.builder().addPaymentMethodType(
                SessionCreateParams.
                        PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment/success/"+order.getId())
                .setCancelUrl("http://localhost:3000/payment/fail")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("eur")
                                .setUnitAmount((long) order.getTotalAmmount()*100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Click Food")
                                        .build())
                                .build()
                        )
                        .build()
                ).build();

        Session session=Session.create(params);
        PaymentResponse response=new PaymentResponse();
        response.setPayment_url(session.getUrl());

        return response;
    }
}
