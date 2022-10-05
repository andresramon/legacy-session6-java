package com.cokaido.shoppingcart.domain.base;

import java.util.UUID;

public class Id {

    private UUID id;

    public Id()
    {
        this.id = UUID.randomUUID();
    }
    public Id(UUID id) {
        this.id = id;
    }
}
