package manh.com.project.SaleManagement.utis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertLocalDate {
    public LocalDate convertStringToLocalDate(String str) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.parse(str, LocalDate::from);
    }
}
