package com.Library.LibrarySystem.dto.Library;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderBookDTO {
    private Date returnedDate;
    private Long customerId;
    private String customerName;
    private List<DetailOrderDTO> detailOrderDTOS;

    @Data
    public static class DetailOrderDTO {
        private Long bookId;
        private Long detailQuantity;
        private int price; //book price
    }
}
