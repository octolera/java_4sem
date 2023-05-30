package com.example.javastudy.decimal;

import java.util.List;

public record DecimalBulkResponse(List<Decimal> calculated, DecimalStatistics statistics) {

}
