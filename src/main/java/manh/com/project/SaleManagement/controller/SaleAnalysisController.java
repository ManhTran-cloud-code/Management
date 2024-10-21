package manh.com.project.SaleManagement.controller;

import manh.com.project.SaleManagement.models.Sale;
import manh.com.project.SaleManagement.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
public class SaleAnalysisController {
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/chart")
    public List<Sale> getChart(){
        return orderRepository.getTotalMoneyByDay();
    }
}
