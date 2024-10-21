package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Discount;
import manh.com.project.SaleManagement.repositories.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService{
    @Autowired
    private DiscountRepository discountRepository;
    @Override
    public Discount saveDiscount(Discount discount) {
        return (discountRepository.saveDiscount(discount)==0)?null:discount;
    }
}
