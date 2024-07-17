package challengeliteralura.com.br.Challenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Format {

    @JsonProperty("text/html")
    private String html;

    @JsonProperty("application/epub+zip")
    private String epub;

    @JsonProperty("application/x-mobipocket-ebook")
    private String mobi;

    @JsonProperty("text/plain")
    private String plainText;

    // Getters e setters

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}