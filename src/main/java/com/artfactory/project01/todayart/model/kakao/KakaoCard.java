package com.artfactory.project01.todayart.model.kakao;

import lombok.Data;

@Data
public class KakaoCard {
    private String purchase_corp, purchase_corp_code;
    private String issuer_corp, issuer_corp_code;
    private String bin, cart_type, install_month, approved_id, card_mid;
    private String interest_free_install, card_item_code;
}
