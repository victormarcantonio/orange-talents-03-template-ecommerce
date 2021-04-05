package com.orange.mercadolivre.cadastroImagem;

import javax.validation.constraints.NotBlank;

public class ImagemRequest {

    @NotBlank
    private String link;

    public String getLink() {
        return link;
    }
}
