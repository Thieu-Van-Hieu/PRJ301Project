/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ngoct
 */
public class SearchEventDTO {

    private String date;
    private String type;
    private String search;
    private String trangthai;

    public SearchEventDTO(String date, String type, String search) {
        this.date = date;
        this.type = type;
        this.search = search;
    }

    public SearchEventDTO() {
    }

    public SearchEventDTO(String date, String type, String search, String trangthai) {
        this.date = date;
        this.type = type;
        this.search = search;
        this.trangthai = trangthai;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

}
