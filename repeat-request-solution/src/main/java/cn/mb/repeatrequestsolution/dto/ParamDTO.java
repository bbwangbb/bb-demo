package cn.mb.repeatrequestsolution.dto;

import java.time.LocalDate;

/**
 * <p>
 *  参数dto
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2020/11/20
 */
public class ParamDTO {

    private Long customerCarId;
    private LocalDate date;

    public Long getCustomerCarId() {
        return customerCarId;
    }

    public void setCustomerCarId(Long customerCarId) {
        this.customerCarId = customerCarId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
