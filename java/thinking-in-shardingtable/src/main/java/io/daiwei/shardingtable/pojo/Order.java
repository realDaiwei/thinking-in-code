package io.daiwei.shardingtable.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Daiwei on 2021/3/12
 */
@Data
@AllArgsConstructor
@ToString
public class Order {

    private Long orderId;

    private Long userId;

    private Long transferId;

    public Order(Long userId, Long transferId) {
        this.userId = userId;
        this.transferId = transferId;
    }
}
