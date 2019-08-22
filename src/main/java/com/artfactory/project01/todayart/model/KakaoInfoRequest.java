package com.artfactory.project01.todayart.model;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoInfoRequest {
    private String aid, tid, cid, sid;
    private String partner_order_id, partner_user_id, payment_method_type;
    private Amount amount;
    private Card card_info;
    private String item_name, item_code, payload;
    private Integer quantity, tax_free_amount, vat_amount;
    private Date created_at, approved_at;
}



@Data
class Amount{
    private Integer total, tax_free, vat, point, discount;
}

@Data
class Card{
    private String purchase_corp, purchase_corp_code;
    private String issuer_corp, issuer_corp_code;
    private String bin, cart_type, install_month, approved_id, card_mid;
    private String interest_free_install, card_item_code;
}

